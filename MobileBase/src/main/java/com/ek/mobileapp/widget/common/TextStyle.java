package com.ek.mobileapp.widget.common;

import android.widget.TextView;

import com.ek.mobileapp.activity.AppActivity;

public abstract class TextStyle {

    public TextStyle(AppActivity appactivity,int ttextSize,int textStyle) {
        this.tabletTextSize = ttextSize;
        this.textStyle = textStyle;
        appContext = appactivity;
        forTablet = appactivity.useTabletSizing();
        boolean flag;
        if (forTablet)
            flag = false;
        else
            flag = true;
        forPhone = flag;
    }

    public TextStyle(AppActivity appactivity) {
        tabletTextSize = 0;
        textStyle = 0;
        appContext = appactivity;
        forTablet = appactivity.useTabletSizing();
        boolean flag;
        if (forTablet)
            flag = false;
        else
            flag = true;
        forPhone = flag;
    }

    public void applyTo(TextView textview) {
        if (gravity() != 0)
            textview.setGravity(gravity());
        textview.setBackgroundResource(0);

        if (backgroundColor() == 0) {//goto _L2; else goto _L1
            if (backgroundResource() > 0)
                textview.setBackgroundResource(backgroundResource());

            //if(true) goto _L4; else goto _L3
            if (textStyle() > 0)
                textview.setTextAppearance(appContext, textStyle());
            if (textSize() > 0)
                textview.setTextSize(textSize());
            if (forTablet && tabletTextSize() > 0)
                textview.setTextSize(tabletTextSize());
        } else {
            //_L1:
            textview.setBackgroundColor(backgroundColor());
        }
    }

    public int backgroundColor() {
        return 0;
    }

    public int backgroundResource() {
        return 0;
    }

    public int gravity() {
        return 0;
    }

    public int pressedTextStyle() {
        return 0;
    }

    public int tabletTextSize() {
        return tabletTextSize;
    }

    public int textSize() {
        return 0;
    }

    public int textStyle() {
        return textStyle;
    }

    protected int tabletTextSize;
    protected int textStyle;
    protected final AppActivity appContext;
    protected final boolean forPhone;
    protected final boolean forTablet;
}
