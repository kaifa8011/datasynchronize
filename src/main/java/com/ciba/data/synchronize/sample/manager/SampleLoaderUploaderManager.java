package com.ciba.data.synchronize.sample.manager;

import android.app.Activity;

import com.ciba.data.synchronize.entity.CustomPackageInfo;
import com.ciba.data.synchronize.entity.DeviceData;
import com.ciba.data.synchronize.entity.OperationData;
import com.ciba.data.synchronize.entity.ProcessData;
import com.ciba.data.synchronize.sample.uploader.SampleActivityLifecycleUploader;
import com.ciba.data.synchronize.sample.uploader.SampleCrashDataUploader;
import com.ciba.data.synchronize.sample.uploader.SampleDeviceDataUploader;
import com.ciba.data.synchronize.sample.uploader.SampleInstallDataUploader;
import com.ciba.data.synchronize.sample.uploader.SampleOperationDataUploader;
import com.ciba.data.synchronize.sample.uploader.SampleProcessDataUploader;
import com.ciba.data.synchronize.uploader.ActivityLifecycleUploader;
import com.ciba.data.synchronize.uploader.CrashDataUploader;
import com.ciba.data.synchronize.uploader.InstallDataUploader;
import com.ciba.data.synchronize.uploader.OperationDataUploader;
import com.ciba.data.synchronize.uploader.ProcessDataUploader;
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
    private OperationDataUploader operationDataUploader;

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

    public void uploadOperationData(List<OperationData> eventMotionList) {
        checkOperationDataUploader();
        operationDataUploader.uploadOperationDataData(eventMotionList);
    }

    private void checkOperationDataUploader() {
        if (operationDataUploader == null) {
            operationDataUploader = new SampleOperationDataUploader();
        }
    }
}
