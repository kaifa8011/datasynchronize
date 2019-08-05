package com.ciba.data.synchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.data.synchronize.entity.OperationData;
import com.ciba.data.synchronize.sample.manager.SampleUrlManager;
import com.ciba.data.synchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.data.synchronize.uploader.OperationDataUploader;
import com.ciba.data.synchronize.util.JsonUtil;
import com.ciba.http.client.AsyncHttpClient;

import java.util.List;
import java.util.Map;

/**
 * @author ciba
 * @description Crash信息上传样例
 * @date 2018/12/4
 */
public class SampleOperationDataUploader implements OperationDataUploader {

    @Override
    public void uploadOperationDataData(List<OperationData> motionEventList) {
        if (motionEventList == null || motionEventList.isEmpty()) {
            return;
        }
        AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
        String motionEventUrl = SampleUrlManager.getInstance().getMotionEventUrl();
        if (httpClient == null || TextUtils.isEmpty(motionEventUrl)) {
            motionEventList.clear();
            motionEventList = null;
            return;
        }

        Map<String, String> params = JsonUtil.operationData2Json(motionEventList);
        if (params == null || params.size() <= 0) {
            motionEventList.clear();
            motionEventList = null;
            return;
        }

        httpClient.post(motionEventUrl, params, null);
    }
}
