package com.ciba.datasynchronize.common;

import android.content.Context;
import com.ciba.datasynchronize.DataGatherListener;
import com.ciba.datasynchronize.entity.CustomPackageInfo;
import com.ciba.datasynchronize.entity.DeviceData;
import com.ciba.datasynchronize.entity.ProcessData;
import com.ciba.datasynchronize.manager.LoaderUploaderManager;

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

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public Context getContext() {
        return context;
    }

    public DataGatherListener getDataGatherListener() {
        return dataGatherListener;
    }
}
