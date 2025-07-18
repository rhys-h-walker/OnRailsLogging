package com.github.rhys_h_walker.misc;

/**
 * A class of general purpose utilities
 */

public class Utilities {
    
    /**
     * Remove all leading zeros from a String
     * @param toRemove The String to have zeros removed
     * @return The String without zeros
     */
    public static String removeLeadingZeros(String toRemove) {
        StringBuilder sb = new StringBuilder();

        String[] split = toRemove.split("");
        boolean hasHitNonZero = false; // After hitting first non 0 char we must be still append character
        for (String c : split) {
            if (!c.equals("0")) {
                hasHitNonZero = true;
                sb.append(c);
            }
            else if (hasHitNonZero) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
