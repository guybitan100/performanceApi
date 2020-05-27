package com.glassboxdigital.ssh;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SshLogger {
    final static Logger log4j = Logger.getLogger(SshLogger.class);

    private final String fileName;

    public SshLogger(String filename) {
        this.fileName = filename;
    }

    public void write(StringBuffer sb, String ext) {
        File logFile = getFileWithTimeStamp(ext);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            log4j.debug(e);
        }

    }

    private File getFileWithTimeStamp(String ext) {
        if (ext.isEmpty()) {
            ext = "log";
        }
        String filePath = System.getProperty("user.dir");
        Path path = Paths.get(filePath + "/logs");

        try {
            Files.createDirectory(path);
        } catch (IOException e) {
        }

        File file = new File(path + "/" + this.fileName + getCurrentTimeStamp().replace(":", "-").replace(".", "-") + "." + ext);

        try {
            if (!file.exists()) {
                file.createNewFile();
                log4j.debug("File is created; file name is " + file.getName());
            } else {
                log4j.debug("File already exist");
            }
        } catch (IOException e) {
            log4j.debug(e);
        }
        return file;
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    private String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}