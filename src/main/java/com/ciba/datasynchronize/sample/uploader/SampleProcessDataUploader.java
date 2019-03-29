package com.ciba.datasynchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.datasynchronize.coder.PublicKey;
import com.ciba.datasynchronize.common.DataSynchronizeManager;
import com.ciba.datasynchronize.entity.ProcessData;
import com.ciba.datasynchronize.manager.DataCacheManager;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleUrlManager;
import com.ciba.datasynchronize.uploader.ProcessDataUploader;
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
public class SampleProcessDataUploader implements ProcessDataUploader {
    @Override
    public void uploadProcessData(List<ProcessData> processDataList) {
        if (processDataList == null || processDataList.isEmpty()) {
            return;
        }
        AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
        String startUpDataUrl = SampleUrlManager.getInstance().getStartUpDataUrl();
        if (httpClient == null || TextUtils.isEmpty(startUpDataUrl)) {
            processDataList.clear();
            return;
        }

        if (!StateUtil.checkFlag()) {
            processDataList.clear();
            return;
        }

        String json = JsonUtil.processData2JsonStr(processDataList);
        processDataList.clear();

        String jsonRsa = PublicKey.keyboards(json);
        json = null;

        Map<String, String> requestParams = new HashMap<>(2);
        requestParams.put("machineId", DataCacheManager.getInstance().getMachineId() + "");

        String dataGatherSdkVersion = DataSynchronizeManager.getInstance().getDataGatherSdkVersion();
        String dataSynchronizeSdkVersion = DataSynchronizeManager.getInstance().getSdkVersion();
        if (!TextUtils.isEmpty(dataGatherSdkVersion) && !TextUtils.isEmpty(dataSynchronizeSdkVersion)) {
            requestParams.put("sdkVersion", dataGatherSdkVersion + "-" + dataSynchronizeSdkVersion);
        }
        requestParams.put("jsons", jsonRsa);
        httpClient.post(startUpDataUrl, requestParams, new SimpleHttpListener(){
            @Override
            public void onRequestSuccess(String result) {
                DataSynchronizeLog.innerI("0x00000005");
            }
        });
    }
}
