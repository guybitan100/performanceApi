package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.TGCommandsInt;
import com.glassboxdigital.utils.TextFileLogger;
import com.glassboxdigital.models.Commands;
import org.apache.poi.ss.usermodel.Sheet;

public class TG extends SshClient implements TGCommandsInt {
    public TG(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishTopRow(Sheet sheet) throws Exception {
        publishTopRow(sheet, new String[]{TOP});
    }

    public void publishTGSession1sRow(Sheet sheet) throws Exception {
        parseSessionsFromTgLog(sheet, new String[]{READ_3_LINES_FROM_TG_LOG1}, READ_15_LINES_FROM_TG_LOG1);
    }

    public void publishTGSession2sRow(Sheet sheet) throws Exception {
        parseSessionsFromTgLog(sheet, new String[]{READ_3_LINES_FROM_TG_LOG2}, READ_15_LINES_FROM_TG_LOG2);
    }

    public Commands printAllErrors(String fileName) throws Exception {
        Commands cmds = runCommands(new String[]{GET_EXCEPTION, OUT_OF_MEMORY_ERROR});
        if (!cmds.toString().isEmpty()) {
            TextFileLogger textFile = new TextFileLogger(fileName);
            textFile.write(cmds.toString());
        }
        return cmds;
    }
    public boolean isNotRunning() throws Exception {
        return runCommands(new String[]{TOP}).isEmpty();
    }
}
