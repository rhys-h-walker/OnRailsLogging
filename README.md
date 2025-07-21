# OnRailsLogger

[![Build Status](https://github.com/rhys-h-walker/OnRailsLogging/actions/workflows/ci.yml/badge.svg)](https://github.com/rhys-h-walker/OnRailsLogging/actions/workflows/ci.yml)
[![](https://jitpack.io/v/rhys-h-walker/OnRailsLogging.svg)](https://jitpack.io/#rhys-h-walker/OnRailsLogging)
[![](https://img.shields.io/badge/JavaDoc-OnRailsLogging-blue)](https://rhys-h-walker.github.io/OnRailsLogging/apidocs/)


Welcome to OnRailsLogger a simple logging application which can fit right into your codebase. OnRails features total of 6 different log types, each of which is individually silenceable, or choose from three builtin configurations. The application is simple requiring one initialization step at your projects entrypoint and then just log whatever you need to.

Check out the [wiki](https://github.com/rhys-h-walker/OnRailsLogging/wiki) for more detailed information.

## Install
Installation into your project uses Jitpack, here is the maven example for:
```xml
<dependencies>
	<dependency>
	    <groupId>com.github.rhys-h-walker</groupId>
	    <artifactId>OnRailsLogging</artifactId>
	    <version>v1.1.2</version>
	</dependency>
<dependencies>

<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

## Minimal running version
```java
// initialize once
Logger.initializeLogger("applicationName", true);

// log anything you need
Logger.logerror("This is an Error message");
Logger.logprogress("This is a progress message");
Logger.loginfo("This is an info message");
Logger.logwarn("This is a warning message");
Logger.logdebug("This is a debug message");
Logger.logmiscellaneous("This is a miscellaneous message");
```

### Example output
![Image showing console highlighting](assets/TerminalOutput.png)