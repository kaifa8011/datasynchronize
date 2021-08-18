package com.ciba.data.synchronize.util;

/**
 * @author songzi
 * @date 2021/5/20
 */
public class InstallListReadTimeController {

    /**
     * 安装列表读取间隔 3天
     */
    private static final long INSTALL_LIST_READ_TIME_INTERVAL_TIME = 1000 * 60 * 60 * 24 * 3;
    /**
     * 安装列表KEY
     */
    public static final String KEY_INSTALL_LIST_READ_TIME = "LOG_INSTALL_LIST_READ_TIME";


    /**
     * 根据本地配置信息判断是否可读取手机安装列表
     *
     * @return
     */
    public static boolean isLocalInstallListRead() {
        // 获取最后一次安装列表读取时间
        Long lastInstallListReadTime = SPUtil.getLong(KEY_INSTALL_LIST_READ_TIME);
        // 当前时间
        Long timeMillis = System.currentTimeMillis();
        // 当前上传间隔时长是否小于INSTALL_LIST_READ_TIME_INTERVAL_TIME间隔
        if (timeMillis - lastInstallListReadTime < INSTALL_LIST_READ_TIME_INTERVAL_TIME) {
            return false;
        }
        return true;
    }


    /**
     * 更新本地上传安装列表时间
     */
    public static void saveLocalInstallListReadTime() {
        Long installListReadTime = System.currentTimeMillis();
        SPUtil.putLong(KEY_INSTALL_LIST_READ_TIME, installListReadTime);
    }


}
