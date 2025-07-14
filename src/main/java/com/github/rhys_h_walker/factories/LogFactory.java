package com.github.rhys_h_walker.factories;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.FileManagement;

/**
 * Class that creates and stores logs in the correct location based on information
 * supplied upon construction of the logger
 * For most use cases the construction of a Log object is pointless.
 * It is only needed when viewing
 */

public class LogFactory {
    private File applicationDirectory;
    private String timestamp;
    private PrintWriter pw;
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");

    public LogFactory(String applicationName) {
        // Now upon creation of this object check and create directories that are required
        String userHome = System.getProperty("user.home");
        applicationDirectory = new File(userHome+"\\OnRailsLogging\\"+applicationName);
        if (!applicationDirectory.exists()) {
            applicationDirectory.mkdirs();
        }

        LocalDateTime now = LocalDateTime.now();
        timestamp = now.format(df);
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(FileManagement.locateFile(now, applicationDirectory))), true);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public void createNewLog(String message, LoggingType logType) {
        // Get the timestamp and locate the file that is relevent
        LocalDateTime now = LocalDateTime.now();
        String curTimestamp = now.format(df);

        // printWriter will need updating if this is different
        if (!timestamp.equals(curTimestamp)) {
            timestamp = curTimestamp;
            try {
                pw.close();
                pw = new PrintWriter(new BufferedWriter(new FileWriter(FileManagement.locateFile(now, applicationDirectory))), true);
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        File logFile = FileManagement.locateFile(now, applicationDirectory);
        FileManagement.writeToLogFile(logFile, timestamp, logType, message, pw);
    }
}
