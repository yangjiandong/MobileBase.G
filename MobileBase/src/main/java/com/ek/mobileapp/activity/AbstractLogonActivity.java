package com.ek.mobileapp.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ek.mobileapp.AppConfig;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.action.LogonAction;
import com.ek.mobileapp.demo.DemoData;
import com.ek.mobileapp.model.UserDTO;
import com.ek.mobileapp.utils.GlobalCache;
import com.ek.mobileapp.utils.HttpTool;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobileapp.utils.UtilString;
import com.ek.mobileapp.utils.ViewUtils;
import com.ek.mobileapp.utils.WebUtils;
import com.ek.mobilebapp.R;

@SuppressLint("HandlerLeak")
public abstract class AbstractLogonActivity extends Activity {
    abstract protected void afterLogin();

    private int screenWidth;
    private int scrollHeight;

    MainApplication ac;
    // Debugging
    static final String TAG = "LogonActivity";
    boolean isDebuggable = true;
    ImageView customerInfo;//医院

    WebView host_info;
    EditText username;
    EditText password;
    Button logonBtn;
    CheckBox savepassword;
    TelephonyManager tm;
    String version = "1";

    public static final int LOGINACTION = 12;
    AlertDialog logonProDialog;
    String ip = "";
    String strURL = "";
    ProgressDialog updateProgressDlg;

    protected String moduleCode = "";
    String HOST = "";

    private CharSequence readHost(int in) {
        StringBuilder sb = new StringBuilder();
        InputStream is = getResources().openRawResource(in);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            while (null != line) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            Log.e(TAG, "", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "", e);
            }
        }
        return sb.toString();
    }

    protected void showTitle() {
        Button leftBtn = (Button) findViewById(R.id.custom_title_btn_left);
        leftBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.more_icon), null,
                null);

        leftBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    private void getSize() {
        RelativeLayout titleView = (RelativeLayout) findViewById(R.id.custome_title_id);
        //RelativeLayout userView = (RelativeLayout) findViewById(R.id.main_User_layout);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        scrollHeight = display.getHeight() - titleView.getHeight() - 20;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.logon);

        getSize();

        ac = (MainApplication) getApplication();
        showTitle();
        ip = ac.getHost();

        int net = ac.getNetworkType();
        if (0 == net) {
            ToastUtils.show(this, "提示,当前没有网络连接");
        } else if (MainApplication.NETTYPE_WIFI == net) {
            HOST = readHost(R.raw.hostdev).toString();
        } else {
            HOST = readHost(R.raw.hostpro).toString();
        }

        if (ip.equals("")) {
            ac.setHost(HOST);
            ip = HOST;
        }

        //直接放在sharedPreferences,MobLogAction取不出值,暂时存放在cache
        //boolean weblog = sharedPreferences.getBoolean("setting_weblog", false);
        GlobalCache.getCache().setWebLog(ac.isUseWeblog());

        isDebuggable = MainApplication.isDebuggable(this);
        if (isDebuggable) {
            //MobLogAction.getMobLogAction().mobLogInfo((MainApplication) this.getApplicationContext(), TAG,
            //        ViewUtils.onTablet(this) ? "on tablet" : "on mobile");
            //MobLogAction.getMobLogAction().mobLogInfo((MainApplication) this.getApplicationContext(), TAG, "开始运行...");
        }

        tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

        //客户端版本号暂时先隐藏掉
        version = "1";
        String vendor = "鑫亿";
        try {
            PackageInfo pinfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pinfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        TextView app_info = (TextView) findViewById(R.id.app_info);
        app_info.setText("Copyright © " + vendor);
        TextView app_info2 = (TextView) findViewById(R.id.app_info2);
        app_info2.setText(" ver:" + version);

        host_info = (WebView) findViewById(R.id.host_info);
        username = (EditText) findViewById(R.id.logon_username);
        password = (EditText) findViewById(R.id.logon_password);
        savepassword = (CheckBox) findViewById(R.id.logon_save_password);
        logonBtn = (Button) findViewById(R.id.logon_ok);
        customerInfo = (ImageView) findViewById(R.id.logon_logo);

        String share_username = ac.getUserName();
        String share_password = ac.getUserPwd();

        host_info.loadUrl(WebUtils.HTTP + ip + WebUtils.NEWS + ac.getModuleName());
        getCustomerInfo();

        // 记住密码
        boolean issave = ac.isSave();
        if (issave) {
            username.setText(share_username);
            password.setText(share_password);
            savepassword.setChecked(issave);
        }

        if (!isDebuggable)
            getLastVersion();

        logonBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (username.getEditableText().toString().trim().equals("")) {
                    username.setError("用户名不能为空！");
                    return;
                }

                login(username.getEditableText().toString().trim(), password.getEditableText().toString().trim());

            }
        });

        ImageView setBtn = (ImageView) findViewById(R.id.user_icon);
        setBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AbstractLogonActivity.this, UserListActivity.class);
                startActivityForResult(intent, UserListActivity.REQUEST_USER_SET);
            }
        });

        //记录服务器地址
        //ImageView tv = (ImageView) findViewById(R.id.logon_icon);
        //tv.setOnClickListener(new OnClickListener() {
        //    public void onClick(View v) {
        //        Intent intent = new Intent(AbstractLogonActivity.this, HostListActivity.class);
        //        startActivityForResult(intent, HostListActivity.REQUEST_HOST_SET);
        //    }
        //});
    }

    @Override
    public void onStart() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            moduleCode = extras.getString("message");
            if (!UtilString.isBlank(moduleCode)) {
                if (!username.getEditableText().toString().trim().equals("")
                        && !password.getEditableText().toString().trim().equals("")) {
                    ip = ac.getHost();
                    login(username.getEditableText().toString().trim(), password.getEditableText().toString().trim());

                }
            }
        }

        super.onStart();
    }

    @SuppressWarnings("static-access")
    private void login(String loginname, String psd) {
        String deviceId = Settings.Secure.getString(AbstractLogonActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        GlobalCache.getCache().setDeviceId(deviceId);//tm.getDeviceId());

        if (loginname.equals(com.ek.mobileapp.demo.DemoData.DEMO) && psd.equals(DemoData.DEMO)) {
            GlobalCache.getCache().setDemo(true);
            DemoData.setUserDTO();
            setPreferences(loginname, psd);

            afterLogin();
            finish();

        } else {
            String agent = GlobalCache.getCache().getUserAgent(
                    (MainApplication) AbstractLogonActivity.this.getApplicationContext());
            Log.d(TAG, agent);

            logonProDialog = ProgressDialog.show(AbstractLogonActivity.this, "", "登录中...", true, true);

            Thread login = new LoginHandler(loginname, psd, ip);
            if (!UtilString.isBlank(moduleCode)) {
                try {
                    login.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            login.start();
        }
    }

    private class LoginHandler extends Thread {
        private String loginname;
        private String psd;
        private String ip;

        private void loginUnSuccess(String msg) {
            logonProDialog.dismiss();
            clearPreferences();
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putInt("type", 0);
            bundle.putString("msg", msg);
            message.setData(bundle);
            UIHandler.sendMessage(message);
        }

        public LoginHandler(String loginname, String psd, String ip) {
            this.loginname = loginname;
            this.psd = psd;
            this.ip = ip;
        }

        public void run() {

            String deviceId = Settings.Secure.getString(AbstractLogonActivity.this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);

            TelephonyManager telephonyMgr = (TelephonyManager) AbstractLogonActivity.this
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String mobileNo = telephonyMgr.getLine1Number();

            //GlobalCache.getCache().setDeviceId(deviceId);//tm.getDeviceId());
            String res = LogonAction.login(AbstractLogonActivity.this, deviceId, mobileNo, loginname, psd, ip,
                    ac.getModuleName());

            if (res.trim().equals(String.valueOf(WebUtils.SUCCESS))) {
                LogonAction.userLog(ac.getModuleName(), "登录|" + GlobalCache.getCache().getDeviceId(), ip);

                //没有授权
                UserDTO currentU = GlobalCache.getCache().getLoginuser();
                if (UtilString.isBlank(currentU.getMobmodules())) {
                    loginUnSuccess("用户还没有授权");
                } else {

                    //更新密码时用
                    saveOldPwd(psd);
                    if (savepassword.isChecked()) {
                        setPreferences(loginname, psd);
                    }

                    afterLogin();
                    logonProDialog.dismiss();
                    finish();
                }
            } else if (res.trim().equals(String.valueOf(WebUtils.APPLICATIONERROR))) {
                loginUnSuccess("用户名密码不正确");
            } else if (res.trim().equals(String.valueOf(com.ek.mobileapp.utils.WebUtils.WEBERROR))) {
                loginUnSuccess("请检查网络,ip地址:" + ip);
            } else {
                loginUnSuccess(res.trim());
            }
        }
    }

    Handler UIHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.getData().getInt("type");
            switch (type) {
            case 0: {
                AlertDialog.Builder builder = new AlertDialog.Builder(AbstractLogonActivity.this);
                builder.setMessage(msg.getData().getString("msg")).setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            }
            default: {

            }
            }
        }
    };

    public void setPreferences(String username, String password) {
        ac.setProperty(AppConfig.CONF_USERNAME, username);
        ac.setProperty(AppConfig.CONF_PWD, password);
        ac.setProperty(AppConfig.CONF_ISSAVE, "true");

        try {
            UserDTO currentU = GlobalCache.getCache().getLoginuser();
            //密码改明文
            currentU.setPassword(password);

            boolean has = false;
            List<UserDTO> newList = new ArrayList<UserDTO>();

            String as = ac.getUserList();//sharedPreferences.getString("ulist", "[]");
            List<UserDTO> barList1 = JSON.parseArray(as, UserDTO.class);
            for (UserDTO u : barList1) {
                if (u.getLoginName().equals(currentU.getLoginName())) {
                    has = true;
                    newList.add(currentU);
                } else {
                    newList.add(u);
                }
            }
            if (!has) {
                newList.add(currentU);
            }
            ac.setProperty(AppConfig.CONF_USERLIST, JSON.toJSONString(newList));

            //记录服务端地址
            has = false;
            List<String> hlist = new ArrayList<String>();

            as = ac.getHostList();//sharedPreferences.getString("hlist", "[]");
            List<String> allList1 = JSON.parseArray(as, String.class);
            for (String u : allList1) {
                if (u.equals(this.ip)) {
                    has = true;
                    hlist.add(this.ip);
                } else {
                    hlist.add(u);
                }
            }
            if (!has) {
                hlist.add(this.ip);
            }
            ac.setProperty(AppConfig.CONF_HOSTLIST, JSON.toJSONString(hlist));

        } catch (Exception e) {
            Log.e(TAG, "", e);
        }
    }

    public void saveOldPwd(String password) {
        ac.setProperty(AppConfig.CONF_OLD_PWD, password);
    }

    public void clearPreferences() {
        ac.setProperty(AppConfig.CONF_USERNAME, "");
        ac.setProperty(AppConfig.CONF_PWD, "");
        ac.setProperty(AppConfig.CONF_ISSAVE, "false");
        ac.setProperty(AppConfig.CONF_ISAUTO, "false");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit(RESULT_OK, "确认退出程序");
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit(final int result, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认退出");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                setResult(result);
                System.exit(0);
            }
        });

        // 设置取消按钮
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ip = ac.getHost();//sharedPreferences.getString(key, HOST);
        Logger.d("logon resume..." + ip);

        host_info.loadUrl(WebUtils.HTTP + ip + WebUtils.NEWS + ac.getModuleName());

        getCustomerInfo();
        //host_info.loadUrl(WebUtils.HTTP + ip + WebUtils.NEWS + ac.getModuleName());
    }

    private void update(final String filename) {
        new AlertDialog.Builder(AbstractLogonActivity.this).setTitle("更新提示").setMessage("发现新版本，是否更新")
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        updateProgressDlg = new ProgressDialog(AbstractLogonActivity.this);
                        updateProgressDlg.setCancelable(false);//设置下载更新事不能取消
                        updateProgressDlg.setTitle("正在下载");
                        updateProgressDlg.setMessage("请稍候...");
                        updateProgressDlg.setIndeterminate(false);
                        updateProgressDlg.setMax(100);
                        updateProgressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        strURL = filename;
                        getFile(strURL);
                    }
                }).setPositiveButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    private void getFile(final String strPath) {
        try {
            updateProgressDlg.show();
            Runnable r = new Runnable() {
                public void run() {
                    try {
                        getDataSource(strPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(r).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getDataSource(String strPath) throws Exception {

        if (!URLUtil.isNetworkUrl(strPath)) {
            ToastUtils.show(this, "下载地址错误");
            updateProgressDlg.setMessage("下载地址错误");
        } else {
            int count;
            try {
                URL myURL = new URL(strPath);
                URLConnection conn = myURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                if (is == null) {
                    throw new RuntimeException("stream is null");
                }
                // getting file length
                int lenghtOfFile = conn.getContentLength();
                long total = 0;

                String DOWN_FILENAME = "sshappMobileapp-" + ac.getModuleName() + ".apk";
                File myTempFile = new File(Environment.getExternalStorageDirectory(), DOWN_FILENAME);//Config.UPDATE_SAVENAME);
                String currentTempFilePath = myTempFile.getAbsolutePath();
                if (isDebuggable)
                    Log.d(TAG, "文件位置:" + currentTempFilePath);

                FileOutputStream fos = new FileOutputStream(myTempFile);
                byte buf[] = new byte[1024];
                while ((count = is.read(buf)) != -1) {
                    total += count;
                    updateProgressDlg.setProgress(Integer.parseInt("" + (int) ((total * 100) / lenghtOfFile)));
                    fos.write(buf, 0, count);
                }
                if (total == 0) {
                }
                fos.flush();
                if (fos != null) {
                    fos.close();
                }
                is.close();

                updateProgressDlg.cancel();
                openFile(myTempFile);
                this.finish();
            } catch (Exception ex) {
                //MobLogAction.getMobLogAction().mobLogError("自动更新", ex.getMessage());
                ViewUtils.saveInfoLog((MainApplication) this.getApplicationContext(), ex.getMessage());
                ToastUtils.show(this, "自动更新操作失败");
                updateProgressDlg.cancel();
                this.finish();
            }
        }
    }

    private void openFile(File f) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        String type = getMIMEType(f);
        intent.setDataAndType(Uri.fromFile(f), type);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
        if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else {
            type = "*";
        }
        if (end.equals("apk")) {
        } else {
            type += "/*";
        }
        return type;
    }

    private void getLastVersion() {
        GetLastVersion getData = new GetLastVersion(getLastVersionHandler);
        Thread thread = new Thread(getData);
        thread.start();
    }

    //提取最新版本
    class GetLastVersion implements Runnable {
        Handler handler;

        public GetLastVersion(Handler h) {
            this.handler = h;
        }

        public void run() {
            Message message = Message.obtain();
            try {
                String url = "http://" + ip + WebUtils.GETLASTDEPLOY;
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("typeCode", ac.getModuleName()));
                JSONObject res = HttpTool.getTool().post(url, params);
                if (res == null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 0);
                    bundle.putString("msg", "没取得版本信息");
                    message.setData(bundle);
                } else if (!res.getBoolean("success")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 0);
                    bundle.putString("msg", "没取得版本信息");
                    message.setData(bundle);
                } else {
                    String lastVersion = res.getString("lastVersion");
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    bundle.putString("msg", lastVersion);
                    message.setData(bundle);

                }
            } catch (Exception e) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 0);
                bundle.putString("msg", e.getMessage());
                message.setData(bundle);
            } finally {
                this.handler.sendMessage(message);
            }
        }
    }

    //回调函数，显示结果
    Handler getLastVersionHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.getData().getInt("type");

            switch (type) {
            case 1: {
                String lastVersion = msg.getData().getString("msg");
                if (!UtilString.isBlank(lastVersion) && version.compareTo(lastVersion) < 0) {
                    processUpdate();
                }
                break;
            }
            case 0: {
                //String mss = msg.getData().getString("msg");
                //MobLogAction.getMobLogAction().mobLogError("自动更新", mss);
                break;
            }
            default: {

            }
            }
        }
    };

    private void processUpdate() {
        String ip = ac.getHost();
        //String ip = sharedPreferences.getString(Constants.SETTING_HTTP_IP, HOST);
        String uriPath = "http://" + ip + WebUtils.UPDATEDOWNLOAD + ac.getModuleName();
        update(uriPath);
    }

    //取得医院信息
    private void getCustomerInfo() {
        GetCustomerInfo getData = new GetCustomerInfo(showCustomerHandler);
        Thread thread = new Thread(getData);
        thread.start();
    }

    //
    class GetCustomerInfo implements Runnable {
        Handler handler;

        public GetCustomerInfo(Handler h) {
            this.handler = h;
        }

        public void run() {
            Message message = Message.obtain();
            try {
                LogonAction.getCustomName(ip, AbstractLogonActivity.this);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                message.setData(bundle);
            } catch (Exception e) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 0);
                bundle.putString("msg", e.getMessage());
                message.setData(bundle);
            } finally {
                this.handler.sendMessage(message);
            }
        }
    }

    //回调函数，显示结果
    Handler showCustomerHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.getData().getInt("type");

            switch (type) {
            case 1: {
                String s = ac.getCustomer();
                customerInfo.setImageDrawable(writeOnDrawable(s));

                break;
            }
            case 0: {
                break;
            }
            default: {

            }
            }
        }
    };

    BitmapDrawable writeOnDrawable(String text) {
        //Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setStyle(Style.FILL);
        //paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        float w = ViewUtils.diptopx(getApplicationContext(), 40);
        paint.setTextSize(w);//30dip

        float width = paint.measureText(text);
        Bitmap bm = Bitmap.createBitmap(10 + (int) width, 100, Bitmap.Config.ARGB_8888);
        Logger.d("window width:" + screenWidth);
        Logger.d("infoImg width:" + width);

        Canvas canvas = new Canvas(bm);
        canvas.drawText(text, 0, bm.getHeight() / 2, paint);

        //
        String filename = "version.jpg";
        File sd = Environment.getExternalStorageDirectory();
        File dest = new File(sd, filename);

        try {
            FileOutputStream out = new FileOutputStream(dest);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new BitmapDrawable(bm);
    }

    //返回用户
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case UserListActivity.REQUEST_USER_SET: {
            if (resultCode == Activity.RESULT_OK) {
                // Get the user list
                String logonName = data.getExtras().getString(UserListActivity.EXTRA_USER_LOGINNAME);
                String pwd = data.getExtras().getString(UserListActivity.EXTRA_USER_PWD);

                username.setText(logonName);
                password.setText(pwd);
            }
            break;
        }
        case HostListActivity.REQUEST_HOST_SET: {
            if (resultCode == Activity.RESULT_OK) {
                String name = data.getExtras().getString(HostListActivity.EXTRA_HOST_NAME);
                ac.setHost(name);
                this.ip = ac.getProperty(AppConfig.CONF_SETTING_HTTP_IP);
            }
            break;
        }
        default: {
        }
        }
    }

    //pop menu
    private void showPopupMenu(View v) {
        Intent intent = new Intent();
        intent.setClass(AbstractLogonActivity.this, SettingActivity.class);//PictureActivity.class);
        startActivity(intent);
    }
}
