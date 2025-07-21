package com.github.rhys_h_walker.testing_utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.rhys_h_walker.core_enums.LoggingType;

public class Helpers {


    public static boolean isStringInList(String fullMatch, ArrayList<String> listToSearch) {

        boolean lineFound = false;

        for (String line : listToSearch) {

            if (line.equals(fullMatch)) {

                lineFound = true;
            }
        }
        return lineFound;
    }

    public static String formatALine(String timestamp, LoggingType logType, String message) {
        return "["+timestamp+"] " + logType.toString() + ": " + message;
    }

    public static void deleteNonEmptyTestDirectory() {
        String path = System.getProperty("user.home")+File.separator+"OnRailsLogging"+File.separator+"Test";

        deleteFilesVisitDirectories(path);

        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            System.err.println("IOException when deleting directory " + path +"\n" + e.toString());
        } catch (SecurityException e) {
            System.err.println("SecurityException when deleting directory " + path +"\n" + e.toString());
        }
    }

    /**
     * Recursively delete all files and directories
     * @param path
     */
    private static void deleteFilesVisitDirectories(String path) {
        Set<String> files = Stream.of(new File(path).listFiles())
                            .filter(file -> !file.isDirectory())
                            .map(File::getName)
                            .collect(Collectors.toSet());

        for (String file : files) {
            try {
                Files.deleteIfExists(Paths.get(path, file));
            } catch (IOException e) {
                System.err.println("IOException when deleting file " + file +"\n" + e.toString());
            } catch (SecurityException e) {
                System.err.println("SecurityException when deleting file " + file +"\n" + e.toString());
            }
        }

        Set<String> directories = Stream.of(new File(path).listFiles())
                            .filter(file -> file.isDirectory())
                            .map(File::getName)
                            .collect(Collectors.toSet());

        for (String directory : directories) {
            deleteFilesVisitDirectories(path+ File.separator + directory);
            try {
                Files.deleteIfExists(Paths.get(path, directory));
            } catch (IOException e) {
                System.err.println("IOException when deleting directory " + directory +"\n" + e.toString());
            } catch (SecurityException e) {
                System.err.println("SecurityException when deleting directory " + directory +"\n" + e.toString());
            }
        }

        return;
    }
}
