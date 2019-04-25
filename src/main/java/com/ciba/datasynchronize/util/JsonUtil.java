package com.ciba.datasynchronize.util;

import android.text.TextUtils;

import com.ciba.datasynchronize.coder.PublicKey;
import com.ciba.datasynchronize.common.DataSynchronizeManager;
import com.ciba.datasynchronize.entity.CustomBluetoothInfo;
import com.ciba.datasynchronize.entity.CustomPackageInfo;
import com.ciba.datasynchronize.entity.DeviceData;
import com.ciba.datasynchronize.entity.OperationData;
import com.ciba.datasynchronize.entity.ProcessData;
import com.ciba.datasynchronize.manager.DataCacheManager;
import com.ciba.datasynchronize.sample.uploader.SampleUploadUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author putao
 * @date 2018/10/30
 * @description : Json解析工具类
 */
public class JsonUtil {

    public static JSONObject deviceData2Json(DeviceData deviceData) {
        if (deviceData == null) {
            return null;
        }
        JSONObject object = null;
        try {
            object = new JSONObject();
            object.put("screenWidth", deviceData.getScreenWidth());
            object.put("screenHeight", deviceData.getScreenHeight());
            object.put("imsi", deviceData.getImsi());
            object.put("machineType", deviceData.getMachineType());
            object.put("ua", deviceData.getUa());
            object.put("networkAddress", deviceData.getNetworkAddress());
            object.put("networkType", deviceData.getNetworkType());
            object.put("sdScreendpi", deviceData.getSdScreendpi());
            object.put("imei", deviceData.getImei());
            object.put("osVersion", deviceData.getOsVersion());
            object.put("vendor", deviceData.getVendor());
            object.put("modelNo", deviceData.getModelNo());
            object.put("androidId", deviceData.getAndroidId());
            object.put("idfa", deviceData.getIdfa());
            object.put("openUdid", deviceData.getOpenUdid());
            object.put("lat", deviceData.getLat() + "");
            object.put("lng", deviceData.getLng() + "");
            object.put("ip", deviceData.getIp());
            object.put("meid", deviceData.getMeid());
            object.put("cid", deviceData.getCid());
            object.put("bscid", deviceData.getBscid());
            object.put("bsss", deviceData.getBsss());
            object.put("deviceType", deviceData.getDeviceType());
            object.put("advertisingId", deviceData.getAdvertisingId());
            object.put("idfv", deviceData.getIdfv());
            object.put("language", deviceData.getLanguage());
            object.put("battery", deviceData.getBattery());
            object.put("isroot", deviceData.getIsroot());
            object.put("btmac", deviceData.getBtmac());
            object.put("pdunid", deviceData.getPdunid());
            object.put("cputy", deviceData.getCputy());
            object.put("iccid", deviceData.getIccid());
            object.put("country", deviceData.getCountry());
            object.put("coordinateType", deviceData.getCoordinateType());
            object.put("locaAccuracy", deviceData.getLocaAccuracy());
            object.put("coordTime", deviceData.getCoordTime());
            object.put("cellularId", deviceData.getCellularId());
            object.put("bssId", deviceData.getBssId());
            object.put("lac", deviceData.getLac());
            object.put("mcc", deviceData.getMcc());
            object.put("netwkId", deviceData.getNetwkId());
            object.put("ssid", deviceData.getSsid());
            object.put("lksd", deviceData.getLksd());
            object.put("rssi", deviceData.getRssi());
            object.put("roaming", deviceData.getRoaming());
            object.put("stbif", deviceData.getStbif());
            object.put("cpuType", deviceData.getCpuType());
            object.put("cpuSubtype", deviceData.getCpuSubtype());
            object.put("wordSize", deviceData.getWordSize());
            object.put("nd", deviceData.getNd() == null ? "" : PublicKey.keyboards(deviceData.getNd()));
            object.put("bt", getBluetoothData(deviceData.getBluetoothInfo()));

            String dataGatherSdkVersion = DataSynchronizeManager.getInstance().getDataGatherSdkVersion();
            String dataSynchronizeSdkVersion = DataSynchronizeManager.getInstance().getSdkVersion();
            if (!TextUtils.isEmpty(dataGatherSdkVersion) && !TextUtils.isEmpty(dataSynchronizeSdkVersion)) {
                object.put("sdkVersion", dataGatherSdkVersion + "-" + dataSynchronizeSdkVersion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 获取加密的蓝牙数据
     */
    private static String getBluetoothData(CustomBluetoothInfo bluetoothInfo) {
        if (bluetoothInfo == null) {
            return "";
        }
        CustomBluetoothInfo.CustomBluetoothDevice bluetoothDevice = bluetoothInfo.getBluetoothDevice();
        List<CustomBluetoothInfo.CustomBluetoothDevice> bondedDevices = bluetoothInfo.getBondedDevices();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("blueToothName", bluetoothDevice == null ? "" : bluetoothDevice.getName());
            jsonObject.put("blueToothMac", bluetoothDevice == null ? "" : bluetoothDevice.getMac());
            if (bondedDevices != null && bondedDevices.size() > 0) {
                JSONArray bondJsonArray = new JSONArray();
                for (int i = 0; i < bondedDevices.size(); i++) {
                    CustomBluetoothInfo.CustomBluetoothDevice bondedDevice = bondedDevices.get(i);
                    if (bondedDevice != null) {
                        JSONObject bondedJsonObject = new JSONObject();
                        bondedJsonObject.put("pairBlueToothName", bondedDevice.getName());
                        bondedJsonObject.put("pairBlueToothMac", bondedDevice.getMac());
                        bondedJsonObject.put("pairBlueToothType", bondedDevice.getType());
                        bondJsonArray.put(bondedJsonObject);
                    }
                }
                jsonObject.put("paired", bondJsonArray);
                bondedDevices.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();
        jsonObject = null;
        String keyboards = PublicKey.keyboards(json);
        json = null;
        return keyboards;
    }

    public static Map<String, String> operationData2Json(OperationData operationData) {
        List<OperationData> operationDataList = new ArrayList<>();
        operationDataList.add(operationData);
        return operationData2Json(operationDataList);
    }

    public static Map<String, String> operationData2Json(List<OperationData> operationDataList) {
        long machineId = DataCacheManager.getInstance().getMachineId();
        if (machineId == 0 || operationDataList == null || operationDataList.isEmpty()) {
            return null;
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (int i = 0; i < operationDataList.size(); i++) {
                JSONObject object = new JSONObject();
                OperationData operationData = operationDataList.get(i);
                object.put("operationType", operationData.getOperationType());
                object.put("machineType", operationData.getMachineType());
                object.put("scheme", operationData.getScheme());
                object.put("startCooX", operationData.getStartCooX());
                object.put("endCooX", operationData.getEndCooX());
                object.put("startCooY", operationData.getStartCooY());
                object.put("endCooY", operationData.getEndCooY());
                object.put("startTime", operationData.getStartTime());
                object.put("endTime", operationData.getEndTime());
                object.put("packageName", operationData.getPackageName());
                object.put("versionNo", operationData.getVersionNo());
                object.put("machineId", operationData.getMachineId());
                jsonArray.put(object);
            }
            operationDataList.clear();
            return SampleUploadUtil.getSameEncryptionParams(machineId, PublicKey.keyboards(jsonArray.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String map2Json(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        JSONObject jsonObject = new JSONObject(params);
        return jsonObject.toString();
    }

    public static String installList2JsonString(List<CustomPackageInfo> customPackageInfoList) {
        if (customPackageInfoList == null || customPackageInfoList.isEmpty()) {
            return "";
        }
        JSONArray jsonArray = new JSONArray();
        try {
            long machineId = DataCacheManager.getInstance().getMachineId();
            for (int i = 0; i < customPackageInfoList.size(); i++) {
                CustomPackageInfo customPackageInfo = customPackageInfoList.get(i);
                JSONObject object = new JSONObject();
                object.put("machineId", machineId);
                object.put("packageName", customPackageInfo.getPackageName());
                object.put("versionNo", customPackageInfo.getVersionNo());
                object.put("applyName", customPackageInfo.getApplyName());
                object.put("versionName", customPackageInfo.getVersionName());
                jsonArray.put(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public static String processData2JsonStr(List<ProcessData> processDataList) {
        if (processDataList == null || processDataList.isEmpty()) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        try {
            long machineId = DataCacheManager.getInstance().getMachineId();
            for (int i = 0; i < processDataList.size(); i++) {
                ProcessData processData = processDataList.get(i);
                JSONObject object = new JSONObject();
                object.put("machineId", machineId);
                object.put("packageName", processData.getPackageName());
                object.put("startTime", processData.getStartTime());
                jsonArray.put(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

}
