package com.twu.biblioteca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class HelperIO {
    public final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    public ByteArrayInputStream inContent;

    public HelperIO() {
        System.setOut(new PrintStream(outContent));
    }

    public void setIn(String data) {
        inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
    }

    public String getOutContent() {
        return this.outContent.toString();
    }

    public void restoreIO() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
