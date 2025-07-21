package com.github.rhys_h_walker.core_enums;

import java.util.HashMap;

/**
 * Type labels for different log messages
 * Definitions located on the GitHub here:
 *      https://github.com/rhys-h-walker/OnRailsLogging/blob/main/README.md
 */

public enum LoggingType {
    /**
     * Logging of any error messages
     */
    ERROR,
    /**
     * Logging of any messages that signify progress of the system
     */
    PROGRESS,
    /**
     * Logging of any debuging information
     */
    DEBUG,
    /**
     * Logging of any general information about the system
     */
    INFO,
    /**
     * Logging of any warnings that the program may create
     */
    WARN,
    /**
     * Logging of miscelaneous messages that do not fit into any other category
     */
    MISCELLANEOUS;

    /**
     * Create a hashmap with all logging types set to true
     * @return A hashmap for log visibility with all True
     */
    public static HashMap<LoggingType, Boolean> logVisibilityAllTrue() {

        HashMap<LoggingType, Boolean> defaultLog = new HashMap<>();
        for (LoggingType type : LoggingType.values()) {
            defaultLog.put(type, true);
        }

        return defaultLog;

    }

    /**
     * Create a hashmap with all logging types set to false
     * @return A hashmap for log visibility with all false
     */
    public static HashMap<LoggingType, Boolean> logVisibilityAllFalse() {

        HashMap<LoggingType, Boolean> defaultLog = new HashMap<>();
        for (LoggingType type : LoggingType.values()) {
            defaultLog.put(type, false);
        }

        return defaultLog;

    }

    /**
     * The default hashmap that is created will only feature ERROR: True
     * @return Default HashMap to use
     */
    public static HashMap<LoggingType, Boolean> defaultVisibility() {
        HashMap<LoggingType, Boolean> defaultLog = new HashMap<>();
        for (LoggingType type : LoggingType.values()) {
            defaultLog.put(type, false);
        }

        defaultLog.put(LoggingType.ERROR, true);

        return defaultLog;
    }
}
