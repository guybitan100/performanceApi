package com.glassboxdigital;

import com.glassboxdigital.clients.ssh.Clifka;
import com.glassboxdigital.clients.ssh.Clingine;
import com.glassboxdigital.clients.ssh.Cloff;
import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.xls.WorkbookXls;
import org.apache.poi.ss.usermodel.Sheet;

public class Main {

    private static String[] headerRowOpenFile = new String[]{"Time", "All", "Fts", "Recent", "Journey"};
    private static String[] headerRowTop = new String[]{"Time", "Cpu", "Memory"};

    public static void main(String args[]) throws Exception {
        WorkbookXls workbookPerformance = new WorkbookXls("Performance.xls");
        Configuration conf = new Configuration("ssh.properties");
        String clingineConf = conf.get("clingine");
        String cloffConf = conf.get("cloff");
        String clifkaConf = conf.get("clifka");
        String user = conf.get("user");
        String tg1 = conf.get("tg1");
        String tg2 = conf.get("tg2");
        String privateKeyLocation = conf.get("privateKeyLocation");
        Clingine clingine = new Clingine(clingineConf, user, privateKeyLocation);
        Cloff cloff = new Cloff(cloffConf, user, privateKeyLocation);
        Clifka clifka = new Clifka(clifkaConf, user, privateKeyLocation);
//        TrafficGenerator trafficGen1 = new TrafficGenerator(tg1, user, privateKeyLocation);
//        TrafficGenerator trafficGen2 = new TrafficGenerator(tg2, user, privateKeyLocation);
//        trafficGen1.isUp();
//        trafficGen2.isUp();
//        new Clingine(clingine, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.CPU_STATUS, sshCommands.MEM_STATUS, sshCommands.SERVER_ROOT_MSG_CONSUMER_STAT}).run();
//        new Clingine(clingine, user, privateKeyLocation, new String[]{sshCommands.SESSION_PIPELINE_METRICS_CSV_FILE}).run();
        Sheet openFileSheet = workbookPerformance.createSheet("OpenFiles");
        Sheet clingineTopSheet = workbookPerformance.createSheet("ClingineTop");
        Sheet cloffTopSheet = workbookPerformance.createSheet("CloffTop");
        Sheet clifkaTopSheet = workbookPerformance.createSheet("ClifkaTop");
        clingine.createHeaderRow(openFileSheet, headerRowOpenFile);
        clingine.createHeaderRow(clingineTopSheet, headerRowTop);
        cloff.createHeaderRow(cloffTopSheet, headerRowTop);
        clifka.createHeaderRow(clifkaTopSheet, headerRowTop);

        for (int i = 1; i <= 3; i++) {
            clingine.runAndCreateOpenfileRow(openFileSheet, i);
            clingine.runAndCreatePSRow(clingineTopSheet, i);
            cloff.runAndCreatePSRow(clingineTopSheet, i);
            clifka.runAndCreatePSRow(clingineTopSheet, i);
        }
        workbookPerformance.writeAndClose();
//            new SshClient(cloff, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS, sshCommands.CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR});
//            new SshClient(clifka, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.KAFKA_CONSUMER_GROUP});
//            new SshClient(tg1, user, privateKeyLocation, new String[]{sshCommands.TG_STATUS});
//            new SshClient(tg2, user, privateKeyLocation, new String[]{sshCommands.TG_STATUS});

    }
}