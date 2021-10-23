package com.ciba.data.synchronize.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

    public static String getTimeZone() {
        try {
            TimeZone tz = TimeZone.getDefault();
            return tz.getDisplayName(false, TimeZone.SHORT);
        } catch (AssertionError e) {
            // Workaround for a bug in Android that can cause crashes on Android 8.0 and 8.1
        } catch (Exception e) {
        }
        return "";
    }
}
