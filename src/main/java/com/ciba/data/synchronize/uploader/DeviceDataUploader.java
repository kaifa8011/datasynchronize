package com.ciba.data.synchronize.uploader;

import com.ciba.data.synchronize.OnDeviceDataUpLoadListener;
import com.ciba.data.synchronize.entity.DeviceData;

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


    void uploadDeviceData(DeviceData deviceData, OnDeviceDataUpLoadListener listener);

}
