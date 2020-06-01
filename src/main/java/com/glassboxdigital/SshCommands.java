package com.glassboxdigital;

public class SshCommands {
    public final String SERVER_ROOT_MSG_CONSUMER_STAT = "grep -r \"MessageConsumerStats\" /opt/glassbox/clingine/log/servers.root.log";
    public final String SESSION_PIPELINE_METRICS_CSV_FILE = "cat /opt/glassbox/clingine/log/session_pipeline_metrics.csv";
    public final String CLICK_HOUSE_CLIENT = "clickhouse-client -q";
    public final String CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS = String.format("%s \"select count(*), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, ClientLogger.getCurrentTime());
    public final String CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR = String.format("%s \"select count(*), uniq(session_uuid), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, ClientLogger.getCurrentTime());
    public final String KAFKA_CONSUMER_GROUP = "export JAVA_HOME=/opt/glassbox/_jvm8_linux; /opt/glassbox/clifka/bin/kafka-consumer-groups.sh -bootstrap-server localhost:8093 -describe -group beacon_offline_group";
    public final String LSOF_FTS = "sudo  lsof | grep \"fts\" | wc -l";
    public final String LSOF_RECENT = "sudo  lsof | grep \"recent\" | wc -l";
    public final String LSOF_LOG = "sudo  lsof | grep \".log\" | wc -l";
    public final String LSOF_JAR = "sudo  lsof | grep \"jar\"  | wc -l";
    public final String LSOF_PIPE = "sudo  lsof | grep \"pipe\"  | wc -l";
    public final String LSOF_EVENT_POLL = "sudo  lsof | grep \"eventpoll\"  | wc -l";
    public final String LSOF_EVENT_JOURNEY = "sudo  lsof | grep \"journey\"  | wc -l";
    public final String LSOF_ALL = "sudo  lsof |  wc -l";
    public final String TG_STATUS = "ps aux | grep -v grep |grep \"raffi\"";
    public final String CLI_STATUS = "ps aux | grep -v grep |grep \"cli\"";
    public final String MEM_STATUS = "cat /proc/meminfo";
    public  final String CPU_STATUS = "mpstat";

}
