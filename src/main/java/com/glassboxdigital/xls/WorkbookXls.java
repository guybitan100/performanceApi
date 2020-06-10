package com.glassboxdigital.xls;

import java.io.FileOutputStream;
import java.io.IOException;

import com.glassboxdigital.clients.ssh.SshClient;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookXls {
    final static Logger log4j = Logger.getLogger(WorkbookXls.class);
    private Workbook workbook = null;
    private FileOutputStream fos = null;

    public WorkbookXls(String fileName) throws Exception {

        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            log4j.debug("invalid file name, should be xls or xlsx");
            throw new Exception("invalid file name, should be xls or xlsx");
        }
        this.fos = new FileOutputStream(fileName);
    }

    public void write() throws IOException {
        workbook.write(fos);
    }

    public void writeAndClose() throws IOException {
        workbook.write(fos);
        fos.close();
    }

    public Sheet createSheet(String name) {
        Sheet sheet = workbook.createSheet(name);
        return sheet;
    }
}
