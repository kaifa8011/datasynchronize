package com.ciba.data.synchronize.manager;

import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ciba.data.synchronize.common.DataSynchronizeManager;
import com.ciba.data.synchronize.constant.Constant;
import com.ciba.data.synchronize.util.SPUtil;

/**
 * @author ciba
 * @date 2018/4/3
 * @description 数据缓存存取帮助类
 */

public class DataCacheManager {
    private static DataCacheManager instance;
    private String currLat;
    private String currLng;
    private String userAgent;
    private long machineId;

    private DataCacheManager() {
    }

    public static DataCacheManager getInstance() {
        if (instance == null) {
            synchronized (DataCacheManager.class) {
                if (instance == null) {
                    instance = new DataCacheManager();
                }
            }
        }
        return instance;
    }

    public void saveLngAndLat(double lng, double lat) {
        currLat = lat + "";
        SPUtil.putString(Constant.SP_CACHE_LAT, lat + "");

        currLng = lng + "";
        SPUtil.putString(Constant.SP_CACHE_LNG, lng + "");

        SPUtil.putLong(Constant.SP_CACHE_LNG_LAT_TIME, System.currentTimeMillis());
    }

    public long getLngLatTime() {
        return SPUtil.getLong(Constant.SP_CACHE_LNG_LAT_TIME);
    }

    public String getLat() {
        if (TextUtils.isEmpty(currLat)) {
            currLat = SPUtil.getString(Constant.SP_CACHE_LAT);
        }
        return currLat;
    }

    public String getLng() {
        if (TextUtils.isEmpty(currLng)) {
            currLng = SPUtil.getString(Constant.SP_CACHE_LNG);
        }
        return currLng;
    }

    /**
     * 获取UserAgent
     */
    public String getUserAgent() {
        if (TextUtils.isEmpty(userAgent)) {
            userAgent = SPUtil.getString(Constant.SP_CACHE_UA);
            if (TextUtils.isEmpty(userAgent)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    try {
                        userAgent = WebSettings.getDefaultUserAgent(DataSynchronizeManager.getInstance().getContext());
                    } catch (Exception e) {
                        userAgent = getUserAgentWithWebViewOrProperty();
                    }
                } else {
                    userAgent = getUserAgentWithWebViewOrProperty();
                }
                if (!TextUtils.isEmpty(userAgent)) {
                    SPUtil.putString(Constant.SP_CACHE_UA, userAgent);
                }
            }
        }
        return userAgent;
    }

    /**
     * 通过WebView或者
     */
    private String getUserAgentWithWebViewOrProperty() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                WebView webView = new WebView(DataSynchronizeManager.getInstance().getContext());
                userAgent = webView.getSettings().getUserAgentString();
                ViewParent parent = webView.getParent();
                if (parent != null && parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(webView);
                }
                webView.stopLoading();
                webView.clearView();
                webView.removeAllViews();
                webView.destroy();
                webView = null;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    userAgent = System.getProperty("http.agent");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            try {
                userAgent = System.getProperty("http.agent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userAgent;
    }

    /**
     * 保存Crash的信息到SP
     */
    public void saveCrashData(String crashData) {
        // 不要apply()异步存储有几率丢失
        SPUtil.getSP().edit().putString(Constant.SP_CACHE_CRASH_DATA, crashData).commit();
    }

    /**
     * 获取SP中的Crash信息
     */
    public String getCrashData() {
        return SPUtil.getString(Constant.SP_CACHE_CRASH_DATA);
    }

    public void saveMachineId(long machineId) {
        this.machineId = machineId;
        SPUtil.putLong(Constant.SP_CACHE_MACHINE_ID, machineId);
    }

    public long getMachineId() {
        if (machineId != 0) {
            return machineId;
        }
        machineId = SPUtil.getLong(Constant.SP_CACHE_MACHINE_ID);
        return machineId;
    }
}
