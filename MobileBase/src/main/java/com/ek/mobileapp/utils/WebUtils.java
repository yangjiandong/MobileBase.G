package com.ek.mobileapp.utils;

public class WebUtils {
    public static final int WEBERROR = -1;
    public static final int APPLICATIONERROR = -2;
    public static final int SUCCESS = 0;
    public static final int NOSESSION = 1;
    public static final int AUTHERROR = 2;
    public static final int LOGINERROR = 4;

    public static final String HTTP = "http://";
    //public static final String HOST = "172.20.141.1:8080/mobile";
    public static final String NEWS = "/common/host_info?typeCode=";
    public static final String LOGINACTION = "/common/logon?type=mobile&typeCode=";
    public static final String LOGOUTACTION = "/common/logout?type=mobile";
    public static final String USERLOG = "/system/user_log?type=mobile";
    public static final String MOBLOG = "/moblog/save?type=mobile";
    public static final String UPDATE = "/common/update?type=mobile";

    public static final String GETGENERALINFOACTION = "/control?action=generalinfo";

    public static final String UPDATEDOWNLOAD = "/common/downloadFile?type=mobile&typeCode=";
    public static final String GETLASTDEPLOY = "/common/get_last_deploy?type=mobile";
}
