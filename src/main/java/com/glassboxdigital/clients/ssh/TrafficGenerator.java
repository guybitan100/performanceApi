package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.TGCommandsInt;
import com.glassboxdigital.models.Commands;
import com.glassboxdigital.utils.TextFileLogger;
import org.apache.poi.ss.usermodel.Sheet;

public class TrafficGenerator extends SshClient implements TGCommandsInt {
    public TrafficGenerator(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishTopRow(Sheet sheet) throws Exception {
        publishTopRow(sheet, new String[]{TOP_TG});
    }

    public void publishTGSession1sRow(Sheet sheet) throws Exception {
        parseSessionsFromTgLog(sheet, new String[]{READ_3_LINES_FROM_TG_LOG1}, READ_15_LINES_FROM_TG_LOG1);
    }

    public void publishTGSession2sRow(Sheet sheet) throws Exception {
        parseSessionsFromTgLog(sheet, new String[]{READ_3_LINES_FROM_TG_LOG2}, READ_15_LINES_FROM_TG_LOG2);
    }

    public Commands printAllErrors(String fileName) throws Exception {
        Commands cmds =  printErrors(new String[]{GET_EXCEPTION, OUT_OF_MEMORY_ERROR});
        if (!cmds.toString().isEmpty()) {
            TextFileLogger textFile = new TextFileLogger(fileName);
            textFile.write(cmds.toString());
        }
        return  cmds;
    }
}
