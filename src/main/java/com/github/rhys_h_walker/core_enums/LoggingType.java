package com.github.rhys_h_walker.core_enums;

import java.util.HashMap;

/**
 * Type labels for different log messages
 * Definitions located on the GitHub here:
 *      https://github.com/rhys-h-walker/OnRailsLogging/blob/main/README.md
 */

public enum LoggingType {
    ERROR,
    PROGRESS,
    DEBUG,
    INFO,
    WARN,
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
