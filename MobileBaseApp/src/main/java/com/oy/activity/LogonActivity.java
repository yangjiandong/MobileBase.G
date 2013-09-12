package com.oy.activity;

import android.content.Intent;
import android.widget.TextView;

import com.ek.mobileapp.activity.AbstractLogonActivity;
import com.ek.mobileapp.utils.UtilString;
import com.ek.mobilebapp.R;

public class LogonActivity extends AbstractLogonActivity {
    public final static int LOGIN_OTHER = 0x00;
    public final static int LOGIN_MAIN = 0x01;
    public final static int LOGIN_SETTING = 0x02;

    @Override
    protected void showTitle() {
        super.showTitle();

        TextView title = (TextView) findViewById(R.id.custom_title_label);
        title.setText(R.string.app_name);

    }

    @Override
    protected void afterLogin() {

        if (UtilString.isBlank(moduleCode)) {
            Intent intent = new Intent(LogonActivity.this, MainActivity.class);//DashboardActivity,MainActivity,MainListActivity.class);
            startActivityForResult(intent, LOGINACTION);
        } else {
        }
    }
}
