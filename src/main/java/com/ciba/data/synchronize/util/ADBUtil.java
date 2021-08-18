package com.ciba.data.synchronize.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * @author songzi
 * @date 2021/8/13
 */
public class ADBUtil {

    public static boolean isEnableAdb(Context context) {
        try {
            boolean enableAdb = (Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 0) > 0);
            return enableAdb;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * APP是否是否Debug状态
     *
     * @return
     */
    public static boolean isApkInDebug(Context context) {
        try {
            if (context == null) {
                return true;
            }
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否是抓包模式下
     */
    public static boolean isProxy() {
        String proxyHost;
        int proxyPort;
        try {
            proxyHost = System.getProperty("http.proxyHost");
            String portStr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
        } catch (Exception e) {
            return true;
        }
        return !TextUtils.isEmpty(proxyHost) && proxyPort != -1;
    }

    /**
     * 是否是VPN
     *
     * @return
     */
    public static boolean isVPN(Context context) {
        try {
            if (context == null) {
                return false;
            }
            Context mContext = context;
            PackageManager pm = mContext.getPackageManager();
            boolean permission = (PackageManager.PERMISSION_GRANTED ==
                    pm.checkPermission("android.permission.ACCESS_NETWORK_STATE", "packageName"));
            if (permission) {
                ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    Network activeNetwork = connectivityManager.getActiveNetwork();
                    NetworkCapabilities caps = connectivityManager.getNetworkCapabilities(activeNetwork);
                    boolean vpnInUse = caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    return vpnInUse;
                } else {
                    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_VPN);
                    boolean isVpnConn = networkInfo == null ? false : networkInfo.isConnectedOrConnecting();
                    return isVpnConn;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
