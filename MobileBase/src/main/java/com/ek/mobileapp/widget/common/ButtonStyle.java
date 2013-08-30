package com.ek.mobileapp.widget.common;

import android.view.Gravity;

import com.ek.mobileapp.activity.AppActivity;

public abstract class ButtonStyle extends TextStyle {

    public ButtonStyle(AppActivity appactivity) {
        super(appactivity);
    }

    public int gravity() {
        return Gravity.CENTER;//17;
    }
}
