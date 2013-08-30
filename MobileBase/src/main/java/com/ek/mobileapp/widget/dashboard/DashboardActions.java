package com.ek.mobileapp.widget.dashboard;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobilebapp.R;

public class DashboardActions {

    public static class Featured extends DashboardAction {

        protected void clickAction() {
            //appContext.startActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
        }

        public Featured(AppActivity appactivity) {
            //Context context = appactivity.getApplicationContext();
            super(appactivity, "医嘱执行", R.drawable.db_icon_featured);
        }
    }

    public static class Demo extends DashboardAction {

        protected void clickAction() {
            //appContext.startActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
        }

        public Demo(AppActivity appactivity) {
            super(appactivity, "体温单测试", R.drawable.db_icon_featured);
            //Context context = appactivity.getApplicationContext();
        }
    }

    public static class Demo2 extends DashboardAction {

        protected void clickAction() {
            //appContext.startActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
        }

        public Demo2(AppActivity appactivity) {
            //Context context = appactivity.getApplicationContext();
            super(appactivity, "治疗单据", R.drawable.db_icon_featured);
        }
    }

    public static class Demo3 extends DashboardAction {

        protected void clickAction() {
            //appContext.startActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
        }

        public Demo3(AppActivity appactivity) {
            //Context context = appactivity.getApplicationContext();
            super(appactivity, "体征查询", R.drawable.db_icon_featured);
        }
    }

    public static class Demo4 extends DashboardAction {

        protected void clickAction() {
            ToastUtils.show(appContext, "演示4......sswc");
            //appContext.startActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
        }

        public Demo4(AppActivity appactivity) {
            //Context context = appactivity.getApplicationContext();
            super(appactivity, "演示4", R.drawable.db_icon_featured);
        }
    }

    public static class Demo5 extends DashboardAction {

        protected void clickAction() {
            //appContext.startActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
            ToastUtils.show(appContext, "演示5......sswc");
        }

        public Demo5(AppActivity appactivity) {
            //Context context = appactivity.getApplicationContext();
            super(appactivity, "演示5", R.drawable.db_icon_featured);
        }
    }

    public DashboardActions() {
    }
}
