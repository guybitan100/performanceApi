package com.glassboxdigital;

import com.glassboxdigital.clients.ssh.Clifka;
import com.glassboxdigital.clients.ssh.Clingine;
import com.glassboxdigital.clients.ssh.Cloff;
import com.glassboxdigital.clients.ssh.TrafficGenerator;
import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.command.XslHeaders;
import com.glassboxdigital.xls.PerformanceManager;
import com.glassboxdigital.xls.WorkbookXls;
import org.apache.poi.ss.usermodel.Sheet;

public class Main {

    public static void main(String args[]) throws Exception {
        WorkbookXls workbookPerformance = new WorkbookXls("Performance.xls");
        Configuration conf = new Configuration("ssh.properties");
        PerformanceManager pm = new PerformanceManager(conf);
        pm.runPerformanceTest(workbookPerformance);
    }
}