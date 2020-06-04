package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.SshCommands;
import com.glassboxdigital.xls.OpenFiles;
import com.glassboxdigital.xls.SessionPipelineMetrics;

import java.util.List;

public class Clingine extends SshClient implements SshCommands {
    private List<OpenFiles> openFilesList;
    private List<SessionPipelineMetrics> sessionPipelineMetrics;
    private String[] openFileCommands = new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_LOG, LSOF_JAR, LSOF_PIPE, LSOF_EVENT_POLL, LSOF_EVENT_JOURNEY};

    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void getOpenFiles() {
        commands2Exe = openFileCommands;
        StringBuffer commands = runCommands();
        System.out.println(commands);
    }

}