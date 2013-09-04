package com.ek.mobileapp.action;

import java.io.File;
import java.io.InputStream;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.utils.HttpTool;
import com.ek.mobileapp.utils.Logger;

public class ApiClient {
    /**
     * 获取网络图片
     * @param url
     * @return
     */
    public static Bitmap getNetBitmap(String url) {
        Bitmap bitmap = null;

        try {
            String ip = MainApplication.host_ip;
            String httpUrl = "http://" + ip + "/common/download_image?id=" + url;
            Logger.d("getNetBitmap:" + httpUrl);
            InputStream inStream = HttpTool.getTool().getFile(httpUrl);
            bitmap = BitmapFactory.decodeStream(inStream);
            inStream.close();
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }

        return bitmap;
    }

    public static Bitmap getNetBitmapByUrl(String url) {
        Bitmap bitmap = null;

        try {
            String ip = MainApplication.host_ip;
            String httpUrl = "http://" + ip + url;
            Logger.d("getNetTitleBitmap:" + httpUrl);
            InputStream inStream = HttpTool.getTool().getFile(httpUrl);
            bitmap = BitmapFactory.decodeStream(inStream);
            inStream.close();
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }

        return bitmap;
    }

    public static boolean uploadImage(File fileName, String address) throws Exception {

        String ip = MainApplication.host_ip;
        String url = "http://" + ip + address;
        JSONObject res = HttpTool.getTool().postFile(fileName, url);

        if (res == null)
            return false;
        else
            return res.getBoolean("success");
    }
}
