package com.tec.staffmanagementsystem.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionWriter extends PrintWriter {
    public ExceptionWriter(StringWriter stringWriter) {
        super(stringWriter);
    }
    private String wrapAroundWithNewlines(String stringWithoutNewlines) {
        return ("\n" + stringWithoutNewlines + "\n");
    }


    public String getExceptionAsString(Throwable throwable) {
        throwable.printStackTrace(this);

        String exception = super.out.toString();

        return (wrapAroundWithNewlines(exception));
    }
}

