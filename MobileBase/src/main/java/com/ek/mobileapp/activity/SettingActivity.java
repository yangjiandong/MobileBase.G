package com.ek.mobileapp.activity;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ek.mobileapp.AppConfig;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.utils.BluetoothService;
import com.ek.mobileapp.utils.DialogFactory;
import com.ek.mobileapp.utils.DialogFactory.DialogCallbacks;
import com.ek.mobileapp.utils.FileUtils;
import com.ek.mobileapp.utils.MethodsCompat;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobilebapp.R;
import com.ek.mobilebapp.R.id;

public class SettingActivity extends Activity {
    final static String TAG = "SettingActivity";

    RelativeLayout passWordLayout;
    RelativeLayout userInfoLayout;
    RelativeLayout serverAddLayout;
    RelativeLayout bluetoothTypeLayout;
    RelativeLayout bluetoothDeviceLayout;
    RelativeLayout updateLogLayout;
    RelativeLayout aboutLayout;
    ToggleButton setting_use_voice;
    ToggleButton setting_weblog;
    TextView cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.setting_layout);

        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView titleTv = (TextView) findViewById(R.id.custom_title_label);
        titleTv.setText("设置");

        RelativeLayout userLayout = (RelativeLayout) findViewById(R.id.user_setting_cate);
        TextView tv1 = (TextView) userLayout.findViewById(R.id.list_category_title);
        tv1.setText("用户设置");

        RelativeLayout systemLayout = (RelativeLayout) findViewById(R.id.system_setting_cate);
        TextView tv2 = (TextView) systemLayout.findViewById(R.id.list_category_title);
        tv2.setText("系统设置");

        RelativeLayout messageLayout = (RelativeLayout) findViewById(R.id.system_message_cate);
        TextView tv3 = (TextView) messageLayout.findViewById(R.id.list_category_title);
        tv3.setText("系统信息");

        //
        final MainApplication ac = (MainApplication) getApplication();

        //语音提示
        setting_use_voice = (ToggleButton) findViewById(id.setting_use_vocie);
        if (ac.isUseVoice()) {
            setting_use_voice.setChecked(true);
        } else {
            setting_use_voice.setChecked(false);
        }
        setting_use_voice.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ac.setProperty(AppConfig.CONF_USE_VOICE, "true");
                } else {
                    ac.setProperty(AppConfig.CONF_USE_VOICE, "false");
                }
            }
        });

        //weblog
        setting_weblog = (ToggleButton) findViewById(id.setting_weblog);
        if (ac.isUseWeblog()) {
            setting_weblog.setChecked(true);
        } else {
            setting_weblog.setChecked(false);
        }
        setting_weblog.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ac.setProperty(AppConfig.CONF_USE_WEBLOG, "true");
                } else {
                    ac.setProperty(AppConfig.CONF_USE_WEBLOG, "false");
                }
            }
        });

        // 计算缓存大小
        long fileSize = 0;
        String cacheSize = "0KB";
        File filesDir = getFilesDir();
        File cacheDir = getCacheDir();

        fileSize += FileUtils.getDirSize(filesDir);
        fileSize += FileUtils.getDirSize(cacheDir);
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (MainApplication.isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            File externalCacheDir = MethodsCompat.getExternalCacheDir(this);
            fileSize += FileUtils.getDirSize(externalCacheDir);
        }
        if (fileSize > 0)
            cacheSize = FileUtils.formatFileSize(fileSize);

        // 清除缓存
        cache = (TextView) findViewById(id.cache);
        cache.setText(cacheSize);
        cache.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCallbacks dCalls = new DialogCallbacks() {
                    @Override
                    public void runOk(DialogInterface dialog, int which) {
                        com.ek.mobileapp.utils.UIHelper.clearAppCache(SettingActivity.this);
                        cache.setText("0KB");
                    }

                    @Override
                    public void runCancel(DialogInterface dialog, int which) {

                    }
                };

                Dialog dialog = DialogFactory.create(SettingActivity.this, "是否清除缓存?", "", dCalls);
                dialog.show();
            }
        });

        passWordLayout = (RelativeLayout) findViewById(R.id.goto_password_list);
        passWordLayout.setOnClickListener(clickListener);

        userInfoLayout = (RelativeLayout) findViewById(R.id.goto_info_list);
        userInfoLayout.setOnClickListener(clickListener);

        if (ac.isCurrentLogin()) {
            passWordLayout.setVisibility(View.VISIBLE);
            userInfoLayout.setVisibility(View.VISIBLE);
        } else {
            passWordLayout.setVisibility(View.GONE);
            userInfoLayout.setVisibility(View.GONE);
        }

        serverAddLayout = (RelativeLayout) findViewById(R.id.goto_server_list);
        serverAddLayout.setOnClickListener(clickListener);

        bluetoothTypeLayout = (RelativeLayout) findViewById(R.id.goto_bluetooth_type_list);
        bluetoothTypeLayout.setOnClickListener(clickListener);

        bluetoothDeviceLayout = (RelativeLayout) findViewById(R.id.goto_bluetooth_device_list);
        bluetoothDeviceLayout.setOnClickListener(clickListener);

        updateLogLayout = (RelativeLayout) findViewById(R.id.goto_update_log_list);
        updateLogLayout.setOnClickListener(clickListener);

        aboutLayout = (RelativeLayout) findViewById(R.id.goto_about_list);
        aboutLayout.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new OnClickListener() {
        public void onClick(View v) {
            if (v.equals(passWordLayout)) {
                Log.d(TAG, "passWordLayout");
                Intent intent = new Intent(SettingActivity.this, PassWordActivity.class);
                startActivity(intent);
            }
            if (v.equals(userInfoLayout)) {
                Log.d(TAG, "userInfoLayout");
                Intent intent = new Intent(SettingActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
            if (v.equals(serverAddLayout)) {
                Log.d(TAG, "serverAddLayout");
                Intent intent = new Intent(SettingActivity.this, ServerActivity.class);
                startActivity(intent);
            }
            if (v.equals(bluetoothTypeLayout)) {
                Log.d(TAG, "bluetoothTypeLayout");
                Intent intent = new Intent(SettingActivity.this, BluetoothTypeActivity.class);
                startActivity(intent);
            }
            if (v.equals(bluetoothDeviceLayout)) {
                Log.d(TAG, "bluetoothDeviceLayout");
                Intent intent = new Intent(SettingActivity.this, BluetoothDeviceActivity.class);
                startActivityForResult(intent, BluetoothService.REQUEST_CONNECT_DEVICE);
            }
            if (v.equals(updateLogLayout)) {
                Log.d(TAG, "updateLogLayout");
                Intent intent = new Intent(SettingActivity.this, UpdateLogActivity.class);
                startActivity(intent);
            }
            if (v.equals(aboutLayout)) {
                Log.d(TAG, "aboutLayout");
                Intent intent = new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case BluetoothService.REQUEST_CONNECT_DEVICE: {
            if (resultCode == Activity.RESULT_OK) {
                String address = data.getExtras().getString(BluetoothDeviceActivity.EXTRA_DEVICE_ADDRESS);
                String name = data.getExtras().getString(BluetoothDeviceActivity.EXTRA_DEVICE_NAME);

                MainApplication ac = (MainApplication) getApplicationContext();
                ac.setProperty(AppConfig.CONF_BLUETOOTHDEVICE, name);
                ac.setProperty(AppConfig.CONF_BLUETOOTHDEVICE_ADDRESS, address);

                ToastUtils.show(this, "当前蓝牙设备为" + name);
            }
            break;
        }
        default: {

        }
        }
    }
}
