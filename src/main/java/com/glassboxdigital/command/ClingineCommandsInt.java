package com.glassboxdigital.command;

public interface ClingineCommandsInt {
    String TOP = "top -bn 1 | grep clingine";
    String DISK_SPACE = "df -h";
    String SESSION_PIPELINE_METRICS_CSV_FILE = "cat /opt/glassbox/clingine/log/session_pipeline_metrics.csv | grep \"beacon\\|pageload\"";
    String LSOF_FTS = "sudo  lsof | grep \"fts\" | wc -l";
    String LSOF_RECENT = "sudo  lsof | grep \"recent\" | wc -l";
    String LSOF_LOG = "sudo  lsof | grep \".log\" | wc -l";
    String LSOF_JAR = "sudo  lsof | grep \"jar\"  | wc -l";
    String LSOF_PIPE = "sudo  lsof | grep \"pipe\"  | wc -l";
    String LSOF_EC = "sudo  lsof | grep \"/ec/\" | wc -l";
    String PERSISTENCY_SIZE = "sudo du -sh /opt/glassbox/persistency";
    String LSOF_POLL = "sudo  lsof | grep \"eventpoll\"  | wc -l";
    String LSOF_JOURNEY = "sudo  lsof | grep \"journey\"  | wc -l";
    String LSOF_ALL = "sudo  lsof |  wc -l";
    String GET_CLINGINE_OUT_OF_MEMORY = "grep -C 10 \"java.lang.OutOfMemoryError\" /opt/glassbox/clingine/log/clinigne.all.log";
    String GET_SERVERS_DISK_SPACE_EXCEPTION = "grep -C 10 \"Usable disk space is\" /opt/glassbox/clingine/log/servers.root.log";
    String GET_COMMON_QUEUE_TOO_LARGE = "grep -C 10 \"because queue is too large\" /opt/glassbox/clingine/log/common.log";
    String FIND_HEAP_DUMP = "find /opt/glassbox/clingine/bin -name *.hprof";
}
