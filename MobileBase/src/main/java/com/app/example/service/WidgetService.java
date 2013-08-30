package com.app.example.service;

import java.util.Date;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.app.example.ExampleAppWidgetProvider;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobilebapp.R;

public class WidgetService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Logger.d("WidgetService - start");
        super.onStart(intent, startId);

        RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.example_appwidget);
        rv.setTextViewText(R.id.tv_time, new Date().toLocaleString());
        ComponentName cn = new ComponentName(this, ExampleAppWidgetProvider.class);
        AppWidgetManager am = AppWidgetManager.getInstance(this);
        am.updateAppWidget(cn, rv);
    }
}
