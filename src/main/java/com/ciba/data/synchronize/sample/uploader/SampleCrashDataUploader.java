package com.ciba.data.synchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.data.synchronize.common.DataSynchronizeManager;
import com.ciba.data.synchronize.entity.OperationData;
import com.ciba.data.synchronize.manager.DataCacheManager;
import com.ciba.data.synchronize.sample.manager.SampleUrlManager;
import com.ciba.data.synchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.data.synchronize.uploader.CrashDataUploader;
import com.ciba.data.synchronize.util.DataSynchronizeLog;
import com.ciba.data.synchronize.util.JsonUtil;
import com.ciba.data.synchronize.util.TimeUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author ciba
 * @description Crash信息上传样例
 * @date 2018/12/4
 */
public class SampleCrashDataUploader implements CrashDataUploader {

    @Override
    public void uploadCrashData(String crashData) {
        //崩溃记录上报逻辑移除 2021.04.16 by松子
//        if (TextUtils.isEmpty(crashData)) {
//            return;
//        }
//        AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
//        String crashDataUrl = SampleUrlManager.getCrashUrl();
//        if (httpClient == null || TextUtils.isEmpty(crashDataUrl)) {
//            return;
//        }
//        Map<String, String> params = JsonUtil.operationData2Json(createOperationData(OperationData.TYPE_CRASH, crashData));
//        crashData = null;
//        if (params == null || params.size() <= 0) {
//            return;
//        }
//
//        httpClient.post(crashDataUrl, params, new SimpleHttpListener() {
//            @Override
//            public void onRequestSuccess(String result) {
//                DataSynchronizeLog.innerI("0x00000002");
//                DataCacheManager.getInstance().saveCrashData("");
//            }
//        });
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
