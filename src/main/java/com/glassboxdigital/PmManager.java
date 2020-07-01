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
    }


    public void runPmOverTimeTest(WorkbookXls workbookPerformance) throws Exception {
        int i = 1;
        Sheet openFileSheet = workbookPerformance.createSheet("ClingineOpenFiles");
        Sheet clingineTopSheet = workbookPerformance.createSheet("ClingineTop");
        Sheet cloffTopSheet = workbookPerformance.createSheet("CloffTop");
        Sheet tgGen1TopSheet = workbookPerformance.createSheet("TG1Top");
        Sheet clinginePipelineMetricsSheet = workbookPerformance.createSheet("ClinginePipelineMetrics");
        Sheet clifkaSheet = workbookPerformance.createSheet("KafkaConsumerGroup");
        Sheet clickhouseSessionsSheet = workbookPerformance.createSheet("ClickhouseSessions");
        Sheet tg1SessionsSheet = workbookPerformance.createSheet("TG1-Sessions");
        Sheet tg2Sessions1Sheet = workbookPerformance.createSheet("TG2-Sessions1");
        Sheet tg2Sessions2Sheet = workbookPerformance.createSheet("TG2-Sessions2");

        clingine.createHeaderRow(openFileSheet, XslHeaders.headerRowOpenFile);
        clingine.createHeaderRow(clinginePipelineMetricsSheet, XslHeaders.headerRowClinginePipelineMetrics);
        clingine.createHeaderRow(clingineTopSheet, XslHeaders.headerRowTop);
        tg1.createHeaderRow(tgGen1TopSheet, XslHeaders.headerRowTop);
        clickHouse.createHeaderRow(cloffTopSheet, XslHeaders.headerRowTop);
        kafka.createHeaderRow(clifkaSheet, XslHeaders.headerRowBeaconOfflineGroup);
        clickHouse.createHeaderRow(clickhouseSessionsSheet, XslHeaders.headerRowClickhouseSessions);
        tg1.createHeaderRow(tg1SessionsSheet, XslHeaders.headerRowTgSessions);
        TimeOut timeOut = new TimeOut(duration);
        checkStatus();
        while (timeOut.isContinueRun()) {
            try {
                log4j.info("|---------Interval " + i++ + " Started-----------|");
                tg1.publishTGSession1sRow(tg1SessionsSheet);
                clingine.publishTopRow(clingineTopSheet);
                clickHouse.publishTopRow(cloffTopSheet);
                tg1.publishTopRow(tgGen1TopSheet);
                clickHouse.publishSessionsCount(clickhouseSessionsSheet);
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
        StringBuffer errorMessage = new StringBuffer();
        if (clingine.isDiskFull()) {
            errorMessage.append("Clingine disk is over 90%");
        }

        if (clickHouse.isThereIsNotTrafficInLastHour()) {
            errorMessage.append("clickHouse No TRAFFIC in last hour !!!");
        }
        if (clingine.isNotRunning() || clickHouse.isNotRunning() || tg1.isNotRunning()) {
            printAllErrors();
            if (clickHouse.isNotRunning()) {
                errorMessage.append("ClickHouse Not Running !!!");
            }
            if (clingine.isNotRunning()) {
                errorMessage.append("Clingine Not Running !!!");
            }
            if (tg1.isNotRunning()) {
                errorMessage.append("TG1 Is Not Running !!!");
            }
            printAllErrors();
        }
        //Send Mail
        if (!errorMessage.toString().isEmpty()) {
            Email.sendEmail(errorMessage.toString());
        }
    }

    public void printAllErrors() throws Exception {
        clingine.printAllErrors("ClingineError");
        tg1.printAllErrors("TG1Error");
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
