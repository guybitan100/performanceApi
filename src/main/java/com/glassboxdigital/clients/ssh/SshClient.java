package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.ClingineCommandsInt;
import com.glassboxdigital.utils.DateTimeUtil;
import org.apache.log4j.Logger;

import java.util.Properties;

import com.jcraft.jsch.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;


public abstract class SshClient implements ClingineCommandsInt {
    final static Logger log4j = Logger.getLogger(SshClient.class);
    protected JSch jsch;
    protected String user;
    protected String host;

    public SshClient(String host, String user, String privateKeyLocation) {
        this.host = host;
        this.user = user;
        this.jsch = new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        jsch.setConfig(config);

        try {
            jsch.addIdentity(privateKeyLocation);
        } catch (JSchException e) {
            log4j.debug(e);
        }
    }

    protected StringBuffer runCommands(String[] commands2Exe) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            log4j.debug("Open Session: " + host);
            Session session = jsch.getSession(user, host);
            session.connect();
            for (String cmd : commands2Exe) {
                log4j.debug(cmd);
                stringBuffer.append(execCommand(session, cmd));
            }
            session.disconnect();
            log4j.debug("Session: " + host + " disconnected");
        } catch (Exception e) {
            log4j.debug(e);
            throw e;
        }
        return stringBuffer;
    }

    private StringBuffer execCommand(Session session, String command) throws JSchException, IOException, InterruptedException {
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        channel.connect();
        byte[] tmp = new byte[1024];
        StringBuffer strBuffer = new StringBuffer();
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                strBuffer.append(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) continue;
                log4j.debug("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log4j.debug(e);
                throw e;
            }
        }
        channel.disconnect();
        log4j.debug(strBuffer);
        return strBuffer;
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

    protected void publishTopRow(Sheet sheet, String[] commands2Exe) throws Exception {
        parseRowByNewlineAndSpaceDelimiter(sheet, commands2Exe);
    }

    public void parseRowByNewlineAndCommaDelimiter(Sheet sheet, String[] commands) throws Exception {
        parseRowByNewlineAndGenericDelimiter(sheet, commands, ",");
    }

    public void parseRowByNewlineAndTabDelimiter(Sheet sheet, String[] commands) throws Exception {
        parseRowByNewlineAndGenericDelimiter(sheet, commands, "\\t");
    }

    public void parseRowByNewlineAndSpaceDelimiter(Sheet sheet, String[] commands) throws Exception {
        parseRowByNewlineAndGenericDelimiter(sheet, commands, "\\s+");
    }

    public void parseSessionsFromTgLog(Sheet sheet, String[] commands, String onError) throws Exception {
        StringBuffer cmdStr = runCommands(commands);
        String publishedStr = "Published";
        String sessionswStr = " sessions w";
        int indSessionStart = cmdStr.toString().indexOf(publishedStr);
        if (indSessionStart >= 0) {
            int indSessionEnd = cmdStr.toString().indexOf(sessionswStr);
            String strSession = cmdStr.substring(indSessionStart + publishedStr.length(), indSessionEnd);
            if (!sessionswStr.isEmpty()) {
                int rowNumber = sheet.getPhysicalNumberOfRows();
                Row row = sheet.createRow(rowNumber);
                Cell cell = row.createCell(0);
                cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
                cell = row.createCell(1);
                cell.setCellValue(strSession);
            }
        } else {
            log4j.debug(onError);
            log4j.debug(runCommands(new String[]{onError}));
        }
    }

    public void parseRowByNewline(Sheet sheet, String[] commands) throws Exception {
        StringBuffer cmdStr = runCommands(commands);
        String[] cmdNewLineSplit = cmdStr.toString().split("\\r?\\n");
        int rowNumber = sheet.getPhysicalNumberOfRows();
        Row row = sheet.createRow(rowNumber);
        int cellInd = 0;
        Cell cell = row.createCell(cellInd++);
        cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
        for (String cellStr : cmdNewLineSplit) {
            cell = row.createCell(cellInd++);
            cellStr = cellStr.trim().replaceAll("([a-z])", "");
            try {
                cell.setCellValue(Integer.parseInt(cellStr));
            } catch (NumberFormatException ne) {
                cell.setCellValue(cellStr);
            }
        }
    }

    public void parseRowByNewlineAndGenericDelimiter(Sheet sheet, String[] commands, String delimiter) throws Exception {
        StringBuffer cmdStr = runCommands(commands);
        String[] cmdNewLineSplit = cmdStr.toString().split("\\r?\\n");
        int rowNumber = sheet.getPhysicalNumberOfRows();
        Cell cell;
        for (String strLine : cmdNewLineSplit) {
            String[] cmdDelimiterSplit = strLine.trim().split(delimiter);
            Row row = sheet.createRow(rowNumber++);
            int cellInd = 0;
            cell = row.createCell(cellInd++);
            cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
            for (String cellStr : cmdDelimiterSplit) {
                cell = row.createCell(cellInd++);
                cellStr = cellStr.trim();
                try {
                    cell.setCellValue(Integer.parseInt(cellStr));
                } catch (NumberFormatException ne) {
                    try {
                        cell.setCellValue(Double.parseDouble(cellStr));
                    } catch (NumberFormatException e) {
                        try {
                            cell.setCellValue(cellStr);
                        } catch (Exception exp) {
                            log4j.debug(exp);
                        }
                    }
                }
            }
        }
    }
}