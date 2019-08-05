package com.ciba.data.synchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.data.synchronize.coder.PublicKey;
import com.ciba.data.synchronize.entity.CustomPackageInfo;
import com.ciba.data.synchronize.manager.DataCacheManager;
import com.ciba.data.synchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.data.synchronize.sample.manager.SampleUrlManager;
import com.ciba.data.synchronize.uploader.InstallDataUploader;
import com.ciba.data.synchronize.util.DataSynchronizeLog;
import com.ciba.data.synchronize.util.JsonUtil;
import com.ciba.data.synchronize.util.StateUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import java.util.List;

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
        long machineId = DataCacheManager.getInstance().getMachineId();

        if (machineId == 0 || httpClient == null || TextUtils.isEmpty(installDataUrl)) {
            installPackageList.clear();
            return;
        }

        if (!StateUtil.checkFlag()) {
            installPackageList.clear();
            return;
        }

        String installJson = JsonUtil.installList2JsonString(installPackageList);
        String jsonRsa = PublicKey.keyboards(installJson);
        installJson = null;

        httpClient.post(installDataUrl, SampleUploadUtil.getSameEncryptionParams(machineId, jsonRsa), new SimpleHttpListener() {
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
