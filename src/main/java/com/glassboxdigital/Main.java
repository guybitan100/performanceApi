package com.glassboxdigital;

import com.glassboxdigital.clients.ssh.Clifka;
import com.glassboxdigital.clients.ssh.Clingine;
import com.glassboxdigital.clients.ssh.Cloff;
import com.glassboxdigital.clients.ssh.TrafficGenerator;
import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.command.XslHeaders;
import com.glassboxdigital.xls.WorkbookXls;
import org.apache.poi.ss.usermodel.Sheet;

public class Main {

    public static void main(String args[]) throws Exception {
        WorkbookXls workbookPerformance = new WorkbookXls("Performance.xls");
        Configuration conf = new Configuration("ssh.properties");
        String clingineHost = conf.get("clingine");
        String cloffHost = conf.get("cloff");
        String clifkaHost = conf.get("clifka");
        String tg1Host = conf.get("tg1");
        String tg2Host = conf.get("tg2");
        String user = conf.get("user");
        String privateKeyLocation = conf.get("privateKeyLocation");
        Clingine clingine = new Clingine(clingineHost, user, privateKeyLocation);
        Cloff cloff = new Cloff(cloffHost, user, privateKeyLocation);
        Clifka clifka = new Clifka(clifkaHost, user, privateKeyLocation);
        TrafficGenerator tg1 = new TrafficGenerator(tg1Host, user, privateKeyLocation);
        TrafficGenerator tg2 = new TrafficGenerator(tg2Host, user, privateKeyLocation);
        Sheet openFileSheet = workbookPerformance.createSheet("ClingineOpenFiles");
        Sheet clingineTopSheet = workbookPerformance.createSheet("ClingineTop");
        Sheet cloffTopSheet = workbookPerformance.createSheet("CloffTop");
        Sheet tgGen1TopSheet = workbookPerformance.createSheet("TG1Top");
        Sheet tgGen2TopSheet = workbookPerformance.createSheet("TG2Top");
        Sheet clinginePipelineMetricsSheet = workbookPerformance.createSheet("ClinginePipelineMetrics");
        Sheet clifkaSheet = workbookPerformance.createSheet("KafkaConsumerGroup");
        Sheet clickhouseSessionsSheet = workbookPerformance.createSheet("ClickhouseSessions");
        Sheet clickhouseEventsSheet = workbookPerformance.createSheet("ClickhouseEvents");


        clingine.createHeaderRow(openFileSheet, XslHeaders.headerRowOpenFile);
        clingine.createHeaderRow(clinginePipelineMetricsSheet, XslHeaders.headerRowClinginePipelineMetrics);
        clingine.createHeaderRow(clingineTopSheet, XslHeaders.headerRowTop);
        tg1.createHeaderRow(tgGen1TopSheet, XslHeaders.headerRowTop);
        tg2.createHeaderRow(tgGen2TopSheet, XslHeaders.headerRowTop);
        cloff.createHeaderRow(cloffTopSheet, XslHeaders.headerRowTop);
        clifka.createHeaderRow(clifkaSheet, XslHeaders.headerRowBeaconOfflineGroup);
        cloff.createHeaderRow(clickhouseSessionsSheet, XslHeaders.headerRowClickhouseSessions);
        cloff.createHeaderRow(clickhouseEventsSheet, XslHeaders.headerRowClickhouseEvents);
        cloff.createHeaderRow(clickhouseEventsSheet, XslHeaders.headerRowClickhouseEvents);

        for (int i = 1; i <= 20; i++) {
            try {
                clingine.publishOpenfileRow(openFileSheet);
                clingine.publishTopRow(clingineTopSheet);
                clingine.publishPipelineMetricsRow(clinginePipelineMetricsSheet);
                cloff.publishTopRow(cloffTopSheet);
                tg1.publishTopRow(tgGen1TopSheet);
                tg2.publishTopRow(tgGen2TopSheet);
                cloff.publishSessionsCount(clickhouseSessionsSheet);
                cloff.publishEventsCount(clickhouseEventsSheet);
                clifka.publishKafkaConsumerGroup(clifkaSheet);
            } catch (Exception e) {
                workbookPerformance.writeAndClose();
                return;
            }
        }
        workbookPerformance.writeAndClose();
    }
}