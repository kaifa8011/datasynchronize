package com.ciba.data.synchronize.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.ciba.data.synchronize.common.DataSynchronizeManager;

/**
 * @author ciba
 * @description 描述
 * @date 2019/1/16
 */
public class DataSynchronizeLog {
    private static boolean showLog = true;
    private static String packageName = "";

    public static void innerI(String log) {
        if (showLog) {
            Log.i(getTag(), log + "");
        }
    }

    public static void setShowLog(boolean log) {
        showLog = log;
    }

    private static String getTag() {
        if (TextUtils.isEmpty(packageName)) {
            Context context = DataSynchronizeManager.getInstance().getContext();
            if (context != null) {
                try {
                    packageName = context.getPackageName();
                } catch (Exception e) {
                }
            }
        }
        return packageName;
    }
}
