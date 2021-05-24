package com.ciba.data.synchronize.entity;

/**
 * Created by lenovo on 2018/1/24.
 */

public class CustomPackageInfo {

    private String packageName;
    private String applyName;
    private String versionNo;
    private String versionName;
    private long machineId;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        CustomPackageInfo customPackageInfo = null;
        if (obj instanceof CustomPackageInfo) {
            customPackageInfo = (CustomPackageInfo) obj;
            return this.packageName.equals(customPackageInfo.getPackageName());
        } else {
            return false;
        }

    }
}
