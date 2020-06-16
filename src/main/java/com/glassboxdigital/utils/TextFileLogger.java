package com.glassboxdigital.utils;

import org.apache.log4j.Logger;
        
        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;

public class TextFileLogger {
   final static Logger log4j = Logger.getLogger(TextFileLogger.class);

           private final String fileName;

           public TextFileLogger(String filename) {
               this.fileName = filename;
           }

           public void write(String str) {
               File logFile = getFileWithTimeStamp("log");
               try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile))) {
                       bw.write(str);
                   } catch (IOException e) {
                       log4j.debug(e);
                   }
           }

           private File getFileWithTimeStamp(String ext) {
               if (ext.isEmpty()) {
                       ext = "log";
                   }
               String filePath = System.getProperty("user.dir");
               Path path = Paths.get(filePath + "/Error-logs");
        
                       try {
                       Files.createDirectory(path);
                   } catch (IOException e) {
                   }
        
                       File file = new File(path + "/" + this.fileName + " [" + DateTimeUtil.getCurrentTimeStamp().replace(":", "").replace(".", "") + "]." + ext);
        
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

}
