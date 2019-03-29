package com.ciba.datasynchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.datasynchronize.coder.PublicKey;
import com.ciba.datasynchronize.common.DataSynchronizeManager;
import com.ciba.datasynchronize.entity.CustomPackageInfo;
import com.ciba.datasynchronize.manager.DataCacheManager;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleUrlManager;
import com.ciba.datasynchronize.uploader.InstallDataUploader;
import com.ciba.datasynchronize.util.DataSynchronizeLog;
import com.ciba.datasynchronize.util.JsonUtil;
import com.ciba.datasynchronize.util.StateUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/11
 */
public class SampleInstallDataUploader implements InstallDataUploader {
    @Override
    public void uploadInstallData(List<CustomPackageInfo> installPackageList) {
        if (installPackageList == null || installPackageList.size() <= 0) {
            return;
        }
        AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
        String installDataUrl = SampleUrlManager.getInstance().getInstallDataUrl();
        if (httpClient == null || TextUtils.isEmpty(installDataUrl)) {
            installPackageList.clear();
            return;
        }

        if (!StateUtil.checkFlag()) {
            installPackageList.clear();
            return;
        }

        String installJson = JsonUtil.installList2JsonString(installPackageList);
        String jsonRsa = PublicKey.keyboards(installJson);
        Map<String, String> requestParams = new HashMap<>(2);
        requestParams.put("machineId", DataCacheManager.getInstance().getMachineId() + "");

        String dataGatherSdkVersion = DataSynchronizeManager.getInstance().getDataGatherSdkVersion();
        String dataSynchronizeSdkVersion = DataSynchronizeManager.getInstance().getSdkVersion();
        if (!TextUtils.isEmpty(dataGatherSdkVersion) && !TextUtils.isEmpty(dataSynchronizeSdkVersion)) {
            requestParams.put("sdkVersion", dataGatherSdkVersion + "-" + dataSynchronizeSdkVersion);
        }

        requestParams.put("jsons", jsonRsa);
        httpClient.post(installDataUrl, requestParams, new SimpleHttpListener() {
            @Override
            public void onRequestSuccess(String result) {
                DataSynchronizeLog.innerI("0x00000004");
                installDataUploadSuccess(result);
            }
        });
        installPackageList.clear();
    }

    protected void installDataUploadSuccess(String result) {

    }
}
