package com.app;

import android.content.Intent;

import com.app.example.DashboardActivity;
import com.app.example.MainActivity;
import com.ek.mobileapp.AppStart;

public class MyAppStart extends AppStart {

    @Override
    protected void redirectTo() {
        Intent intent = new Intent(this, LogonActivity.class);//LogonActivity,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
