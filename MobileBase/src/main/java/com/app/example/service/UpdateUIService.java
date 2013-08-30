package com.app.example.service;

import com.ek.mobileapp.utils.Logger;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.TextView;

public class UpdateUIService extends Service{
    int data;
    Handler handler;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder{
        public void setDate(final TextView tv, final UpdateData updata){
            handler = new Handler(){
                public void handleMessage(Message msg){
                    int type = msg.getData().getInt("type");
                    int message = msg.getData().getInt("msg");
                    if (type == 0){
                        updata.update(tv, message);
                    }
                }
            };

            new Thread(new MyThread()).start();
        }

        //取其他信息
        public void getServiceInfo(final TextView tv, final UpdateData updata){
            handler = new Handler(){
                public void handleMessage(Message msg){
                    int type = msg.getData().getInt("type");
                    int message = msg.getData().getInt("msg");
                    if (type == 0){
                        updata.update(tv, message);
                    }
                }
            };

            new Thread(new MyThread()).start();
        }
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            //while(true){
                data ++;
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("type", 0);
                bundle.putInt("msg", data);
                msg.setData(bundle);
                handler.sendMessage(msg);
                try{
                    Thread.sleep(1000);
                }catch (Exception e) {
                    Logger.e("MyThread error");
                }
            //}
        }
    }

    //界面需完成的操作
    public interface UpdateData {
        public void update(TextView tv, int data);
    }

}
