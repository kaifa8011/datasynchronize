package com.ciba.datasynchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.datasynchronize.entity.OperationData;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.sample.manager.SampleUrlManager;
import com.ciba.datasynchronize.uploader.MotionEventDataUploader;
import com.ciba.datasynchronize.util.JsonUtil;
import com.ciba.http.client.AsyncHttpClient;

import org.json.JSONArray;

import java.util.List;

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
            return;
        }
        JSONArray motionEventDataJson = JsonUtil.operationData2Json(motionEventList);
        if (motionEventDataJson == null || motionEventDataJson.length() <= 0) {
            return;
        }

        String motionEventJson = motionEventDataJson.toString();
        motionEventDataJson = null;
        if (TextUtils.isEmpty(motionEventJson)) {
            return;
        }
        httpClient.postJson(motionEventUrl, motionEventJson, null);
    }
}
