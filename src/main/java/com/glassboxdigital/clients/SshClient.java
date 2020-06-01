package com.glassboxdigital.clients;
import com.glassboxdigital.ClientLogger;
import org.apache.log4j.Logger;
import java.util.Properties;
import com.jcraft.jsch.*;
import java.io.*;


public  class SshClient {
    final static Logger log4j = Logger.getLogger(SshClient.class);

    private JSch jsch;
    private String user;
    private String host;
    private ClientLogger clientLogger;
    public String[] commands;

    public SshClient(String host, String user, String privateKeyLocation) {
        this(host, user, privateKeyLocation, null);
    }

    public SshClient(String host, String user, String privateKeyLocation, String[] commands) {
        this.host = host;
        this.user = user;
        this.commands = commands;
        this.clientLogger = new ClientLogger(host);
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

    public synchronized void run() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            log4j.debug("Open Session: " + host);
            Session session = jsch.getSession(user, host);
            session.connect();
            for (String cmd : commands) {
                stringBuffer.append("\n\n" + clientLogger.getCurrentTimeStamp() + " " + cmd + "\n\n");
                stringBuffer.append(execCommand(session, cmd));
            }
            clientLogger.write(stringBuffer);
            session.disconnect();
            log4j.debug("Session: " + host + " disconnected");
        } catch (Exception e) {
            log4j.debug(e);
        }
    }

    protected synchronized StringBuffer execCommand(Session session, String command) throws JSchException, IOException {
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