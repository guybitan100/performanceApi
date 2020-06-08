package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.ClickhouseCommands;
import com.glassboxdigital.command.ClingineCommands;
import com.glassboxdigital.command.KafkaCommands;
import org.apache.poi.ss.usermodel.Sheet;

public class Cloff extends SshClient implements ClickhouseCommands {
    public Cloff(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void runAndCreatePSRow(Sheet sheet, int rowNumber) {
        runAndCreatePSRow(sheet, rowNumber, new String[]{PS_CLOFF});
    }
}