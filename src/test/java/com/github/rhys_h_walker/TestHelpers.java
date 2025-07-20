package com.github.rhys_h_walker;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.testing_utilities.Helpers;

/**
 * A class designed to help the Helpers class
 */

public class TestHelpers {

    private static Stream<Arguments> testFormatDataProvider() {
        return Stream.of (
            arguments("2025-07-18-14:52:26", LoggingType.ERROR, "test_error_1", "[2025-07-18-14:52:26] ERROR: test_error_1"),
            arguments("2025-07-18-14:52:26", LoggingType.PROGRESS, "test_error_2", "[2025-07-18-14:52:26] PROGRESS: test_error_2"),
            arguments("2025-07-18-14:52:26", LoggingType.DEBUG, "test_error_3", "[2025-07-18-14:52:26] DEBUG: test_error_3"),
            arguments("2025-07-18-14:52:26", LoggingType.INFO, "test_error_4", "[2025-07-18-14:52:26] INFO: test_error_4"),
            arguments("2025-07-18-14:52:26", LoggingType.WARN, "test_error_5", "[2025-07-18-14:52:26] WARN: test_error_5"),
            arguments("2025-07-18-14:52:26", LoggingType.MISCELLANEOUS, "test_error_6", "[2025-07-18-14:52:26] MISCELLANEOUS: test_error_6")
        );
    }
    
    @ParameterizedTest
    @MethodSource("testFormatDataProvider")
    public void testFormatALine(String timestamp, LoggingType logType, String message, String expected) {
        assertEquals(Helpers.formatALine(timestamp, logType, message), expected);
    }

    private static Stream<Arguments> testStringInListProvider() {

        ArrayList<String> randomLongList = new ArrayList<>(Arrays.asList(
            "[2025-07-18-14:52:26] ERROR: test_error_1",
            "Some more random text",
            "[2025-07-18-14:52:26] PROGRESS: test_error_2",
            "Extra text",
            "Even more random text",
            "Letters, letters, letters",
            "[2025-07-18-14:52:26] DEBUG: test_error_3",
            "[2025-07-18-14:52:26] INFO: test_error_4",
            "[2025-07-18-14:52:26] WARN: test_error_5",
            "[2025-07-18-14:52:26] MISCELLANEOUS: test_error_6"
        ));

        return Stream.of (
            arguments("[2025-07-18-14:52:26] ERROR: test_error_1", randomLongList),
            arguments("[2025-07-18-14:52:26] PROGRESS: test_error_2", randomLongList),
            arguments("[2025-07-18-14:52:26] DEBUG: test_error_3", randomLongList),
            arguments("[2025-07-18-14:52:26] INFO: test_error_4", randomLongList),
            arguments("[2025-07-18-14:52:26] WARN: test_error_5", randomLongList),
            arguments("[2025-07-18-14:52:26] MISCELLANEOUS: test_error_6", randomLongList)
        );
    }

    @ParameterizedTest
    @MethodSource("testStringInListProvider")
    public void testStringInList(String input, ArrayList<String> listToSearch) {
        assertTrue(Helpers.isStringInList(input, listToSearch));
    }

}
