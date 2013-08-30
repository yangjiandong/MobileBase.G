package com.ek.mobileapp.widget.common;

import android.content.Context;
import android.widget.LinearLayout;

public class VGroup extends LinearLayout {

    public VGroup(Context context) {
        super(context);
        setOrientation(VERTICAL);//1);
    }
}
