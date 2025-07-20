package com.github.rhys_h_walker.testing_utilities;

import java.util.ArrayList;

import com.github.rhys_h_walker.core_enums.LoggingType;

public class Helpers {


    public static boolean isStringInList(String fullMatch, ArrayList<String> listToSearch) {

        boolean lineFound = false;

        for (String line : listToSearch) {

            if (line.equals(fullMatch)) {

                lineFound = true;
            }
        }
        return lineFound;
    }

    public static String formatALine(String timestamp, LoggingType logType, String message) {
        return "["+timestamp+"] " + logType.toString() + ": " + message;
    }
}
