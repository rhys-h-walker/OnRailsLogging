package com.github.rhys_h_walker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.rhys_h_walker.core_enums.LoggingType;

/**
 * Ensure that any methods associated with LoggingType work correctly
 */

public class TestLoggingType {


    private static Stream<Arguments> testVisibilityMapCorrectnessDataProvider() {
        // ArrayList represents those that should be True
        return Stream.of(
            arguments(LoggingType.errorOnlyHashMap(), new ArrayList<LoggingType>(List.of(LoggingType.ERROR))),
            arguments(LoggingType.logVisibilityAllFalse(), new ArrayList<LoggingType>()),
            arguments(LoggingType.logVisibilityAllTrue(), new ArrayList<LoggingType>(Arrays.asList(LoggingType.values())))
        );
    }
 
    // Check that the HashMaps being returned are correct
    @ParameterizedTest
    @MethodSource("testVisibilityMapCorrectnessDataProvider")
    void testVisibilityMapCorrectness(HashMap<LoggingType, Boolean> mapToCheck, ArrayList<LoggingType> shouldTrue) {
        
        for (LoggingType key : mapToCheck.keySet()) {
            if (shouldTrue.contains(key)) {
                assertTrue(mapToCheck.get(key));
                continue;
            }

            assertFalse(mapToCheck.get(key));
        }

    }

    
}
