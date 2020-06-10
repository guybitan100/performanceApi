package com.glassboxdigital.command;

public interface XslHeaders {
    static String[] headerRowOpenFile = new String[]{"RUN-TIME", "ALL", "FTS", "RECENT", "JOURNEY"};
    static String[] headerRowPs = new String[]{"RUN-TIME", "CPU", "MEMORY"};
    static String[] headerRowTop = new String[]{"RUN-TIME","PID", "USER", "PR", "NI", "VIRT", "RES", "SHR", "S", "%CPU", "%MEM", "TIME+", "COMMAND"};
    static String[] headerRowBeaconOfflineGroup = new String[]{"RUN-TIME", "GROUP", "TOPIC", "PARTITION", "CURRENT-OFFSET", "LOG-END-OFFSET", "LAG", "CONSUMER-ID", "HOST", "CLIENT-ID"};
    static String[] headerRowClickhouseSessions = new String[]{"RUN-TIME", "COUNT", "UNIQ(SESSIONS_UUID)", "DAY", "HOUR"};
    static String[] headerRowClickhouseEvents = new String[]{"RUN-TIME", "COUNT", "DAY", "HOUR"};
    static String[] headerRowClinginePipelineMetrics = new String[]{"RUN-TIME", "DATE", "KEY", "COUNT", "EXCEPTIONCOUNT", "TOTALTIMENANOS", "SQUARESOFTIME", "AVERAGEMS", "STD", "MAXMS", "TIMELENGTH"};
}
