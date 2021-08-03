package com.ciba.data.synchronize.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ciba.data.synchronize.common.DataSynchronizeManager;

import java.util.Set;

/**
 * @author ciba
 * @description SharedPreferences工具类
 * @date 2018/10/26
 */
public class SPUtil {
    private static final String PREFS_NAME = "com.ciba.data";

    /**
     * 动态域名初始化成功标记
     */
    public static final String DYNAMIC_DOMAIN_INIT_SUCCESS_FLAG_KEY = "dynamic_domain_init_success_flag";
    /**
     * 动态域名key
     */
    public static final String DYNAMIC_DOMAIN_KEY = "dynamic_domain";


    public static SharedPreferences getSP() {
        return DataSynchronizeManager.getInstance().getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /***************************string********************************/

    public static void putString(String key, String value) {
        getSP().edit().putString(key, value).apply();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return getSP().getString(key, defaultValue);
    }

    /***************************long********************************/

    public static void putLong(String key, long value) {
        getSP().edit().putLong(key, value).apply();
    }

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static long getLong(String key, long defaultValue) {
        return getSP().getLong(key, defaultValue);
    }

    /***************************boolean********************************/

    public static void putBoolean(String key, boolean value) {
        getSP().edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getSP().getBoolean(key, defaultValue);
    }

    /***************************float********************************/

    public static void putFloat(String key, float value) {
        getSP().edit().putFloat(key, value).apply();
    }

    public static float getFloat(String key) {
        return getFloat(key, 0f);
    }

    public static float getFloat(String key, float defaultValue) {
        return getSP().getFloat(key, defaultValue);
    }


    public static void putStringSet(String key, Set<String> values) {
        getSP().edit().putStringSet(key, values).apply();
    }

    public static Set<String> getStringSet(String key) {
        return getSP().getStringSet(key, null);
    }


    public static boolean getHostInitSuccessFlag() {
        return getBoolean(DYNAMIC_DOMAIN_INIT_SUCCESS_FLAG_KEY);
    }

    public static void putHostInitSuccessFlag(boolean flag) {
         putBoolean(DYNAMIC_DOMAIN_INIT_SUCCESS_FLAG_KEY, flag);
    }

    public static String getDynamicDomain() {
        return getString(DYNAMIC_DOMAIN_KEY);
    }

    public static void putDynamicDomain(String domain) {
        putString(DYNAMIC_DOMAIN_KEY, domain);
    }

}
