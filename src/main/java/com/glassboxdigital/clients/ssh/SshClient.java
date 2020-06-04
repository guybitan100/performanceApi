package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.DateTimeUtil;
import org.apache.log4j.Logger;

import java.util.Properties;

import com.jcraft.jsch.*;

import java.io.*;


public abstract class SshClient {
    final static Logger log4j = Logger.getLogger(SshClient.class);

    protected JSch jsch;
    protected String user;
    protected String host;
    public String[] commands2Exe;

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

    protected StringBuffer runCommands() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            log4j.debug("Open Session: " + host);
            Session session = jsch.getSession(user, host);
            session.connect();
            for (String cmd : commands2Exe) {
                stringBuffer.append("\n\n" + DateTimeUtil.getCurrentTimeStamp() + " " + cmd + "\n\n");
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
}