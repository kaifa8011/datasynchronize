package com.ciba.datasynchronize.sample.uploader;

import android.app.Activity;
import android.text.TextUtils;

import com.ciba.datasynchronize.common.DataSynchronizeManager;
import com.ciba.datasynchronize.entity.OperationData;
import com.ciba.datasynchronize.manager.DataCacheManager;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleUrlManager;
import com.ciba.datasynchronize.uploader.ActivityLifecycleUploader;
import com.ciba.datasynchronize.util.JsonUtil;
import com.ciba.datasynchronize.util.StateUtil;
import com.ciba.datasynchronize.util.TimeUtil;
import com.ciba.http.client.AsyncHttpClient;

import org.json.JSONArray;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/11
 */
public class SampleActivityLifecycleUploader implements ActivityLifecycleUploader {

    @Override
    public void uploadActivityLifecycle(int lifecycle, Activity activity) {
        if (activity == null) {
            return;
        }
        if (ACTIVITY_CREATED != lifecycle) {
            return;
        }
        if (!StateUtil.checkFlag()) {
            return;
        }
        long machineId = DataCacheManager.getInstance().getMachineId();
        if (machineId <= 0) {
            return;
        }
        AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
        String activityLifeUrl = SampleUrlManager.getInstance().getActivityLifeUrl();
        if (httpClient == null || TextUtils.isEmpty(activityLifeUrl)) {
            return;
        }

        String packageName = DataSynchronizeManager.getInstance().getContext().getPackageName();
        OperationData operationData = new OperationData();
        operationData.setMachineId(machineId);
        operationData.setPackageName(packageName);
        operationData.setVersionNo(getVersionName(packageName));
        operationData.setScheme(activity.getLocalClassName());

        String currentTime = TimeUtil.getCurrentTime();
        operationData.setStartTime(currentTime);
        operationData.setOperationType(OperationData.TYPE_OPEN);

        JSONArray jsonArray = JsonUtil.operationData2Json(operationData);
        operationData = null;
        if (jsonArray == null || jsonArray.length() <= 0) {
            return;
        }

        String jsonData = jsonArray.toString();
        jsonArray = null;
        if (TextUtils.isEmpty(jsonData)) {
            return;
        }

        httpClient.postJson(activityLifeUrl, jsonData, null);
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
