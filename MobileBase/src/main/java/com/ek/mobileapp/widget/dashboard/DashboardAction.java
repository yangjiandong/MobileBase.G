package com.ek.mobileapp.widget.dashboard;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.utils.ViewUtils;
import com.ek.mobileapp.widget.common.AppButton;
import com.ek.mobilebapp.R;

public class DashboardAction extends AppButton {

    public DashboardAction(AppActivity context, String text, int iconRes) {
        super(context.getApplicationContext(), text, iconRes);

        appContext = context;
        if (appContext.useTabletLayout()) {
            setTextAppearance(context, R.style.tablet_menu_text);
            setShadowLayer(0.0F, 0.0F, 0.0F, 0);
            setBackgroundResource(R.drawable.default_selection_btn);
            if (appContext.isInLandscape() && iconRes > 0) {
                setCompoundDrawablesWithIntrinsicBounds(iconRes, 0, 0, 0);
                setCompoundDrawablePadding(ViewUtils.getPixelsOf(6, context));
                int j = ViewUtils.getPixelsOf(25, context);
                setPadding(j, getPaddingTop(), j, getPaddingBottom());
                setGravity(16);
            } else {
                setGravity(1);
                setPadding(0, getPaddingTop(), 0, getPaddingBottom());
            }
        }
    }

    protected final AppActivity appContext;
}
