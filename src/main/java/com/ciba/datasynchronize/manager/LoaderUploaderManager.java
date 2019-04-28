package com.ciba.datasynchronize.manager;

import android.app.Activity;

import com.ciba.datasynchronize.entity.CustomPackageInfo;
import com.ciba.datasynchronize.entity.DeviceData;
import com.ciba.datasynchronize.entity.OperationData;
import com.ciba.datasynchronize.entity.ProcessData;
import com.ciba.datasynchronize.loder.LocationLoader;
import com.ciba.datasynchronize.sample.manager.SampleLoaderUploaderManager;
import com.ciba.datasynchronize.uploader.ActivityLifecycleUploader;
import com.ciba.datasynchronize.uploader.CrashDataUploader;
import com.ciba.datasynchronize.uploader.DeviceDataUploader;
import com.ciba.datasynchronize.uploader.InstallDataUploader;
import com.ciba.datasynchronize.uploader.OperationDataUploader;
import com.ciba.datasynchronize.uploader.ProcessDataUploader;

import java.util.List;


/**
 * @author ciba
 * @description 加载器和上传器管理，管理自定义的数据加载和数据上传逻辑
 * @date 2018/12/4
 */
public class LoaderUploaderManager {
    private static LoaderUploaderManager instance;
    private LocationLoader locationLoader;
    private ActivityLifecycleUploader activityLifecycleUploader;
    private DeviceDataUploader deviceDataUploader;
    private CrashDataUploader crashDataUploader;
    private InstallDataUploader installDataUploader;
    private ProcessDataUploader processDataUploader;
    private OperationDataUploader operationDataUploader;

    private LoaderUploaderManager() {
    }

    public static LoaderUploaderManager getInstance() {
        if (instance == null) {
            synchronized (LoaderUploaderManager.class) {
                if (instance == null) {
                    instance = new LoaderUploaderManager();
                }
            }
        }
        return instance;
    }

    public void uploadData(String crashData
            , DeviceData deviceData
            , List<CustomPackageInfo> installPackageList
            , List<ProcessData> appProcessList) {

        // 上传Crash信息
        uploadCrashData(crashData);

        // 获取设备唯一标识
        long machineId = DataCacheManager.getInstance().getMachineId();

        if (deviceDataUploader != null) {
            // 如果自定义的设备信息上传器不为空，则使用自定义的上传
            deviceDataUploader.uploadDeviceData(deviceData);
        } else {
            if (0 != machineId) {
                // 如果唯一标识不为0，则同时上传设备信息和安装列表和启动列表
                uploadDeviceData(deviceData);
                uploadInstallData(installPackageList);
                uploadProcessData(appProcessList);
            } else {
                // 否则使用默认的设备上传器上传
                SampleLoaderUploaderManager.getInstance().uploadDeviceData(deviceData, installPackageList, appProcessList);
            }
        }
    }

    /**
     * 设置自定义地理位置Loader（主要用于本地没有获取到之后的补充）
     */
    public void setLocationLoader(LocationLoader locationLoader) {
        this.locationLoader = locationLoader;
    }

    public void loaderLocation() {
        if (locationLoader != null) {
            locationLoader.loaderLocation();
        }
    }

    /**
     * 设置Activity生命周期统计上报者（Activity生命周期统计交由用户实现）
     */
    public void setActivityLifecycleUploader(ActivityLifecycleUploader activityLifecycleUploader) {
        this.activityLifecycleUploader = activityLifecycleUploader;
    }

    public void uploadActivityLifecycle(int lifecycle, Activity activity) {
        if (activity == null) {
            return;
        }
        if (activityLifecycleUploader != null) {
            activityLifecycleUploader.uploadActivityLifecycle(lifecycle, activity);
        } else {
            SampleLoaderUploaderManager.getInstance().uploadActivityLifecycle(lifecycle, activity);
        }
    }

    /**
     * 设置设备信息上传者
     */
    public void setDeviceDataUploader(DeviceDataUploader deviceDataUploader) {
        this.deviceDataUploader = deviceDataUploader;
    }

    /**
     * 上传搜集的设备信息
     */
    public void uploadDeviceData(DeviceData deviceData) {
        if (deviceData == null) {
            return;
        }
        if (deviceDataUploader != null) {
            deviceDataUploader.uploadDeviceData(deviceData);
        } else {
            // 设备信息上传样例
            SampleLoaderUploaderManager.getInstance().uploadDeviceData(deviceData, null, null);
        }
    }

    /**
     * 设置Crash信息上传者
     */
    public void setCrashDataUploader(CrashDataUploader crashDataUploader) {
        this.crashDataUploader = crashDataUploader;
    }

    /**
     * 上传搜集的Crash信息
     */
    public void uploadCrashData(String crashData) {
        if (crashData == null) {
            return;
        }
        if (crashDataUploader != null) {
            crashDataUploader.uploadCrashData(crashData);
        } else {
            SampleLoaderUploaderManager.getInstance().uploadCrashData(crashData);
        }
    }

    /**
     * 设置Crash信息上传者
     */
    public void setInstallDataUploader(InstallDataUploader installDataUploader) {
        this.installDataUploader = installDataUploader;
    }

    public void uploadInstallData(List<CustomPackageInfo> installPackageList) {
        if (installPackageList == null || installPackageList.isEmpty()) {
            return;
        }
        if (installDataUploader != null) {
            installDataUploader.uploadInstallData(installPackageList);
        } else {
            SampleLoaderUploaderManager.getInstance().uploadInstallData(installPackageList);
        }
    }

    /**
     * 设置Crash信息上传者
     */
    public void setProcessDataUploader(ProcessDataUploader processDataUploader) {
        this.processDataUploader = processDataUploader;
    }

    public void uploadProcessData(List<ProcessData> processDataList) {
        if (processDataList == null || processDataList.isEmpty()) {
            return;
        }
        if (processDataUploader != null) {
            processDataUploader.uploadProcessData(processDataList);
        } else {
            SampleLoaderUploaderManager.getInstance().uploadProcessData(processDataList);
        }
    }

    /**
     * 设置Activity生命周期统计上报者（Activity生命周期统计交由用户实现）
     */
    public void setOperationDataUploader(OperationDataUploader operationDataUploader) {
        this.operationDataUploader = operationDataUploader;
    }

    public void uploadOperationData(List<OperationData> operationDataList) {
        if (operationDataUploader != null) {
            operationDataUploader.uploadOperationDataData(operationDataList);
        } else {
            SampleLoaderUploaderManager.getInstance().uploadOperationData(operationDataList);
        }
    }
}
