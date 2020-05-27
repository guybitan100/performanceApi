package com.glassboxdigital.ssh;

import com.glassboxdigital.http.utils.Configuration;
import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class SshClient {
    final static Logger log4j = Logger.getLogger(SshClient.class);


    private JSch jsch;
    private String usr;
    private String host;
    private String[] commands;
    private SshLogger sshLogger;

    public SshClient(String host, String[] commands) {
        this.sshLogger = new SshLogger(host + "-");
        this.jsch = new JSch();
        Configuration sshConf = new Configuration("ssh.properties");
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        jsch.setConfig(config);
        this.usr = sshConf.get("user");
        this.host = host;
        this.commands = commands;

        try {
            jsch.addIdentity(sshConf.get("privateKeyLocation"));
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            log4j.debug("Open Session: " + host);
            Session session = jsch.getSession(usr, host, 22);
            session.connect();
            for (String cmd : commands) {
                stringBuffer.append("\n\n" + cmd + "\n\n");
                stringBuffer.append(runCommand(session, cmd));
            }
            sshLogger.write(stringBuffer);
            session.disconnect();
            log4j.debug("Session: " + host + " disconnected");
        } catch (Exception e) {
            log4j.debug(e);
        }
    }

    private synchronized StringBuffer runCommand(Session session, String command) throws JSchException, IOException {
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