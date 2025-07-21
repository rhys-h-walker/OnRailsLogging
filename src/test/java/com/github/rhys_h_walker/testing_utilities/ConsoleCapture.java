package com.github.rhys_h_walker.testing_utilities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A simple console output capture class
 */

public class ConsoleCapture {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    public void startCapture() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    public void stopCapture() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    public String getOutput() {
        return outContent.toString();
    }
    
    public String getErrorOutput() {
        return errContent.toString();
    }
    
    public void clearCapture() {
        outContent.reset();
        errContent.reset();
    }
}