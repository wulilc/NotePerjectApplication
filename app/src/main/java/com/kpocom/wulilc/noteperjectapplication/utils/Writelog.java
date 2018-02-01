package com.kpocom.wulilc.noteperjectapplication.utils;

import android.util.Log;

public class Writelog {

    private static boolean isDebug = false;

    private Writelog() {
    }

    public static void init(boolean debugMode) {
        isDebug = debugMode;
    }

    public static void d(String tag, String format, Object... args) {
        if (!isDebug) {
            return;
        }
        Log.d(tag, String.format(format, args));
    }

    public static void v(String tag, String format, Object... args) {
        if (!isDebug) {
            return;
        }
        Log.v(tag, String.format(format, args));
    }

    public static void w(String tag, String format, Object... args) {
        if (!isDebug) {
            return;
        }
        Log.w(tag, String.format(format, args));
    }

    public static void i(String tag, String format, Object... args) {
        if (!isDebug) {
            return;
        }
        Log.i(tag, String.format(format, args));
    }

    public static void e(String tag, String format, Object... args) {
        if (!isDebug) {
            return;
        }
        Log.e(tag, String.format(format, args));
    }

    public static void buffer(String tag, String msg, byte[] data, int offset, int count) {
        if (!isDebug) {
            return;
        }
        String str = " ";
        for (int i = 0; i < count; i++) {
            str += String.format("%02X ", data[offset + i]);
        }
        Log.d(tag, msg + str);
    }

    public static void buffer(String tag, String msg, byte[] data) {
        if (!isDebug) {
            return;
        }
        buffer(tag, msg, data, 0, (data != null) ? data.length : 0);
    }

    public static void exception(String tag, Exception e) {
        if (!isDebug)
            return;
        Log.e(tag, e.getClass().toString());
        StackTraceElement[] messages = e.getStackTrace();
        for (StackTraceElement t : messages) {
            Log.e(tag, t.toString());
        }
    }

}
