package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.DateTimeUtil;
import com.glassboxdigital.SshCommands;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.regex.Matcher;

public class Clingine extends SshClient implements SshCommands {
    private final String regExOpenFile = "^[^\\s+^A-Za-z]+\\W$";
    private final String regExPS = "^(\\d{1,9}\\.\\d{1,9})\\s+(\\d{1,9}\\.\\d{1,9})";
    private Matcher matcher;

    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void createHeaderRow(Sheet sheet, String... arg) {
        int cellInd = 0;
        Cell cell;
        Row row = sheet.createRow(0);
        for (String str : arg) {
            cell = row.createCell(cellInd++);
            cell.setCellValue(str);
        }
    }

    public void createPSRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{PS_Clingine_STATUS});
        matcher = createMatcher(commands, regExPS);
        int cellInd = 0;
        Cell cell;
        Row row = sheet.createRow(rowNumber);
        cell = row.createCell(cellInd++);
        cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                cell = row.createCell(cellInd++);
                cell.setCellValue(matcher.group(i));
            }
        }
    }

    public void createOpenFilesRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_JOURNEY});
        matcher = createMatcher(commands, regExOpenFile);
        int cellInd = 0;
        Cell cell;
        Row row = sheet.createRow(rowNumber);
        cell = row.createCell(cellInd++);
        cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                cell = row.createCell(cellInd++);
                cell.setCellValue(matcher.group(i));
            }
        }
    }
}