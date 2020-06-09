package com.glassboxdigital.xls;

public interface XslHeaders {
    static String[] headerRowOpenFile = new String[]{"TIME", "ALL", "FTS", "RECENT", "JOURNEY"};
    static String[] headerRowTop = new String[]{"TIME", "CPU", "MEMORY"};
    static String[] headerRowBeaconOfflineGroup = new String[]{"DATE", "GROUP", "TOPIC", "PARTITION", "CURRENT-OFFSET", "LOG-END-OFFSET", "LAG", "CONSUMER-ID", "HOST", "CLIENT-ID"};
    static String[] headerRowClickhouseSessions = new String[]{"DATE", "COUNT", "SESSIONS", "DAY", "HOUR"};
    static String[] headerRowClickhouseEvents = new String[]{"DATE", "COUNT", "EVENTS", "DAY", "HOUR"};
}
