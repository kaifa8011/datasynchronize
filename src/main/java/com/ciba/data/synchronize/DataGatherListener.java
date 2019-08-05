package com.ciba.data.synchronize;

import com.ciba.data.synchronize.entity.CustomPackageInfo;
import com.ciba.data.synchronize.entity.DeviceData;
import com.ciba.data.synchronize.entity.ProcessData;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/6
 */
public interface DataGatherListener {
    void onDataGather(String crashData, DeviceData deviceData, List<CustomPackageInfo> installPackageList
            , List<ProcessData> appProcessList);
}
