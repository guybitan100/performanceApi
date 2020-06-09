package com.glassboxdigital.xls;

public interface XslHeaders {
    static String[] headerRowOpenFile = new String[]{"RUNNING-DATE", "ALL", "FTS", "RECENT", "JOURNEY"};
    static String[] headerRowTop = new String[]{"RUNNING-DATE", "CPU", "MEMORY"};
    static String[] headerRowBeaconOfflineGroup = new String[]{"RUNNING-DATE", "GROUP", "TOPIC", "PARTITION", "CURRENT-OFFSET", "LOG-END-OFFSET", "LAG", "CONSUMER-ID", "HOST", "CLIENT-ID"};
    static String[] headerRowClickhouseSessions = new String[]{"RUNNING-DATE", "COUNT", "UNIQ(SESSIONS_UUID)", "DAY", "HOUR"};
    static String[] headerRowClickhouseEvents = new String[]{"RUNNING-DATE", "COUNT","DAY", "HOUR"};
    static String[] headerRowClinginePipelineMetrics = new String[]{"RUNNING-DATE", "DATE","A", "B","C","D","E","F","G","H"};
}
