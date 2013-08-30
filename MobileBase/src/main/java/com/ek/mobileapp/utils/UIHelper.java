package com.ek.mobileapp.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.LogonActivity;
import com.app.example.MainActivity;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.action.ApiClient;
import com.ek.mobileapp.activity.ImageZoomDialog;
import com.ek.mobileapp.activity.SettingActivity;
import com.ek.mobileapp.widget.ScreenShotView;
import com.ek.mobilebapp.R;

public class UIHelper {

    /**
     * 弹出Toast消息
     *
     * @param msg
     */
    public static void ToastMessage(Context cont, String msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, int msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, String msg, int time) {
        Toast.makeText(cont, msg, time).show();
    }

    /**
     * 显示登录页面
     *
     * @param activity
     */
    public static void showLoginDialog(Context context) {
        Intent intent = new Intent(context, LogonActivity.class);
        if (context instanceof MainActivity)
            intent.putExtra("LOGINTYPE", LogonActivity.LOGIN_MAIN);
        else if (context instanceof SettingActivity)
            intent.putExtra("LOGINTYPE", LogonActivity.LOGIN_SETTING);
        else
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 清除app缓存
     *
     * @param activity
     */
    public static void clearAppCache(Activity activity) {
        final MainApplication ac = (MainApplication) activity.getApplication();
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    ToastMessage(ac, "缓存清除成功");
                } else {
                    ToastMessage(ac, "缓存清除失败");
                }
            }
        };
        new Thread() {
            public void run() {
                Message msg = new Message();
                try {
                    ac.clearAppCache();
                    msg.what = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = -1;
                }
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 添加截屏功能
     */
    @SuppressLint("NewApi")
    public static void addScreenShot(Activity context,
            com.ek.mobileapp.widget.ScreenShotView.OnScreenShotListener mScreenShotListener) {
        Activity cxt = null;
        if (context instanceof Activity) {
            cxt = (Activity) context;
            //cxt.setAllowFullScreen(false);
            ScreenShotView screenShot = new ScreenShotView(cxt,
                    mScreenShotListener);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            context.getWindow().addContentView(screenShot, lp);
        }
    }

    /**
     * 显示图片对话框
     *
     * @param context
     * @param imgUrl
     */
    public static void showImageDialog(Context context, String imgUrl) {
        Intent intent = new Intent(context, com.ek.mobileapp.activity.ImageDialog.class);
        intent.putExtra("img_url", imgUrl);
        context.startActivity(intent);
    }

    public static void showImageZoomDialog(Context context, String imgUrl) {
        Intent intent = new Intent(context, ImageZoomDialog.class);
        intent.putExtra("img_url", imgUrl);
        context.startActivity(intent);
    }

    /**
     * 加载显示用户头像
     *
     * @param imgFace
     * @param faceURL
     */
    public static void showUserFace(final ImageView imgFace,
            final String faceURL) {
        showLoadImage(imgFace, faceURL,
                imgFace.getContext().getString(R.string.msg_load_userface_fail));
    }

    /**
     * 加载显示图片
     *
     * @param imgFace
     * @param faceURL
     * @param errMsg
     */
    public static void showLoadImage(final ImageView imgView,
            final String imgURL, final String errMsg) {
        // 读取本地图片
        if (StringUtils.isEmpty(imgURL) || imgURL.endsWith("portrait.gif")) {
            Bitmap bmp = BitmapFactory.decodeResource(imgView.getResources(),
                    R.drawable.widget_dface);
            imgView.setImageBitmap(bmp);
            return;
        }

        // 是否有缓存图片
        final String filename = FileUtils.getFileName(imgURL);
        // Environment.getExternalStorageDirectory();返回/sdcard
        String filepath = imgView.getContext().getFilesDir() + File.separator
                + filename;
        File file = new File(filepath);
        if (file.exists()) {
            Bitmap bmp = ImageUtils.getBitmap(imgView.getContext(), filename);
            imgView.setImageBitmap(bmp);
            return;
        }

        // 从网络获取&写入图片缓存
        String _errMsg = imgView.getContext().getString(
                R.string.msg_load_image_fail);
        if (!StringUtils.isEmpty(errMsg))
            _errMsg = errMsg;
        final String ErrMsg = _errMsg;
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1 && msg.obj != null) {
                    imgView.setImageBitmap((Bitmap) msg.obj);
                    try {
                        // 写图片缓存
                        ImageUtils.saveImage(imgView.getContext(), filename,
                                (Bitmap) msg.obj);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastMessage(imgView.getContext(), ErrMsg);
                }
            }
        };
        new Thread() {
            public void run() {
                Message msg = new Message();
                try {
                    Bitmap bmp = ApiClient.getNetBitmap(imgURL);
                    msg.what = 1;
                    msg.obj = bmp;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = -1;
                    msg.obj = e;
                }
                handler.sendMessage(msg);
            }
        }.start();
    }

    public static boolean isServiceRunning(Context mContext,String className) {

        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
        mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                   = activityManager.getRunningServices(30);

        if (!(serviceList.size()>0)) {
            return false;
        }

        for (int i=0; i<serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
