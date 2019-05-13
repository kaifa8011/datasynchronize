package com.ciba.datasynchronize.entity;

import java.util.Map;

/**
 * Created by lenovo on 2018/1/25.
 */

public class OperationData {
    public static final String TYPE_CRASH = "CRASH";
    public static final String TYPE_OPEN = "OPEN";
    public static final String TYPE_CLOSE = "CLOSE";
    public static final String TYPE_CLICK = "CLICK";
    private String operationType;
    private int machineType = 1;
    private String scheme;
    private String startCooX;
    private String endCooX;
    private String startCooY;
    private String endCooY;
    private String startTime;
    private String endTime;
    private String packageName;
    private String versionNo;
    private Map<String, String> customParam;
    private long machineId;

    public int getMachineType() {
        return machineType;
    }

    public void setMachineType(int machineType) {
        this.machineType = machineType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getStartCooX() {
        return startCooX;
    }

    public void setStartCooX(String startCooX) {
        this.startCooX = startCooX;
    }

    public String getEndCooX() {
        return endCooX;
    }

    public void setEndCooX(String endCooX) {
        this.endCooX = endCooX;
    }

    public String getStartCooY() {
        return startCooY;
    }

    public void setStartCooY(String startCooY) {
        this.startCooY = startCooY;
    }

    public String getEndCooY() {
        return endCooY;
    }

    public void setEndCooY(String endCooY) {
        this.endCooY = endCooY;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public Map<String, String> getCustomParam() {
        return customParam;
    }

    public void setCustomParam(Map<String, String> customParam) {
        this.customParam = customParam;
    }

    @Override
    public String toString() {
        return "OperationBean{" +
                "operationType='" + operationType + '\'' +
                ", machineType=" + machineType +
                ", scheme='" + scheme + '\'' +
                ", startCooX='" + startCooX + '\'' +
                ", endCooX='" + endCooX + '\'' +
                ", startCooY='" + startCooY + '\'' +
                ", endCooY='" + endCooY + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionNo='" + versionNo + '\'' +
                ", machineId=" + machineId +
                '}';
    }
}
