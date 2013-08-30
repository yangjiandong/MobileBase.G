package com.ek.mobileapp.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ek.mobileapp.utils.TimeTool;
import com.ek.mobilebapp.R;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.about_layout);

        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView titleTv = (TextView) findViewById(R.id.custom_title_label);
        titleTv.setText("关于");

        Resources res = getResources();
        String text = String.format(res.getString(R.string.about_commons), TimeTool.getYear());
        TextView aboutCommons = (TextView) findViewById(R.id.about_commons);
        aboutCommons.setText(text);

        try {

            PackageInfo pinfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = pinfo.versionName;
            TextView aboutV = (TextView) findViewById(R.id.about_appversion);
            aboutV.setText(versionName);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
