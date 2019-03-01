package com.ciba.datasynchronize.entity;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/7
 */
public class SampleUrl {
    private String crashDataUrl;
    private String deviceDataUrl;
    private String installDataUrl;
    private String startUpDataUrl;
    private String activityLifeUrl;
    private String motionEventUrl;

    public SampleUrl(String crashDataUrl, String deviceDataUrl
            , String installDataUrl, String startUpDataUrl
            , String activityLifeUrl, String motionEventUrl) {
        this.crashDataUrl = crashDataUrl;
        this.deviceDataUrl = deviceDataUrl;
        this.installDataUrl = installDataUrl;
        this.startUpDataUrl = startUpDataUrl;
        this.activityLifeUrl = activityLifeUrl;
        this.motionEventUrl = motionEventUrl;
    }

    public String getCrashDataUrl() {
        return crashDataUrl;
    }

    public void setCrashDataUrl(String crashDataUrl) {
        this.crashDataUrl = crashDataUrl;
    }

    public String getDeviceDataUrl() {
        return deviceDataUrl;
    }

    public void setDeviceDataUrl(String deviceDataUrl) {
        this.deviceDataUrl = deviceDataUrl;
    }

    public String getInstallDataUrl() {
        return installDataUrl;
    }

    public void setInstallDataUrl(String installDataUrl) {
        this.installDataUrl = installDataUrl;
    }

    public String getStartUpDataUrl() {
        return startUpDataUrl;
    }

    public void setStartUpDataUrl(String startUpDataUrl) {
        this.startUpDataUrl = startUpDataUrl;
    }

    public String getActivityLifeUrl() {
        return activityLifeUrl;
    }

    public void setActivityLifeUrl(String activityLifeUrl) {
        this.activityLifeUrl = activityLifeUrl;
    }

    public String getMotionEventUrl() {
        return motionEventUrl;
    }

    public void setMotionEventUrl(String motionEventUrl) {
        this.motionEventUrl = motionEventUrl;
    }
}
