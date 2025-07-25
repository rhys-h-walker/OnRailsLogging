# Testing framework:
Below is a comprehensive list of what is tested for in the framework. Check to implement for upcoming test additions. Items in "On The Backburner" may be implemented at some point, they are not guarantees however.

## Completed
- Core:
    - Logging to file
    - Logging to console
    - Timestamp decoder
    - String in Array
    - Format a String
    - File location creation
    - Ensure console output matches currently set visibility
    - Null logging type handling
- Configuration:
    - Default visibility settings
    - GetApplicationName
    - Custom visibility maps
    - ChangeVisibility
    - ViewLogVisibility
    - Ensure shutdown and initialize restarts under a different name / different visibility
    - Empty application names
- LoggingType:
    - Test returned maps are correct
- LogFactory:
    - PrintWriter recreation, for timestamps
    - isInitialized status checking

## To implement
- Core:
    - File rotation behaviour - *still to implement*
- Configuration
    - Nothing for now!
- LoggingType:
    - Nothing for now!
- Error handling
    - Permission denied scenarios
    - Null configuration handling
    - Null configuration handling
    - System property access failures
    - Security exceptions
    - LogFactory initialization failures
    - PrintWriter creation failures
    - File locking Scenarios
    - Shutdown hook execution
- LogFactory
    - Constructor error handling
    - ErrorFlag management
- Input validation
    - Nothing for now!

## On the Backburner
- Integration Testing
    - End-to-end workflow, init->log->verify
    - File+console dual output verification
    - Console only mode
    - Fallback behaviour chains
- Thread safety - *Not of current concern*
    - Potentially will remain untested
- Cross-platform testing - *Not of current concern*
    - Potentially may remain untested
    - Path characters on (Windows/Unix/Mac)
    - Case sensitivity (Platform dependent)
    - Unicode characters
    - Path length limits