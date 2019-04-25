package com.ciba.datasynchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.datasynchronize.common.DataSynchronizeManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ciba
 * @description 描述
 * @date 2019/4/25
 */
public class SampleUploadUtil {
    public static Map<String, String> getSameEncryptionParams(long machineId, String jsonRsa) {
        Map<String, String> requestParams = new HashMap<>(3);

        // 添加machineId
        if (machineId != 0) {
            requestParams.put("machineId", machineId + "");
        }

        // 添加sdkVersion
        String dataGatherSdkVersion = DataSynchronizeManager.getInstance().getDataGatherSdkVersion();
        String dataSynchronizeSdkVersion = DataSynchronizeManager.getInstance().getSdkVersion();
        if (!TextUtils.isEmpty(dataGatherSdkVersion) && !TextUtils.isEmpty(dataSynchronizeSdkVersion)) {
            requestParams.put("sdkVersion", dataGatherSdkVersion + "-" + dataSynchronizeSdkVersion);
        }

        // 添加jsons
        requestParams.put("jsons", jsonRsa);
        return requestParams;
    }
}
