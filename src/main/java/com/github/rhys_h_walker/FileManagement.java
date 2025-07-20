package com.github.rhys_h_walker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.github.rhys_h_walker.core_enums.LoggingType;

/**
 * A static class which implements most of the IO features required by OnRailsLogger
 */

public class FileManagement {

    /**
     * Based on a timestamp either locate or create a file for logging
     * @param timestamp The timestamp to use to find the log file
     * @return
     */
    public static File locateFile(LocalDateTime ts, File applicationDirectory) {
        // We have our timestamp in this format yyyy-MM-dd-HH:mm:ss
        File loggingFile = new File(applicationDirectory.getAbsolutePath()+File.separator+ts.getYear()+File.separator+ts.getMonthValue()+File.separator+ts.getDayOfMonth()+File.separator+ts.getHour()+"_"+ts.getMinute()+"_"+ts.getSecond()+".log");

        if (loggingFile.exists()){
            return loggingFile;
        } else {
            File parentDirectory = loggingFile.getParentFile();
            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }
            return loggingFile;
        }
    }

    public static void writeToLogFile(File logFile, String timestamp, LoggingType logType, String message, PrintWriter pw) {
        String logMessage = "[" + timestamp + "] " + logType.toString() + ": " + message; 
        pw.println(logMessage);
        pw.flush();
    }

    /**
     * Read in a log file and return it split by line
     * @param logFile
     * @return
     */
    public static ArrayList<String> readLogFile(File logFile) {

        if (!logFile.exists()) {
            System.exit(1);
        }

        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();

            return lines;
        } catch(Exception e) {
            // Failure detected error and then return null
            System.err.println("Failed opening file " + e);
            return null;
        }

    }
}
