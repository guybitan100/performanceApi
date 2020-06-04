package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.DateTimeUtil;
import com.glassboxdigital.SshCommands;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clingine extends SshClient implements SshCommands {
    private final String openFileCommandsRegEx = "^[^\\s+^A-Za-z]+\\W$";
    private final String psCommandsRegEx = "^\\s(\\d{1,9}.\\d{1,9})\\s(\\d{1,9}.\\d{1,9})";

    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void createPSRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{String.format(PS_CLI_STATUS, "clingine")});
        Pattern pattern = Pattern.compile(psCommandsRegEx, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(commands);
        int cellInd = 0;
        Cell cell = null;
        Row row = sheet.createRow(rowNumber);
        if (rowNumber == 0) {
            cell = row.createCell(cellInd);
            cell.setCellValue("Time");
            cell = row.createCell(cellInd++);
            cell.setCellValue("Cpu");
            cell = row.createCell(cellInd++);
            cell.setCellValue("Memory");
            cell = row.createCell(cellInd++);
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
}