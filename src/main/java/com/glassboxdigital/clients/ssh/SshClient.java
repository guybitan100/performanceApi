package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.DateTimeUtil;
import org.apache.log4j.Logger;

import java.util.Properties;

import com.jcraft.jsch.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class SshClient {
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

    protected StringBuffer execCommand(Session session, String command) throws JSchException, IOException {
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

    public void createCells(Row row, Matcher matcher) {
        int cellInd = 0;
        Cell cell = row.createCell(cellInd++);
        cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                cell = row.createCell(cellInd++);
                cell.setCellValue(Integer.parseInt(matcher.group(i)));
            }
        }
    }

    public Matcher createMatcher(StringBuffer commands, String regEx) {
        Pattern pattern = Pattern.compile(regEx, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(commands);
        return matcher;
    }
}