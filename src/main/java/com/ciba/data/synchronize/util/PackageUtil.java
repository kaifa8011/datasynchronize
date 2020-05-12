package com.ciba.data.synchronize.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ciba.data.synchronize.common.DataSynchronizeManager;

/**
 * @author ciba
 * @description 与安装包有关的工具类
 * @date 2018/12/3
 */
public class PackageUtil {

    /**
     * 获取包名
     */
    public static String getPackageName() {
        return DataSynchronizeManager.getInstance().getContext().getPackageName();
    }

    /**
     * 获取版本名称
     */
    public static String getVersionName() {
        try {
            PackageInfo packageInfo = getPackageInfo();
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取版本号
     */
    public static int getVersionCode() {
        try {
            PackageInfo packageInfo = getPackageInfo();
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 获取包信息
     */
    public static PackageInfo getPackageInfo() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取包管理器
     */
    public static PackageManager getPackageManager() {
        return DataSynchronizeManager.getInstance().getContext().getPackageManager();
    }
}
