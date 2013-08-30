package com.ek.mobileapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ek.mobileapp.AppConfig;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.utils.UtilString;
import com.ek.mobilebapp.R;

public class BluetoothTypeActivity extends Activity {
    MainApplication ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.bluetooth_type_layout);

        ac = (MainApplication) getApplicationContext();

        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView titleTv = (TextView) findViewById(R.id.custom_title_label);
        titleTv.setText("蓝牙连接类型");

        RadioButton type1 = (RadioButton) findViewById(R.id.bluetooth_type_1);
        type1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ac.setProperty(AppConfig.CONF_BLUETOOTHDEVICE_TYPE, AppConfig.BLUETOOTH_COMMON);
            }
        });
        RadioButton type2 = (RadioButton) findViewById(R.id.bluetooth_type_2);
        type2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ac.setProperty(AppConfig.CONF_BLUETOOTHDEVICE_TYPE, AppConfig.BLUETOOTH_UIDS);
            }
        });
        RadioButton type3 = (RadioButton) findViewById(R.id.bluetooth_type_3);
        type3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ac.setProperty(AppConfig.CONF_BLUETOOTHDEVICE_TYPE, AppConfig.BLUETOOTH_ZBK);
            }
        });

        if (!UtilString.isBlank(ac.getBlueToothDeviceType())) {
            if (ac.getBlueToothDeviceType().equals(AppConfig.BLUETOOTH_COMMON))
                type1.setChecked(true);
            if (ac.getBlueToothDeviceType().equals(AppConfig.BLUETOOTH_UIDS))
                type2.setChecked(true);
            if (ac.getBlueToothDeviceType().equals(AppConfig.BLUETOOTH_ZBK))
                type3.setChecked(true);
        }
    }
}
