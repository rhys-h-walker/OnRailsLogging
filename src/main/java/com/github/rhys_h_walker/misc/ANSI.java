package com.github.rhys_h_walker.misc;

/**
 * Static references to ANSI colour codes
 * 
 * @author Rhys Walker
 * @since 23/04/2025
 */

public class ANSI {

    private ANSI() {
        // Private constructor so is uninstantiatable
    }
    
    /**
     * Reset ANSI code
     */
    public static final String RESET = "\u001B[0m";

    /**
     * Black text code
     */
    public static final String BLACK = "\u001B[30m";
    /**
     * Red text code
     */
    public static final String RED = "\u001B[31m";
    /**
     * Green text code
     */
    public static final String GREEN = "\u001B[32m";
    /**
     * Yellow text code
     */
    public static final String YELLOW = "\u001B[33m";
    /**
     * Blue text code
     */
    public static final String BLUE = "\u001B[34m";
    /**
     * Magenta text code
     */
    public static final String MAGENTA = "\u001B[35m";
    /**
     * Cyan text code
     */
    public static final String CYAN = "\u001B[36m";
    /**
     * White text code
     */
    public static final String WHITE = "\u001B[37m";

    /**
     * Black background text code
     */
    public static final String BLACK_BG = "\u001B[40m";
    /**
     * Red background text code
     */
    public static final String RED_BG = "\u001B[41m";
    /**
     * Green background text code
     */
    public static final String GREEN_BG = "\u001B[42m";
    /**
     * Yellow background text code
     */
    public static final String YELLOW_BG = "\u001B[43m";
    /**
     * Blue background text code
     */
    public static final String BLUE_BG = "\u001B[44m";
    /**
     * Magenta background text code
     */
    public static final String MAGENTA_BG = "\u001B[45m";
    /**
     * Cyan background text code
     */
    public static final String CYAN_BG = "\u001B[46m";
    /**
     * White background text code
     */
    public static final String WHITE_BG = "\u001B[47m";

}