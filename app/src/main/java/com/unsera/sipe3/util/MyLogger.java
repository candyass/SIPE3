package com.unsera.sipe3.util;

import android.util.Log;

public class MyLogger {

    private static final String LOG_NAME = "kopi";

    public static void log(String pesan) {
        Log.d(LOG_NAME, pesan);
    }
}
