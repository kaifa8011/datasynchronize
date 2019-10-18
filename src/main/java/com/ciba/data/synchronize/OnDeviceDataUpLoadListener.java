package com.ciba.data.synchronize;

/**
 * 设备数据上报监听
 *
 * @author parting_soul
 * @date 2019-10-15
 */
public interface OnDeviceDataUpLoadListener {
    void onUploadSuccess(long machineId);

    void onUploadFailed();
}
