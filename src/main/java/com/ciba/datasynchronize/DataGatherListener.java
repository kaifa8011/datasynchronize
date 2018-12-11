package com.ciba.datasynchronize;

import com.ciba.datasynchronize.entity.CustomPackageInfo;
import com.ciba.datasynchronize.entity.DeviceData;
import com.ciba.datasynchronize.entity.ProcessData;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/6
 */
public interface DataGatherListener {
    void onDataGather(String crashData, DeviceData deviceData, List<CustomPackageInfo> installPackageList, List<ProcessData> appProcessList);
}
