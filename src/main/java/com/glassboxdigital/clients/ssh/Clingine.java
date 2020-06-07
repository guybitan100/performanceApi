package com.glassboxdigital.clients.ssh;
import com.glassboxdigital.SshCommands;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.regex.Matcher;

public class Clingine extends SshClient implements SshCommands {
    private final String regExOpenFile = "^(\\d{1,9})+\\n(\\d{1,9})+\\n(\\d{1,9})+\\n(\\d{1,9})$";
    private final String regExPS = "^(\\d{1,9}\\.\\d{1,9})\\s+(\\d{1,9}\\.\\d{1,9})";

    public Clingine(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    public void createPSRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{PS_Clingine_STATUS});
        Matcher matcher = createMatcher(commands, regExPS);
        Row row = sheet.createRow(rowNumber);
        createDoubleCells(row, matcher);
    }

    public void createOpenFilesRow(Sheet sheet, int rowNumber) {
        StringBuffer commands = runCommands(new String[]{LSOF_ALL, LSOF_FTS, LSOF_RECENT, LSOF_JOURNEY});
        Matcher matcher = createMatcher(commands, regExOpenFile);
        Row row = sheet.createRow(rowNumber);
        createIntegerCells(row, matcher);
    }
}