package com.ciba.data.synchronize.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
    private static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTime() {
        return getTargetTime(DEFAULT_TIME_FORMAT, System.currentTimeMillis());
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTargetTime(String format, long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        return sdf.format(date);
    }
}
