package com.ciba.data.synchronize.sample.uploader;

import android.text.TextUtils;

import com.ciba.data.synchronize.common.DataSynchronizeManager;
import com.ciba.data.synchronize.util.PackageUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ciba
 * @description 描述
 * @date 2019/4/25
 */
public class SampleUploadUtil {
    public static Map<String, String> getSameEncryptionParams(long machineId, String jsonRsa) {
        Map<String, String> requestParams = new HashMap<>(5);

        try {
            // 添加machineId
            if (machineId != 0) {
                //用dcid 替换 machineId
                //requestParams.put("machineId", machineId + "");
                requestParams.put("dcid", machineId + "");
            }

            // 添加sdkVersion
            String dataGatherSdkVersion = DataSynchronizeManager.getInstance().getDataGatherSdkVersion();
            String dataSynchronizeSdkVersion = DataSynchronizeManager.getInstance().getSdkVersion();
            if (!TextUtils.isEmpty(dataGatherSdkVersion) && !TextUtils.isEmpty(dataSynchronizeSdkVersion)) {
                requestParams.put("sdkVersion", dataGatherSdkVersion + "-" + dataSynchronizeSdkVersion);
            }

            // 添加jsons
            requestParams.put("jsons", jsonRsa);

            requestParams.put("packname", PackageUtil.getPackageName());
            requestParams.put("version", PackageUtil.getVersionName());

        } catch (Exception e) {
        }

        return requestParams;
    }
}
