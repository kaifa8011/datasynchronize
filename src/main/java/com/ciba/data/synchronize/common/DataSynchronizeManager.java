package com.ciba.data.synchronize.common;

import android.content.Context;

import com.ciba.data.synchronize.DataGatherListener;
import com.ciba.data.synchronize.OnDeviceDataUpLoadListener;
import com.ciba.data.synchronize.entity.CustomPackageInfo;
import com.ciba.data.synchronize.entity.DeviceData;
import com.ciba.data.synchronize.entity.ProcessData;
import com.ciba.data.synchronize.manager.LoaderUploaderManager;
import com.ciba.datasynchronize.BuildConfig;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/6
 */
public class DataSynchronizeManager {
    private static DataSynchronizeManager instance;
    private Context context;
    private DataGatherListener dataGatherListener;
    private String dataGatherSdkVersion;
    private int flag;

    private DataSynchronizeManager() {
        dataGatherListener = new DataGatherListener() {
            @Override
            public void onDataGather(String crashData
                    , DeviceData deviceData
                    , List<CustomPackageInfo> installPackageList
                    , List<ProcessData> appProcessList
                    , OnDeviceDataUpLoadListener upLoadListener
                    , boolean isGetMachineId) {
                if (isGetMachineId) {
                    LoaderUploaderManager.getInstance().uploadDeviceData(deviceData, upLoadListener);
                } else {
                    LoaderUploaderManager.getInstance().uploadData(crashData, deviceData, installPackageList, appProcessList);
                }
            }
        };
    }

    public static DataSynchronizeManager getInstance() {
        if (instance == null) {
            synchronized (DataSynchronizeManager.class) {
                if (instance == null) {
                    instance = new DataSynchronizeManager();
                }
            }
        }
        return instance;
    }

    public void init(Context context, String dataGatherSdkVersion) {
        this.context = context.getApplicationContext();
        this.dataGatherSdkVersion = dataGatherSdkVersion;
    }

    public Context getContext() {
        return context;
    }

    public DataGatherListener getDataGatherListener() {
        return dataGatherListener;
    }

    public String getSdkVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public String getDataGatherSdkVersion() {
        return dataGatherSdkVersion;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
