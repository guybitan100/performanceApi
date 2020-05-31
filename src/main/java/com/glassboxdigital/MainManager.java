package com.glassboxdigital;

import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.clients.SshClient;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import com.glassboxdigital.clients.RestClient;
import com.glassboxdigital.models.*;

public class MainManager {
    private static final String SERVER_ROOT_MSG_CONSUMER_STAT = "grep -r \"MessageConsumerStats\" /opt/glassbox/clingine/log/servers.root.log";
    private static final String SESSION_PIPELINE_METRICS_CSV_FILE = "cat /opt/glassbox/clingine/log/session_pipeline_metrics.csv | grep beacon | pageload";
    private static final String CLICK_HOUSE_CLIENT = "clickhouse-client -q";
    private static final String CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS = String.format("%s \"select count(*), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, ClientLogger.getCurrentTime());
    private static final String CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR = String.format("%s \"select count(*), uniq(session_uuid), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, ClientLogger.getCurrentTime());
    private static final String KAFKA_CONSUMER_GROUP = "export JAVA_HOME=/opt/glassbox/_jvm8_linux; /opt/glassbox/clifka/bin/kafka-consumer-groups.sh -bootstrap-server localhost:8093 -describe -group beacon_offline_group";
    private static final String LSOF_FTS = "sudo  lsof | grep \"fts\" | wc -l";
    private static final String LSOF_RECENT = "sudo  lsof | grep \"recent\" | wc -l";
    private static final String LSOF_LOG = "sudo  lsof | grep \".log\" | wc -l";
    private static final String LSOF_AJR = "sudo  lsof | grep \"ajr\"  | wc -l";
    private static final String LSOF_JAR = "sudo  lsof | grep \"jar\"  | wc -l";
    private static final String LSOF_PIPE = "sudo  lsof | grep \"pipe\"  | wc -l";
    private static final String LSOF_EVENT_POLL = "sudo  lsof | grep \"eventpoll\"  | wc -l";
    private static final String LSOF_EVENT_JOURNEY = "sudo  lsof | grep \"journey\"  | wc -l";

    public static void main(String[] args) {
        Configuration conf = new Configuration("ssh.properties");
        SshClient clingine = new SshClient(conf, "clingine", new String[]{SERVER_ROOT_MSG_CONSUMER_STAT});
        clingine.run();
        clingine.setCommands(new String[]{SESSION_PIPELINE_METRICS_CSV_FILE});
        clingine.run();
        clingine.setCommands(new String[]{LSOF_FTS,LSOF_RECENT,LSOF_LOG,LSOF_AJR,LSOF_JAR,LSOF_PIPE,LSOF_EVENT_POLL,LSOF_EVENT_JOURNEY });
        clingine.run();
        new SshClient(conf, "cloff", new String[]{CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS, CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR}).run();
        new SshClient(conf, "clifka", new String[]{KAFKA_CONSUMER_GROUP}).run();
        RestClient.disableSslVerification();
        RestClient apiRestClient = new RestClient(conf.get("base_url"));
        HttpHeaders headers = apiRestClient.postEntity(conf.get("login_endpoint")).getHeaders();
        String set_cookie = (headers.getFirst(headers.SET_COOKIE)).split(";")[0];
        apiRestClient.addHeader("Cookie", set_cookie);
        Gson gson = new Gson();
        Sessions sessions = gson.fromJson("{\"timeFrame\": {\"from\":0 ,\"till\": 0},\"limit\": 100000,\"uniqueCount\": {\"field\": \"SESSIONGUID\"},\"steps\": [{\"name\": \"\",\"operator\": \"AND\",\"query\": [{\"field\": \"APPID\",\"value\": [\"3\"],\"operator\": \"AND\"}]}],\"filters\": {\"query\": []}}", Sessions.class);
        sessions.setTimeFrame(new TimeFrame(System.currentTimeMillis() - (86400000) , System.currentTimeMillis()));
        String re = apiRestClient.post(conf.get("session_endpoint"), gson.toJson(sessions));
        Results res = gson.fromJson(re, Results.class);
    }
}