package com.ek.mobileapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 */
@SuppressLint("NewApi")
public class AppConfig {
    public static final String SETTING_HTTP_IP ="setting_http_ip";

    //首页轮询时间
    public static final int TIMEOUT = 300 * 1000;//300
    public static final String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss.SS";

    //bluetooth
    public static final String BLUETOOTH_COMMON = "1";
    public static final String BLUETOOTH_UIDS = "2";
    public static final String BLUETOOTH_ZBK = "3";

    private final static String APP_CONFIG = "config";
    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
    //public final static String CONF_COOKIE = "cookie";
    public final static String CONF_VOICE = "perf_voice";
    public final static String CONF_USE_VOICE = "use_voice";
    public final static String CONF_USE_WEBLOG = "setting_weblog";
    public final static String CONF_SETTING_HTTP_IP = "SETTING_HTTP_IP";
    public final static String CONF_USERNAME = "username";
    public final static String CONF_OLD_PWD = "old_password";
    public final static String CONF_PWD = "pwd";
    public final static String CONF_ISSAVE = "issave";
    public final static String CONF_ISAUTO = "isauto";
    public final static String CONF_USERLIST = "ulist";
    public final static String CONF_HOSTLIST = "hlist";
    public final static String CONF_CUSTOMER = "customer";
    public final static String CONF_BLUETOOTHDEVICE = "blueToothDevice";
    public final static String CONF_BLUETOOTHDEVICE_ADDRESS = "blueToothDevice_Address";
    public final static String CONF_BLUETOOTHDEVICE_TYPE = "blueToothDevice_Type";

    public final static String SAVE_IMAGE_PATH = "save_image_path";
    @SuppressLint("NewApi")
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator
            + MainApplication.getMyPackageName() + File.separator + "images";

    private Context mContext;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }

    /**
     * 获取Preference设置
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //public String getCookie() {
    //    return get(CONF_COOKIE);
    //}

    public String get(String key) {
        Properties props = get();
        return (props != null) ? props.getProperty(key) : null;
    }

    public Properties get() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            // 读取files目录下的config
            // fis = activity.openFileInput(APP_CONFIG);

            // 读取app_config目录下的config
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG);

            props.load(fis);
        } catch (Exception e) {
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return props;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            // 把config建在files目录下
            // fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

            // 把config建在(自定义)app_config的目录下
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    public void set(Properties ps) {
        Properties props = get();
        props.putAll(ps);
        setProps(props);
    }

    public void set(String key, String value) {
        Properties props = get();
        props.setProperty(key, value);
        setProps(props);
    }

    public void remove(String... key) {
        Properties props = get();
        for (String k : key)
            props.remove(k);
        setProps(props);
    }
}
