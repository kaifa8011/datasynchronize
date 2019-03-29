package com.ciba.datasynchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.datasynchronize.common.DataSynchronizeManager;
import com.ciba.datasynchronize.entity.OperationData;
import com.ciba.datasynchronize.manager.DataCacheManager;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleUrlManager;
import com.ciba.datasynchronize.uploader.CrashDataUploader;
import com.ciba.datasynchronize.util.DataSynchronizeLog;
import com.ciba.datasynchronize.util.JsonUtil;
import com.ciba.datasynchronize.util.TimeUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author ciba
 * @description Crash信息上传样例
 * @date 2018/12/4
 */
public class SampleCrashDataUploader implements CrashDataUploader {

    @Override
    public void uploadCrashData(String crashData) {
        if (TextUtils.isEmpty(crashData)) {
            return;
        }
        AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
        String crashDataUrl = SampleUrlManager.getInstance().getCrashUrl();
        if (httpClient == null || TextUtils.isEmpty(crashDataUrl)) {
            return;
        }
        JSONArray crashDataJson = JsonUtil.operationData2Json(createOperationData(OperationData.TYPE_CRASH, crashData));
        crashData = null;
        if (crashDataJson == null || crashDataJson.length() <= 0) {
            return;
        }

        String crashJson = crashDataJson.toString();
        crashDataJson = null;
        if (TextUtils.isEmpty(crashJson)) {
            return;
        }
        httpClient.postJson(crashDataUrl, crashJson, new SimpleHttpListener() {
            @Override
            public void onRequestSuccess(String result) {
                DataSynchronizeLog.innerI("0x00000002");
                DataCacheManager.getInstance().saveCrashData("");
            }
        });
    }

    /**
     * 获取操作数据
     */
    private static OperationData createOperationData(String type, String crashData) {
        OperationData operationData = new OperationData();
        operationData.setOperationType(type);
        operationData.setEndTime(TimeUtil.getCurrentTime());
        operationData.setPackageName(DataSynchronizeManager.getInstance().getContext().getPackageName());
        operationData.setMachineId(DataCacheManager.getInstance().getMachineId());
        try {
            JSONObject jsonObject = new JSONObject(crashData);
            operationData.setVersionNo(jsonObject.optString("version", ""));
            operationData.setScheme(jsonObject.optString("data", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operationData;
    }
}
