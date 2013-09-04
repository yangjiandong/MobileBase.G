package com.ek.mobileapp.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.model.ChartData;
import com.ek.mobileapp.model.UserDTO;

public class GlobalCache {

    private static GlobalCache cache = null;
    static String appUserAgent = "";
    boolean demo = false;

    //是否启用调试
    private boolean useDebug = false;
    //后台分页
    private int pageLimit = 20;
    private String cookie = "";

    private UserDTO loginuser;
    private String lastIp;//客户端ip
    private String hostIp;//服务端ip
    private String startDate = "";
    private String endDate = "";
    private int screenWidth;
    private int screenHeight;
    private String deviceId = "";
    private boolean webLog;

    private String hospitalName;
    private String userCaption;//用户身份,主任,组长..

    //显示图表用参数和数据
    private Map<String, List<ChartData>> chartDataMap = new HashMap<String, List<ChartData>>();
    private String[] chartHeaders;
    private Map<String, String> chartContent = new HashMap<String, String>();

    //蓝牙扫描仪
    //String blueToothDevice = Constants.BLUETOOTH_COMMON;

    //条码判断规则
    String barcode_patient;
    String barcode_orderno;
    String barcode_lis;
    String barcode_outpatient;

	protected GlobalCache() {
    }

    public static synchronized GlobalCache getCache() {
        return getCache(null);
    }

    public static synchronized void clearCache() {
        cache = null;
    }

    public static synchronized GlobalCache getCache(String name) {
        if (cache == null) {
            cache = new GlobalCache();
        }

        return cache;
    }

    public String getUserAgent() {
        return appUserAgent;
    }

    public String getUserAgent(MainApplication appContext) {
        if (appUserAgent == null || appUserAgent == "") {
            StringBuilder ua = new StringBuilder("eksoft.mobile");
            ua.append(" package[" + appContext.getPackageInfo().packageName);//标识
            ua.append("] version[" + appContext.getPackageInfo().versionCode + "_"
                    + appContext.getPackageInfo().versionName);//App版本
            ua.append("] Android ");//手机系统平台
            ua.append("osversion[" + android.os.Build.VERSION.RELEASE);//手机系统版本
            ua.append("] model[" + android.os.Build.MODEL); //手机型号
            ua.append("] deviceid[" + deviceId);//客户端唯一标识
            ua.append("]");
            appUserAgent = ua.toString();
        }
        return appUserAgent;
    }

    public UserDTO getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(UserDTO loginuser) {
        this.loginuser = loginuser;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public boolean isUseDebug() {
        return useDebug;
    }

    public void setUseDebug(boolean useDebug) {
        this.useDebug = useDebug;
    }

    public String getDeviceId() {
        if (appUserAgent == null || appUserAgent == "") {
            return deviceId;
        } else {
            return appUserAgent;
        }
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isWebLog() {
        return webLog;
    }

    public void setWebLog(boolean webLog) {
        this.webLog = webLog;
    }

    public Map<String, List<ChartData>> getChartDataMap() {
        return chartDataMap;
    }

    public void setChartDataMap(Map<String, List<ChartData>> chartDataMap) {
        this.chartDataMap = chartDataMap;
    }

    public String[] getChartHeaders() {
        return chartHeaders;
    }

    public void setChartHeaders(String[] chartHeaders) {
        this.chartHeaders = chartHeaders;
    }

    public Map<String, String> getChartContent() {
        return chartContent;
    }

    public void setChartContent(Map<String, String> chartContent) {
        this.chartContent = chartContent;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getUserCaption() {
        return userCaption;
    }

    public void setUserCaption(String userCaption) {
        this.userCaption = userCaption;
    }

    public String getBarcode_patient() {
        return barcode_patient;
    }

    public void setBarcode_patient(String barcode_patient) {
        this.barcode_patient = barcode_patient;
    }

    public String getBarcode_orderno() {
        return barcode_orderno;
    }

    public void setBarcode_orderno(String barcode_orderno) {
        this.barcode_orderno = barcode_orderno;
    }

    public String getBarcode_lis() {
        return barcode_lis;
    }

    public void setBarcode_lis(String barcode_lis) {
        this.barcode_lis = barcode_lis;
    }

    public String getBarcode_outpatient() {
		return barcode_outpatient;
	}

	public void setBarcode_outpatient(String barcode_outpatient) {
		this.barcode_outpatient = barcode_outpatient;
	}
}
