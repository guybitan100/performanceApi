package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.DateTimeUtil;
import com.glassboxdigital.SshCommands;
import com.jcraft.jsch.Session;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clingine extends SshClient implements SshCommands {
    private String[] status = new String[]{SESSION_PIPELINE_METRICS_CSV_FILE};
    private final String openFileCommandsRegEx = "^[^\\s+^A-Za-z]+\\W$";
    private final String openPipelinemetricsCommandRegEx = "^[^\\s+^A-Za-z]+\\W$";

    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void createTopRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{PS_EF_STATUS});
    }

    public void createOpenFilesRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_JOURNEY});
        Pattern pattern = Pattern.compile(openFileCommandsRegEx, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(commands);
        int cellInd = 0;
        Cell cell = null;
        Row row = sheet.createRow(rowNumber);
        if (rowNumber == 0) {
            cell = row.createCell(cellInd);
            cell.setCellValue("Time");
            cell = row.createCell(cellInd++);
            cell.setCellValue("All");
            cell = row.createCell(cellInd++);
            cell.setCellValue("Fts");
            cell = row.createCell(cellInd++);
            cell.setCellValue("Recent");
            cell = row.createCell(cellInd++);
            cell.setCellValue("Journey");
        } else {
            cell = row.createCell(cellInd++);
            cell.setCellValue(DateTimeUtil.getCurrentTime());
            while (matcher.find()) {
                cell = row.createCell(cellInd++);
                cell.setCellValue(matcher.group());
                cellInd++;
            }
        }
    }

    public boolean isUp() {
        String res = "";
        try {
            log4j.debug("Open Session: " + host);
            Session session = jsch.getSession(user, host);
            session.connect();
            res = execCommand(session, "ps aux |  grep -v grep |grep \"clingine\"").toString();
            log4j.debug(res);
            session.disconnect();
            log4j.debug("Session: " + host + " disconnected");

        } catch (Exception e) {
            log4j.debug(e);
        }
        return !res.isEmpty();
    }
}