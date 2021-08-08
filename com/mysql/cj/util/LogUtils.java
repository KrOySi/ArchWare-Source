/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.util;

import com.mysql.cj.util.Util;

public class LogUtils {
    public static final String CALLER_INFORMATION_NOT_AVAILABLE = "Caller information not available";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int LINE_SEPARATOR_LENGTH = LINE_SEPARATOR.length();

    public static String findCallingClassAndMethod(Throwable t) {
        int endOfLine;
        String stackTraceAsString = Util.stackTraceToString(t);
        String callingClassAndMethod = CALLER_INFORMATION_NOT_AVAILABLE;
        int endInternalMethods = Math.max(Math.max(stackTraceAsString.lastIndexOf("com.mysql.cj"), stackTraceAsString.lastIndexOf("com.mysql.cj.core")), stackTraceAsString.lastIndexOf("com.mysql.cj.jdbc"));
        if (endInternalMethods != -1 && (endOfLine = stackTraceAsString.indexOf(LINE_SEPARATOR, endInternalMethods)) != -1) {
            int nextEndOfLine = stackTraceAsString.indexOf(LINE_SEPARATOR, endOfLine + LINE_SEPARATOR_LENGTH);
            String string = callingClassAndMethod = nextEndOfLine != -1 ? stackTraceAsString.substring(endOfLine + LINE_SEPARATOR_LENGTH, nextEndOfLine) : stackTraceAsString.substring(endOfLine + LINE_SEPARATOR_LENGTH);
        }
        if (!callingClassAndMethod.startsWith("\tat ") && !callingClassAndMethod.startsWith("at ")) {
            return "at " + callingClassAndMethod;
        }
        return callingClassAndMethod;
    }
}

