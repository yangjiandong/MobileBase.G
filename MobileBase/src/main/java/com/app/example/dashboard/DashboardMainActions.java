package com.app.example.dashboard;

import android.content.Intent;

import com.app.example.EndlessListActivity;
import com.app.example.HzCursorListActivity;
import com.app.example.HzListActivity;
import com.app.example.LetterListActivity;
import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.widget.dashboard.DashboardAction;
import com.ek.mobilebapp.R;

public class DashboardMainActions {
    public static class CursorLists extends DashboardAction {
        protected void clickAction() {
            //appContext.startActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
            Intent intent = new Intent(getContext(), HzCursorListActivity.class);
            appContext.startActivity(intent);
        }

        public CursorLists(AppActivity appactivity) {
            super(appactivity, "本地汉字", R.drawable.doctor);
        }
    }

    public static class Lists extends DashboardAction {
        protected void clickAction() {
            //appContext.startActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
            Intent intent = new Intent(getContext(), EndlessListActivity.class);
            appContext.startActivity(intent);
        }

        public Lists(AppActivity appactivity) {
            super(appactivity, "列  表", R.drawable.db_icon_featured);
        }
    }

    public static class HzLists extends DashboardAction {
        protected void clickAction() {
            Intent intent = new Intent(getContext(), HzListActivity.class);
            appContext.startActivity(intent);
        }

        public HzLists(AppActivity appactivity) {
            super(appactivity, "汉  字", R.drawable.db_icon_featured);
        }
    }

    public static class ALists extends DashboardAction {
        protected void clickAction() {
            Intent intent = new Intent(getContext(), LetterListActivity.class);
            appContext.startActivity(intent);
        }

        public ALists(AppActivity appactivity) {
            super(appactivity, "字母索引", R.drawable.db_icon_featured);
        }
    }

    public DashboardMainActions() {
    }
}
