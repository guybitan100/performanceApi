package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.ClingineCommandsInt;
import com.glassboxdigital.models.Command;
import com.glassboxdigital.models.Commands;
import com.glassboxdigital.utils.TextFileLogger;
import org.apache.poi.ss.usermodel.Sheet;

public class Clingine extends SshClient implements ClingineCommandsInt {


    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishTopRow(Sheet sheet) throws Exception {
        publishTopRow(sheet, new String[]{TOP_CLINGINE});
    }

    public void publishPipelineMetricsCsvRow(Sheet sheet) throws Exception {
        parseRowByNewlineAndCommaDelimiter(sheet, new String[]{SESSION_PIPELINE_METRICS_CSV_FILE});
    }

    public void publishOpenfileRow(Sheet sheet) throws Exception {
        parseRowByNewline(sheet, new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_JOURNEY, LSOF_EC, PERSISTENCY_SIZE});
    }

    public Commands printAllErrors(String fileName) throws Exception {

        Commands cmds = printErrors(new String[]{FIND_HEAP_DUMP, GET_CLINGINE_OUT_OF_MEMORY, GET_SERVERS_EXCEPTION, GET_CLINGINE_EXCEPTION});
        if (!cmds.toString().isEmpty()) {
            TextFileLogger textFile = new TextFileLogger(fileName);
            textFile.write(cmds.toString());
        }

        return cmds;
    }
}