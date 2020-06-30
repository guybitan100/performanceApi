package com.glassboxdigital;

import com.glassboxdigital.clients.ssh.*;
import com.glassboxdigital.command.XslHeaders;
import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.utils.DateTimeUtil;
import com.glassboxdigital.utils.TimeOut;
import com.glassboxdigital.xls.WorkbookXls;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;

public class PmManager {
    final static Logger log4j = Logger.getLogger(PmManager.class);
    Configuration conf;
    Clingine clingine;
    ClickHouse clickHouse;
    Kafka kafka;
    TG tg1;
    TG tg2;
    int duration;

    public PmManager(Configuration conf) {
        this.conf = conf;
        this.duration = Integer.parseInt(conf.get("durationMin"));
        String user = conf.get("user");
        String privateKeyLocation = conf.get("privateKeyLocation");
        this.clingine = new Clingine(conf.get("clingine"), user, privateKeyLocation);
        this.clickHouse = new ClickHouse(conf.get("cloff"), user, privateKeyLocation);
        this.kafka = new Kafka(conf.get("clifka"), user, privateKeyLocation);
        this.tg1 = new TG(conf.get("tg1"), user, privateKeyLocation);
        this.tg2 = new TG(conf.get("tg2"), user, privateKeyLocation);
    }


    public void runPmOverTimeTest(WorkbookXls workbookPerformance) throws Exception {
        int i = 1;
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
        clickHouse.createHeaderRow(cloffTopSheet, XslHeaders.headerRowTop);
        kafka.createHeaderRow(clifkaSheet, XslHeaders.headerRowBeaconOfflineGroup);
        clickHouse.createHeaderRow(clickhouseSessionsSheet, XslHeaders.headerRowClickhouseSessions);
        clickHouse.createHeaderRow(clickhouseEventsSheet, XslHeaders.headerRowClickhouseEvents);
        clickHouse.createHeaderRow(clickhouseEventsSheet, XslHeaders.headerRowClickhouseEvents);
        tg1.createHeaderRow(tg1SessionsSheet, XslHeaders.headerRowTgSessions);
        tg2.createHeaderRow(tg2Sessions1Sheet, XslHeaders.headerRowTgSessions);
        TimeOut timeOut = new TimeOut(duration);
        checkStatus();
        while (timeOut.isContinueRun()) {
            try {
                log4j.info("|---------Interval " + i++ + " Started-----------|");
                tg1.publishTGSession1sRow(tg1SessionsSheet);
                tg2.publishTGSession1sRow(tg2Sessions1Sheet);
                tg2.publishTGSession2sRow(tg2Sessions2Sheet);
                clingine.publishTopRow(clingineTopSheet);
                clickHouse.publishTopRow(cloffTopSheet);
                tg1.publishTopRow(tgGen1TopSheet);
                tg2.publishTopRow(tgGen2TopSheet);
                clickHouse.publishSessionsCount(clickhouseSessionsSheet);
                clickHouse.publishEventsCount(clickhouseEventsSheet);
                kafka.publishKafkaConsumerGroup(clifkaSheet);
                clingine.publishOpenfileRow(openFileSheet);
            } catch (Exception e) {
                log4j.info(e);
            }
        }
        checkStatus();
        clingine.publishPipelineMetricsCsvRow(clinginePipelineMetricsSheet);
    }

    public void checkStatus() throws Exception {
        StringBuffer errorMessage = null;
        if (clingine.isNotRunning() || clickHouse.isNotRunning() || tg1.isNotRunning()) {
            printAllErrors();
            if (clickHouse.isNotRunning()) {
                errorMessage.append("ClickHouse Is Not Running");
            }
            if (clingine.isNotRunning()) {
                errorMessage.append("Clingine Is Not Running");
            }
            if (tg1.isNotRunning()) {
                errorMessage.append("TG1 Is Not Running");
            }
            if (clickHouse.isThereIsNotTrafficInLastHour()) {
                errorMessage.append("No TRAFFIC IN Last Hour");
            }
        }
        printAllErrors();
    }

    public void printAllErrors() throws Exception {
        clingine.printAllErrors("ClingineError");
        tg1.printAllErrors("TG1Error");
        tg2.printAllErrors("TG2Error");
    }

    public static void main(String args[]) throws Exception {
        Configuration conf = new Configuration("ssh.properties");
        WorkbookXls workbookPerformance = new WorkbookXls("Performance" + DateTimeUtil.getCurrentTime() + ".xls");
        PmManager pm = new PmManager(conf);
        try {
            pm.runPmOverTimeTest(workbookPerformance);
        } catch (Exception e) {
            workbookPerformance.writeAndClose();
        }
        workbookPerformance.writeAndClose();
    }
}
