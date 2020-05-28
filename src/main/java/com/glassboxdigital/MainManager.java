package com.glassboxdigital;


import com.glassboxdigital.http.ApiRestClient;
import com.glassboxdigital.http.utils.Configuration;
import com.glassboxdigital.ssh.SshClient;
import com.glassboxdigital.ssh.SshLogger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class MainManager {
    private static final String SERVER_ROOT_MSG_CONSUMER_STAT = "grep -r \"MessageConsumerStats\" /opt/glassbox/clingine/log/servers.root.log";
    private static final String SESSION_PIPELINE_METRICS_CSV_FILE = "cat /opt/glassbox/clingine/log/session_pipeline_metrics.csv";
    private static final String CLICK_HOUSE_CLIENT = "clickhouse-client -q";
    private static final String CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS = String.format("%s \"select count(*), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, SshLogger.getCurrentTime());
    private static final String CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR = String.format("%s \"select count(*), uniq(session_uuid), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, SshLogger.getCurrentTime());
    private static final String KAFKA_CONSUMER_GROUP = "export JAVA_HOME=/opt/glassbox/_jvm8_linux; /opt/glassbox/clifka/bin/kafka-consumer-groups.sh -bootstrap-server localhost:8093 -describe -group beacon_offline_group";
    private static String API_LOGIN = "j_spring_security_check?j_username=clarisite&j_password=ysyghb&redirect_response=true&submit=Login&application=x-www-form-urlencoded";

    public static void main(String[] args) {
        Configuration sshConf = new Configuration("ssh.properties");
        SshClient clingine = new SshClient(sshConf,"clingine",new String[]{SERVER_ROOT_MSG_CONSUMER_STAT});
        clingine.run();
        clingine.setCommands(new String[]{SESSION_PIPELINE_METRICS_CSV_FILE});
        clingine.run();
        new SshClient(sshConf,"cloff",new String[]{CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS, CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR}).run();
        new SshClient(sshConf,"clifka", new String[]{KAFKA_CONSUMER_GROUP}).run();

        ApiRestClient.disableSslVerification();
        ApiRestClient apiRestClient = new ApiRestClient("https://10.10.1.40:8443/webinterface/");
        ApiRestClient apiRestClient2 = new ApiRestClient("https://10.10.1.40:8443/webinterface/");
        HttpHeaders headers = apiRestClient.postEntity(API_LOGIN, "").getHeaders();
        String set_cookie = headers.getFirst(headers.SET_COOKIE);
        HttpHeaders headersReq = new HttpHeaders();
        headersReq.add("Cookie", set_cookie);
        headersReq.add("Content-Type", "application/json");
        headersReq.add("Accept", "*/*");
        headersReq.add("User-Agent", "PostmanJava");
        apiRestClient2.setHeaders(headersReq);
        String re = apiRestClient2.post("api/v2/sessions", "{\"timeFrame\": {\"from\": 1590475425153,\"till\": 1590479025153},\"limit\": 100000,\"uniqueCount\": {\"field\": \"SESSIONGUID\"},\"steps\": [{\"name\": \"\",\"operator\": \"AND\",\"query\": [{\"field\": \"APPID\",\"value\": [\"3\"],\"operator\": \"AND\"}]}],\"filters\": {\"query\": []}}");
        System.out.println(re);
    }



}