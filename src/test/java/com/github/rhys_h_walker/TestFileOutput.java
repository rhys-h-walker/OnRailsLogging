package com.github.rhys_h_walker;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.misc.TimestampDecoder;
import com.github.rhys_h_walker.testing_utilities.Helpers;

/**
 * A test class testing the output of logs to a file
 */

public class TestFileOutput {

    @BeforeAll
    public static void startup() {
        // Create a test-specific directory in temp space (Allows functionality on CI)
        String tempDir = System.getProperty("java.io.tmpdir")+File.separator+"OnRailsLogging"+File.separator+"TestFileOutput";

        File testDir = new File(System.getProperty("user.home"), "OnRailsLogging"+File.separator+"TestFileOutput");

        testDir.mkdir();
        
        if (!testDir.exists()) {
            boolean created = new File(tempDir).mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create test directory: " + testDir.getAbsolutePath());
            }
            // Override user.home to point to our test directory
            System.setProperty("user.home", testDir.getParent());
        }
        
        // Startup a new application for this file and set logging to NONE
        Logger.initializeLogger("TestFileOutput", LoggingType.logVisibilityAllFalse(), false);
    }

    private static Stream<Arguments> loggingTypesDataProvider() {
        return Stream.of(
            arguments("This is a sample error message", LoggingType.ERROR, (Function<String, String>) Logger::logerror),
            arguments("This is a sample progress message", LoggingType.PROGRESS, (Function<String, String>) Logger::logprogress),
            arguments("This is a sample debug message", LoggingType.DEBUG, (Function<String, String>) Logger::logdebug),
            arguments("This is a sample info message", LoggingType.INFO, (Function<String, String>) Logger::loginfo),
            arguments("This is a sample warn message", LoggingType.WARN, (Function<String, String>) Logger::logwarn),
            arguments("This is a sample misc message", LoggingType.MISCELLANEOUS, (Function<String, String>) Logger::logmiscellaneous)
        );
    }

    @ParameterizedTest
    @MethodSource("loggingTypesDataProvider")
    void testAllLogTypes(String message, LoggingType type, Function<String, String> logFunction) {
        // Create the log
        String timestamp = logFunction.apply(message);

        // Locate and assert the log file exists
        File logFile = TimestampDecoder.timestampToFile(timestamp);
        assertTrue(logFile.exists());

        // Read the log file
        ArrayList<String> fileContents = FileManagement.readLogFile(logFile);

        // Assert that the formatted line is present in the file
        assertTrue(Helpers.isStringInList(Helpers.formatALine(timestamp, type, message), fileContents));
    }

    @AfterAll
    static void cleanup() {
        // Remove application called Test
        Logger.shutdown();
        Helpers.deleteNonEmptyTestDirectory("TestFileOutput");
    }
    
}
