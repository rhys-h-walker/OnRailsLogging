package com.github.rhys_h_walker.misc;

import java.io.File;

import com.github.rhys_h_walker.Logger;

/**
 * Static method to decode a String timestamp into various useful formats
 */

public class TimestampDecoder {

    /**
     * Resolve a timestamp into a File object for Java
     * This method assumes the timestamp given complies with the format
     *              "year-month-day-hour:minute:second"
     * No checking is done as to whether this is true
     * No checking is done whether it even exists
     * @param timestamp The timestamp to convert
     * @return A file object pointing to the log that is being located
     */
    public static File timestampToFile(String timestamp) {
        // A timestamp is given as so: "year-month-day-hour:minute:second"
        // A file is stored year/month/day/hour_minute_second.log

        String[] tsSplitOnDash = timestamp.split("-");
        String year = Utilities.removeLeadingZeros(tsSplitOnDash[0]);
        String month = Utilities.removeLeadingZeros(tsSplitOnDash[1]);
        String day = Utilities.removeLeadingZeros(tsSplitOnDash[2]);
        
        String[] time = tsSplitOnDash[3].split(":");
        String hour = Utilities.removeLeadingZeros(time[0]);
        String minute = Utilities.removeLeadingZeros(time[1]);
        String second = Utilities.removeLeadingZeros(time[2]);

        File path = new File(System.getProperty("user.home")+File.separator+"OnRailsLogging"+File.separator+Logger.getApplicationName()+File.separator+year+File.separator+month+File.separator+day+File.separator+hour+"_"+minute+"_"+second+".log");

        return path;
    }
}
