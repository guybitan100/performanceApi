package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.ClingineCommandsInt;
import com.glassboxdigital.utils.TextFileLogger;
import com.glassboxdigital.models.Commands;
import org.apache.poi.ss.usermodel.Sheet;

public class Clingine extends SshClient implements ClingineCommandsInt {


    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public boolean isNotRunning() throws Exception {
        return runCommands(new String[]{TOP}).isEmpty();
    }

    public void publishTopRow(Sheet sheet) throws Exception {
        publishTopRow(sheet, new String[]{TOP});
    }

    public void publishPipelineMetricsCsvRow(Sheet sheet) throws Exception {
        parseRowByNewlineAndCommaDelimiter(sheet, new String[]{SESSION_PIPELINE_METRICS_CSV_FILE});
    }

    public void publishOpenfileRow(Sheet sheet) throws Exception {
        parseRowByNewline(sheet, new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_JOURNEY, LSOF_EC, PERSISTENCY_SIZE});
    }

    public Commands printAllErrors(String fileName) throws Exception {

        Commands cmds = runCommands(new String[]{FIND_HEAP_DUMP, GET_CLINGINE_OUT_OF_MEMORY, GET_SERVERS_EXCEPTION, GET_CLINGINE_EXCEPTION});
        if (!cmds.toString().isEmpty()) {
            TextFileLogger textFile = new TextFileLogger(fileName);
            textFile.write(cmds.toString());
        }

        return cmds;
    }
}