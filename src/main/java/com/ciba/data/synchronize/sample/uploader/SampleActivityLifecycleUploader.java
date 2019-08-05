package com.ciba.data.synchronize.sample.uploader;

import android.app.Activity;
import android.text.TextUtils;

import com.ciba.data.synchronize.common.DataSynchronizeManager;
import com.ciba.data.synchronize.entity.OperationData;
import com.ciba.data.synchronize.manager.DataCacheManager;
import com.ciba.data.synchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.data.synchronize.sample.manager.SampleUrlManager;
import com.ciba.data.synchronize.uploader.ActivityLifecycleUploader;
import com.ciba.data.synchronize.util.DataSynchronizeLog;
import com.ciba.data.synchronize.util.JsonUtil;
import com.ciba.data.synchronize.util.StateUtil;
import com.ciba.data.synchronize.util.TimeUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import java.util.Map;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/11
 */
public class SampleActivityLifecycleUploader implements ActivityLifecycleUploader {
    private SimpleHttpListener listener = new SimpleHttpListener() {
        @Override
        public void onRequestSuccess(String result) {
            DataSynchronizeLog.innerI("0x00000001");
        }
    };

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

        Map<String, String> params = JsonUtil.operationData2Json(operationData);
        operationData = null;
        if (params == null || params.size() <= 0) {
            return;
        }

        httpClient.post(activityLifeUrl, params, listener);
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
