package com.github.rhys_h_walker;

import com.github.rhys_h_walker.core_enums.LoggingLevel;
import com.github.rhys_h_walker.factories.LogFactory;
import com.github.rhys_h_walker.misc.ANSI;

public class Logger {
    
    public static LoggingLevel loggingLevel = LoggingLevel.NONE;
    public static LogFactory logFactory;

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
