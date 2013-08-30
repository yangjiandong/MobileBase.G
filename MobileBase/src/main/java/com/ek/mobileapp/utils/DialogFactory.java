package com.ek.mobileapp.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.ek.mobileapp.AppResource;

public class DialogFactory {
    public interface DialogCallbacks {
        public void runOk(DialogInterface dialog, int which);

        public void runCancel(DialogInterface dialog, int which);
    }

    public static Dialog create(final Context context, String title, String msg, final DialogCallbacks call) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title)
                .setIcon(AppResource.IconLauncher).setNegativeButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        call.runOk(dialog, which);
                    }
                }).setPositiveButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        call.runCancel(dialog, which);
                    }
                });
        if (!UtilString.isBlank(msg)) {
            builder.setMessage(msg);
        }

        Dialog alertDialog = builder.create();
        return alertDialog;
    }
}
