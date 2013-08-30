package com.app.example;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.app.example.service.UpdateTimeService;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.UIHelper;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Logger.i(UpdateTimeService.class.getName() + ",update time...");
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // 程序升级时,后台服务会停止
        if (!UIHelper.isServiceRunning(context, UpdateTimeService.class.getName())){
            Intent intent = new Intent(context, UpdateTimeService.class);
            context.startService(intent);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Logger.i("onDeleted");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        Logger.i("onEnabled");
        super.onEnabled(context);

        Intent intent = new Intent(context, UpdateTimeService.class);
        context.startService(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.i("receive...");
        Logger.i(intent.getAction());
        super.onReceive(context, intent);
    }

    @Override
    public void onDisabled(Context context) {
        Logger.i("onDisabled");
        super.onDisabled(context);

        Intent intent = new Intent(context, UpdateTimeService.class);
        context.stopService(intent);
    }
}
