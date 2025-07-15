package com.github.rhys_h_walker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.rhys_h_walker.core_enums.LoggingLevel;
import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.misc.TimestampDecoder;

/**
 * A test class testing the output of logs to a file
 */

public class TestFileOutput {

    @BeforeAll
    public static void startup() {
        // Startup a new application for this file and set logging to NONE
        // No output to console in this test is required
        Logger.initializeLogger("TestFileOutput", LoggingLevel.NONE);
    }

    @Test
    public void testErrorLogs() {
        // Create the error log
        String errorMessage = "This is a sample error message";
        String timestamp = Logger.logerror(errorMessage);

        // Locate and assert the log file exists
        File logFile = TimestampDecoder.timestampToFile(timestamp);
        assertTrue(logFile.exists());

        // Read the log file
        ArrayList<String> fileContents = FileManagement.readLogFile(logFile);

        // Assert that the file contains the line given above with the time stamp and error code
        assertTrue(Helpers.isStringInList(Helpers.formatALine(timestamp, LoggingType.ERROR, errorMessage), fileContents));
    }
    
}
