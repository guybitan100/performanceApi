package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.TGCommands;
import org.apache.poi.ss.usermodel.Sheet;

public class TrafficGenerator extends SshClient implements TGCommands {
    public TrafficGenerator(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void createPsRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{PS_TG_STATUS});
    }

}
