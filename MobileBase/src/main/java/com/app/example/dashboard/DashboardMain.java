package com.app.example.dashboard;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.widget.common.AppContent;

public class DashboardMain extends AppContent {
    public DashboardMain(AppActivity appactivity) {
        super(appactivity);
        createLayout();
    }

    private void createLayout() {
        loggedInMenu = new DashboardMainMenuGrid(appContext);
        addView(loggedInMenu, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);//-1, -2);
    }

    public void refresh() {

    }

    private DashboardMainMenuGrid loggedInMenu;

}
