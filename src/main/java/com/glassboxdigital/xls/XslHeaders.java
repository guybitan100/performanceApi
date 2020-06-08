package com.glassboxdigital.xls;

public interface XslHeaders {
    static String[] headerRowOpenFile = new String[]{"Time", "All", "Fts", "Recent", "Journey"};
    static String[] headerRowTop = new String[]{"Time", "Cpu", "Memory"};
    static String[] headerRowBeaconOfflineGroup = new String[]{"DATE", "GROUP", "TOPIC", "PARTITION", "CURRENT-OFFSET", "LOG-END-OFFSET", "LAG", "CONSUMER-ID", "HOST", "CLIENT-ID"};
}
