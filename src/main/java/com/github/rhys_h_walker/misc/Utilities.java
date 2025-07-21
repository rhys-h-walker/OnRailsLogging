package com.github.rhys_h_walker.misc;

import java.io.File;
import java.time.LocalDateTime;

/**
 * A class of general purpose utilities
 */

public class Utilities {

    private static final String INVALID_FILENAME_CHARS_REGEX = "[<>:\"|?*\\\\/\\x00-\\x1F]";
    private static final String[] reservedNames = {
        "CON", "PRN", "AUX", "NUL",
        "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9",
        "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"
    };

    private Utilities() {
        // Private constructor so is uninstantiatable
    }
    
    /**
     * Remove all leading zeros from a String
     * @param toRemove The String to have zeros removed
     * @return The String without zeros
     */
    public static String removeLeadingZeros(String toRemove) {
        StringBuilder sb = new StringBuilder();

        String[] split = toRemove.split("");
        boolean hasHitNonZero = false; // After hitting first non 0 char we must be still append character
        for (String c : split) {
            if (!c.equals("0")) {
                hasHitNonZero = true;
                sb.append(c);
            }
            else if (hasHitNonZero) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Create a path from a timestamp and app directory
     * @param ts Timestamp to format fileString from
     * @param applicationDirectory The directory to begin searching from
     * @return String representing the path to a .log file
     */
    public static String formatFilepathFromTimestamp(LocalDateTime ts, File applicationDirectory) {
        return applicationDirectory.getAbsolutePath() +
                File.separator + ts.getYear() +
                File.separator + ts.getMonthValue() +
                File.separator + ts.getDayOfMonth() +
                File.separator + ts.getHour()+"_" +
                ts.getMinute() + "_"+
                ts.getSecond() + ".log";
    }

    /**
     * Clean a non empty String for use as a filename
     * REGEX USED = INVALID_FILENAME_CHARS_REGEX *Check Utilities.java*
     * Trailing dots and spaces removed
     * File name size limit imposed 100 chars
     * 
     * On error no alternative name will be given
     * 
     * @param filename The filename to clean
     * @return A cleaned version/null if errored
     */
    public static String cleanFileNameString(String filename) {

        String file_t = filename.trim();
        file_t = file_t.replaceAll(INVALID_FILENAME_CHARS_REGEX, "_");

        if (file_t.isEmpty()) {
            System.err.println("file_t is empty after all illegal chars removed");
            return null;
        }

        if (file_t.length() > 100) {
            file_t = file_t.substring(0, 100);
        }

        file_t = file_t.replaceAll("[. ]+$", "");

        if (file_t.isEmpty() || file_t.endsWith(".")) {
            System.err.println("file_t is empty after substring and dots removed chars removed");
            return null;
        }

        return file_t;
    }

}
