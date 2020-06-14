package com.glassboxdigital.xls;

import com.glassboxdigital.clients.ssh.*;
import com.glassboxdigital.command.XslHeaders;
import com.glassboxdigital.conf.Configuration;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;

public class PerformanceManager {
    final static Logger log4j = Logger.getLogger(PerformanceManager.class);
    Configuration conf;
    Clingine clingine;
    Cloff cloff;
    Clifka clifka;
    TrafficGenerator tg1;
    TrafficGenerator tg2;
    int interval = 100;


    public PerformanceManager(Configuration conf) {
        this.conf = conf;
        this.interval = Integer.parseInt(conf.get("interval"));
        String user = conf.get("user");
        String privateKeyLocation = conf.get("privateKeyLocation");
        this.clingine = new Clingine(conf.get("clingine"), user, privateKeyLocation);
        this.cloff = new Cloff(conf.get("cloff"), user, privateKeyLocation);
        this.clifka = new Clifka(conf.get("clifka"), user, privateKeyLocation);
        this.tg1 = new TrafficGenerator(conf.get("tg1"), user, privateKeyLocation);
        this.tg2 = new TrafficGenerator(conf.get("tg2"), user, privateKeyLocation);
    }

    public void runPerformanceTest(WorkbookXls workbookPerformance) throws Exception {

        Sheet openFileSheet = workbookPerformance.createSheet("ClingineOpenFiles");
        Sheet clingineTopSheet = workbookPerformance.createSheet("ClingineTop");
        Sheet cloffTopSheet = workbookPerformance.createSheet("CloffTop");
        Sheet tgGen1TopSheet = workbookPerformance.createSheet("TG1Top");
        Sheet tgGen2TopSheet = workbookPerformance.createSheet("TG2Top");
        Sheet clinginePipelineMetricsSheet = workbookPerformance.createSheet("ClinginePipelineMetrics");
        Sheet clifkaSheet = workbookPerformance.createSheet("KafkaConsumerGroup");
        Sheet clickhouseSessionsSheet = workbookPerformance.createSheet("ClickhouseSessions");
        Sheet clickhouseEventsSheet = workbookPerformance.createSheet("ClickhouseEvents");
        Sheet tg1SessionsSheet = workbookPerformance.createSheet("TG1-Sessions");
        Sheet tg2Sessions1Sheet = workbookPerformance.createSheet("TG2-Sessions1");
        Sheet tg2Sessions2Sheet = workbookPerformance.createSheet("TG2-Sessions2");

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
        tg1.createHeaderRow(tg1SessionsSheet, XslHeaders.headerRowTgSessions);
        tg2.createHeaderRow(tg2Sessions1Sheet, XslHeaders.headerRowTgSessions);

        for (int i = 1; i <= interval; i++) {
            try {
                log4j.info("|---------Interval " + i + " From " + interval + " Started-----------|");
                tg1.publishTGSession1sRow(tg1SessionsSheet);
                tg2.publishTGSession1sRow(tg2Sessions1Sheet);
                tg2.publishTGSession2sRow(tg2Sessions2Sheet);
                clingine.publishOpenfileRow(openFileSheet);
                clingine.publishTopRow(clingineTopSheet);
                clingine.printAllErrors();
                cloff.publishTopRow(cloffTopSheet);
                tg1.publishTopRow(tgGen1TopSheet);
                tg2.publishTopRow(tgGen2TopSheet);
                cloff.publishSessionsCount(clickhouseSessionsSheet);
                cloff.publishEventsCount(clickhouseEventsSheet);
                clifka.publishKafkaConsumerGroup(clifkaSheet);
            } catch (Exception e) {
                log4j.info(e);
            }
        }
        clingine.publishPipelineMetricsCsvRow(clinginePipelineMetricsSheet);
    }
}
