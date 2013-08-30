package com.ek.mobileapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.utils.StringUtils;
import com.ek.mobilebapp.R;

public class ServerActivity extends Activity {
    MainApplication ac;
    EditText server_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.server_layout);

        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView titleTv = (TextView) findViewById(R.id.custom_title_label);
        titleTv.setText("服务器端地址设置");

        ac = (MainApplication) getApplication();

        server_ip = (EditText) findViewById(R.id.server_ip);
        server_ip.setText(ac.getHost());

        Button ok = (Button) findViewById(com.ek.mobilebapp.R.id.button_ok);
        ok.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String ip = server_ip.getText().toString();

                if (!StringUtils.isEmpty(ip)) {
                    ac.setHost(ip);
                }
                finish();
            }
        });
    }
}
