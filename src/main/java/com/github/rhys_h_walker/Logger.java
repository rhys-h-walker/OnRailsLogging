package com.github.rhys_h_walker;

import java.util.HashMap;

import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.factories.LogFactory;
import com.github.rhys_h_walker.misc.ANSI;

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

    //
    // Overrriden initializeLogger which allows for different initialization configurations
    //

    /**
     * Initialize the logging logic
     * @param applicationName The name of the application we are logging for
     * @param level A HashMap detailing the level of logging
     */
    public static void initializeLogger(String appName, HashMap<LoggingType, Boolean> logVisibility) {
        commonConstructor(appName, logVisibility);
    }

    /**
     * Initialize the logging logic with the default value of =
     *      LoggingLevel.ERRORS
     * @param applicationName The name of the application we are logging for
     */
    public static void initializeLogger(String appName) {
        commonConstructor(appName, LoggingType.defaultVisibility());
    }

    /**
     * Change whether a log type is visibile or not
     * @param type the type of log to change visibility for
     * @param active a boolean describing the whether the log is active
     */
    public static void changeLogVisibility(LoggingType type, boolean active) {
        logVisibility.put(type, active);
    }

    public static boolean viewLogVisibility(LoggingType type) {
        return logVisibility.get(type);
    }

    /**
     * Get the name of the currently running application
     * @return String -> Currently running application
     */
    public static String getApplicationName() {
        return applicationName;
    }

    /**
     * Log a miscellaneous message
     * @param message
     * @return the timestamp of the log
     */
    public static String logmiscellaneous(String message) {
        String logMessage = "";
        if (logVisibility.get(LoggingType.MISCELLANEOUS)) {
            logMessage = (ANSI.CYAN_BG + "Misc:" + ANSI.RESET + " " + ANSI.CYAN + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.MISCELLANEOUS);
    }

    /**
     * Log a info message
     * @param message
     * @return the timestamp of the log
     */
    public static String loginfo(String message) {
        String logMessage = "";
        if (logVisibility.get(LoggingType.INFO)) {
            logMessage = (ANSI.BLUE_BG + "Info:" + ANSI.RESET + " " + ANSI.BLUE + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.INFO);
    }

    /**
     * Log a warning message
     * @param message
     * @return the timestamp of the log
     */
    public static String logwarn(String message) {
        String logMessage = "";
        if (logVisibility.get(LoggingType.WARN)) {
            logMessage = (ANSI.MAGENTA_BG + "Warn:" + ANSI.RESET + " " + ANSI.MAGENTA + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.WARN);
    }

    /**
     * Log a debug message
     * @param message
     * @return the timestamp of the log
     */
    public static String logdebug(String message) {
        String logMessage = "";
        if (logVisibility.get(LoggingType.DEBUG)) {
            logMessage = (ANSI.YELLOW_BG + "Debug:" + ANSI.RESET + " " + ANSI.YELLOW + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.DEBUG);
    }

    /**
     * Log a progress message
     * @param message
     * @return the timestamp of the log
     */
    public static String logprogress(String message) {
        String logMessage = "";
        if (logVisibility.get(LoggingType.PROGRESS)) {
            logMessage = (ANSI.GREEN_BG + "Progress:" + ANSI.RESET + " " + ANSI.GREEN + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.PROGRESS);
    }

    /**
     * Log an error message
     * @param message
     * @return the timestamp of the log
     */
    public static String logerror(String message) {
        String logMessage = "";

        if (logVisibility.get(LoggingType.ERROR)) {
            logMessage = (ANSI.RED_BG + "Error:" + ANSI.RESET + " " + ANSI.RED + message + ANSI.RESET);
        }

        return produceLog(message, logMessage, LoggingType.ERROR);
    }

    //
    // Private methods used by the Logger class
    //

    /**
     * Output a log message and create the log in memory
     * @param message The message formatted with colours to be output
     * @param printableMessage The message formatted with ANSI codes
     * @param level The logging level for creation of the log in memory
     * @return the timestamp of the log, null if log factory not set, or LoggingLevel does not allow its outpu
     */
    private static String produceLog(String message, String printableMessage, LoggingType type) {
        String timestamp = null;

        // So long as we are set then output
        if (logFactory != null) {
            // Output to file regardless of logging level
            timestamp = logFactory.createNewLog(message, type);

            // Message will be empty if the logging level does not allow its output
            if (!printableMessage.equals("")){
                System.out.println("[" + timestamp + "] " + printableMessage);
            }
        } else {
            System.err.println("Log factory not set, most likely no initialize call was made\n" + message);
        }

        return timestamp;
    }

    /**
     * Run common methods featured in other constructors
     * @param appName The name of the application
     * @param customisedVisibility The visibility of each log
     */
    private static void commonConstructor(String appName, HashMap<LoggingType, Boolean> customisedVisibility) {

        logFactory = new LogFactory(appName);
        applicationName = appName;
        logVisibility = customisedVisibility;
        
    }
}
