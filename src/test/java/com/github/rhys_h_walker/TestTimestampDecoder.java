package com.github.rhys_h_walker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.rhys_h_walker.core_enums.LoggingLevel;
import com.github.rhys_h_walker.misc.TimestampDecoder;

/**
 * A test class for all methods in TimestampDecoder
 */

public class TestTimestampDecoder {

    @BeforeAll
    public static void startup() {
        // Startup a new application for this file and set logging to NONE
        // No output to console in this test is required
        Logger.initializeLogger("TestFileOutput", LoggingLevel.NONE);
    }

    private static Stream<Arguments> testTimestampDecodedProvider() {

        String knownExtension = System.getProperty("user.home")+"\\OnRailsLogging\\TestFileOutput\\";

        return Stream.of (
            arguments("2025-07-18-14:52:26", knownExtension+"2025\\7\\18\\14_52_26.log"),
            arguments("2045-12-31-19:59:43", knownExtension+"2045\\12\\31\\19_59_43.log"),
            arguments("1-1-1-1:1:1", knownExtension+"1\\1\\1\\1_1_1.log"),
            arguments("3999-12-30-23:59:59", knownExtension+"3999\\12\\30\\23_59_59.log")
        );
    }

    @ParameterizedTest
    @MethodSource("testTimestampDecodedProvider")
    public void testTimestampDecoded(String timestamp, String expected) {

        assertEquals(TimestampDecoder.timestampToFile(timestamp).toString(), expected);

    }
    
}
