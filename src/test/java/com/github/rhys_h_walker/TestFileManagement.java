package com.github.rhys_h_walker;

import java.io.File;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;


import com.github.rhys_h_walker.misc.Utilities;
import com.github.rhys_h_walker.testing_utilities.Helpers;

/**
 * Test general file output:
 *  - File writing
    - File location/creation
    - Directory structure creation
    - File content verification

    - Will implement file rotation
 */
public class TestFileManagement {

    private static Stream<Arguments> locateFileTestData() {

    File applicationDirectory = new File(System.getProperty("user.home")+File.separator+"OnRailsLogging/Test");
    
    return Stream.of(
        arguments(applicationDirectory, LocalDateTime.of(2004, 3, 30, 12, 12, 12)),
        arguments(applicationDirectory, LocalDateTime.of(2005, 8, 30, 10, 15, 50)),
        arguments(applicationDirectory, LocalDateTime.of(1976, 01, 16, 23, 10, 1)),
        arguments(applicationDirectory, LocalDateTime.of(1, 1, 1, 1, 1, 1)),
        arguments(applicationDirectory, LocalDateTime.of(9999, 12, 30, 23, 59, 59)),
        arguments(applicationDirectory, LocalDateTime.of(1976, 2, 21, 19, 25, 50)),
        arguments(applicationDirectory, LocalDateTime.of(2007, 12, 22, 19, 14, 44))
        );
    }

    @ParameterizedTest
    @MethodSource("locateFileTestData")
    void testLocateFile(File applicationDirectory, LocalDateTime timestamp) {

        FileManagement.locateFile(timestamp, applicationDirectory);

        // This is our expected directory
        File location = new File(Utilities.formatFilepathFromTimestamp(timestamp, applicationDirectory));

        // Creation of the file only happens on write to we can just check path
        assertTrue(location.getParentFile().exists());

    }

    @AfterAll
    static void cleanup() {
        // Remove application called Test
        Helpers.deleteNonEmptyTestDirectory();
    }
}
