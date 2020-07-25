package com.ciba.data.synchronize.sample.uploader;

import android.text.TextUtils;
import android.util.Log;

import com.ciba.data.synchronize.common.DataSynchronizeManager;
import com.ciba.data.synchronize.sample.manager.SampleUrlManager;
import com.ciba.data.synchronize.uploader.IPV6DataUploader;
import com.ciba.data.synchronize.util.IPV6Check;
import com.ciba.http.client.SyncHttpClient;
import com.ciba.http.manager.AsyncThreadPoolManager;

import java.util.HashMap;
import java.util.Map;

/**
 * ipv6上报
 *
 * @author parting_soul
 * @date 2020-07-25
 */
public class SampleIPV6DataUploader implements IPV6DataUploader {
    private SyncHttpClient mSyncHttpClient = new SyncHttpClient();
    private String[] IPV6_CHECK_URLS = {
            "http://v6.ip.zxinc.org/getip",
            "https://api6.ipify.org"
    };

    public static SampleIPV6DataUploader getInstance() {
        return Holder.SAMPLE_IPV_6_DATA_UPLOADER;
    }

    private static class Holder {
        static final SampleIPV6DataUploader SAMPLE_IPV_6_DATA_UPLOADER = new SampleIPV6DataUploader();
    }

    @Override
    public void uploadIPV6(final long machineId) {
        AsyncThreadPoolManager.getInstance().getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String ipv6 = checkIPV6Available();
                if (!TextUtils.isEmpty(ipv6)) {
                    postIPV6(machineId, ipv6);
                }
            }
        });
    }

    private void postIPV6(long machineId, String ipv6) {
        Map<String, String> params = new HashMap<>();
        params.put("machineId", String.valueOf(machineId));
        params.put("ipv6", ipv6);

        String dataGatherSdkVersion = DataSynchronizeManager.getInstance().getDataGatherSdkVersion();
        String dataSynchronizeSdkVersion = DataSynchronizeManager.getInstance().getSdkVersion();
        if (!TextUtils.isEmpty(dataGatherSdkVersion) && !TextUtils.isEmpty(dataSynchronizeSdkVersion)) {
            params.put("sdkVersion", dataGatherSdkVersion + "-" + dataSynchronizeSdkVersion);
        }

        String result = mSyncHttpClient.post(SampleUrlManager.getInstance().getIPV6PostUrl(), params);
        Log.e("result ---", " " + result);
    }

    /**
     * 判断是否支持ipv6
     *
     * @return ipv6
     */
    private String checkIPV6Available() {
        if (IPV6_CHECK_URLS == null) {
            return null;
        }
        String url;
        for (int i = 0; i < IPV6_CHECK_URLS.length; i++) {
            url = IPV6_CHECK_URLS[i];
            String result = mSyncHttpClient.get(url, null);
            if (IPV6Check.isValidIpv6Addr(result)) {
                return result;
            }
        }
        return null;
    }

}
