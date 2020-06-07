package com.glassboxdigital.clients.ssh;
import org.apache.poi.ss.usermodel.Sheet;

public class Clingine extends SshClient {
    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void runAndCreatePSRow(Sheet sheet, int rowNumber) {
        runAndCreatePSRow(sheet, rowNumber, new String[]{PS_CLINGINE});
    }
}