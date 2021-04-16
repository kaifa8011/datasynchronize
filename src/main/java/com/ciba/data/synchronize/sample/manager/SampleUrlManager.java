package com.ciba.data.synchronize.sample.manager;

import android.text.TextUtils;
import android.util.Log;

import com.ciba.data.synchronize.util.SPUtil;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/6
 */
public class SampleUrlManager {

    /**
     * 动态域名初始化成功标记
     */
    public static final String DYNAMIC_DOMAIN_INIT_SUCCESS_FLAG_KEY = "dynamic_domain_init_success_flag";
    /**
     * 动态域名key
     */
    public static final String DYNAMIC_DOMAIN_KEY = "dynamic_domain";

    private static final String DEFAULT_BASE_URL = "http://dc.114dev.com/";
    //    private static final String SAMPLE_OPERATION_DATA_URL = DEFAULT_BASE_URL + "log/secOplog.json";
//    private static final String SAMPLE_DEVICE_DATA_URL = DEFAULT_BASE_URL + "log/getToken.json";
//    private static final String SAMPLE_INSTALL_DATA_URL = DEFAULT_BASE_URL + "log/inrlog.json";
//    private static final String SAMPLE_PROCESS_DATA_URL = DEFAULT_BASE_URL + "log/strlog.json";
//    private static final String IPV6_INFO_POST_URL = DEFAULT_BASE_URL + "log/ip/log";

//    private SampleUrl sampleUrl;

//    private void checkSampleUrl() {
//        if (sampleUrl == null) {
//            sampleUrl = new SampleUrl(SAMPLE_OPERATION_DATA_URL
//                    , SAMPLE_DEVICE_DATA_URL
//                    , SAMPLE_INSTALL_DATA_URL
//                    , SAMPLE_PROCESS_DATA_URL
//                    , SAMPLE_OPERATION_DATA_URL
//                    , SAMPLE_OPERATION_DATA_URL);
//        }
//    }


    public static String getDeviceDataUrl() {
//        getDeviceDataUrl
        String host = getRequestHost();
        String url = host + "log/getToken.json";
        Log.e("wsong", "logext host url :" + url);
        return url;
    }

    public static String getInstallDataUrl() {
//        getInstallDataUrl();
        String host = getRequestHost();
        String url = host + "log/inrlog.json";
        return url;
    }

    public static String getIPV6PostUrl() {
//      IPV6_INFO_POST_URL;
        String host = getRequestHost();
        String url = host + "log/ip/log";
        return url;
    }

    public static String getStartUpDataUrl() {
//        getStartUpDataUrl();
        String host = getRequestHost();
        String url = host + "log/strlog.json";
        return url;
    }

    public static String getCrashUrl() {
//      getCrashDataUrl();
        String host = getRequestHost();
        String url = host + "log/secOplog.json";
        return url;
    }

    public static String getActivityLifeUrl() {
        //        getActivityLifeUrl();
        String host = getRequestHost();
        String url = host + "log/secOplog.json";
        return url;
    }

    public static String getMotionEventUrl() {
//       getMotionEventUrl();
        String host = getRequestHost();
        String url = host + "log/secOplog.json";
        return url;
    }


    private static String getRequestHost() {
        boolean initSuccessFlag = SPUtil.getBoolean(DYNAMIC_DOMAIN_INIT_SUCCESS_FLAG_KEY);
        if (!initSuccessFlag) {
            return DEFAULT_BASE_URL;
        }

        String dynamicDomain = SPUtil.getString(DYNAMIC_DOMAIN_KEY);
        if (TextUtils.isEmpty(dynamicDomain)) {
            return DEFAULT_BASE_URL;
        }
        return dynamicDomain;
    }
}
