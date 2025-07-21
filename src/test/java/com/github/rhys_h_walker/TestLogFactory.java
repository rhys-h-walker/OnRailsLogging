package com.github.rhys_h_walker;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.factories.LogFactory;
import com.github.rhys_h_walker.testing_utilities.Helpers;

/**
 * Generalised tests relating to the LogFactory
 */
public class TestLogFactory {

    private static String appName = "LogFactoryTests";

    @AfterAll
    static void cleanup() {
        Helpers.deleteNonEmptyTestDirectory(appName);
    }
    
    // Test that the Facotory is initialized after a name is passed
    @Test
    void testInstantiation() {
        LogFactory logFactory = new LogFactory(appName);

        assertTrue(logFactory.initialized());

        logFactory.cleanup();
    }

    @Test
    void testTimestampChanges() {
        LogFactory logFactory = new LogFactory(appName);
        assertTrue(logFactory.initialized());

        String timestamp = logFactory.createNewLog("Log1", LoggingType.ERROR);

        try {
            Thread.sleep(1001); // Make sure timestamps are different
        } catch (InterruptedException e) {
            System.err.println("Thread sleep has been interrupted, failing test");
            assertTrue(false);
        }
        
        String timestamp2 = logFactory.createNewLog("Log2", LoggingType.ERROR);

        assertNotEquals(timestamp, timestamp2);

        logFactory.cleanup();
    }

}
