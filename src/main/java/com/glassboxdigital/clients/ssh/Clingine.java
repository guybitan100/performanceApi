package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.xls.OpenFiles;
import com.glassboxdigital.xls.SessionPipelineMetrics;

import java.util.List;

public class Clingine extends SshClient {
    List<OpenFiles> openFilesList;
    List<SessionPipelineMetrics> sessionPipelineMetrics;

    public Clingine(String host, String user, String privateKeyLocation, String[] commands) {
        super(host, user, privateKeyLocation, commands);
    }

    public void getOpenFiles() {
        StringBuffer str = super.runCommands();
        System.out.println(str);
    }
}