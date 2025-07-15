# OnRailsLogger

Welcome to OnRailsLogger a simple logging application which can fit right into your codebase. OnRails features total of 6 different log types, and 6 different logging levels. The application is simple requiring one initialization step at your codes entrypoint and then just report your error.

**This is a simple Logger, it is not safe to be used concurrently, it will not help you figure out the last interleaving of code if you do run it as so. Concurrent access is not restricted.**

Logs can be found in your user.home directory under OnRailsLogging and then your application name.

## Quick notes

The logger is still in development. Simple things you may expect to exist may not at this point. They will come soon, checkout the roadmap at the bottom which details the next features to come.

## How to use

To use the logger you must first decide how you wish it to work. Currently there are three options:
- Console reporting
- Logging to file
- Both of the above

To just log to a console ignore the initialization step. To just output to file set LoggingLevel.NONE in initialization.

```Java
Logger.initializeLogger("applicationName");

// Or

Logger.initializeLogger("applicationName", LoggingLevel.ALL);

// Then

Logger.logerror("This is an Error message");
Logger.logprogress("This is a progress message");
Logger.loginfo("This is an info message");
Logger.logwarn("This is a warning message");
Logger.logdebug("This is a debug message");
Logger.logmiscellaneous("This is a miscellaneous message");
```

The output file will look like this:
```
[2025-07-14-23:15:16] ERROR: This is an an Error message
[2025-07-14-23:15:16] PROGRESS: This is a progress message
[2025-07-14-23:15:16] INFO: This is an info message
[2025-07-14-23:15:16] WARN: This is an warning message
[2025-07-14-23:15:16] DEBUG: This is a debug message
[2025-07-14-23:15:16] MISCELLANEOUS: This is a miscellaneous message
```

When printing to console the outputs have colours like so: <br>
![Image showing console highlighting](assets/TerminalOutput.png)

## Roadmap

- Add more customisation:
    - Adjust logging level dynamically
    - Allow for logging to be more fine grained with level
    - Allow for colour formatting
- Make it a dependency in Maven
- Make a Log viewer.
- Add tests in Junit
