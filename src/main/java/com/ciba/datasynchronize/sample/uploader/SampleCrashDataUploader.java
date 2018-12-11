package com.ciba.datasynchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.datasynchronize.common.DataSynchronizeManager;
import com.ciba.datasynchronize.entity.OperationData;
import com.ciba.datasynchronize.manager.DataCacheManager;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleUrlManager;
import com.ciba.datasynchronize.uploader.CrashDataUploader;
import com.ciba.datasynchronize.util.JsonUtil;
import com.ciba.datasynchronize.util.TimeUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import org.json.JSONArray;

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
                super.onRequestSuccess(result);
                DataCacheManager.getInstance().saveCrashData("");
            }
        });
    }

    /**
     * 获取操作数据
     */
    private static OperationData createOperationData(String type, String des) {
        OperationData operationData = new OperationData();
        operationData.setOperationType(type);
        operationData.setEndTime(TimeUtil.getCurrentTime());
        operationData.setPackageName(DataSynchronizeManager.getInstance().getContext().getPackageName());
        operationData.setVersionNo(getVersionName(operationData.getPackageName()));
        operationData.setMachineId(DataCacheManager.getInstance().getMachineId());
        operationData.setScheme(des);
        return operationData;
    }

    /**
     * 获取包信息
     *
     * @param packageName
     */
    public static String getVersionName(String packageName) {
        try {
            return DataSynchronizeManager.getInstance().getContext().getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
