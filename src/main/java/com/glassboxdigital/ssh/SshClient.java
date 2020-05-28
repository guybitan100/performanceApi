package com.glassboxdigital.ssh;

import com.glassboxdigital.http.conf.Configuration;
import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class SshClient {
    final static Logger log4j = Logger.getLogger(SshClient.class);
    private JSch jsch;
    private String user;
    private String host;
    private SshLogger sshLogger;
    private String[] commands;

    public SshClient(Configuration sshConf, String serverName, String[] commands) {
        this(sshConf, serverName);
        this.commands = commands;
    }

    public SshClient(Configuration sshConf, String serverName) {
        this.host = sshConf.get(serverName);
        this.user = sshConf.get("user");
        this.sshLogger = new SshLogger(host);
        this.jsch = new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        jsch.setConfig(config);

        try {
            jsch.addIdentity(sshConf.get("privateKeyLocation"));
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        StringBuffer stringBuffer = new StringBuffer();
        String ext = "log";
        try {
            log4j.debug("Open Session: " + host);
            Session session = jsch.getSession(user, host);
            session.connect();
            for (String cmd : commands) {
                stringBuffer.append("\n\n" + cmd + "\n\n");
                stringBuffer.append(runCommand(session, cmd));
            }
            if (stringBuffer.toString().contains(".csv")) {
                ext = "csv";
            }
            sshLogger.write(stringBuffer, ext);
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

    public void setCommands(String[] commands) {
        this.commands = commands;
    }
}