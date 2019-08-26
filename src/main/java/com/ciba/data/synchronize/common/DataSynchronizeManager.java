package com.ciba.data.synchronize.common;

import android.content.Context;

import com.ciba.data.synchronize.DataGatherListener;
import com.ciba.data.synchronize.entity.CustomPackageInfo;
import com.ciba.data.synchronize.entity.DeviceData;
import com.ciba.data.synchronize.entity.ProcessData;
import com.ciba.data.synchronize.manager.LoaderUploaderManager;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/6
 */
public class DataSynchronizeManager {
    /**
     * TODO: 2019/1/21 : 更新SDK版本号
     */
    private final static String SDK_VERSION = "0.4.2";
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
                    , List<ProcessData> appProcessList) {
                LoaderUploaderManager.getInstance().uploadData(crashData, deviceData, installPackageList, appProcessList);
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
        return SDK_VERSION;
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
