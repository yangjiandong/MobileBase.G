package com.app.example.dashboard;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.utils.ViewUtils;
import com.ek.mobileapp.widget.dashboard.DashboardAction;
import com.ek.mobileapp.widget.dashboard.DashboardMenuGrid;

//主页菜单
public class DashboardMainMenuGrid extends DashboardMenuGrid {
    public static final int MAXCMS = 3;

    public DashboardMainMenuGrid(AppActivity appactivity) {
        super(appactivity.getApplicationContext(), MAXCMS);

        appContext = appactivity;
        int i = ViewUtils.getPixelsOf(10, appactivity);
        setPadding(i, i, i, i);

        addItemS(new DashboardMainActions.CursorLists(appactivity));
        addItemS(new DashboardMainActions.Lists(appactivity));
        addItemS(new DashboardMainActions.HzLists(appactivity));
        addItemS(new DashboardMainActions.ALists(appactivity));
        addItemS(new DashboardMainActions.ALists(appactivity));

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
