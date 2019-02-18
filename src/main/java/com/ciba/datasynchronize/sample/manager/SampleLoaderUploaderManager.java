package com.ciba.datasynchronize.sample.manager;

import android.app.Activity;

import com.ciba.datasynchronize.entity.CustomPackageInfo;
import com.ciba.datasynchronize.entity.DeviceData;
import com.ciba.datasynchronize.entity.ProcessData;
import com.ciba.datasynchronize.sample.uploader.SampleActivityLifecycleUploader;
import com.ciba.datasynchronize.sample.uploader.SampleCrashDataUploader;
import com.ciba.datasynchronize.sample.uploader.SampleDeviceDataUploader;
import com.ciba.datasynchronize.sample.uploader.SampleInstallDataUploader;
import com.ciba.datasynchronize.sample.uploader.SampleProcessDataUploader;
import com.ciba.datasynchronize.uploader.ActivityLifecycleUploader;
import com.ciba.datasynchronize.uploader.CrashDataUploader;
import com.ciba.datasynchronize.uploader.InstallDataUploader;
import com.ciba.datasynchronize.uploader.ProcessDataUploader;
import com.ciba.http.client.AsyncHttpClient;

import java.util.List;


/**
 * @author ciba
 * @description 自定义加载上传样例管理器
 * @date 2018/12/4
 */
public class SampleLoaderUploaderManager {
    private static SampleLoaderUploaderManager instance;
    private AsyncHttpClient httpClient;

    private SampleDeviceDataUploader deviceDataUploader;
    private CrashDataUploader crashDataUploader;
    private InstallDataUploader installDataUploader;
    private ProcessDataUploader processDataUploader;
    private ActivityLifecycleUploader activityLifecycleUploader;

    private SampleLoaderUploaderManager() {
        httpClient = new AsyncHttpClient();
    }

    public static SampleLoaderUploaderManager getInstance() {
        if (instance == null) {
            synchronized (SampleLoaderUploaderManager.class) {
                if (instance == null) {
                    instance = new SampleLoaderUploaderManager();
                }
            }
        }
        return instance;
    }

    public AsyncHttpClient getHttpClient() {
        return httpClient;
    }

    public void uploadDeviceData(DeviceData deviceData
            , List<CustomPackageInfo> installPackageList
            , List<ProcessData> appProcessList) {
        checkDeviceDataUploader();
        deviceDataUploader.uploadDeviceData(deviceData, installPackageList, appProcessList);
    }

    private void checkDeviceDataUploader() {
        if (deviceDataUploader == null) {
            deviceDataUploader = new SampleDeviceDataUploader();
        }
    }

    public void uploadCrashData(String crashData) {
        checkCrashDataUploader();
        crashDataUploader.uploadCrashData(crashData);
    }

    private void checkCrashDataUploader() {
        if (crashDataUploader == null) {
            crashDataUploader = new SampleCrashDataUploader();
        }
    }

    public void uploadInstallData(List<CustomPackageInfo> installPackageList) {
        checkInstallDataUploader();
        installDataUploader.uploadInstallData(installPackageList);
    }

    private void checkInstallDataUploader() {
        if (installDataUploader == null) {
            installDataUploader = new SampleInstallDataUploader();
        }
    }

    public void uploadProcessData(List<ProcessData> processDataList) {
        checkProcessDataUploader();
        processDataUploader.uploadProcessData(processDataList);
    }

    private void checkProcessDataUploader() {
        if (processDataUploader == null) {
            processDataUploader = new SampleProcessDataUploader();
        }
    }

    public void uploadActivityLifecycle(int lifecycle, Activity activity) {
        checkActivityLifecycleUploader();
        activityLifecycleUploader.uploadActivityLifecycle(lifecycle, activity);
    }

    private void checkActivityLifecycleUploader() {
        if (activityLifecycleUploader == null) {
            activityLifecycleUploader = new SampleActivityLifecycleUploader();
        }
    }
}
