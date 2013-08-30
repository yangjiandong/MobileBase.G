package com.ek.mobileapp.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TabHost;
import android.widget.TextView;

import com.ek.mobileapp.MainApplication;

public class ViewUtils {

    public static final float SCROLL_SPIT = 20.0f;

    public static final int color_spit = Color.BLACK;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int diptopx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int pxtodip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void putViewsInLine(TextView[] views, int width, double space_width) {
        int padding = (int) (width * space_width);
        for (TextView v : views) {
            v.setPadding(padding, 0, 0, 0);
        }
    }

    public static Bitmap textAsBitmap(Bitmap image, String text, float textSize, int textColor) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        //paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        int width = (int) (paint.measureText(text) + 0.5f); // round
        float baseline = (int) (paint.ascent() + 0.5f);
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap newMapBitmap = image.copy(Bitmap.Config.ARGB_8888, true);
        //Bitmap newMapBitmap=null;
        try {

            //newMapBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888); // this creates a MUTABLE bitmap
            //Canvas canvas = new Canvas(bmp);

            //Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(newMapBitmap);
            //canvas.drawColor(Color.GREEN);
            canvas.drawText(text, 10, 20, paint);
        } catch (Exception e) {
            Log.e("textAsBitmap", e.getMessage());
        }

        String filename = "version.jpg";
        File sd = Environment.getExternalStorageDirectory();
        File dest = new File(sd, filename);

        try {
            FileOutputStream out = new FileOutputStream(dest);
            newMapBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newMapBitmap;
    }

    public static void setTabColor(TabHost tabhost) {
        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#cccccc")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#006699")); // selected
    }

    public static void saveInfoToFile(String logFilePath, String s, Exception excp) {
        //没有挂载SD卡，无法写文件
        if (logFilePath == "") {
            return;
        }

        FileWriter fw = null;
        PrintWriter pw = null;
        try {

            File logFile = new File(logFilePath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            pw.println("--------------------" + (new Date().toLocaleString()) + "---------------------");
            //
            if (s == null) {
                excp.printStackTrace(pw);
            } else {
                pw.println(s);
            }

            pw.close();
            fw.close();
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("saveInfoToFile", e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void saveInfoToFile(MainApplication appContext, String logFileName, String s, Exception excp) {
        String savePath = "";
        String logFilePath = "";
        //判断是否挂载了SD卡
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + appContext.getPackageName()
                    + "/Log/";
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            logFilePath = savePath + logFileName;
        }
        //没有挂载SD卡，无法写文件
        if (logFilePath == "") {
            return;
        }
        saveInfoToFile(logFilePath, s, excp);
    }

    public static void saveErrorLogFromMyPackageName(Exception excp) {
        String errorlog = "errorlog.txt";
        saveInfoToFileFromMyPackageName(errorlog, null, excp);
    }

    public static void saveInfoToFileFromMyPackageName(String logFileName, String s, Exception excp) {
        String savePath = "";
        String logFilePath = "";
        //判断是否挂载了SD卡
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + MainApplication.getMyPackageName()
                    + "/Log/";
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            logFilePath = savePath + logFileName;
        }
        //没有挂载SD卡，无法写文件
        if (logFilePath == "") {
            return;
        }
        saveInfoToFile(logFilePath, s, excp);
    }

    public static void saveInfoLog(MainApplication appContext, String s) {
        String errorlog = "inoflog.txt";
        saveInfoToFile(appContext, errorlog, s, null);
    }

    /**
     * 保存异常日志
     * @param excp
     */
    public static void saveErrorLog(MainApplication appContext, Exception excp) {
        String errorlog = "errorlog.txt";
        saveInfoToFile(appContext, errorlog, null, excp);
    }

    //
    public static boolean onTablet(Context c) {
        int intScreenSize = c.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        return (intScreenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) // LARGE
                || (intScreenSize == Configuration.SCREENLAYOUT_SIZE_LARGE + 1); // Configuration.SCREENLAYOUT_SIZE_XLARGE
    }

    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    //摇头效果
    //ImageView tv = (ImageView) findViewById(R.id.logon_icon);
    //ViewUtils.startShakeAnimation(tv);
    public static void startShakeAnimation(View v) {
        int pivot = Animation.RELATIVE_TO_SELF;
        CycleInterpolator interpolator = new CycleInterpolator(3.0f);
        RotateAnimation animation = new RotateAnimation(0, 15, pivot, 0.5f, pivot, 0.5f);
        animation.setStartOffset(4000);
        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setInterpolator(interpolator);
        v.startAnimation(animation);
    }

    public static int getPixelsOf(int i, float f)
    {
        return (int)(0.5F + f * (float)i);
    }

    public static int getPixelsOf(int i, Context context)
    {
        return getPixelsOf(i, context.getResources().getDisplayMetrics().density);
    }

    public static int getPixelsOf(int i, View view)
    {
        return getPixelsOf(i, view.getContext());
    }
}
