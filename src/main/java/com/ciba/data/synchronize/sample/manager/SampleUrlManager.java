package com.ciba.data.synchronize.sample.manager;

import android.text.TextUtils;

import com.ciba.data.synchronize.util.SPUtil;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/6
 */
public class SampleUrlManager {

    private static final String DEFAULT_BASE_URL = "http://dc.admobile.top";

    public static String getDeviceDataUrl() {
        String host = getRequestHost();
        String url = host + "/log/getToken.json";
        return url;
    }

    public static String getInstallDataUrl() {
        String host = getRequestHost();
        String url = host + "/log/inrlog.json";
        return url;
    }

    public static String getIPV6PostUrl() {
        String host = getRequestHost();
        String url = host + "/log/ip/log";
        return url;
    }

    public static String getStartUpDataUrl() {
        String host = getRequestHost();
        String url = host + "/log/strlog.json";
        return url;
    }


    public static String getInstallPackageUrl() {
        String host = getRequestHost();
        String url = host + "/log/config/ppl";
        return url;
    }

    public static String getCrashUrl() {
        String host = getRequestHost();
        String url = host + "/log/secOplog.json";
        return url;
    }

    public static String getActivityLifeUrl() {
        String host = getRequestHost();
        String url = host + "/log/secOplog.json";
        return url;
    }

    public static String getMotionEventUrl() {
        String host = getRequestHost();
        String url = host + "/log/secOplog.json";
        return url;
    }


    private static String getRequestHost() {
        try {
            boolean initSuccessFlag = SPUtil.getHostInitSuccessFlag();
            if (!initSuccessFlag) {
                return DEFAULT_BASE_URL;
            }

            String dynamicDomain = SPUtil.getDynamicDomain();
            if (TextUtils.isEmpty(dynamicDomain)) {
                return DEFAULT_BASE_URL;
            }
            return dynamicDomain;
        } catch (Exception e) {
            return DEFAULT_BASE_URL;
        }

    }
}
