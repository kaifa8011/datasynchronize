package com.ciba.data.synchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.data.synchronize.coder.PublicKey;
import com.ciba.data.synchronize.entity.ProcessData;
import com.ciba.data.synchronize.manager.DataCacheManager;
import com.ciba.data.synchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.data.synchronize.sample.manager.SampleUrlManager;
import com.ciba.data.synchronize.uploader.ProcessDataUploader;
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
public class SampleProcessDataUploader implements ProcessDataUploader {
    @Override
    public void uploadProcessData(List<ProcessData> processDataList) {
//        if (processDataList == null || processDataList.isEmpty()) {
//            return;
//        }
//        AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
//        String startUpDataUrl = SampleUrlManager.getStartUpDataUrl();
//        long machineId = DataCacheManager.getInstance().getMachineId();
//        if (machineId == 0 || httpClient == null || TextUtils.isEmpty(startUpDataUrl)) {
//            processDataList.clear();
//            return;
//        }
//
//        if (!StateUtil.checkFlag()) {
//            processDataList.clear();
//            return;
//        }
//
//        String json = JsonUtil.processData2JsonStr(processDataList);
//        processDataList.clear();
//
//        String jsonRsa = PublicKey.keyboards(json);
//        json = null;
//
//        httpClient.post(startUpDataUrl, SampleUploadUtil.getSameEncryptionParams(machineId, jsonRsa), new SimpleHttpListener() {
//            @Override
//            public void onRequestSuccess(String result) {
//                DataSynchronizeLog.innerI("0x00000005");
//            }
//        });
    }
}
