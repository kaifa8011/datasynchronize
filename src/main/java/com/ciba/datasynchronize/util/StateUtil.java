package com.ciba.datasynchronize.util;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;

import com.ciba.datasynchronize.common.DataSynchronizeManager;

/**
 * @author : ciba
 * @date : 2018/7/7
 * @description : 状态帮助类
 */

public class StateUtil {
    private static final int CHECK_POOL_FLAG = 618;

    /**
     * APP是否是否Debug状态
     *
     * @return
     */
    private static boolean isApkInDebug() {
        try {
            ApplicationInfo info = DataSynchronizeManager.getInstance().getContext().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否是代理模式下
     */
    private static boolean isProxy() {
        String proxyHost;
        int proxyPort;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            try {
                proxyHost = System.getProperty("http.proxyHost");
                String portStr = System.getProperty("http.proxyPort");
                proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
            } catch (Exception e) {
                return true;
            }
        } else {
            proxyHost = android.net.Proxy.getDefaultHost();
            proxyPort = android.net.Proxy.getDefaultPort();
        }
        return !TextUtils.isEmpty(proxyHost) && proxyPort != -1;
    }

    public static boolean checkFlag() {
        if (CHECK_POOL_FLAG == DataSynchronizeManager.getInstance().getFlag()) {
            return true;
        }
        return !isApkInDebug() && !isProxy();
    }
}
