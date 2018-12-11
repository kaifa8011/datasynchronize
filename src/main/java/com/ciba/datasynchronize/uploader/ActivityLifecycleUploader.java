package com.ciba.datasynchronize.uploader;

import android.app.Activity;

/**
 * @author ciba
 * @description Activity生命周期统计上报者
 * @date 2018/12/3
 */
public interface ActivityLifecycleUploader {
    int ACTIVITY_CREATED = 0;
    int ACTIVITY_STARTED = 1;
    int ACTIVITY_RESUMED = 2;
    int ACTIVITY_PAUSED = 3;
    int ACTIVITY_STOPPED = 4;
    int ACTIVITY_SAVE_INSTANCE_STATE = 5;
    int ACTIVITY_DESTROYED = 6;

    /**
     * Activity生命周期统计上报
     *
     * @param lifecycle ：生命周期
     * @param activity  ：当前Activity
     */
    void uploadActivityLifecycle(int lifecycle, Activity activity);
}
