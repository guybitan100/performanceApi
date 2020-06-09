package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.ClickhouseCommandsInt;
import org.apache.poi.ss.usermodel.Sheet;

public class Cloff extends SshClient implements ClickhouseCommandsInt {
    public Cloff(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishPSRow(Sheet sheet) {
        publishPSRow(sheet, new String[]{PS_CLOFF});
    }
    public void publishTopRow(Sheet sheet) {
        publishTopRow(sheet, new String[]{TOP_CLOFF});
    }
    public void publishSessionsCount(Sheet sheet) {
        parseRowByNewlineAndTabDelimiter(sheet, new String[]{CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR});
    }

    public void publishEventsCount(Sheet sheet) {
        parseRowByNewlineAndTabDelimiter(sheet, new String[]{CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS});

    }


}