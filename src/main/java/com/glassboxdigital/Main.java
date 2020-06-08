package com.glassboxdigital;

import com.glassboxdigital.clients.ssh.Clifka;
import com.glassboxdigital.clients.ssh.Clingine;
import com.glassboxdigital.clients.ssh.Cloff;
import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.xls.XslHeaders;
import com.glassboxdigital.xls.WorkbookXls;
import org.apache.poi.ss.usermodel.Sheet;

public class Main {

    public static void main(String args[]) throws Exception {
        WorkbookXls workbookPerformance = new WorkbookXls("Performance.xls");
        Configuration conf = new Configuration("ssh.properties");
        String clingineHost = conf.get("clingine");
        String cloffHost = conf.get("cloff");
        String clifkaHost = conf.get("clifka");
        String user = conf.get("user");
        String tg1Host = conf.get("tg1");
        String tg2Host = conf.get("tg2");
        String privateKeyLocation = conf.get("privateKeyLocation");
        Clingine clingine = new Clingine(clingineHost, user, privateKeyLocation);
        Cloff cloff = new Cloff(cloffHost, user, privateKeyLocation);
        Clifka clifka = new Clifka(clifkaHost, user, privateKeyLocation);
//        TrafficGenerator trafficGen1 = new TrafficGenerator(tg1Host, user, privateKeyLocation);
//        TrafficGenerator trafficGen2 = new TrafficGenerator(tg2Host, user, privateKeyLocation);
//        trafficGen1.isUp();
//        trafficGen2.isUp();
//        new Clingine(clingine, user, privateKeyLocation, new String[]{sshCommands.SESSION_PIPELINE_METRICS_CSV_FILE}).run();
        Sheet openFileSheet = workbookPerformance.createSheet("OpenFiles");
        Sheet clingineTopSheet = workbookPerformance.createSheet("ClingineTop");
        Sheet cloffTopSheet = workbookPerformance.createSheet("CloffTop");
        Sheet clifkaSheet = workbookPerformance.createSheet("KafkaConsumerGroup");
        clingine.createHeaderRow(openFileSheet, XslHeaders.headerRowOpenFile);
        clingine.createHeaderRow(clingineTopSheet, XslHeaders.headerRowTop);
        cloff.createHeaderRow(cloffTopSheet, XslHeaders.headerRowTop);
        clifka.createHeaderRow(clifkaSheet, XslHeaders.headerRowBeaconOfflineGroup);
        for (int i = 1; i <= 6; i++) {
            clingine.publishOpenfileRow(openFileSheet);
            clingine.publishPSRow(clingineTopSheet);
            cloff.publishPSRow(cloffTopSheet);
            clifka.publishKafkaConsumerGroup(clifkaSheet);
        }
        workbookPerformance.writeAndClose();
//            new SshClient(cloff, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS, sshCommands.CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR});
//            new SshClient(clifka, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.KAFKA_CONSUMER_GROUP});
//            new SshClient(tg1, user, privateKeyLocation, new String[]{sshCommands.TG_STATUS});
//            new SshClient(tg2, user, privateKeyLocation, new String[]{sshCommands.TG_STATUS});

    }
}