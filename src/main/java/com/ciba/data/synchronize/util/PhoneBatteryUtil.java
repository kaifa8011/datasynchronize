package com.ciba.data.synchronize.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

/**
 *
 * @author songzi
 * @date 2021/8/13
 */
public class PhoneBatteryUtil {

    public static boolean isCharging(Context context) {
        try {
            if (context == null) {
                return false;
            }
            Intent batteryBroadcast = context.registerReceiver(null,
                    new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            boolean isCharging = batteryBroadcast.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) != 0;
            Log.d(PhoneBatteryUtil.class.getSimpleName(), "isCharging = " + isCharging);
            return isCharging;
        } catch (Exception e) {
            return false;
        }

    }

}
