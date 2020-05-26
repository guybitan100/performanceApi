package com.glassboxdigital.ssh;


public class SshWorkersManager {
    private static final String SERVER_ROOT_MSG_CONSUMER_STAT = "grep -r \"MessageConsumerStats\" /opt/glassbox/clingine/log/servers.root.log";
    private static final String SESSION_PIPELINE_METRICS_CSV_FILE = "cat /opt/glassbox/clingine/log/session_pipeline_metrics.csv";
    private static final String CLICK_HOUSE_TOTAL_COUNT_PER_HOUR_SESSIONS = "clickhouse-client -q \"select count(*), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=20200526 group by day,hour order by day,hour desc\"";
    private static final String CLICK_HOUSE_SESSION_COUNT_PER_HOUR = "select count(*), uniq(session_uuid), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=20200525 group by day,hour order by day,hour desc";
    private static final String KAFKA_CONSUMER_GROUP = "export JAVA_HOME=/opt/glassbox/_jvm8_linux; /opt/glassbox/clifka/bin/kafka-consumer-groups.sh -bootstrap-server localhost:8093 -describe -group beacon_offline_group";
    public static void main(String[] args) {
        new SshClient("10.10.1.40", new String[]{SERVER_ROOT_MSG_CONSUMER_STAT, SESSION_PIPELINE_METRICS_CSV_FILE}).run();
        new SshClient("10.10.1.238", new String[]{CLICK_HOUSE_TOTAL_COUNT_PER_HOUR_SESSIONS,CLICK_HOUSE_SESSION_COUNT_PER_HOUR}).run();
        new SshClient("10.10.1.176",new String[]{KAFKA_CONSUMER_GROUP}).run();
    }
}