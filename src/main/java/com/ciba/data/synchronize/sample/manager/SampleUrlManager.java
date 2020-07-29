package com.ciba.data.synchronize.sample.manager;

import com.ciba.data.synchronize.entity.SampleUrl;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/6
 */
public class SampleUrlManager {
    private static final String SAMPLE_BASE_URL = "http://dc.114dev.com/";
    //    private static final String SAMPLE_DEVICE_DATA_URL = SAMPLE_BASE_URL + "/log/secMalog.json";
    private static final String SAMPLE_DEVICE_DATA_URL = SAMPLE_BASE_URL + "/log/getToken.json";
    private static final String SAMPLE_INSTALL_DATA_URL = SAMPLE_BASE_URL + "/log/inrlog.json";
    private static final String SAMPLE_OPERATION_DATA_URL = SAMPLE_BASE_URL + "/log/secOplog.json";
    private static final String SAMPLE_PROCESS_DATA_URL = SAMPLE_BASE_URL + "/log/strlog.json";
    private static final String IPV6_INFO_POST_URL = SAMPLE_BASE_URL + "log/ip/log";
    private static SampleUrlManager instance;
    private SampleUrl sampleUrl;

    private SampleUrlManager() {
    }

    public static SampleUrlManager getInstance() {
        if (instance == null) {
            synchronized (SampleUrlManager.class) {
                if (instance == null) {
                    instance = new SampleUrlManager();
                }
            }
        }
        return instance;
    }

    public String getCrashUrl() {
        checkSampleUrl();
        return sampleUrl == null ? null : sampleUrl.getCrashDataUrl();
    }

    public String getDeviceDataUrl() {
        checkSampleUrl();
        return sampleUrl == null ? null : sampleUrl.getDeviceDataUrl();
    }

    public String getInstallDataUrl() {
        checkSampleUrl();
        return sampleUrl == null ? null : sampleUrl.getInstallDataUrl();
    }

    public String getStartUpDataUrl() {
        checkSampleUrl();
        return sampleUrl == null ? null : sampleUrl.getStartUpDataUrl();
    }

    public String getActivityLifeUrl() {
        checkSampleUrl();
        return sampleUrl == null ? null : sampleUrl.getActivityLifeUrl();
    }

    public String getMotionEventUrl() {
        checkSampleUrl();
        return sampleUrl == null ? null : sampleUrl.getMotionEventUrl();
    }

    public String getIPV6PostUrl() {
        return IPV6_INFO_POST_URL;
    }

    private void checkSampleUrl() {
        if (sampleUrl == null) {
            sampleUrl = new SampleUrl(SAMPLE_OPERATION_DATA_URL
                    , SAMPLE_DEVICE_DATA_URL
                    , SAMPLE_INSTALL_DATA_URL
                    , SAMPLE_PROCESS_DATA_URL
                    , SAMPLE_OPERATION_DATA_URL
                    , SAMPLE_OPERATION_DATA_URL);
        }
    }
}
