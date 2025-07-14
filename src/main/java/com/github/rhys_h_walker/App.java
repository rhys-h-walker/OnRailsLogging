package com.github.rhys_h_walker;

import com.github.rhys_h_walker.*;
import com.github.rhys_h_walker.core_enums.LoggingLevel;
import com.github.rhys_h_walker.core_enums.LoggingType;
import com.github.rhys_h_walker.core_objects.Log;
import com.github.rhys_h_walker.factories.LogFactory;

public class App {
    public static void main(String[] args){
        // Logger.loggingLevel = LoggingLevel.ERRORS;

        // Logger.logerror("THIS IS an Error");

        // Logger.loggingLevel = LoggingLevel.PROGRESS;

        // Logger.logprogress("This is a progress message");

        // Logger.loggingLevel = LoggingLevel.INFO;

        // Logger.loginfo("This is an info message");

        // Logger.loggingLevel = LoggingLevel.DEBUG;

        // Logger.logdebug("This is a debug message");

        // Logger.loggingLevel = LoggingLevel.ALL;

        // Logger.logmiscellaneous("This is a miscellaneous message");

        LogFactory logFac = new LogFactory("testing_application");

        logFac.createNewLog("This is a log that will be output", LoggingType.ERROR);
        logFac.createNewLog("Some other ERROR", LoggingType.INFO);
    }
}
