package com.glassboxdigital.ssh;

import com.glassboxdigital.http.utils.Configuration;
import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class WorkerSsh implements Runnable {
    final static Logger logger = Logger.getLogger(WorkerSsh.class);

    private Channel channel = null;
    private JSch jsch;
    private String usr;
    private String host;
    String[] commands;

    public WorkerSsh(String host, String[] commands) {
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

    @Override
    public void run() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Session session = jsch.getSession(usr, host, 22);
            session.connect();
            for (String cmd : commands) {
                stringBuffer.append(runCommand(session, cmd));
            }
            session.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private synchronized StringBuffer runCommand(Session session, String command) throws JSchException, IOException {
        channel = session.openChannel("exec");
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
                logger.debug("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }
        channel.disconnect();
        logger.info(strBuffer);
        return strBuffer;
    }
}