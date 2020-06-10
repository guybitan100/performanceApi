package com.glassboxdigital.clients.ssh;
import org.apache.poi.ss.usermodel.Sheet;

public class Clingine extends SshClient {
    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishPSRow(Sheet sheet) throws Exception {
        publishPSRow(sheet,new String[]{TOP_CLINGINE});
    }
    public void publishTopRow(Sheet sheet) throws Exception {
        publishTopRow(sheet,new String[]{TOP_CLINGINE});
    }
    public void publishPipelineMetricsRow(Sheet sheet) throws Exception {
        parseRowByNewlineAndCommaDelimiter(sheet,new String[]{SESSION_PIPELINE_METRICS_CSV_FILE});
    }
}