package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.ClingineCommandsInt;
import com.glassboxdigital.command.RegexInt;
import com.glassboxdigital.utils.DateTimeUtil;
import org.apache.log4j.Logger;

import java.util.Properties;

import com.jcraft.jsch.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class SshClient implements RegexInt, ClingineCommandsInt {
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
            e.printStackTrace();
        }
    }

    protected StringBuffer runCommands(String[] commands2Exe) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            log4j.debug("Open Session: " + host);
            Session session = jsch.getSession(user, host);
            session.connect();
            for (String cmd : commands2Exe) {
                stringBuffer.append(execCommand(session, cmd));
            }
            session.disconnect();
            log4j.debug("Session: " + host + " disconnected");
        } catch (Exception e) {
            log4j.debug(e);
        }
        return stringBuffer;
    }

    private StringBuffer execCommand(Session session, String command) throws JSchException, IOException {
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
            } catch (Exception ee) {
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

    private void createIntegerCells(Row row, Matcher matcher) {
        int cellInd = 0;
        Cell cell = row.createCell(cellInd++);
        cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                cell = row.createCell(cellInd++);
                try {
                    cell.setCellValue(Integer.parseInt(matcher.group(i)));
                } catch (NumberFormatException e) {
                    cell.setCellValue(matcher.group(i));
                }
            }
        }
    }

    protected void publishTopRow(Sheet sheet, String[] commands2Exe) {
        parseRowByNewlineAndSpaceDelimiter(sheet, commands2Exe);
    }

    protected void publishPSRow(Sheet sheet, String[] commands2Exe) {
        StringBuffer cmdStr = runCommands(commands2Exe);
        Matcher matcher = createMatcher(cmdStr, REG_EX_PS);
        int rowNumber = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNumber);
        createDoubleCells(row, matcher);
    }

    private void createDoubleCells(Row row, Matcher matcher) {
        int cellInd = 0;
        Cell cell = row.createCell(cellInd++);
        cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                cell = row.createCell(cellInd++);
                try {
                    cell.setCellValue(Double.parseDouble(matcher.group(i)));
                } catch (NumberFormatException e) {
                    cell.setCellValue(matcher.group(i));
                }

            }
        }
    }

    public void publishOpenfileRow(Sheet sheet) {
        StringBuffer cmdStr = runCommands(new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_JOURNEY});
        Matcher matcher = createMatcher(cmdStr, REGEX_OPEN_FILE);
        int rowNumber = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNumber);
        createIntegerCells(row, matcher);
    }

    protected Matcher createMatcher(StringBuffer commands, String regEx) {
        Pattern pattern = Pattern.compile(regEx, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(commands);
        return matcher;
    }

    public void parseRowByNewlineAndCommaDelimiter(Sheet sheet, String[] commands) {
        parseRowByNewlineAndGenericDelimiter(sheet, commands, ",");
    }

    public void parseRowByNewlineAndTabDelimiter(Sheet sheet, String[] commands) {
        parseRowByNewlineAndGenericDelimiter(sheet, commands, "\\t");
    }
    public void parseRowByNewlineAndSpaceDelimiter(Sheet sheet, String[] commands) {
        parseRowByNewlineAndGenericDelimiter(sheet, commands, "\\s+");
    }
    public void parseRowByNewlineAndGenericDelimiter(Sheet sheet, String[] commands, String delimiter) {
        StringBuffer cmdStr = runCommands(commands);
        String[] cmdNewLineSplit = cmdStr.toString().split("\\r?\\n");
        int rowNumber = sheet.getLastRowNum() + 1;
        Cell cell;
        for (String str : cmdNewLineSplit) {
            String[] cmdDelimiterSplit = str.trim().split(delimiter);
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
                        cell.setCellValue(cellStr);
                    }
                }
            }
        }
    }
}