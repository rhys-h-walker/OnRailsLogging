package com.github.rhys_h_walker.core_objects;

import com.github.rhys_h_walker.core_enums.LoggingType;

/**
 * Representation of a Log in memory, not used for creating and outputing
 * Will be used for viewing logs that exist in memory
 * 
 * BIG NOTE:
 *      Currently not used by the program, this is retained for future use
 */

public class Log {
    
    private String id;
    private String timestamp;
    private LoggingType logType;
    private String message;

    /**
     * Create a new log object on the fly and outputs correctly to the console
     * @param logType The type of log that is being used LoggingType enum
     * @param message The message to be associated with the log
     * @param id The lineno that the log appears on in the log file (Pre appended with leading 0s if necessary)
     * @param timestamp The timestamp given in the form yyyy-MM-dd-HH:mm:ss
     */
    public Log(LoggingType logType, String message, String id, String timestamp) {
        // Set message and logging type
        this.logType = logType;
        this.message = message;
        this.id = id;
        this.timestamp = timestamp;
    }

    /**
     * Get the ID String for this log
     * @return The ID of the log
     */
    public String getId() {
        return id;
    }

    /**
     * Get the timestamp associated with this log
     * @return The timestamp String to use, format of (YYYY-MM-DD-HH:MM:SS)
     */
    public String getTimestamp() {
        return timestamp.toString();
    }

    /**
     * Get the logging type this log is a member of
     * @return The logging type
     */
    public LoggingType getLoggingType(){
        return logType;
    }

    /**
     * Get the logging message that is associated with this log
     * @return The logging message
     */
    public String getLogMessage() {
        return message;
    }
}
