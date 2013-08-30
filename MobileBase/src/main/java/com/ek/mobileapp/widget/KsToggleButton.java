package com.ek.mobileapp.widget;

import com.ek.mobilebapp.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ToggleButton;

public class KsToggleButton extends ToggleButton {
    private Drawable enableChecked = null;
    private Drawable disableChecked = null;
    private Drawable enable = null;
    private Drawable disable = null;

    public KsToggleButton(Context paramContext) {
        super(paramContext);
        a(paramContext);
    }

    public KsToggleButton(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        a(paramContext);
    }

    public KsToggleButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        a(paramContext);
    }

    private void setDr() {
        if (super.isChecked()) {
            if (super.isEnabled())
                setBackgroundDrawable(this.enableChecked);
            else {
                setBackgroundDrawable(this.disableChecked);
            }
        } else {
            setBackgroundDrawable(this.enable);

            if (super.isEnabled())
                setBackgroundDrawable(this.enable);
            else
                setBackgroundDrawable(this.disable);
        }
    }

    protected void a(Context paramContext) {
        this.disableChecked = paramContext.getResources().getDrawable(R.drawable.toggle_btn_on_disable_background);
        this.enableChecked = paramContext.getResources().getDrawable(R.drawable.toggle_btn_on_background);
        this.enable = paramContext.getResources().getDrawable(R.drawable.toggle_btn_off_background);
        this.disable = paramContext.getResources().getDrawable(R.drawable.toggle_btn_off_disable_background);
    }

    protected void onFinishInflate() {
        setDr();
    }

    public void refreshDrawableState() {
        super.refreshDrawableState();
        setDr();
    }
}
