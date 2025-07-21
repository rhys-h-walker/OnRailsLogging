package com.github.rhys_h_walker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.misc.ANSI;
import com.github.rhys_h_walker.testing_utilities.ConsoleCapture;

import java.util.function.Function;
import java.util.stream.Stream;

class TestConsoleOutput {
    
    private ConsoleCapture consoleCapture;
    
    @BeforeEach
    void setUp() {
        consoleCapture = new ConsoleCapture();
        consoleCapture.startCapture();
        
        // Initialize logger for console-only mode (no file output)
        Logger.initializeLogger("Test", LoggingType.logVisibilityAllTrue(), true);
    }
    
    @AfterEach
    void tearDown() {
        consoleCapture.stopCapture();
        Logger.shutdown();
    }

    private static Stream<Arguments> ansiColorTestData() {
    return Stream.of(
        arguments("This is a sample error message", LoggingType.ERROR, 
                 (Function<String, String>) Logger::logerror, 
                 ANSI.RED_BG, ANSI.RED),
        arguments("This is a sample warn message", LoggingType.WARN, 
                 (Function<String, String>) Logger::logwarn, 
                 ANSI.MAGENTA_BG, ANSI.MAGENTA),
        arguments("This is a sample info message", LoggingType.INFO, 
                 (Function<String, String>) Logger::loginfo, 
                 ANSI.BLUE_BG, ANSI.BLUE),
        arguments("This is a sample debug message", LoggingType.DEBUG, 
                 (Function<String, String>) Logger::logdebug, 
                 ANSI.YELLOW_BG, ANSI.YELLOW),
        arguments("This is a sample progress message", LoggingType.PROGRESS, 
                 (Function<String, String>) Logger::logprogress, 
                 ANSI.GREEN_BG, ANSI.GREEN),
        arguments("This is a sample misc message", LoggingType.MISCELLANEOUS, 
                 (Function<String, String>) Logger::logmiscellaneous, 
                 ANSI.CYAN_BG, ANSI.CYAN)
        );
    }

    @ParameterizedTest
    @MethodSource("ansiColorTestData")
    void testErrorMessageFormat(String message, LoggingType logType, Function<String, String> logFunction, String BGCode, String CharCode) {        
        // When
        String timestamp = logFunction.apply(message);
        String output = consoleCapture.getOutput();
        
        // Then
        assertNotNull(timestamp, "Timestamp should not be null");
        assertTrue(output.contains("[" + timestamp + "]"), "Output should contain timestamp in brackets");
        assertTrue(output.contains(BGCode + logType.toString() +":" + ANSI.RESET), "Should contain" + BGCode + "background for LogType");
        assertTrue(output.contains(CharCode + message + ANSI.RESET), "Should contain" + CharCode + "text for message");
    }
    
    private static Stream<Arguments> testLogVisibilityDataProvider() {
        return Stream.of(
            arguments(LoggingType.MISCELLANEOUS, (Function<String, String>) Logger::logmiscellaneous, "MISC MESSAGE"),
            arguments(LoggingType.INFO, (Function<String, String>) Logger::loginfo, "INFO MESSAGE"),
            arguments(LoggingType.WARN, (Function<String, String>) Logger::logwarn, "WARN MESSAGE"),
            arguments(LoggingType.DEBUG, (Function<String, String>) Logger::logdebug, "DEBUG MESSAGE"),
            arguments(LoggingType.PROGRESS, (Function<String, String>) Logger::logprogress, "PROGRESS MESSAGE"),
            arguments(LoggingType.ERROR, (Function<String, String>) Logger::logerror, "ERROR MESSAGE")
        );
    }
    // Test that when true the log shows, then when false it does not show
    @ParameterizedTest
    @MethodSource("testLogVisibilityDataProvider")
    void testLogVisibility(LoggingType logType, Function<String, String> func, String message) {

        // Message should appear in console output
        func.apply(message);
        String output = consoleCapture.getOutput();
        assertTrue(output.contains(message));

        Logger.changeLogVisibility(logType, false);
        consoleCapture.clearCapture();

        // Message should no longer appear in console output
        func.apply(message);
        output = consoleCapture.getOutput();
        assertFalse(output.contains(message));

    }
}