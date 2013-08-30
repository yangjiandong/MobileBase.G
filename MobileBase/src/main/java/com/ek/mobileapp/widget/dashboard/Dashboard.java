package com.ek.mobileapp.widget.dashboard;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.widget.common.AppContent;

public class Dashboard extends AppContent {
    public Dashboard(AppActivity appactivity) {
        super(appactivity);
        createLayout();
    }

    private void createLayout() {
        loggedInMenu = new DashboardLoggedInMenuGrid(appContext);
        addView(loggedInMenu, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);//-1, -2);
    }

    public void refresh() {

    }

    private DashboardLoggedInMenuGrid loggedInMenu;

}
