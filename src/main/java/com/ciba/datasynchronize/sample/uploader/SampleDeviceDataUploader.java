package com.ciba.datasynchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.datasynchronize.coder.PublicKey;
import com.ciba.datasynchronize.common.DataSynchronizeManager;
import com.ciba.datasynchronize.entity.CustomPackageInfo;
import com.ciba.datasynchronize.entity.DeviceData;
import com.ciba.datasynchronize.entity.ProcessData;
import com.ciba.datasynchronize.manager.DataCacheManager;
import com.ciba.datasynchronize.manager.LoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleUrlManager;
import com.ciba.datasynchronize.uploader.DeviceDataUploader;
import com.ciba.datasynchronize.util.DataSynchronizeLog;
import com.ciba.datasynchronize.util.JsonUtil;
import com.ciba.datasynchronize.util.StateUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ciba
 * @description 设备信息上传样例
 * @date 2018/12/4
 */
public class SampleDeviceDataUploader implements DeviceDataUploader {

    @Override
    public void uploadDeviceData(DeviceData deviceData) {
        uploadDeviceData(deviceData, null, null);
    }

    public void uploadDeviceData(DeviceData deviceData
            , final List<CustomPackageInfo> installPackageList
            , final List<ProcessData> appProcessList) {

        if (deviceData == null) {
            clearData(installPackageList, appProcessList);
            return;
        }
        if (!StateUtil.checkFlag()) {
            clearData(installPackageList, appProcessList);
            return;
        }
        AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
        String deviceDataUrl = SampleUrlManager.getInstance().getDeviceDataUrl();
        if (httpClient != null && !TextUtils.isEmpty(deviceDataUrl)) {
            JSONObject jsonObject = JsonUtil.deviceData2Json(deviceData);
            long machineId = DataCacheManager.getInstance().getMachineId();
            deviceData = null;
            if (jsonObject == null) {
                clearData(installPackageList, appProcessList);
                return;
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
                return;
            }

            String dataGatherSdkVersion = DataSynchronizeManager.getInstance().getDataGatherSdkVersion();
            String dataSynchronizeSdkVersion = DataSynchronizeManager.getInstance().getSdkVersion();

            Map<String, String> params = new HashMap<>(3);
            params.put("jsons", PublicKey.keyboards(deviceDataJson));
            if (machineId != 0) {
                params.put("machineId", DataCacheManager.getInstance().getMachineId() + "");
            }
            params.put("sdkVersion", dataGatherSdkVersion + "-" + dataSynchronizeSdkVersion);
            deviceDataJson = null;

            httpClient.post(deviceDataUrl, params, new SimpleHttpListener() {
                @Override
                public void onRequestSuccess(String result) {
                    DataSynchronizeLog.innerI("0x00000003");
                    uploadSuccess(result, installPackageList, appProcessList);
                }
            });
        } else {
            clearData(installPackageList, appProcessList);
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
}
