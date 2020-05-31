package com.glassboxdigital;

import com.glassboxdigital.clients.Client;
import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.clients.SshClient;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import com.glassboxdigital.clients.RestClient;
import com.glassboxdigital.models.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<Client> queue;

    private static final String SERVER_ROOT_MSG_CONSUMER_STAT = "grep -r \"MessageConsumerStats\" /opt/glassbox/clingine/log/servers.root.log";
    private static final String SESSION_PIPELINE_METRICS_CSV_FILE = "cat /opt/glassbox/clingine/log/session_pipeline_metrics.csv";
    private static final String CLICK_HOUSE_CLIENT = "clickhouse-client -q";
    private static final String CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS = String.format("%s \"select count(*), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, ClientLogger.getCurrentTime());
    private static final String CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR = String.format("%s \"select count(*), uniq(session_uuid), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, ClientLogger.getCurrentTime());
    private static final String KAFKA_CONSUMER_GROUP = "export JAVA_HOME=/opt/glassbox/_jvm8_linux; /opt/glassbox/clifka/bin/kafka-consumer-groups.sh -bootstrap-server localhost:8093 -describe -group beacon_offline_group";
    private static final String LSOF_FTS = "sudo  lsof | grep \"fts\" | wc -l";
    private static final String LSOF_RECENT = "sudo  lsof | grep \"recent\" | wc -l";
    private static final String LSOF_LOG = "sudo  lsof | grep \".log\" | wc -l";
    private static final String LSOF_JAR = "sudo  lsof | grep \"jar\"  | wc -l";
    private static final String LSOF_PIPE = "sudo  lsof | grep \"pipe\"  | wc -l";
    private static final String LSOF_EVENT_POLL = "sudo  lsof | grep \"eventpoll\"  | wc -l";
    private static final String LSOF_EVENT_JOURNEY = "sudo  lsof | grep \"journey\"  | wc -l";
    private static final String LSOF_ALL = "sudo  lsof |  wc -l";
    private static final String TG_STATUS = "ps aux | grep -v grep |grep \"raffi\"";
    private static final String CLINGINE_CLI_STATUS = "ps aux | grep -v grep |grep \"cli\"";
    private static final String CLINGINE_MEM_STATUS = "cat /proc/meminfo";
    private static final String CLINGINE_CPU_STATUS = "mpstat";

    public Producer(BlockingQueue<Client> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Configuration conf = new Configuration("ssh.properties");
        String clingine = conf.get("clingine");
        String user = conf.get("user");
        String privateKeyLocation = conf.get("privateKeyLocation");

        try {
            queue.put(new SshClient(clingine, user, privateKeyLocation, new String[]{CLINGINE_CPU_STATUS, CLINGINE_MEM_STATUS, CLINGINE_CLI_STATUS, SERVER_ROOT_MSG_CONSUMER_STAT}));
            queue.put(new SshClient(clingine, user, privateKeyLocation, new String[]{SESSION_PIPELINE_METRICS_CSV_FILE}));
            queue.put(new SshClient(clingine, user, privateKeyLocation, new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_LOG, LSOF_JAR, LSOF_PIPE, LSOF_EVENT_POLL, LSOF_EVENT_JOURNEY}));
            queue.put(new SshClient(conf.get("cloff"), user, privateKeyLocation, new String[]{CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS, CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR}));
            queue.put(new SshClient(conf.get("clifka"), user, privateKeyLocation, new String[]{KAFKA_CONSUMER_GROUP}));
            queue.put(new SshClient(conf.get("tg1"), user, privateKeyLocation, new String[]{TG_STATUS}));
            queue.put(new SshClient(conf.get("tg2"), user, privateKeyLocation, new String[]{TG_STATUS}));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RestClient.disableSslVerification();
        RestClient apiRestClient = new RestClient(conf.get("base_url"));
        HttpHeaders headers = apiRestClient.postEntity(conf.get("login_endpoint")).getHeaders();
        String set_cookie = (headers.getFirst(headers.SET_COOKIE)).split(";")[0];
        apiRestClient.addHeader("Cookie", set_cookie);
        Gson gson = new Gson();
        Sessions sessions = gson.fromJson("{\"timeFrame\": {\"from\":0 ,\"till\": 0},\"limit\": 100000,\"uniqueCount\": {\"field\": \"SESSIONGUID\"},\"steps\": [{\"name\": \"\",\"operator\": \"AND\",\"query\": [{\"field\": \"APPID\",\"value\": [\"3\"],\"operator\": \"AND\"}]}],\"filters\": {\"query\": []}}", Sessions.class);
        sessions.setTimeFrame(new TimeFrame(System.currentTimeMillis() - (86400000), System.currentTimeMillis()));
        String re = apiRestClient.post(conf.get("session_endpoint"), gson.toJson(sessions));
        Results res = gson.fromJson(re, Results.class);
    }

}