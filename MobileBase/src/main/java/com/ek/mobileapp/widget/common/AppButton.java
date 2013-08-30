package com.ek.mobileapp.widget.common;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AppButton extends Button {

    public AppButton(Context context) {
        this(context, ((ButtonStyle) (null)));
    }

    public AppButton(Context context, int iconRes) {
        this(context, (new StyleSheet(context)).buttons().action());
        defaultIconRes = iconRes;
        setCompoundDrawablesWithIntrinsicBounds(0, iconRes, 0, 0);
    }

    public AppButton(Context context, ButtonStyle buttonstyle) {
        super(context);

        ButtonStyle myStyle = defaultStyle();
        if (myStyle == null)
            myStyle = buttonstyle;
        if (myStyle == null)
            myStyle = getStyleSheet().buttons().defaultButton();
        setStyle(myStyle);

        setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View view) {
                invokeClickAction();
            }

            final AppButton this$0;
            {
                this$0 = AppButton.this;
            }
        });
    }

    public AppButton(Context context, CharSequence charsequence) {
        this(context);
        setText(charsequence);
    }

    public AppButton(Context context, String text, int iconRes) {
        this(context, iconRes);
        setText(text);
    }

    public AppButton(ViewGroup viewgroup, CharSequence charsequence) {
        this(viewgroup.getContext(), charsequence);
        viewgroup.addView(this);
    }

    protected void clickAction() {
    }

    protected ButtonStyle defaultStyle() {
        return null;
    }

    public StyleSheet getStyleSheet() {
        return new StyleSheet(getContext());
    }

    public void invokeClickAction() {
        clickAction();

        //AppActivity appactivity = AppActivity.getActivityOf(this);
        //LoginView.ensureLoggedIn(requiresLogin, appactivity, new Runnable() {
        //    public void run() {
        //        clickAction();
        //    }

        //    final AppButton this$0;
        //    {
        //        this$0 = AppButton.this;
        //        //super();
        //    }
        //});
    }

    protected void onDraw(Canvas canvas) {
        int i;
        if (style != null)
            i = style.pressedTextStyle();
        else
            i = 0;
        if (i > 0) {
            Context context = getContext();
            if (!isPressed())
                i = style.textStyle();
            setTextAppearance(context, i);
        }
        super.onDraw(canvas);
    }

    //public void setOnClickListener(android.view.View.OnClickListener onclicklistener) {
    //    throw new AssertionError("explicit click listener disabled, override clickAction() instead");
    //}

    public void setStyle(ButtonStyle buttonstyle) {
        style = buttonstyle;
        if (buttonstyle != null)
            buttonstyle.applyTo(this);
    }

    public int defaultIconRes;
    public boolean requiresLogin;
    private ButtonStyle style;
}
