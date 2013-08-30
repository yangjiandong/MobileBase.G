package com.app;

import com.ek.mobileapp.MainApplication;
import com.ek.mobilebapp.R;

public class MyApplication extends MainApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //NurseGlobalCache.clearCache();
    }

    @Override
    public boolean saveLoginCookie() {
        return false;
    }

    @Override
    public String getModuleName() {
        return this.getResources().getString(R.string.module_name);
    }

    @Override
    public int getAppName() {
        return R.string.app_name;
    }
}
