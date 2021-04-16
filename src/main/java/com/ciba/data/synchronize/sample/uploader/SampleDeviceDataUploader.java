package com.ciba.data.synchronize.sample.uploader;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.ciba.data.synchronize.OnDeviceDataUpLoadListener;
import com.ciba.data.synchronize.coder.PublicKey;
import com.ciba.data.synchronize.constant.Constant;
import com.ciba.data.synchronize.entity.CustomPackageInfo;
import com.ciba.data.synchronize.entity.DeviceData;
import com.ciba.data.synchronize.entity.ProcessData;
import com.ciba.data.synchronize.manager.DataCacheManager;
import com.ciba.data.synchronize.manager.LoaderUploaderManager;
import com.ciba.data.synchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.data.synchronize.sample.manager.SampleUrlManager;
import com.ciba.data.synchronize.uploader.DeviceDataUploader;
import com.ciba.data.synchronize.util.DataSynchronizeLog;
import com.ciba.data.synchronize.util.JsonUtil;
import com.ciba.data.synchronize.util.StateUtil;
import com.ciba.http.client.AsyncHttpClient;
import com.ciba.http.listener.SimpleHttpListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author ciba
 * @description 设备信息上传样例
 * @date 2018/12/4
 */
public class SampleDeviceDataUploader implements DeviceDataUploader {
    private String mToken;
    public static final int GET_MACHINE_ID_MAX_RETRY_COUNT = 3;
    private int mRetryCount;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void uploadDeviceData(DeviceData deviceData) {
        uploadDeviceData(deviceData, null);
    }

    @Override
    public void uploadDeviceData(DeviceData deviceData, OnDeviceDataUpLoadListener listener) {
        uploadDeviceData(deviceData, null, null, listener);
    }


    /**
     * 第一次启动，machineId不存在，需要凭借上报信息获取token,然后根据token去获取machineId
     *
     * @param deviceData
     * @param installPackageList
     * @param appProcessList
     * @param upLoadListener
     */
    public void uploadDeviceData(DeviceData deviceData
            , final List<CustomPackageInfo> installPackageList
            , final List<ProcessData> appProcessList
            , final OnDeviceDataUpLoadListener upLoadListener) {

        try {
            if (deviceData == null) {
                clearData(installPackageList, appProcessList);
                throw new Exception(Constant.UPLOAD_DATA_FAILED);
            }
            if (!StateUtil.checkFlag()) {
                clearData(installPackageList, appProcessList);
                throw new Exception(Constant.UPLOAD_DATA_FAILED);
            }
            AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
            String deviceDataUrl = SampleUrlManager.getDeviceDataUrl();

            if (httpClient != null && !TextUtils.isEmpty(deviceDataUrl)) {
                JSONObject jsonObject = JsonUtil.deviceData2Json(deviceData);
                long machineId = DataCacheManager.getInstance().getMachineId();
                deviceData = null;
                if (jsonObject == null) {
                    clearData(installPackageList, appProcessList);
                    throw new Exception(Constant.UPLOAD_DATA_FAILED);
                }

                if (machineId != 0) {
                    try {
                        jsonObject.put("machineId", machineId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                String deviceDataJson = jsonObject.toString();
                jsonObject = null;
                if (TextUtils.isEmpty(deviceDataJson)) {
                    clearData(installPackageList, appProcessList);
                    throw new Exception(Constant.UPLOAD_DATA_FAILED);
                }

                String jsonRsa = PublicKey.keyboards(deviceDataJson);
                deviceDataJson = null;
                //上传设备数据
                postDeviceData(null, httpClient, deviceDataUrl, machineId, jsonRsa,
                        installPackageList, appProcessList, upLoadListener);
            } else {
                clearData(installPackageList, appProcessList);
                throw new Exception(Constant.UPLOAD_DATA_FAILED);
            }
        } catch (Exception e) {
            notifyUploadDevicesDataFailed(upLoadListener);
        }
    }


    /**
     * @param token              新用户第一次打开上报设备数据获取的是一个token,而非machineId
     * @param httpClient
     * @param deviceDataUrl
     * @param machineId
     * @param jsonRsa
     * @param installPackageList
     * @param appProcessList
     * @param upLoadListener
     */
    private void postDeviceData(String token, AsyncHttpClient httpClient, String deviceDataUrl,
                                final long machineId, final String jsonRsa, final List<CustomPackageInfo> installPackageList, final List<ProcessData> appProcessList, final OnDeviceDataUpLoadListener upLoadListener) {

        Map<String, String> params = SampleUploadUtil.getSameEncryptionParams(machineId, jsonRsa);
        if (!TextUtils.isEmpty(token)) {
            // 第一次启动，不存在machineId,需要凭借token 去获取machineId
            params.put("token", token);
            params.remove("machineId");
        }

        httpClient.post(deviceDataUrl, params, new SimpleHttpListener() {
            @Override
            public void onRequestSuccess(String result) {
                DataSynchronizeLog.innerI("0x00000003");

                if (TextUtils.isEmpty(result)) {
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String message = jsonObject.optString("message");
                    String code = jsonObject.getString("code");

                    //是否需要轮询获取machineId(第一次拿到的是token或者machineId获取失败都需要重新根据token去获取machineId)
                    boolean shouldLoopGetMachineId = TextUtils.equals(message, "token")
                            || TextUtils.equals(code, "1003");
                    if (shouldLoopGetMachineId) {
                        String token = jsonObject.optString("data");
                        mToken = TextUtils.isEmpty(token) || "null".equals(token) ? mToken : token;
                        //重新根据token去获取machineId
                        retryGetMachineId(jsonRsa, mToken, installPackageList, appProcessList, upLoadListener);
                        return;
                    }

                    //拿到了真实的machineId
                    long data = jsonObject.optLong("data");
                    if (data != 0) {
                        DataCacheManager.getInstance().saveMachineId(data);
                        LoaderUploaderManager.getInstance().uploadInstallData(installPackageList);
                        LoaderUploaderManager.getInstance().uploadProcessData(appProcessList);
                    } else {
                        clearData(installPackageList, appProcessList);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //通知更新成功或者失败
                long machineId = DataCacheManager.getInstance().getMachineId();
                if (machineId != 0) {
                    notifyUploadDevicesDataSuccess(upLoadListener, machineId);
                } else {
                    notifyUploadDevicesDataFailed(upLoadListener);
                }
            }

            @Override
            public void onRequestFailed(int code, String error) {
                super.onRequestFailed(code, error);
                notifyUploadDevicesDataFailed(upLoadListener);
            }
        });
    }


    /**
     * 重试machineId的获取
     *
     * @param jsonRsa
     * @param token
     * @param installPackageList
     * @param appProcessList
     * @param upLoadListener
     */
    private void retryGetMachineId(final String jsonRsa, final String token, final List<CustomPackageInfo> installPackageList,
                                   final List<ProcessData> appProcessList, final OnDeviceDataUpLoadListener upLoadListener) {
        if (mRetryCount >= GET_MACHINE_ID_MAX_RETRY_COUNT) {
            //超过重试次数
            notifyUploadDevicesDataFailed(upLoadListener);
            return;
        }
        final long machineId = DataCacheManager.getInstance().getMachineId();
        final AsyncHttpClient httpClient = SampleLoaderUploaderManager.getInstance().getHttpClient();
        final String deviceDataUrl = SampleUrlManager.getDeviceDataUrl();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                postDeviceData(token, httpClient, deviceDataUrl, machineId, jsonRsa,
                        installPackageList, appProcessList, upLoadListener);
                mRetryCount++;
            }
        }, 2000);
    }

    private void clearData(List<CustomPackageInfo> installPackageList, List<ProcessData> appProcessList) {
        if (installPackageList != null) {
            installPackageList.clear();
        }
        if (appProcessList != null) {
            appProcessList.clear();
        }
    }


    private void notifyUploadDevicesDataSuccess(final OnDeviceDataUpLoadListener upLoadListener, long machineId) {
        if (upLoadListener != null) {
            upLoadListener.onUploadSuccess(machineId);
        }
    }

    private void notifyUploadDevicesDataFailed(final OnDeviceDataUpLoadListener upLoadListener) {
        if (upLoadListener != null) {
            upLoadListener.onUploadFailed();
        }
    }

}
