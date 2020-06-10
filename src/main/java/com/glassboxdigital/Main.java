package com.glassboxdigital;

import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.utils.DateTimeUtil;
import com.glassboxdigital.xls.PerformanceManager;
import com.glassboxdigital.xls.WorkbookXls;

public class Main {

    public static void main(String args[]) throws Exception {
        Configuration conf = new Configuration("ssh.properties");
        int totalIterations = Integer.parseInt(conf.get("total-iterations"));
        for (int i = 0; i < totalIterations; i++) {
            WorkbookXls workbookPerformance = new WorkbookXls("Performance" + DateTimeUtil.getCurrentTime() + ".xls");
            PerformanceManager pm = new PerformanceManager(conf);
            try {
                pm.runPerformanceTest(workbookPerformance);
            } catch (Exception e) {
                workbookPerformance.writeAndClose();
                break;
            }
            workbookPerformance.writeAndClose();
        }
    }
}