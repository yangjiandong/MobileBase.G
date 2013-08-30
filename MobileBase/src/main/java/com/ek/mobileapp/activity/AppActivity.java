package com.ek.mobileapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public abstract class AppActivity extends Activity {
    private static AppActivity currentActivity;

    public static AppActivity getCurrentActivity() {
        return currentActivity;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//1);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//3
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        currentActivity = this;
        if (useTabletLayout())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);//4);//由物理感应器决定显示方向
    }

    protected void onResume() {
        super.onResume();
        currentActivity = this;
    }

    public boolean isInLandscape() {
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        boolean flag;
        if (displaymetrics.widthPixels >= displaymetrics.heightPixels)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean useTabletLayout() {
        return isRunningInLargeTablet();
    }

    public boolean useTabletSizing() {
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        boolean flag;
        if (!isRunningInLargeTablet() && (!isRunningInSmallTablet() || displaymetrics.density > 1.0F))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public boolean isRunningInAnyTablet() {
        boolean flag;
        if (!isRunningInLargeTablet() && !isRunningInSmallTablet())
            flag = false;
        else
            flag = true;
        return flag;
    }

    public boolean isRunningInLargeTablet() {
        boolean flag;
        if ((0xf & getResources().getConfiguration().screenLayout) == Configuration.SCREENLAYOUT_SIZE_XLARGE)//4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isRunningInSmallTablet() {
        boolean flag;
        if ((0xf & getResources().getConfiguration().screenLayout) == Configuration.SCREENLAYOUT_SIZE_LARGE)//3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static AppActivity getActivityOf(Context context) {
        AppActivity appactivity1;
        if (context instanceof AppActivity)
            appactivity1 = (AppActivity) context;
        else if (context instanceof ContextThemeWrapper) {
            appactivity1 = getActivityOf(((ContextThemeWrapper) context).getBaseContext());
        } else {
            AppActivity appactivity = getCurrentActivity();
            //boolean flag;
            //if (appactivity != null)
            //    flag = true;
            //else
            //    flag = false;
            //Verifier.check(flag, "no application activity found!");
            //TODO
            appactivity1 = appactivity;
        }
        return appactivity1;
    }

    public static AppActivity getActivityOf(View view) {
        return getActivityOf(view.getContext());
    }
}
