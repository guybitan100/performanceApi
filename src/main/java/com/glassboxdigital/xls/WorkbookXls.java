package com.glassboxdigital.xls;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookXls {
    private Workbook workbook = null;
    private FileOutputStream fos = null;

    public WorkbookXls(String fileName) throws Exception {
        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new Exception("invalid file name, should be xls or xlsx");
        }
        this.fos = new FileOutputStream(fileName);
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
