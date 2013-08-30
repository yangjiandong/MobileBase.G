package com.ek.mobileapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.DisplayMetrics;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ek.mobileapp.model.Hz;
import com.ek.mobileapp.model.HzList;
import com.ek.mobileapp.model.UserDTO;
import com.ek.mobileapp.utils.GlobalCache;
import com.ek.mobileapp.utils.HttpTool;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.StringUtils;
import com.ek.mobileapp.utils.UtilString;
import com.ek.mobileapp.utils.Utils;
import com.ek.mobileapp.utils.ViewUtils;
import com.ek.mobilebapp.R;

public abstract class MainApplication extends Application {
    abstract public String getModuleName();//return "nurse";
    abstract public int getAppName();
    //是否保存用户cookie
    abstract public boolean saveLoginCookie();

    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "JSESSIONID";//JSESSIONID 为各服务端不同而定

    private static MainApplication _instance;
    private RequestQueue _requestQueue;
    private SharedPreferences _preferences;

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static final int PAGE_SIZE = 20;//默认分页大小
    private static final int CACHE_TIME = 60 * 60000;//缓存失效时间,1个小时

    private boolean login = false; //登录状态
    private int loginUid = 0; //登录用户的id
    private Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();

    private String saveImagePath;//保存图片路径
    public static String host_ip = "";//直接取ip
    public static String myPackageName;//方便直接取包名

    private Handler unLoginHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                com.ek.mobileapp.utils.UIHelper.ToastMessage(MainApplication.this, getString(R.string.msg_login_error));
                com.ek.mobileapp.utils.UIHelper.showLoginDialog(MainApplication.this);
            }
        }
    };

    public static MainApplication get() {
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = this;
        _preferences = PreferenceManager.getDefaultSharedPreferences(this);
        _requestQueue = Volley.newRequestQueue(this);

        myPackageName = getPackageName();
        host_ip = getHost();

        Logger.d("isCurrentLogin:clearCache");
        GlobalCache.clearCache();
        //注册App异常崩溃处理器
        //Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());

        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        GlobalCache.getCache().setDeviceId(deviceId);//tm.getDeviceId());
        //产生客户端http agent
        String agent = GlobalCache.getCache().getUserAgent(this);
        Logger.d("user agent - " + agent);
        Logger.d(getDeviceDisplayDetails());

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //设置保存图片的路径
        saveImagePath = getProperty(AppConfig.SAVE_IMAGE_PATH);
        if (com.ek.mobileapp.utils.StringUtils.isEmpty(saveImagePath)) {
            setProperty(AppConfig.SAVE_IMAGE_PATH, AppConfig.DEFAULT_SAVE_IMAGE_PATH);
            saveImagePath = AppConfig.DEFAULT_SAVE_IMAGE_PATH;
        }
    }

    public boolean isRunningInSmallTablet() {
        boolean flag;
        if ((0xf & getResources().getConfiguration().screenLayout) == Configuration.SCREENLAYOUT_SIZE_LARGE)//3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    /**
     * 获取App安装包信息
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 获取App唯一标识
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    public boolean isCurrentLogin() {
        UserDTO u = GlobalCache.getCache().getLoginuser();
        if (u != null) {
            Logger.d("isCurrentLogin:" + u.getLoginName());
        }
        return u != null;
    }

    public String getDeviceDisplayDetails() {
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        Object aobj[] = new Object[16];
        aobj[0] = configuration.screenLayout;
        aobj[1] = configuration.fontScale;
        aobj[2] = displaymetrics.density;
        aobj[3] = displaymetrics.densityDpi;
        aobj[4] = displaymetrics.xdpi;
        aobj[5] = displaymetrics.ydpi;
        aobj[6] = displaymetrics.widthPixels;
        aobj[7] = displaymetrics.heightPixels;
        aobj[8] = System.getProperty("os.name");
        aobj[9] = System.getProperty("os.version");
        aobj[10] = System.getProperty("os.arch");
        aobj[11] = android.os.Build.VERSION.RELEASE;
        aobj[12] = Build.VERSION.SDK_INT;
        aobj[13] = Build.DISPLAY;
        aobj[14] = 60;
        aobj[15] = ViewUtils.getPixelsOf(60, this);
        return StringUtils
                .formatOf(
                        "layout: {0} fontScale: {1}\ndensity: {2} densityDpi: {3} xdpi: {4} ydpi: {5}\ndisplay width: {6} height: {7} pixels: {14} == {15}\nos: {8} {9} {10}\nandroid: {11} ({12}) {13}",
                        aobj);
    }

    //    /**
    //     * 用户是否登录
    //     * @return
    //     */
    //    public boolean isLogin() {
    //        return login;
    //    }
    //
    //    /**
    //     * 获取登录用户id
    //     * @return
    //     */
    //    public int getLoginUid() {
    //        return this.loginUid;
    //    }
    //
    //    /**
    //     * 用户注销
    //     */
    //    public void Logout() {
    //        HttpTool.cleanCookie();
    //        this.cleanCookie();
    //        this.login = false;
    //        this.loginUid = 0;
    //    }

    /**
     * 未登录或修改密码后的处理
     */
    public Handler getUnLoginHandler() {
        return this.unLoginHandler;
    }

    /**
     * 检测当前系统声音是否为正常模式
     */
    public boolean isAudioNormal() {
        AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        return mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
    }

    /**
     * 应用程序是否发出提示音
     */
    public boolean isAppSound() {
        return isAudioNormal() && isVoice();
    }

    /**
     * 是否发出提示音
     */
    public boolean isVoice() {
        String perf_voice = getProperty(AppConfig.CONF_VOICE);
        //默认是开启提示声音
        if (StringUtils.isEmpty(perf_voice))
            return true;
        else
            return StringUtils.toBool(perf_voice);
    }

    /**
     * 是否启用语音提示,默认启用
     */
    public boolean isUseVoice() {
        String st = getProperty(AppConfig.CONF_USE_VOICE);

        return StringUtils.isEmpty(st) || StringUtils.toBool(st);
    }

    public String getHost() {
        String st = getProperty(AppConfig.CONF_SETTING_HTTP_IP);
        if (StringUtils.isEmpty(st)) {
            String ip = Utils.readHost(R.raw.hostdev, MainApplication.this).toString();
            return ip;
        } else {
            return st;
        }
    }

    public void setHost(String v_ip) {
        setProperty(AppConfig.CONF_SETTING_HTTP_IP, v_ip);
        host_ip = v_ip;
    }

    public String getCustomer() {
        String st = getProperty(AppConfig.CONF_CUSTOMER);
        if (StringUtils.isEmpty(st)) {
            return "医院";
        } else {
            return st;
        }
    }

    public String getBlueToothDevice() {
        String st = getProperty(AppConfig.CONF_BLUETOOTHDEVICE);
        if (StringUtils.isEmpty(st)) {
            return "";
        } else {
            return st;
        }
    }

    public String getBlueToothDeviceAddress() {
        String st = getProperty(AppConfig.CONF_BLUETOOTHDEVICE_ADDRESS);
        if (StringUtils.isEmpty(st)) {
            return "";
        } else {
            return st;
        }
    }

    public String getBlueToothDeviceType() {
        String st = getProperty(AppConfig.CONF_BLUETOOTHDEVICE_TYPE);
        if (StringUtils.isEmpty(st)) {
            return AppConfig.BLUETOOTH_COMMON;
        } else {
            return st;
        }
    }

    public String getUserName() {
        String st = getProperty(AppConfig.CONF_USERNAME);
        if (StringUtils.isEmpty(st)) {
            return "";
        } else {
            return st;
        }
    }

    public String getUserList() {
        String st = getProperty(AppConfig.CONF_USERLIST);
        if (StringUtils.isEmpty(st)) {
            return "[]";
        } else {
            return st;
        }
    }

    public String getHostList() {
        String st = getProperty(AppConfig.CONF_HOSTLIST);
        if (StringUtils.isEmpty(st)) {
            return "[]";
        } else {
            return st;
        }
    }

    public String getUserPwd() {
        String st = getProperty(AppConfig.CONF_PWD);
        if (StringUtils.isEmpty(st)) {
            return "";
        } else {
            return st;
        }
    }

    public boolean isSave() {
        String st = getProperty(AppConfig.CONF_ISSAVE);
        return StringUtils.isEmpty(st) || StringUtils.toBool(st);
    }

    /**
     * 是否启用服务端记录日志,默认不记录
     */
    public boolean isUseWeblog() {
        String st = getProperty(AppConfig.CONF_USE_WEBLOG);
        return !StringUtils.isEmpty(st) && StringUtils.toBool(st);
    }

    /**
     * 检测网络是否可用
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络类型
     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
     */
    public int getNetworkType() {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!UtilString.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    public String getImageFileDir() {
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + getPackageName()
                + File.separator + "images";
        Logger.d(dir);

        return dir;
    }

    public static String getMyPackageName() {
        return myPackageName;
    }

    /**
     * 退出应用程序
     */
    public static void AppExit(Context context) {
        //finishAllActivity();
        ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityMgr.restartPackage(context.getPackageName());
        System.exit(0);
    }

    public static boolean isDebuggable(Context c) {
        return (0 != (c.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

    /**
     * 发送App异常崩溃报告
     *
     */
    public static void sendAppCrashReport(final Context cont, final String crashReport) {
        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(R.string.app_error);
        builder.setMessage(R.string.app_error_message);
        builder.setPositiveButton(R.string.submit_report, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 发送异常报告
                Intent i = new Intent(Intent.ACTION_SEND);
                // i.setType("text/plain"); //模拟器
                i.setType("message/rfc822"); // 真机
                i.putExtra(Intent.EXTRA_EMAIL, new String[] { "exkingsoft@sian.cn" });
                i.putExtra(Intent.EXTRA_SUBJECT, "移动医疗Android客户端 - 错误报告");
                i.putExtra(Intent.EXTRA_TEXT, crashReport);
                cont.startActivity(Intent.createChooser(i, "发送错误报告"));
                // 退出
                AppExit(cont);
            }
        });
        builder.setNegativeButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 退出
                AppExit(cont);
            }
        });
        builder.show();
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    /**
     * 清除保存的缓存
     */
    public void cleanCookie() {
        //removeProperty(AppConfig.CONF_COOKIE);
    }

    //离线使用
    public List<Hz> getHzList(int pageIndex, boolean isRefresh) throws Exception {
        HzList list = null;
        String key = "hzlist_" + loginUid + "_" + pageIndex + "_" + PAGE_SIZE;
        //时效判断
        //if(isNetworkConnected() && (isCacheDataFailure(key) || isRefresh)) {
        if (isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
            try {
                //String ip = Utils.readHost(R.raw.hostdev, MainApplication.this).toString();
                String ip = getHost();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("page", pageIndex + ""));
                JSONObject res = HttpTool.getTool().post("http://" + ip + "/common/get_hzs", params);

                Logger.d("times:" + res.getInt("times"));
                int ps = res.getInt("pages");
                Logger.d("pages:" + ps);

                list = new HzList();
                JSONArray arrays = res.getJSONArray("hzs");
                for (int i = 0; i < arrays.length(); i++) {
                    JSONObject p = (JSONObject) arrays.get(i);
                    list.getHzs().add(JSON.parseObject(p.toString(), Hz.class));
                }

                list.setCacheKey(key);
                saveObject(list, key);

            } catch (Exception e) {
                list = (HzList) readObject(key);
                if (list == null)
                    throw e;
            }
        } else {
            list = (HzList) readObject(key);
            Logger.d("离线数据:" + list.getCacheKey() + "," + list.getHzs().size());
            if (list == null)
                list = new HzList();
        }
        return list.getHzs();
    }

    /**
     * 判断缓存数据是否可读
     */
    private boolean isReadDataCache(String cachefile) {
        return readObject(cachefile) != null;
    }

    /**
     * 判断缓存是否存在
     */
    private boolean isExistDataCache(String cachefile) {
        boolean exist = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

    /**
     * 判断缓存是否失效
     */
    public boolean isCacheDataFailure(String cachefile) {
        boolean failure = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists() && (System.currentTimeMillis() - data.lastModified()) > CACHE_TIME)
            failure = true;
        else if (!data.exists())
            failure = true;
        return failure;
    }

    /**
     * 清除app缓存
     */
    public void clearAppCache() {
        //清除webview缓存
        //File file = CacheManager.getCacheFileBaseDir();
        //if (file != null && file.exists() && file.isDirectory()) {
        //    for (File item : file.listFiles()) {
        //        item.delete();
        //    }
        //    file.delete();
        //}
        //deleteDatabase("webviewCache.db-wal");
        //清除数据缓存
        clearCacheFolder(getFilesDir(), System.currentTimeMillis());
        clearCacheFolder(getCacheDir(), System.currentTimeMillis());
        //2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            clearCacheFolder(com.ek.mobileapp.utils.MethodsCompat.getExternalCacheDir(this), System.currentTimeMillis());
        }
        //清除编辑器保存的临时内容
        Properties props = getProperties();
        for (Object key : props.keySet()) {
            String _key = key.toString();
            if (_key.startsWith("temp"))
                removeProperty(_key);
        }
    }

    /**
     * 清除缓存目录
     * @param dir 目录
     * @param curTime 当前系统时间
     */
    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    /**
     * 将对象保存到内存缓存中
     */
    public void setMemCache(String key, Object value) {
        memCacheRegion.put(key, value);
    }

    /**
     * 从内存缓存中获取对象
     */
    public Object getMemCache(String key) {
        return memCacheRegion.get(key);
    }

    /**
     * 保存磁盘缓存
     * @throws IOException
     */
    public void setDiskCache(String key, String value) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("cache_" + key + ".data", Context.MODE_PRIVATE);
            fos.write(value.getBytes());
            fos.flush();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                Logger.e("");
            }
        }
    }

    /**
     * 获取磁盘缓存数据
     */
    public String getDiskCache(String key) throws IOException {
        FileInputStream fis = null;
        try {
            fis = openFileInput("cache_" + key + ".data");
            byte[] datas = new byte[fis.available()];
            fis.read(datas);
            return new String(datas);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                Logger.e("fis.close,error");
            }
        }
    }

    /**
     * 保存对象
     */
    public boolean saveObject(Serializable ser, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(file, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                Logger.e("oos.close, error");
            }
            try {
                fos.close();
            } catch (IOException e) {
                Logger.e("");
            }
        }
    }

    /**
     * 读取对象
     */
    public Serializable readObject(String file) {
        if (!isExistDataCache(file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
            Logger.e("");
        } catch (Exception e) {
            e.printStackTrace();
            //反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                Logger.e("");
            }
            try {
                fis.close();
            } catch (IOException e) {
                Logger.e("");
            }
        }
        return null;
    }

    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 获取内存中保存图片的路径
     */
    public String getSaveImagePath() {
        return saveImagePath;
    }

    /**
     * 设置内存中保存图片的路径
     */
    public void setSaveImagePath(String saveImagePath) {
        this.saveImagePath = saveImagePath;
    }

    public RequestQueue getRequestQueue() {
        return _requestQueue;
    }

    //http://stackoverflow.com/questions/16680701/using-cookies-with-android-volley-library
    /**
     * Checks the response headers for session cookie and saves it
     * if it finds it.
     * @param headers Response Headers.
     */
    public final void checkSessionCookie(Map<String, String> headers) {
        if (headers.containsKey(SET_COOKIE_KEY) && headers.get(SET_COOKIE_KEY).startsWith(SESSION_COOKIE)) {
            String cookie = headers.get(SET_COOKIE_KEY);
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                Editor prefEditor = _preferences.edit();
                prefEditor.putString(SESSION_COOKIE, cookie);
                prefEditor.commit();
            }
        }
    }

    public final void saveSessionCookie(String cookie) {
        Editor prefEditor = _preferences.edit();
        prefEditor.putString(SESSION_COOKIE, cookie);
        prefEditor.commit();
    }

    /**
     * Adds session cookie to headers if exists.
     */
    public final void addSessionCookie(Map<String, String> headers) {
        String sessionId = _preferences.getString(SESSION_COOKIE, "");

        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(SESSION_COOKIE);
            builder.append("=");
            builder.append(sessionId);
            if (headers.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(headers.get(COOKIE_KEY));
            }
            Logger.d(builder.toString());
            headers.put(COOKIE_KEY, builder.toString());

            Logger.d("-- addSessionCookie");
            Logger.d(headers.toString());
        }
    }
}
