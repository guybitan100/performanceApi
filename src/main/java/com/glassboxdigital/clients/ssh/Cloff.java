package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.ClickhouseCommandsInt;
import com.glassboxdigital.utils.DateTimeUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Cloff extends SshClient implements ClickhouseCommandsInt {
    public Cloff(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void publishPSRow(Sheet sheet) {
        publishPSRow(sheet, new String[]{PS_CLOFF});
    }

    public void publishSessionsCount(Sheet sheet) {
        StringBuffer cmdStr = runCommands(new String[]{CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR});
        String[] cmdsStrSplit = cmdStr.toString().split("\\r?\\n");
        int rowNumber = sheet.getLastRowNum() + 1;
        Cell cell;
        for (String str : cmdsStrSplit) {
            String[] cmdStrSplit = str.split("\\s+");
            Row row = sheet.createRow(rowNumber++);
            int cellInd = 0;
            cell = row.createCell(cellInd++);
            cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
            for (String cellStr : cmdStrSplit) {
                cell = row.createCell(cellInd++);
                cell.setCellValue(cellStr);
            }
        }
    }
}