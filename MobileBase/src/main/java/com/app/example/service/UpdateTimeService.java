package com.app.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.app.example.ExampleAppWidgetProvider;
import com.app.example.MainActivity;
import com.ek.mobilebapp.R;

public class UpdateTimeService extends Service {
    int i = 0;
    Timer timer;
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            i++;
            //Logger.d("time task" + i);

            AppWidgetManager am = AppWidgetManager.getInstance(getApplicationContext());
            ComponentName componetName = new ComponentName(getApplicationContext(), ExampleAppWidgetProvider.class);
            int[] appIds = am.getAppWidgetIds(componetName);
            for (int id : appIds) {
                int layoutId = R.layout.example_appwidget;
                RemoteViews views = new RemoteViews(getPackageName(), layoutId);

                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String time = format.format(date);
                views.setTextViewText(R.id.tv_time, time);

                //views.not
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 100, intent, 0);

                //设置widget点击事件
                views.setOnClickPendingIntent(R.id.tv_time, pIntent);

                am.updateAppWidget(id, views);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        timer = new Timer();
        timer.schedule(task, 0, 1000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
