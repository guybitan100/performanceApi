package com.glassboxdigital;

import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.utils.DateTimeUtil;
import com.glassboxdigital.xls.PerformanceManager;
import com.glassboxdigital.xls.WorkbookXls;

public class Main {

    public static void main(String args[]) throws Exception {
        Configuration conf = new Configuration("ssh.properties");

        for (int i = 0; i < 100; i++) {
            WorkbookXls workbookPerformance = new WorkbookXls("Performance" + DateTimeUtil.getCurrentTime() + ".xls");
            PerformanceManager pm = new PerformanceManager(conf, 1);
            try {
                pm.runPerformanceTest(workbookPerformance);
            } catch (Exception e) {
                workbookPerformance.writeAndClose();
            }
            workbookPerformance.writeAndClose();
        }
    }
}