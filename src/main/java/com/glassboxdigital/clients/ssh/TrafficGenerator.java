package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.SshCommands;
import com.jcraft.jsch.Session;
import org.apache.poi.ss.usermodel.Sheet;

public class TrafficGenerator extends SshClient implements SshCommands {
    public TrafficGenerator(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void createTopRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{PS_EF_STATUS});
    }

    public boolean isUp() {
        String res = "";
        try {
            log4j.debug("Open Session: " + host);
            Session session = jsch.getSession(user, host);
            session.connect();
            res = execCommand(session, "ps aux |  grep -v grep |grep \"raffi\"").toString();
            log4j.debug(res);
            session.disconnect();
            log4j.debug("Session: " + host + " disconnected");

        } catch (Exception e) {
            log4j.debug(e);
        }
        return !res.isEmpty();
    }
}
