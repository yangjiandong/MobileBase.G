package com.ek.mobileapp;

import java.io.File;

import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ek.mobileapp.utils.FileUtils;
import com.ek.mobileapp.utils.ImageUtils;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.TimeTool;
import com.ek.mobileapp.utils.UIHelper;
import com.ek.mobilebapp.R;

/**
 * 应用程序启动类：显示欢迎界面并跳转到主界面
 */
public abstract class AppStart extends Activity {
    //MainApplication ac;

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

        // 是否有缓存图片
        if (!MainApplication.get().getConfTitle().equals("")) {
            ImageView imgView = (ImageView) findViewById(R.id.loading_des);
            final String filename = FileUtils.getFileName(MainApplication.get().getConfTitle());
            String filepath = imgView.getContext().getFilesDir() + File.separator + filename;
            Logger.d("imgView:" + filepath);
            File file = new File(filepath);
            if (file.exists()) {
                Bitmap bmp = ImageUtils.getBitmap(imgView.getContext(), filename);
                imgView.setImageBitmap(bmp);
            }
        }

        //volley
        RequestQueue queue = Volley.newRequestQueue(this);
        String ip = MainApplication.host_ip;
        String url = "http://" + ip + "/common/host_title";
        JsonObjectRequest jsobjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                String titleString = response.getString("title");

                                //显示标语
                                ImageView face = (ImageView) findViewById(R.id.loading_des);
                                String imgUrl = "/common/download_title_image?id=" + titleString;
                                Logger.d("title url:" + imgUrl);

                                UIHelper.showLoadImage(face, titleString, "服务端图片下载失败", R.drawable.loading_des_r, imgUrl);//指定url

                                MainApplication.get().setProperty(AppConfig.CONF_TITLE_IMG, titleString);
                            }
                        } catch (Exception e) {
                            Logger.e("volley 取数出错");
                            Logger.e(e.toString());
                        } finally {
                            try {
                                Thread.sleep(2000);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            redirectTo();
                        }

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError verror) {
                        Logger.e("volley 失败");
                        Logger.e(verror.toString());

                        redirectTo();
                    }
                });
        queue.add(jsobjRequest);
    }

    /**
     * 跳转到...
     */
    abstract protected void redirectTo();

    void start() {
        final View view = View.inflate(this, AppResource.AppStartLayout, null);

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();//
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

        });
    }
}