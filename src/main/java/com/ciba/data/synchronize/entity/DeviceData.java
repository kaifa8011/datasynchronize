package com.ciba.data.synchronize.entity;

/**
 * 全面的设备信息数据
 */
public class DeviceData {

    private long machineId;
    /**
     * 屏幕宽度
     */
    private int screenWidth;
    /**
     * 屏幕高度
     */
    private int screenHeight;
    /**
     * 运营商识别码
     */
    private String imsi = "";

    private int machineType;
    /**
     * 浏览器的user-agent
     */
    private String ua = "";
    /**
     * 网卡地址
     */
    private String networkAddress = "";
    /**
     * 网络类型
     */
    private String networkType = "";
    /**
     * densityDpi
     */
    private String sdScreendpi = "";
    /**
     * IMEI号
     */
    private String imei = "";
    /**
     * 手机操作系统版本号
     */
    private String osVersion = "";
    /**
     * 手机生产厂商
     */
    private String vendor = "";
    /**
     * 手机型号
     */
    private String modelNo = "";
    private String androidId = "";
    private String idfa = "";
    private String openUdid = "";
    /**
     * 纬度
     */
    private double lat;
    /**
     * 经度
     */
    private double lng;
    /**
     * ip地址
     */
    private String ip = "";

    /**
     * 设备类型 5 - pad ， 4 - 手机
     */
    private int deviceType;
    /**
     * android 设备的 Android Advertising ID，在保证取值正确有效的前提下填写，用于定向优化
     */
    private String advertisingId = "";
    private String idfv = "";
    /**
     * 设备的语言设置
     */
    private String language = "";
    /**
     * 设备电量百分比，取整数，数值区间0~100。
     */
    private int battery;
    /**
     * 设备是否root
     */
    private int isroot;
    /**
     * 蓝牙mac号。有蓝牙功能的设备存在该参数
     */
    private String btmac = "";
    /**
     * 安卓设备pseudo-unique id
     */
    private String pdunid = "";
    /**
     * cpu类型
     */
    private String cputy = "";

    /**
     * 国家
     */
    private String country = "";
    /**
     * 坐标类型 1：全球卫星定位系统坐标系，2：国家测绘局坐标系，3：百度坐标系，4：其他坐标系
     */
    private int coordinateType;
    /**
     * 经纬度精度半径，单位为米。
     */
    private double locaAccuracy;
    /**
     * 获取经纬度(lat/lng)的时间。
     */
    private long coordTime;

    /**
     * wifi地址
     */
    private String bssId = "";

    /**
     * 移动国家代码
     */
    private String mcc = "";
    /**
     * wifi网络id
     */
    private String netwkId = "";
    /**
     * 局域网名称
     */
    private String ssid = "";
    /**
     * wifi连接速度
     */
    private int lksd;
    /**
     * 手机接收信号强度
     */
    private int rssi;
    /**
     * 是否漫游。
     */
    private int roaming;

    /**
     * cpu型号
     */
    private int cpuType;
    /**
     * cpu子型号
     */
    private int cpuSubtype;

    /**
     * 同一WIFI下其他设备信息（mac、ip）
     */
    private String nd = "";

    private CustomBluetoothInfo bluetoothInfo;

    private String wordSize = "";

    private String uqid = "";

    private String oaid = "";

    private String vaid = "";

    private String hasReadExternalPermission = "";

    /**
     * 周边wifi信息
     */
    private String surroundingWifi = "";

    /**
     * 总硬盘容量 byte
     */
    private long capacity;

    /**
     * 硬盘剩余容量 byte
     */
    private long remainCapacity;

    /**
     * 手机屏幕亮度，正常情况下是1-255，个别品牌手机阈值不能确定 1-1024
     */
    private int brightness;

    /**
     * 开机时间戳,单位:ms
     */
    private long uptime;

    /**
     * 运行时间，单位:ms
     */
    private long runtime;

    /**
     * 时区
     */
    private String timezone = "";

    /**
     * 是否开网络代理，1 是，0 否
     */
    private int isagent;

    /**
     * 是否开VPN，1 是，0 否
     */
    private int isvpn;

    /**
     * 是否release包，1 是，0 否
     */
    private int isrelease;

    /**
     * 安卓签名指纹
     */
    private String sign = "";

    /**
     * 是否充电中，1 是，0 否
     */
    private int ischarging;

    /**
     * 是否debug模式，1 是，0 否
     */
    private int isdebug;

    /**
     * rom软件版本号
     */
    private String romversion = "";

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getIsagent() {
        return isagent;
    }

    public void setIsagent(int isagent) {
        this.isagent = isagent;
    }

    public int getIsrelease() {
        return isrelease;
    }

    public void setIsrelease(int isrelease) {
        this.isrelease = isrelease;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getIscharging() {
        return ischarging;
    }

    public void setIscharging(int ischarging) {
        this.ischarging = ischarging;
    }

    public int getIsdebug() {
        return isdebug;
    }

    public void setIsdebug(int isdebug) {
        this.isdebug = isdebug;
    }

    public String getRomversion() {
        return romversion;
    }

    public void setRomversion(String romversion) {
        this.romversion = romversion;
    }

    public int getIsvpn() {
        return isvpn;
    }

    public void setIsvpn(int isvpn) {
        this.isvpn = isvpn;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getRemainCapacity() {
        return remainCapacity;
    }

    public void setRemainCapacity(long remainCapacity) {
        this.remainCapacity = remainCapacity;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public String getSurroundingWifi() {
        return surroundingWifi;
    }

    public void setSurroundingWifi(String surroundingWifi) {
        this.surroundingWifi = surroundingWifi;
    }

    public String getHasReadExternalPermission() {
        return hasReadExternalPermission;
    }

    public void setHasReadExternalPermission(String hasReadExternalPermission) {
        this.hasReadExternalPermission = hasReadExternalPermission;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getVaid() {
        return vaid;
    }

    public void setVaid(String vaid) {
        this.vaid = vaid;
    }

    public String getUqid() {
        return uqid;
    }

    public void setUqid(String uqid) {
        this.uqid = uqid;
    }

    public String getWordSize() {
        return wordSize;
    }

    public void setWordSize(String wordSize) {
        this.wordSize = wordSize;
    }

    public CustomBluetoothInfo getBluetoothInfo() {
        return bluetoothInfo;
    }

    public void setBluetoothInfo(CustomBluetoothInfo bluetoothInfo) {
        this.bluetoothInfo = bluetoothInfo;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

//    public String getBscid() {
//        return bscid;
//    }
//
//    public void setBscid(String bscid) {
//        this.bscid = bscid;
//    }
//
//    public String getBsss() {
//        return bsss;
//    }
//
//    public void setBsss(String bsss) {
//        this.bsss = bsss;
//    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getAdvertisingId() {
        return advertisingId;
    }

    public void setAdvertisingId(String advertisingId) {
        this.advertisingId = advertisingId;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getIsroot() {
        return isroot;
    }

    public void setIsroot(int isroot) {
        this.isroot = isroot;
    }

    public String getBtmac() {
        return btmac;
    }

    public void setBtmac(String btmac) {
        this.btmac = btmac;
    }

    public String getPdunid() {
        return pdunid;
    }

    public void setPdunid(String pdunid) {
        this.pdunid = pdunid;
    }

    public String getCputy() {
        return cputy;
    }

    public void setCputy(String cputy) {
        this.cputy = cputy;
    }

//    public String getIccid() {
//        return iccid;
//    }
//
//    public void setIccid(String iccid) {
//        this.iccid = iccid;
//    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(int coordinateType) {
        this.coordinateType = coordinateType;
    }

    public double getLocaAccuracy() {
        return locaAccuracy;
    }

    public void setLocaAccuracy(double locaAccuracy) {
        this.locaAccuracy = locaAccuracy;
    }

    public long getCoordTime() {
        return coordTime;
    }

    public void setCoordTime(long coordTime) {
        this.coordTime = coordTime;
    }

//    public String getCellularId() {
//        return cellularId;
//    }
//
//    public void setCellularId(String cellularId) {
//        this.cellularId = cellularId;
//    }

    public String getBssId() {
        return bssId;
    }

    public void setBssId(String bssId) {
        this.bssId = bssId;
    }

//    public String getLac() {
//        return lac;
//    }
//
//    public void setLac(String lac) {
//        this.lac = lac;
//    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getNetwkId() {
        return netwkId;
    }

    public void setNetwkId(String netwkId) {
        this.netwkId = netwkId;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getLksd() {
        return lksd;
    }

    public void setLksd(int lksd) {
        this.lksd = lksd;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getRoaming() {
        return roaming;
    }

    public void setRoaming(int roaming) {
        this.roaming = roaming;
    }

//    public String getStbif() {
//        return stbif;
//    }
//
//    public void setStbif(String stbif) {
//        this.stbif = stbif;
//    }

    public int getCpuType() {
        return cpuType;
    }

    public void setCpuType(int cpuType) {
        this.cpuType = cpuType;
    }

    public int getCpuSubtype() {
        return cpuSubtype;
    }

    public void setCpuSubtype(int cpuSubtype) {
        this.cpuSubtype = cpuSubtype;
    }

//    public String getMeid() {
//        return meid;
//    }
//
//    public void setMeid(String meid) {
//        this.meid = meid;
//    }
//
//    public String getCid() {
//        return cid;
//    }
//
//    public void setCid(String cid) {
//        this.cid = cid;
//    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public int getMachineType() {
        return machineType;
    }

    public void setMachineType(int machineType) {
        this.machineType = machineType;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getSdScreendpi() {
        return sdScreendpi;
    }

    public void setSdScreendpi(String sdScreendpi) {
        this.sdScreendpi = sdScreendpi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getOpenUdid() {
        return openUdid;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    @Override
    public String toString() {
        return "DeviceData{" +
                "machineId=" + machineId +
                ", screenWidth=" + screenWidth +
                ", screenHeight=" + screenHeight +
                ", imsi='" + imsi + '\'' +
                ", machineType=" + machineType +
                ", ua='" + ua + '\'' +
                ", networkAddress='" + networkAddress + '\'' +
                ", networkType='" + networkType + '\'' +
                ", sdScreendpi='" + sdScreendpi + '\'' +
                ", imei='" + imei + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", vendor='" + vendor + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", androidId='" + androidId + '\'' +
                ", idfa='" + idfa + '\'' +
                ", openUdid='" + openUdid + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", ip='" + ip + '\'' +
                ", deviceType=" + deviceType +
                ", advertisingId='" + advertisingId + '\'' +
                ", idfv='" + idfv + '\'' +
                ", language='" + language + '\'' +
                ", battery=" + battery +
                ", isroot=" + isroot +
                ", btmac='" + btmac + '\'' +
                ", pdunid='" + pdunid + '\'' +
                ", cputy='" + cputy + '\'' +
                ", country='" + country + '\'' +
                ", coordinateType=" + coordinateType +
                ", locaAccuracy=" + locaAccuracy +
                ", coordTime=" + coordTime +
                ", bssId='" + bssId + '\'' +
                ", mcc='" + mcc + '\'' +
                ", netwkId='" + netwkId + '\'' +
                ", ssid='" + ssid + '\'' +
                ", lksd=" + lksd +
                ", rssi=" + rssi +
                ", roaming=" + roaming +
                ", cpuType=" + cpuType +
                ", cpuSubtype=" + cpuSubtype +
                ", nd='" + nd + '\'' +
                ", bluetoothInfo=" + bluetoothInfo +
                ", wordSize='" + wordSize + '\'' +
                ", uqid='" + uqid + '\'' +
                ", oaid='" + oaid + '\'' +
                ", vaid='" + vaid + '\'' +
                ", hasReadExternalPermission='" + hasReadExternalPermission + '\'' +
                ", surroundingWifi='" + surroundingWifi + '\'' +
                ", capacity=" + capacity +
                ", remainCapacity=" + remainCapacity +
                ", brightness=" + brightness +
                ", uptime=" + uptime +
                ", runtime=" + runtime +
                ", timezone='" + timezone + '\'' +
                ", isagent=" + isagent +
                ", isvpn=" + isvpn +
                ", isrelease=" + isrelease +
                ", sign='" + sign + '\'' +
                ", ischarging=" + ischarging +
                ", isdebug=" + isdebug +
                ", romversion='" + romversion + '\'' +
                '}';
    }
}
