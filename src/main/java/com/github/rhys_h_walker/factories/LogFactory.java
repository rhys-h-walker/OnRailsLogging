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
 * supplied upon construction of the logger. This class handles the cycling of the
 * PrintWriter object.
 */

public class LogFactory {
    private File applicationDirectory;
    private String timestamp;
    private PrintWriter pw;
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");

    // Error reporting flags
    private static boolean reportedFileSystemError = false;
    private static boolean reportedPrintWriterError = false;

    // Non LogFactory error reporting fields
    private boolean initialized = false;

    /**
     * Create a LogFactory for a specific application
     * All logs will be located under this name like this:
     * 
     * user.home/OnRailsLogging/applicationName/ regular dated directory structure
     * 
     * THIS METHOD WILL RETURN ON ANY ERROR
     * 
     * @param applicationName The name to be used
     */
    public LogFactory(String applicationName) {
        // Reset flags on creation of LogFactory
        reportedFileSystemError = false;
        reportedPrintWriterError = false;

        // Now upon creation of this object check and create directories that are required
        String userHome = System.getProperty("user.home");

        if (userHome == null) {
            // User home not accessible, falling back to console Logging
            System.err.println("User home not accessible, falling back to console Logging");
            return;
        }

        // Save the directory that will be being used later
        applicationDirectory = new File(userHome, "OnRailsLogging" + File.separator + applicationName);

        if (!applicationDirectory.exists()) {
            boolean success = applicationDirectory.mkdirs();

            if (!success) {
                System.err.println("Creation of directory was not successfull in LogFactory constructor");
                return;
            }
        }

        // Assign our current printwriter
        LocalDateTime now = LocalDateTime.now();
        timestamp = now.format(df);

        File initialLogFile = FileManagement.locateFile(now, applicationDirectory);
        if (initialLogFile == null) {
            System.err.println("Error in finding the initial log file, returning in constructor");
            return;
        }

        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(initialLogFile)), true);
        } catch (IOException e) {
            System.err.println("Failed to create printwriter, returning in constructor");
            return;
        }

        // Close the PrintWriter when execution terminates
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (pw != null) pw.close();
        }));

        initialized = true;
    }

    /**
     * Has this LogFactory been initialized
     * @return true if ok, false if not ok
     */
    public boolean initialized() {
        return initialized;
    }

    /**
     * Create a new log given a log type and message
     * @param message The message to acompany the log
     * @param logType The type to be logged
     * @return The timestamp String to use, format of (YYYY-MM-DD-HH:MM:SS)
     */
    public String createNewLog(String message, LoggingType logType) {
        // Get the timestamp and locate the file that is relevent
        LocalDateTime now = LocalDateTime.now();
        String curTimestamp = now.format(df);

        // Get the current log file
        File logFile = FileManagement.locateFile(now, applicationDirectory);
        if (logFile == null) {
            if (!reportedFileSystemError) {
                reportedFileSystemError = true;
                System.err.println("Error in creation/access of logFile");
            }
            return null;
        }

        // printWriter will need updating if this is different
        if (!timestamp.equals(curTimestamp)) {
            timestamp = curTimestamp;
            try {
                pw.close();
                pw = new PrintWriter(new BufferedWriter(new FileWriter(logFile)), true);
            } catch (IOException e) {

                if (!reportedPrintWriterError) {
                    reportedPrintWriterError = true;
                    System.err.println("PrintWriter creation error:\n" + e);
                }
                
                return null;
            }
        }

        // Should never be required
        if (pw == null || pw.checkError()) {
            System.err.println("Printwriter was either null or has found an error");
            return null;
        }

        // Write to the log file
        FileManagement.writeToLogFile(timestamp, logType, message, pw);

        return timestamp;
    }

    /**
     * Close any system resources correctly
     * @return true for success (Can only return true, retained functionality for future updates)
     */
    public boolean cleanup() {

        if (pw == null) {
            return true;
        }

        pw.flush();
        pw.close();
        pw = null;

        return true;

    }
}
