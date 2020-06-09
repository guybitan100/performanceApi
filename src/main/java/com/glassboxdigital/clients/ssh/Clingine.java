package com.glassboxdigital.clients.ssh;
import org.apache.poi.ss.usermodel.Sheet;

public class Clingine extends SshClient {
    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishPSRow(Sheet sheet) {
        publishPSRow(sheet,new String[]{TOP_CLINGINE});
    }
    public void publishTopRow(Sheet sheet) {
        publishTopRow(sheet,new String[]{TOP_CLINGINE});
    }
    public void publishPipelineMetricsRow(Sheet sheet) {
        parseRowByNewlineAndCommaDelimiter(sheet,new String[]{SESSION_PIPELINE_METRICS_CSV_FILE});
    }
}