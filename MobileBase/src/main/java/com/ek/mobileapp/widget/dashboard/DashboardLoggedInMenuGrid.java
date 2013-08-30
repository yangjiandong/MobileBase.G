package com.ek.mobileapp.widget.dashboard;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.utils.ViewUtils;

public class DashboardLoggedInMenuGrid extends DashboardMenuGrid {
    public static final int MAXCMS = 3;

    public DashboardLoggedInMenuGrid(AppActivity appactivity) {
        super(appactivity.getApplicationContext(), MAXCMS);

        appContext = appactivity;
        int i = ViewUtils.getPixelsOf(10, appactivity);
        setPadding(i, i, i, i);

        addItemS(new DashboardActions.Featured(appactivity));
        addItemS(new DashboardActions.Demo(appactivity));
        addItemS(new DashboardActions.Demo2(appactivity));
        addItem(new DashboardActions.Demo3(appactivity));
        addItem(new DashboardActions.Demo4(appactivity));
        addItem(new DashboardActions.Demo5(appactivity));
    }

    public void addItemS(DashboardAction dashboardaction) {
        super.addItem(dashboardaction);

        byte byte0;
        int i;
        if (appContext.useTabletSizing())
            byte0 = 50;
        else
            byte0 = 20;
        i = ViewUtils.getPixelsOf(byte0, appContext);
        dashboardaction.setPadding(0, i, 0, i);
    }

    private final AppActivity appContext;
}
