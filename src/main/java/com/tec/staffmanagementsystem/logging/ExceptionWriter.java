package com.tec.staffmanagementsystem.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author tec
 */

/**
 * The class Exception writer extends print writer
 */
public class ExceptionWriter extends PrintWriter {

    /**
     *
     * Exception writer
     *
     * @param stringWriter  the string writer
     * @return public
     */
    public ExceptionWriter(StringWriter stringWriter) {

        super(stringWriter);
    }

    /**
     *
     * Wrap around with newlines
     *
     * @param stringWithoutNewlines  the string without newlines
     * @return String
     */
    private String wrapAroundWithNewlines(String stringWithoutNewlines) {

        return ("\n" + stringWithoutNewlines + "\n");
    }



    /**
     *
     * Gets the exception as string
     *
     * @param throwable  the throwable
     * @return the exception as string
     */
    public String getExceptionAsString(Throwable throwable) {

        throwable.printStackTrace(this);

        String exception = super.out.toString();

        return (wrapAroundWithNewlines(exception));
    }
}

