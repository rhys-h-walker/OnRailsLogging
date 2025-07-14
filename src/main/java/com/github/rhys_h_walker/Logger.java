package com.github.rhys_h_walker;

import com.github.rhys_h_walker.core_enums.LoggingLevel;
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
    
    private static LoggingLevel loggingLevel = LoggingLevel.NONE;
    public static LogFactory logFactory;


    //
    // Overrriden initializeLogger which allows for different initialization configurations
    //

    /**
     * Initialize the logging logic
     * @param applicationName The name of the application we are logging for
     * @param level The level at which the logger is being set
     */
    public static void initializeLogger(String applicationName, LoggingLevel level) {
        logFactory = new LogFactory(applicationName);
        loggingLevel = level;
    }

    /**
     * Initialize the logging logic with the default value of =
     *      LoggingLevel.ERRORS
     * @param applicationName The name of the application we are logging for
     */
    public static void initializeLogger(String applicationName) {
        logFactory = new LogFactory(applicationName);
        loggingLevel = LoggingLevel.ERRORS;
    }

    /**
     * Log a miscellaneous message
     * @param message
     */
    public static void logmiscellaneous(String message) {
        if (loggingLevel == LoggingLevel.ALL) {
            System.out.println(ANSI.CYAN_BG + "Misc:" + ANSI.RESET + " " + ANSI.CYAN + message + ANSI.RESET);
        }

        if (logFactory != null) {
            logFactory.createNewLog(message, LoggingType.MISCELLANEOUS);
        }
    }

    /**
     * Log a info message
     * @param message
     */
    public static void loginfo(String message) {
        if (loggingLevel == LoggingLevel.ALL || loggingLevel == LoggingLevel.INFO) {
            System.out.println(ANSI.BLUE_BG + "Info:" + ANSI.RESET + " " + ANSI.BLUE + message + ANSI.RESET);
        }

        if (logFactory != null) {
            logFactory.createNewLog(message, LoggingType.INFO);
        }
    }

    /**
     * Log a warning message
     * @param message
     */
    public static void logwarn(String message) {
        if (loggingLevel == LoggingLevel.ALL || loggingLevel == LoggingLevel.INFO) {
            System.out.println(ANSI.MAGENTA_BG + "Warn:" + ANSI.RESET + " " + ANSI.MAGENTA + message + ANSI.RESET);
        }

        if (logFactory != null) {
            logFactory.createNewLog(message, LoggingType.WARN);
        }
    }

    /**
     * Log a debug message
     * @param message
     */
    public static void logdebug(String message) {
        if (loggingLevel == LoggingLevel.ALL || loggingLevel == LoggingLevel.INFO || loggingLevel == LoggingLevel.DEBUG) {
            System.out.println(ANSI.YELLOW_BG + "Debug:" + ANSI.RESET + " " + ANSI.YELLOW + message + ANSI.RESET);
        }

        if (logFactory != null) {
            logFactory.createNewLog(message, LoggingType.DEBUG);
        }
    }

    /**
     * Log a progress message
     * @param message
     */
    public static void logprogress(String message) {
        if (loggingLevel == LoggingLevel.ALL || loggingLevel == LoggingLevel.INFO || loggingLevel == LoggingLevel.DEBUG || loggingLevel == LoggingLevel.PROGRESS) {
            System.out.println(ANSI.GREEN_BG + "Progress:" + ANSI.RESET + " " + ANSI.GREEN + message + ANSI.RESET);
        }

        if (logFactory != null) {
            logFactory.createNewLog(message, LoggingType.PROGRESS);
        }
    }

    /**
     * Log an error message
     * @param message
     */
    public static void logerror(String message) {
        if (loggingLevel != LoggingLevel.NONE) {
            System.err.println(ANSI.RED_BG + "Error:" + ANSI.RESET + " " + ANSI.RED + message + ANSI.RESET);
        }

        if (logFactory != null) {
            logFactory.createNewLog(message, LoggingType.ERROR);
        }
    }
}
