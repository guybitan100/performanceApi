package com.glassboxdigital.command;

public interface ClingineCommandsInt {
    String CLI_STATUS = "ps aux | grep -v grep |grep \"cli\"";
    String PS_CLINGINE = "ps -C clingine -o %cpu,%mem,cmd";
    String TOP_CLINGINE = "top -bn 1 | grep clingine";
    String SERVER_ROOT_MSG_CONSUMER_STAT = "grep -r \"MessageConsumerStats\" /opt/glassbox/clingine/log/servers.root.log";
    String SESSION_PIPELINE_METRICS_CSV_FILE = "cat /opt/glassbox/clingine/log/session_pipeline_metrics.csv | grep \"beacon\\|pageload\"";
    String LSOF_FTS = "sudo  lsof | grep \"fts\" | wc -l";
    String LSOF_RECENT = "sudo  lsof | grep \"recent\" | wc -l";
    String LSOF_LOG = "sudo  lsof | grep \".log\" | wc -l";
    String LSOF_JAR = "sudo  lsof | grep \"jar\"  | wc -l";
    String LSOF_PIPE = "sudo  lsof | grep \"pipe\"  | wc -l";
    String LSOF_EC =  "sudo  lsof | grep \"/ec/\" | wc -l";
    String LSOF_POLL = "sudo  lsof | grep \"eventpoll\"  | wc -l";
    String LSOF_JOURNEY = "sudo  lsof | grep \"journey\"  | wc -l";
    String LSOF_ALL = "sudo  lsof |  wc -l";
}
