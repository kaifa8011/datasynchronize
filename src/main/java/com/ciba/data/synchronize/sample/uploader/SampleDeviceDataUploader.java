package com.ciba.data.synchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.data.synchronize.OnDeviceDataUpLoadListener;
import com.ciba.data.synchronize.coder.PublicKey;
import com.ciba.data.synchronize.constant.Constant;
import com.ciba.data.synchronize.entity.CustomPackageInfo;
import com.ciba.data.synchronize.entity.DeviceData;
import com.ciba.data.synchronize.entity.ProcessData;
import com.ciba.data.synchronize.manager.DataCacheManager;
import com.ciba.data.synchronize.manager.LoaderUploaderManager;
import com.ciba.data.synchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.data.synchronize.sample.manager.SampleUrlManager;
import com.ciba.data.synchronize.uploader.DeviceDataUploader;
import com.ciba.data.synchronize.util.DataSynchronizeLog;
import com.ciba.data.synchronize.util.JsonUtil;
import com.ciba.data.synchronize.util.StateUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author ciba
 * @description 设备信息上传样例
 * @date 2018/12/4
 */
public class SampleDeviceDataUploader implements DeviceDataUploader {

    @Override
    public void uploadDeviceData(DeviceData deviceData) {
        uploadDeviceData(deviceData, null);
    }

    @Override
    public void uploadDeviceData(DeviceData deviceData, OnDeviceDataUpLoadListener listener) {
        uploadDeviceData(deviceData, null, null, listener);
    }

    public void uploadDeviceData(DeviceData deviceData
            , final List<CustomPackageInfo> installPackageList
            , final List<ProcessData> appProcessList
            , final OnDeviceDataUpLoadListener upLoadListener) {

        try {
            if (deviceData == null) {
                clearData(installPackageList, appProcessList);
                throw new Exception(Constant.UPLOAD_DATA_FAILED);
            }
            if (!StateUtil.checkFlag()) {
                clearData(installPackageList, appProcessList);
                throw new Exception(Constant.UPLOAD_DATA_FAILED);
            }
            AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
            String deviceDataUrl = SampleUrlManager.getInstance().getDeviceDataUrl();

            if (httpClient != null && !TextUtils.isEmpty(deviceDataUrl)) {
                JSONObject jsonObject = JsonUtil.deviceData2Json(deviceData);
                long machineId = DataCacheManager.getInstance().getMachineId();
                deviceData = null;
                if (jsonObject == null) {
                    clearData(installPackageList, appProcessList);
                    throw new Exception(Constant.UPLOAD_DATA_FAILED);
                }
                if (machineId != 0) {
                    try {
                        jsonObject.put("machineId", machineId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                String deviceDataJson = jsonObject.toString();
                jsonObject = null;
                if (TextUtils.isEmpty(deviceDataJson)) {
                    clearData(installPackageList, appProcessList);
                    throw new Exception(Constant.UPLOAD_DATA_FAILED);
                }

                String jsonRsa = PublicKey.keyboards(deviceDataJson);
                deviceDataJson = null;
                httpClient.post(deviceDataUrl, SampleUploadUtil.getSameEncryptionParams(machineId, jsonRsa), new SimpleHttpListener() {
                    @Override
                    public void onRequestSuccess(String result) {
                        DataSynchronizeLog.innerI("0x00000003");
                        uploadSuccess(result, installPackageList, appProcessList);
                        long machineId = DataCacheManager.getInstance().getMachineId();
                        if (machineId != 0) {
                            notifyUploadDevicesDataSuccess(upLoadListener, machineId);
                        } else {
                            notifyUploadDevicesDataFailed(upLoadListener);
                        }
                    }

                    @Override
                    public void onRequestFailed(int code, String error) {
                        super.onRequestFailed(code, error);
                        notifyUploadDevicesDataFailed(upLoadListener);
                    }
                });
            } else {
                clearData(installPackageList, appProcessList);
                throw new Exception(Constant.UPLOAD_DATA_FAILED);
            }
        } catch (Exception e) {
            notifyUploadDevicesDataFailed(upLoadListener);
        }
    }

    /**
     * 上传成功，或者做自己的事情
     */
    public void uploadSuccess(String result, List<CustomPackageInfo> installPackageList, List<ProcessData> appProcessList) {
        if (TextUtils.isEmpty(result)) {
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(result);
            long machineId = jsonObject.optLong("data");
            if (machineId != 0) {
                DataCacheManager.getInstance().saveMachineId(machineId);
                LoaderUploaderManager.getInstance().uploadInstallData(installPackageList);
                LoaderUploaderManager.getInstance().uploadProcessData(appProcessList);
            } else {
                clearData(installPackageList, appProcessList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearData(List<CustomPackageInfo> installPackageList, List<ProcessData> appProcessList) {
        if (installPackageList != null) {
            installPackageList.clear();
        }
        if (appProcessList != null) {
            appProcessList.clear();
        }
    }


    private void notifyUploadDevicesDataSuccess(final OnDeviceDataUpLoadListener upLoadListener, long machineId) {
        if (upLoadListener != null) {
            upLoadListener.onUploadSuccess(machineId);
        }
    }

    private void notifyUploadDevicesDataFailed(final OnDeviceDataUpLoadListener upLoadListener) {
        if (upLoadListener != null) {
            upLoadListener.onUploadFailed();
        }
    }

}
