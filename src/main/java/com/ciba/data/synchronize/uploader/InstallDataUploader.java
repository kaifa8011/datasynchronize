package com.ciba.data.synchronize.uploader;

import com.ciba.data.synchronize.entity.CustomPackageInfo;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/11
 */
public interface InstallDataUploader {
    void uploadInstallData(List<CustomPackageInfo> installPackageList);
}
