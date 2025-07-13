package com.github.rhys_h_walker;

/**
 * Class that provides the primary functionality of the logging, colours are applied at output
 * All prints are in stdout (One error per line)
 * 
 * Colours:
 *      - miscellaneous: CYAN
 *      - info: BLUE
 *      - debug: YELLOW
 *      - progress: GREEN
 *      - error: RED
 * 
 * @author Rhys Walker
 * @since 13/07/2025
 */

import com.github.rhys_h_walker.LoggingLevel;
import com.github.rhys_h_walker.ANSI;

public class Logger {
    
    public static LoggingLevel loggingLevel = LoggingLevel.NONE;

    public static void logmiscellaneous(String message) {
        if (loggingLevel == LoggingLevel.ALL) {
            System.out.println(ANSI.CYAN_BG + "Misc:" + ANSI.RESET + " " + ANSI.CYAN + message + ANSI.RESET);
        }
    }

    public static void loginfo(String message) {
        if (loggingLevel == LoggingLevel.ALL || loggingLevel == LoggingLevel.INFO) {
            System.out.println(ANSI.BLUE_BG + "Info:" + ANSI.RESET + " " + ANSI.BLUE + message + ANSI.RESET);
        }
    }

    public static void logdebug(String message) {
        if (loggingLevel == LoggingLevel.ALL || loggingLevel == LoggingLevel.INFO || loggingLevel == LoggingLevel.DEBUG) {
            System.out.println(ANSI.YELLOW_BG + "Debug:" + ANSI.RESET + " " + ANSI.YELLOW + message + ANSI.RESET);
        }
    }

    public static void logprogress(String message) {
        if (loggingLevel == LoggingLevel.ALL || loggingLevel == LoggingLevel.INFO || loggingLevel == LoggingLevel.DEBUG || loggingLevel == LoggingLevel.PROGRESS) {
            System.out.println(ANSI.GREEN_BG + "Progress:" + ANSI.RESET + " " + ANSI.GREEN + message + ANSI.RESET);
        }
    }

    public static void logerror(String message) {
        if (loggingLevel != LoggingLevel.NONE) {
            System.err.println(ANSI.RED_BG + "Error:" + ANSI.RESET + " " + ANSI.RED + message + ANSI.RESET);
        }
    }

}
