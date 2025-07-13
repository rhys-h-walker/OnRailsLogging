package com.github.rhys_h_walker;

public enum LoggingLevel {
    ALL, // Show Progress, Debug, Error, Info and Mics Messages
    INFO, // Show Progress, Debug, Error, and Info Messages
    DEBUG, // Show Progress, Debug, and Error Messages
    PROGRESS, // Show progress messages and Error Messages
    ERRORS, // Show only Error Messages
    NONE // Show no Messages
}
