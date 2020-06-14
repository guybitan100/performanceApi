package com.glassboxdigital.clients.ssh;

import org.apache.poi.ss.usermodel.Sheet;

public class Clingine extends SshClient {
    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishTopRow(Sheet sheet) throws Exception {
        publishTopRow(sheet, new String[]{TOP_CLINGINE});
    }

    public void publishPipelineMetricsCsvRow(Sheet sheet) throws Exception {
        parseRowByNewlineAndCommaDelimiter(sheet, new String[]{SESSION_PIPELINE_METRICS_CSV_FILE});
    }

    public void publishOpenfileRow(Sheet sheet) throws Exception {
        parseRowByNewline(sheet, new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_JOURNEY, LSOF_EC, PERSISTENCY_SIZE});
    }

    public void printAllErrors() throws Exception {
        printErrors(new String[]{GET_EXCEPTION});
    }
}