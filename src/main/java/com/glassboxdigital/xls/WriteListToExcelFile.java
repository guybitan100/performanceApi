package com.glassboxdigital.xls;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteListToExcelFile {
    private Workbook workbook = null;
    private FileOutputStream fos = null;

    public WriteListToExcelFile(String fileName) throws Exception {
        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new Exception("invalid file name, should be xls or xlsx");
        }
        this.fos = new FileOutputStream(fileName);
    }

    private void close() throws IOException {
        fos.close();
    }

    private void crateOpenFileSheet(List<OpenFiles> openFilesList) throws IOException {
        Sheet sheet = workbook.createSheet("Open-Files");
        Iterator<OpenFiles> iterator = openFilesList.iterator();
        int rowIndex = 0;
        int cellIndex = 0;
        Cell cell = null;
        while (iterator.hasNext()) {
            OpenFiles openFiles = iterator.next();
            Row row = sheet.createRow(rowIndex++);
            cell = row.createCell(cellIndex++);
            cell.setCellValue(openFiles.getDate());
            cell = row.createCell(cellIndex++);
            cell.setCellValue(openFiles.getFts());
            cell = row.createCell(cellIndex++);
            cell.setCellValue(openFiles.getJourney());
            cell = row.createCell(cellIndex++);
            cell.setCellValue(openFiles.getRecent());
            cell = row.createCell(cellIndex);
            cell.setCellValue(openFiles.getTotal());
        }
        workbook.write(fos);
    }
    private void crateTrafficGeneratorSheet(List<TrafficGenerator> trafficGeneratorList) throws IOException {
        Sheet sheet = workbook.createSheet("TrafficGenerator");
        Iterator<TrafficGenerator> iterator = trafficGeneratorList.iterator();
        int rowIndex = 0;
        int cellIndex = 0;
        Cell cell = null;
        while (iterator.hasNext()) {
            TrafficGenerator trafficGenerator = iterator.next();
            Row row = sheet.createRow(rowIndex++);
            cell = row.createCell(cellIndex++);
            cell.setCellValue(trafficGenerator.getDate());
            cell = row.createCell(cellIndex++);
            cell.setCellValue(trafficGenerator.getConcurrentSessions());
            cell = row.createCell(cellIndex++);
            cell.setCellValue(trafficGenerator.getSleepInterval());
            cell = row.createCell(cellIndex);
            cell.setCellValue(trafficGenerator.getActualSessions());
        }
        workbook.write(fos);
    }

    //Main//
    public static void main(String args[]) throws Exception {
        List<OpenFiles> openFilesList = new ArrayList<OpenFiles>();
        OpenFiles guy = new OpenFiles("Guy", "Bitan", "w", "w", "w");
        openFilesList.add(guy);
        WriteListToExcelFile writeListToExcelFile = new WriteListToExcelFile("Countries.xls");
        writeListToExcelFile.crateOpenFileSheet(openFilesList);
        writeListToExcelFile.close();
    }
}
