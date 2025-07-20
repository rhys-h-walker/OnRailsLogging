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

    /**
     * Create a LogFactory for a specific application
     * All logs will be located under this name like this:
     * 
     * user.home/OnRailsLogging/applicationName/ regular dated directory structure
     * 
     * @param applicationName The name to be used
     */
    public LogFactory(String applicationName) {
        // Now upon creation of this object check and create directories that are required
        String userHome = System.getProperty("user.home");
        applicationDirectory = new File(userHome, "OnRailsLogging" + File.separator + applicationName);
        if (!applicationDirectory.exists()) {
            applicationDirectory.mkdirs();
        }

        // Assign our current printwriter
        LocalDateTime now = LocalDateTime.now();
        timestamp = now.format(df);
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(FileManagement.locateFile(now, applicationDirectory))), true);
        } catch (IOException e) {
            System.err.println(e);
        }

        // Close the PrintWriter when execution terminates
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (pw != null) pw.close();
        }));

    }

    /**
     * Create a new log given a log type and message
     * @param message The message to acompany the log
     * @param logType The type to be logged
     * @return A string representing the timestamp for the log
     */
    public String createNewLog(String message, LoggingType logType) {
        // Get the timestamp and locate the file that is relevent
        LocalDateTime now = LocalDateTime.now();
        String curTimestamp = now.format(df);

        // Get the current log file
        File logFile = FileManagement.locateFile(now, applicationDirectory);

        // printWriter will need updating if this is different
        if (!timestamp.equals(curTimestamp)) {
            timestamp = curTimestamp;
            try {
                pw.close();
                pw = new PrintWriter(new BufferedWriter(new FileWriter(logFile)), true);
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        // Write to the log file
        FileManagement.writeToLogFile(logFile, timestamp, logType, message, pw);

        return timestamp;
    }
}
