package com.ek.mobileapp.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

import com.alibaba.fastjson.JSON;
import com.ek.mobileapp.AppConfig;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.model.MobConstants;
import com.ek.mobileapp.model.UserDTO;
import com.ek.mobileapp.secret.DDES;
import com.ek.mobileapp.utils.GlobalCache;
import com.ek.mobileapp.utils.HttpTool;
import com.ek.mobileapp.utils.WebUtils;

public class LogonAction {

    public static String login(Context mContext, String deviceId, String mobileNo, String loginname, String password,
            String ip, String moduleName) {

        String shaLoginname = DDES.eencryptDES(loginname, DDES.KEY);//DigestUtils.sha1ToHex(loginname);
        String shaPassword = DDES.eencryptDES(password, DDES.KEY);//DigestUtils.sha1ToHex(password);
        //增加usesha参数,是否加密,暂时保证以前版本也能正常登录

        try {

            String url = HttpTool.REQUEST_HTTP + ip + WebUtils.LOGINACTION + moduleName + "&deviceId=" + deviceId
                    + "&mobileNo=" + mobileNo + "&netType=" + getCurrentNetType(mContext) + "&username="
                    + URLEncoder.encode(shaLoginname, "UTF-8") + "&password=" + URLEncoder.encode(shaPassword, "UTF-8")
                    + "&usesha=Y";

            JSONObject res = HttpTool.getTool().login(mContext, url);
            if (res == null)
                return String.valueOf(WebUtils.WEBERROR);

            if (!res.getBoolean("success")) {
                return res.getString("message");
            }

            UserDTO user = JSON.parseObject(res.getJSONObject("user").toString(), UserDTO.class);
            String lastIp = res.getString("lastIp");//JSON.parseObject(res.getJSONObject("lastIp").toString(), String.class);
            String caption = res.getString("caption");

            GlobalCache.getCache().setHostIp(ip);
            GlobalCache.getCache().setLastIp(lastIp);
            GlobalCache.getCache().setLoginuser(user);
            GlobalCache.getCache().setUserCaption(caption);

            return String.valueOf(WebUtils.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(WebUtils.APPLICATIONERROR);
        }
    }

    public static int logout(Context mContext, String ip) {
        String url = HttpTool.REQUEST_HTTP + ip + WebUtils.LOGOUTACTION;
        HttpTool.getTool().logout(mContext, url);

        return WebUtils.SUCCESS;

    }

    public static int userLog(String module, String infos, String ip) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("infos", infos));
        params.add(new BasicNameValuePair("module", module));

        String url = HttpTool.REQUEST_HTTP + ip + WebUtils.USERLOG;
        JSONObject res = HttpTool.getTool().post(url, params);
        if (res == null)
            return WebUtils.WEBERROR;
        try {
            if (!res.getBoolean("success")) {
                return WebUtils.LOGINERROR;
            }
            return WebUtils.SUCCESS;
        } catch (JSONException e) {
            e.printStackTrace();
            return WebUtils.APPLICATIONERROR;
        }
    }

    public static int updatePwd(String password) {
        UserDTO user = GlobalCache.getCache().getLoginuser();
        String hostIp = GlobalCache.getCache().getHostIp();

        String url = HttpTool.REQUEST_HTTP + hostIp + "/system/update_password";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", user.getId().toString()));
        params.add(new BasicNameValuePair("newPwd", password));
        JSONObject res = HttpTool.getTool().post(url, params);
        if (res == null)
            return WebUtils.WEBERROR;
        try {
            if (!res.getBoolean("success")) {
                return WebUtils.LOGINERROR;
            }

            return WebUtils.SUCCESS;
        } catch (JSONException e) {
            e.printStackTrace();
            return WebUtils.APPLICATIONERROR;
        }
    }

    public static int getCustomName(String ip, Context context) {
        String url = HttpTool.REQUEST_HTTP + ip + "/common/host_customer?type=mobile";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONObject res = HttpTool.getTool().post(url, params);
        if (res == null)
            return WebUtils.WEBERROR;
        try {
            if (!res.getBoolean("success")) {
                return WebUtils.LOGINERROR;
            }
            String customer = res.getString("customer");

            //
            MainApplication ac = (MainApplication) context.getApplicationContext();
            ac.setProperty(AppConfig.CONF_CUSTOMER, customer);

            //his
            GlobalCache.getCache().setBarcode_patient(res.getString("barcode_patient"));
            GlobalCache.getCache().setBarcode_orderno(res.getString("barcode_orderno"));
            GlobalCache.getCache().setBarcode_lis(res.getString("barcode_lisno"));

            return WebUtils.SUCCESS;
        } catch (JSONException e) {
            e.printStackTrace();
            return WebUtils.APPLICATIONERROR;
        }
    }

    public static int testConnection() {

        String ip = GlobalCache.getCache().getHostIp();
        String url = HttpTool.REQUEST_HTTP + ip + "/common/test_connection?type=mobile";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONObject res = HttpTool.getTool().post(url, params);
        if (res == null)
            return WebUtils.WEBERROR;

        try {
            if (!res.getBoolean("success")) {
                return WebUtils.WEBERROR;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return WebUtils.WEBERROR;
        }
        return WebUtils.SUCCESS;
    }

    public static String getCurrentNetType(Context mContext) {
        ConnectivityManager connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); // wifi
        NetworkInfo gprs = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE); // gprs

        if (wifi != null && wifi.getState() == State.CONNECTED) {
            return MobConstants.MOB_NET_TYPE_WIFI;
        } else if (gprs != null && gprs.getState() == State.CONNECTED) {
            return MobConstants.MOB_NET_TYPE_3G;
        }
        return MobConstants.MOB_NET_TYPE_ERROR;
    }
}
