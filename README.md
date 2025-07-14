# Logger

A simple console logger for Java. Features 5 different logging levels, all with their own ANSI colour formatted codes.

## Future work

- Log file, outputs
- Full customisation of which logs are on
- Colour customisation
- Logging sessions
- Log viewer

## Roadmap

1. Create a Log object which represents a specific individual log in memory. (Will have data such as:
        id = The timestamp plus line number that the object will be saved in
        timestamp = An exact timestamp in seconds from unix epoch
        type = What type of Log is the message (Error, Progress, etc)
        message = What is the log saying
    )
2. Create a log provider factory, creates a log based on the criteria given to it (
    This factory will have all logs written through it. The log is created and written to the file
)
3. Create a FileManagement tool which manages the stored logs (Logs will be stored in a directory structure as so (
    $AppData$
        |- OnRailsLogging
                    |- Year
                        |- Month
                            |- Day
                                |- filename.log

    file structure will be as follows:
        filename.log:
            hour_minute_second.log
        contents:
            [timestamp] Type: message
            [14/07/2025:21:00:00] ERROR: Database failed due to external reasons that are unknown
))
