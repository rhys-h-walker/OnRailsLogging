package com.github.rhys_h_walker;

import com.github.rhys_h_walker.core_enums.LoggingLevel;
import com.github.rhys_h_walker.Logger;

public class App {
    public static void main(String[] args){

        Logger.initializeLogger("test_application", LoggingLevel.ALL);
        Logger.logerror("THIS IS an Error");
        Logger.logprogress("This is a progress message");
        Logger.loginfo("This is an info message");
        Logger.logdebug("This is a debug message");
        Logger.logmiscellaneous("This is a miscellaneous message");
        
    }
}
