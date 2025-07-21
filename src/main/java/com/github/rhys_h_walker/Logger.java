package com.github.rhys_h_walker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.factories.LogFactory;
import com.github.rhys_h_walker.misc.ANSI;
import com.github.rhys_h_walker.misc.Utilities;

/**
 * The primary entrypoint for a the logger.
 * This file provides the methods for easy logging using different categories
 * 
 * @author Rhys Walker
 */

public class Logger {
    
    private static LogFactory logFactory;
    private static String applicationName;
    private static HashMap<LoggingType, Boolean> logVisibility;

    // Booleans to not spam with log errors
    private static boolean logFactoryNullErrorReported = false;

    //
    // Overrriden initializeLogger which allows for different initialization configurations
    //

    /**
     * Initialize the logging logic
     * @param applicationName The name of the application we are logging for
     * @param level A HashMap detailing the level of logging
     */
    public static void initializeLogger(String appName, HashMap<LoggingType, Boolean> logVisibility, boolean consoleOnly) {
        commonConstructor(appName, logVisibility, consoleOnly);
    }

    /**
     * Initialize the logging logic with the default value of =
     *      LoggingLevel.ERRORS
     * @param applicationName The name of the application we are logging for
     */
    public static void initializeLogger(String appName, boolean consoleOnly) {
        commonConstructor(appName, LoggingType.defaultVisibility(), consoleOnly);
    }

    /**
     * Change whether a log type is visibile or not
     * @param type the type of log to change visibility for
     * @param active a boolean describing the whether the log is active
     */
    public static void changeLogVisibility(LoggingType type, boolean active) {

        if (type == null) {
            System.err.println("Type cannot be null");
            return;
        }

        if (logVisibility == null) {
            System.err.println("Visibility cannot be null");
            return;
        }

        logVisibility.put(type, active);
    }

    /**
     * Is a log type currently visible
     * @param type Type of log to check for
     * @return Boolean representing visibility
     */
    public static Boolean viewLogVisibility(LoggingType type) {

        return logVisibility.get(type);

    }

    /**
     * Get the log visibility map
     * @return
     */
    public static HashMap<LoggingType, Boolean> viewVisibilityMap() {

        return logVisibility;

    }

    /**
     * Set the log visibility map
     * @param newVisibility The new hashmap to set
     */
    public static void changeLogVisibilityMap(HashMap<LoggingType, Boolean> newVisibility) {

        if (newVisibility == null) {
            System.err.println("Map not accepted, null value");
            return;
        }

        // We should have 6 values as allowed by the LoggingType enum
        if (newVisibility.keySet().size() != LoggingType.values().length) {
            System.err.println("Map not accepted, include all values of LoggingType");
        }

        // Bad practice but for size 6 on an infrequently called method should be ok
        for (LoggingType logType : newVisibility.keySet()) {
            if (newVisibility.get(logType) == null) {
                System.err.println("Map not accepted, null value found for " + logType.toString());
            }
            continue;
        }

        logVisibility = newVisibility;

    }

    /**
     * Get the name of the currently running application
     * @return String -> Currently running application
     */
    public static String getApplicationName() {
        return applicationName;
    }

    public static void shutdown() {
        if (logFactory != null) {
            logFactory.cleanup();
            logFactory = null;
        }

        applicationName = null;
    }

    /**
     * Log a miscellaneous message
     * @param message
     * @return the timestamp of the log
     */
    public static String logmiscellaneous(String message) {

        if (logVisibility == null) {
            return null;
        }

        if (message == null) {
            message = "null";
        }

        String logMessage = "";
        if (logVisibility.get(LoggingType.MISCELLANEOUS)) {
            logMessage = (ANSI.CYAN_BG + "MISCELLANEOUS:" + ANSI.RESET + " " + ANSI.CYAN + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.MISCELLANEOUS);
    }

    /**
     * Log a info message
     * @param message
     * @return the timestamp of the log
     */
    public static String loginfo(String message) {

        if (logVisibility == null) {
            return null;
        }

        if (message == null) {
            message = "null";
        }

        String logMessage = "";
        if (logVisibility.get(LoggingType.INFO)) {
            logMessage = (ANSI.BLUE_BG + "INFO:" + ANSI.RESET + " " + ANSI.BLUE + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.INFO);
    }

    /**
     * Log a warning message
     * @param message
     * @return the timestamp of the log
     */
    public static String logwarn(String message) {

        if (logVisibility == null) {
            return null;
        }

        if (message == null) {
            message = "null";
        }

        String logMessage = "";
        if (logVisibility.get(LoggingType.WARN)) {
            logMessage = (ANSI.MAGENTA_BG + "WARN:" + ANSI.RESET + " " + ANSI.MAGENTA + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.WARN);
    }

    /**
     * Log a debug message
     * @param message
     * @return the timestamp of the log
     */
    public static String logdebug(String message) {

        if (logVisibility == null) {
            return null;
        }

        if (message == null) {
            message = "null";
        }

        String logMessage = "";
        if (logVisibility.get(LoggingType.DEBUG)) {
            logMessage = (ANSI.YELLOW_BG + "DEBUG:" + ANSI.RESET + " " + ANSI.YELLOW + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.DEBUG);
    }

    /**
     * Log a progress message
     * @param message
     * @return the timestamp of the log
     */
    public static String logprogress(String message) {

        if (logVisibility == null) {
            return null;
        }

        if (message == null) {
            message = "null";
        }

        String logMessage = "";
        if (logVisibility.get(LoggingType.PROGRESS)) {
            logMessage = (ANSI.GREEN_BG + "PROGRESS:" + ANSI.RESET + " " + ANSI.GREEN + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.PROGRESS);
    }

    /**
     * Log an error message
     * @param message
     * @return the timestamp of the log
     */
    public static String logerror(String message) {

        if (logVisibility == null) {
            return null;
        }

        if (message == null) {
            message = "null";
        }

        String logMessage = "";

        if (logVisibility.get(LoggingType.ERROR)) {
            logMessage = (ANSI.RED_BG + "ERROR:" + ANSI.RESET + " " + ANSI.RED + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.ERROR);
    }

    //
    // Private methods used by the Logger class
    //

    /**
     * Output a log message and create the log in memory
     * @param message The message formatted without colours to be output
     * @param printableMessage The message formatted with ANSI codes
     * @param level The logging level for creation of the log in memory
     * @return the timestamp of the log, null if log factory not set, or LoggingLevel does not allow its outpu
     */
    private static synchronized String produceLog(String message, String printableMessage, LoggingType type) {
        String timestamp = null;

        // Replace null message with "null"
        if (message == null) {
            message = "null";
        }

        // So long as we are set then output
        if (logFactory != null) {
            // Output to file regardless of logging level
            timestamp = logFactory.createNewLog(message, type);

            // If timestamp is null then logging to file has failed, give a "good enough" timestamp
            if (timestamp == null) {
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"));
            }

            // Message will be empty if the logging level does not allow its output
            if (!printableMessage.equals("")){
                System.out.println("[" + timestamp + "] " + printableMessage);
            }
        } else if (!logFactoryNullErrorReported){
            logFactoryNullErrorReported = true;
            System.err.println("Log factory not set, most likely no initialize call was made\nPlease call initialize before any other logging actions");
        } else { // Fallback option if no fileoutput is allowed

            // Message will be empty if the logging level does not allow its output
            if (!printableMessage.equals("")){
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"));
                System.out.println("[" + timestamp + "] " + printableMessage);
                return timestamp;
            }
        }

        return timestamp;
    }

    /**
     * Run common methods featured in other constructors
     * @param appName The name of the application
     * @param customisedVisibility The visibility of each log
     */
    private static void commonConstructor(String appName, HashMap<LoggingType, Boolean> customisedVisibility, boolean consoleOnly) {
        // Reset error flags
        logFactoryNullErrorReported = false;

        // Check the appName for issues (No app needed for console only output)
        if (appName == null || appName.trim().isEmpty()) {
            System.err.println("Initialization failed due to appName being null or empty. Logger will only output to console");
            return;
        }
        appName = Utilities.cleanFileNameString(appName);
        if (appName == null) {
            System.err.println("Error in sanitization of filename");
            return;
        }
        applicationName = appName;

        // Check that logging visibility is not null
        if (customisedVisibility == null) {
            System.err.println("customisedVisibility is null, this could be internal or based on value given \ndefaulting to default values");
            logVisibility = LoggingType.defaultVisibility();
        } else {
            logVisibility = customisedVisibility;
        }

        if (consoleOnly) {
            // They don't want file output so silence the error
            logFactoryNullErrorReported = true;
            logFactory = null;
            return;
        }
        
        // Attempt to make a logFactory, if this fails then output and fallback to console only
        try {
            logFactory = new LogFactory(appName);

            if (!logFactory.initialized()) {
                System.err.println("Log factory initialization not successfull, closing it down.\nReverting to console only logging");
                logFactory = null;
            }

        } catch (SecurityException e) {
            System.err.println("Security restriction during initialization: " + e.getMessage());
            System.err.println("Falling back to console-only logging.");
            logFactory = null;
        } catch (Exception e) {
            System.err.println("Unexpected error during initialization: " + e.getMessage());
            System.err.println("Falling back to console-only logging.");
            logFactory = null;
        }
    }
}
