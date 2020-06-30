package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.ClickhouseCommandsInt;
import com.glassboxdigital.models.Command;
import com.glassboxdigital.models.Commands;
import com.glassboxdigital.utils.DateTimeUtil;
import org.apache.poi.ss.usermodel.Sheet;

public class ClickHouse extends SshClient implements ClickhouseCommandsInt {
    public ClickHouse(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishTopRow(Sheet sheet) throws Exception {
        publishTopRow(sheet, new String[]{TOP});
    }

    public void publishSessionsCount(Sheet sheet) throws Exception {
        parseRowByNewlineAndTabDelimiter(sheet, new String[]{CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR});
    }

    public void publishEventsCount(Sheet sheet) throws Exception {
        parseRowByNewlineAndTabDelimiter(sheet, new String[]{CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS});

    }

    public boolean isNotRunning() throws Exception {
        return runCommands(new String[]{TOP}).isEmpty();
    }

    public boolean isThereTrafficToday() throws Exception {
        return runCommands(new String[]{CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS}).isEmpty();
    }

    public boolean isThereIsNotTrafficInLastHour() throws Exception {
        Commands commands = runCommands(new String[]{CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS});
        boolean status = true;
        for (Command cmd : commands.getCommands()) {
            status = (cmd.getResultsLines().length + 3 != DateTimeUtil.getHour());
        }
        return status;
    }
}