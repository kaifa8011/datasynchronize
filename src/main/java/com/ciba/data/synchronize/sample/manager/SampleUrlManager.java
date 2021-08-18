package com.ciba.data.synchronize.sample.manager;

import android.text.TextUtils;

import com.ciba.data.synchronize.util.SPUtil;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/6
 */
public class SampleUrlManager {
    /**
     * 正式域名
     */
    private static final String DEFAULT_BASE_URL = "http://dc.admobile.top";
    /**
     * 测试域名
     */
//    private static final String DEFAULT_BASE_URL = "http://test.dc.admobile.top";

    /**
     * 获取token和machineId信息
     * @return
     */
    public static String getDeviceDataUrl() {
        String host = getRequestHost();
        String url = host + "/log/getToken.json";
        return url;
    }

    /**
     * 上传安装列表信息
     * @return
     */
    public static String getInstallDataUrl() {
        String host = getRequestHost();
        String url = host + "/log/inrlog.json";
        return url;
    }

    /**
     * 上传配置扫描目录包名信息
     * @return
     */
    public static String getInstallPackageUrl() {
        String host = getRequestHost();
        String url = host + "/log/config/ppl";
        return url;
    }

    /**
     * 获取ipv6信息，目前业务删除
     * @return
     */
    @Deprecated
    public static String getIPV6PostUrl() {
        String host = getRequestHost();
        String url = host + "/log/ip/log";
        return url;
    }

    /**
     * 上报进程信息，目前业务删除
     * @return
     */
    @Deprecated
    public static String getStartUpDataUrl() {
        String host = getRequestHost();
        String url = host + "/log/strlog.json";
        return url;
    }

    /**
     * 上报崩溃信息，目前业务删除
     * @return
     */
    @Deprecated
    public static String getCrashUrl() {
        String host = getRequestHost();
        String url = host + "/log/secOplog.json";
        return url;
    }

    /**
     * 上报应用生命周期信息，目前业务删除
     * @return
     */
    @Deprecated
    public static String getActivityLifeUrl() {
        String host = getRequestHost();
        String url = host + "/log/secOplog.json";
        return url;
    }

    /**
     * 上报点击事件信息，目前业务删除
     * @return
     */
    @Deprecated
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
