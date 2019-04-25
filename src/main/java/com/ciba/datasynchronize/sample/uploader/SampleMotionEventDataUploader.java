package com.ciba.datasynchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.datasynchronize.entity.OperationData;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleUrlManager;
import com.ciba.datasynchronize.uploader.MotionEventDataUploader;
import com.ciba.datasynchronize.util.JsonUtil;
import com.ciba.http.client.AsyncHttpClient;

import java.util.List;
import java.util.Map;

/**
 * @author ciba
 * @description Crash信息上传样例
 * @date 2018/12/4
 */
public class SampleMotionEventDataUploader implements MotionEventDataUploader {

    @Override
    public void uploadMotionEventData(List<OperationData> motionEventList) {
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
