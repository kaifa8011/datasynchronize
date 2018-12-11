package com.ciba.datasynchronize.uploader;

import com.ciba.datasynchronize.entity.DeviceData;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/4
 */
public interface DeviceDataUploader {
    /**
     * 上传手机的设备信息
     */
    void uploadDeviceData(DeviceData deviceData);
}
