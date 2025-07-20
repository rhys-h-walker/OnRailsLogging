package com.github.rhys_h_walker;

import com.github.rhys_h_walker.core_enums.LoggingType;

public class App {
    public static void main(String[] args) {
        Logger.initializeLogger("testApplication");

        Logger.logerror("This is an error message");

        Logger.changeLogVisibility(LoggingType.PROGRESS, true);
        Logger.logprogress("This is a progress message");
        Logger.loginfo("This is an info message");
        Logger.logwarn("This is a warning message");
        Logger.logdebug("This is a debug message");
        Logger.logmiscellaneous("This is a miscellaneous message");

    }
}
