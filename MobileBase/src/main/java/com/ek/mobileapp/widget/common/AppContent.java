package com.ek.mobileapp.widget.common;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobilebapp.R;

public class AppContent extends VGroup {

    public AppContent(AppActivity appactivity) {
        super(appactivity);
        appContext = appactivity;
        setBackgroundResource(R.color.light_background);
    }

    protected StyleSheet getStyleSheet() {
        return new StyleSheet(appContext);
    }

    public void onReorient() {
    }

    public void refresh() {
    }

    public void refreshAfterLogin() {
        refresh();
    }

    protected AppActivity appContext;
}
