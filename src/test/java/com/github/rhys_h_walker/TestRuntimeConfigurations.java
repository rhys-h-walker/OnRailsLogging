package com.github.rhys_h_walker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.rhys_h_walker.core_enums.LoggingType;

/**
 * A test class which tests the alteration of different runtime configurations
 * 
 * Initialization occurs per test, we are testing different initialization options
 * Ensure resources are cleaned up between tests
 */

public class TestRuntimeConfigurations {

    // Test that the default configuration is applied here
    @Test
    void testDefaultConfiguration() {
        Logger.initializeLogger("Test", true);

        HashMap<LoggingType, Boolean> defaultExpected = LoggingType.defaultVisibility();
        HashMap<LoggingType, Boolean> set = Logger.viewVisibilityMap();

        assertTrue(defaultExpected.equals(set));
        
        Logger.shutdown();
    }

    // Test that setting an application name works correctly
    @Test
    void testGetApplicationName() {
        String appName = "Test";
        Logger.initializeLogger(appName, true);

        assertTrue(appName.equals(Logger.getApplicationName()));

        Logger.shutdown();
    }

    // Test that shutdown acts as expected, change name and visibility of ERROR
    @Test
    void testShutdown() {
        String startName = "Test";
        String changedName = "Test1";

        assertNotEquals(startName, changedName);

        Logger.initializeLogger(startName, true);

        assertEquals(Logger.getApplicationName(), startName);
        assertTrue(Logger.viewLogVisibility(LoggingType.ERROR));

        Logger.shutdown();
        Logger.initializeLogger(changedName, LoggingType.logVisibilityAllFalse(), true);

        assertEquals(Logger.getApplicationName(), changedName);
        assertFalse(Logger.viewLogVisibility(LoggingType.ERROR));

        Logger.shutdown();
    }

    //
    // TESTS BELOW ARE PARAMETERISED
    //

    private static Stream<Arguments> checkMapSetDataProvider() {
        return Stream.of(
            arguments(LoggingType.defaultVisibility()),
            arguments(LoggingType.logVisibilityAllFalse()),
            arguments(LoggingType.logVisibilityAllTrue())
        );
    }

    // Test setting a custom HashMap works as expected
    @ParameterizedTest
    @MethodSource("checkMapSetDataProvider")
    void testDifferentmaps(HashMap<LoggingType, Boolean> mapToSet) {
        Logger.initializeLogger("Test", mapToSet, true);

        HashMap<LoggingType, Boolean> set = Logger.viewVisibilityMap();

        assertEquals(set, mapToSet);

        Logger.shutdown();

    }

    private static Stream<Arguments> testViewLogVisibilityDataProvider() {
        return Stream.of(
            arguments(LoggingType.MISCELLANEOUS, true),
            arguments(LoggingType.INFO, true),
            arguments(LoggingType.WARN, true),
            arguments(LoggingType.DEBUG, true),
            arguments(LoggingType.PROGRESS, true),
            arguments(LoggingType.ERROR, false)
        );
    }

    // Test that viewing a log matches the expected output and changing it matches
    @ParameterizedTest
    @MethodSource("testViewLogVisibilityDataProvider")
    void testViewAndAlterLogVisibility(LoggingType log, Boolean logSet) {

        Logger.initializeLogger("Test", true);

        assertTrue(Logger.viewLogVisibility(log).equals(!logSet));

        Logger.changeLogVisibility(log, logSet);

        assertTrue(Logger.viewLogVisibility(log).equals(logSet));

        Logger.shutdown();
    }
    
}
