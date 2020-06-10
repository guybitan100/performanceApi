package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.TGCommandsInt;
import org.apache.poi.ss.usermodel.Sheet;

public class TrafficGenerator extends SshClient implements TGCommandsInt {
    public TrafficGenerator(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void createPsRow(Sheet sheet, int rowNumber) throws Exception {
        StringBuffer commands = runCommands(new String[]{PS_TG_STATUS});
    }

}
