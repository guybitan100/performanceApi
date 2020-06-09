package com.glassboxdigital;

import com.glassboxdigital.clients.ssh.Clifka;
import com.glassboxdigital.clients.ssh.Clingine;
import com.glassboxdigital.clients.ssh.Cloff;
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
        String user = conf.get("user");
        String privateKeyLocation = conf.get("privateKeyLocation");
        Clingine clingine = new Clingine(clingineHost, user, privateKeyLocation);
        Cloff cloff = new Cloff(cloffHost, user, privateKeyLocation);
        Clifka clifka = new Clifka(clifkaHost, user, privateKeyLocation);
        Sheet openFileSheet = workbookPerformance.createSheet("ClingineOpenFiles");
        Sheet clingineTopSheet = workbookPerformance.createSheet("ClingineTop");
        Sheet clinginePipelineMetricsSheet = workbookPerformance.createSheet("ClinginePipelineMetrics");
        Sheet cloffTopSheet = workbookPerformance.createSheet("CloffTop");
        Sheet clifkaSheet = workbookPerformance.createSheet("KafkaConsumerGroup");
        Sheet clickhouseSessionsSheet = workbookPerformance.createSheet("ClickhouseSessions");
        Sheet clickhouseEventsSheet = workbookPerformance.createSheet("ClickhouseEvents");

        clingine.createHeaderRow(openFileSheet, XslHeaders.headerRowOpenFile);
        clingine.createHeaderRow(clinginePipelineMetricsSheet, XslHeaders.headerRowClinginePipelineMetrics);
        clingine.createHeaderRow(clingineTopSheet, XslHeaders.headerRowTop);
        cloff.createHeaderRow(cloffTopSheet, XslHeaders.headerRowTop);
        clifka.createHeaderRow(clifkaSheet, XslHeaders.headerRowBeaconOfflineGroup);
        cloff.createHeaderRow(clickhouseSessionsSheet, XslHeaders.headerRowClickhouseSessions);
        cloff.createHeaderRow(clickhouseEventsSheet, XslHeaders.headerRowClickhouseEvents);
        for (int i = 1; i <= 5; i++) {
          // clingine.publishOpenfileRow(openFileSheet);
           clingine.publishTopRow(clingineTopSheet);
         //  clingine.publishPipelineMetricsRow(clinginePipelineMetricsSheet);
           cloff.publishTopRow(cloffTopSheet);
         //  cloff.publishSessionsCount(clickhouseSessionsSheet);
         //  cloff.publishEventsCount(clickhouseEventsSheet);
        //   clifka.publishKafkaConsumerGroup(clifkaSheet);
        }

        workbookPerformance.writeAndClose();
    }
}