package com.ek.mobileapp;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

import com.ek.mobileapp.utils.TimeTool;

/**
 * 应用程序启动类：显示欢迎界面并跳转到主界面
 */
public abstract class AppStart extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final View view = View.inflate(this, AppResource.AppStartLayout, null);
        setContentView(view);

        //
        Resources res = getResources();
        String text = String.format(res.getString(AppResource.AboutContentRightReserved), TimeTool.getYear());
        TextView t = (TextView) view.findViewById(AppResource.LoadingCopyright);
        t.setText(text);

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

        });

    }

    /**
     * 跳转到...
     */
    abstract protected void redirectTo();

}