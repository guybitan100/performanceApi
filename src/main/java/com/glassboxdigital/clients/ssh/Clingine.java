package com.glassboxdigital.clients.ssh;
import com.glassboxdigital.command.ClingineCommandsInt;
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

    public void printAllErrors() throws Exception {
        log4j.debug("### - Find Clingine Errors - ###");
        printErrors(new String[]{FIND_HEAP_DUMP, GET_CLINGINE_OUT_OF_MEMORY,GET_SERVERS_EXCEPTION, GET_SERVERS_OUT_OF_MEMORY,GET_CLINGINE_EXCEPTION});
    }
}