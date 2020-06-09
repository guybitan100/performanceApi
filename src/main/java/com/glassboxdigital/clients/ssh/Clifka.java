package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.KafkaCommandsInt;
import com.glassboxdigital.utils.DateTimeUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Clifka extends SshClient implements KafkaCommandsInt {
    public Clifka(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }


    public void publishKafkaConsumerGroup(Sheet sheet) {
        StringBuffer cmdStr = runCommands(new String[]{KAFKA_CONSUMER_GROUP});
        String[] cmdsStrSplit = cmdStr.toString().split("\\r?\\n");
        int rowNumber = sheet.getLastRowNum() + 1;
        Cell cell;

        for (String str : cmdsStrSplit) {
            String[] cmdStrSplit = str.split("\\s+");
            if (str.contains("beacon_offline_group")) {
                Row row = sheet.createRow(rowNumber++);
                int cellInd = 0;
                cell = row.createCell(cellInd++);
                cell.setCellValue(DateTimeUtil.getCurrentTimeStamp());
                for (String cellStr : cmdStrSplit) {
                    cell = row.createCell(cellInd++);
                    try {
                        cell.setCellValue(Integer.parseInt(cellStr));
                    } catch (NumberFormatException e) {
                        cell.setCellValue(cellStr);
                    }
                }
            }
        }
    }
}
