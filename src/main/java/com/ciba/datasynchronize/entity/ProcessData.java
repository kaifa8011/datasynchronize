package com.ciba.datasynchronize.entity;

/**
 * Created by lenovo on 2018/2/2.
 */

public class ProcessData {

    private String packageName;
    private String startTime;
    private String name;
    private long machineId;
    public boolean foreground;
    public int uid;
    public int pid;

    public ProcessData(String packageName, String startTime, String name, boolean foreground, int uid, int pid) {
        this.packageName = packageName;
        this.startTime = startTime;
        this.name = name;
        this.foreground = foreground;
        this.uid = uid;
        this.pid = pid;
    }

    public boolean isForeground() {
        return foreground;
    }

    public void setForeground(boolean foreground) {
        this.foreground = foreground;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
