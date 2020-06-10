package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.TGCommandsInt;
import org.apache.poi.ss.usermodel.Sheet;

public class TrafficGenerator extends SshClient implements TGCommandsInt {
    public TrafficGenerator(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishTopRow(Sheet sheet) throws Exception {
        publishTopRow(sheet, new String[]{TOP_TG});
    }
    public void publishTGSessionsRow(Sheet sheet) throws Exception {
        parseSessionsFromTgLog(sheet, new String[]{READ_3_LINES_FROM_TG_LOG});
    }
}
