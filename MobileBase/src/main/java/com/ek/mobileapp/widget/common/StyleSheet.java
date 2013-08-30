package com.ek.mobileapp.widget.common;

import android.content.Context;
import android.widget.TextView;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.utils.ViewUtils;
import com.ek.mobilebapp.R;

public class StyleSheet {
    private abstract class BigSolidButton extends ButtonStyle {

        public void applyTo(TextView textview) {
            super.applyTo(textview);
            textview.setShadowLayer(1.0F, -1F, -1F, 0xff000000);
        }

        public int pressedTextStyle() {
            return R.style.vbutton_text_medium_large;
        }

        public int textStyle() {
            return R.style.vbutton_text_light_large;
        }

        public BigSolidButton(AppActivity appactivity) {
            super(appactivity);
        }
    }

    public class Buttons {

        public ButtonStyle action() {
            return new ButtonStyle(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    textview.setShadowLayer(1.0F, 1.0F, 1.0F, -1);
                }

                public int backgroundResource() {
                    return R.drawable.icon_action_item;
                }

                public int gravity() {
                    return 81;
                }

                public int textStyle() {
                    return R.style.icon_action_item;
                }
            };
        }

        public ButtonStyle bigBlue() {
            return new BigSolidButton(appContext) {
                public int backgroundResource() {
                    return R.drawable.blue_button;
                }
            };
        }

        public ButtonStyle bigGreen() {
            return new BigSolidButton(appContext) {

                public int backgroundResource() {
                    return R.drawable.green_button;
                }

            };
        }

        public ButtonStyle defaultButton() {
            return new DefaultButton(appContext);
        }

        public ButtonStyle link() {
            return new LightSolidButton(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    textview.setShadowLayer(1.0F, 1.0F, 1.0F, 0xff000000);
                }

                public int backgroundResource() {
                    return 0;
                }

                public int gravity() {
                    return 19;
                }

            };
        }

        public ButtonStyle list() {
            return new ButtonStyle(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    textview.setShadowLayer(0.0F, 0.0F, 0.0F, 0xff000000);
                }

                public int backgroundResource() {
                    return R.drawable.list_btn;
                }

                public int gravity() {
                    return 19;
                }

                public int textStyle() {
                    return R.style.vbutton_text_dark;
                }

            };
        }

        public ButtonStyle smallBlue() {
            return new LightSolidButton(appContext) {

                public int backgroundResource() {
                    return R.drawable.blue_button;
                }

            };
        }

        public ButtonStyle smallGreen() {
            return new LightSolidButton(appContext) {

                public int backgroundResource() {
                    return R.drawable.green_button;
                }

            };
        }

        public ButtonStyle switchableDefault(int tabletTextSize) {
            return new DefaultButton(appContext, tabletTextSize) {

            };
        }

        public TextStyle tabBar() {
            return new TextStyle(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    int i = ViewUtils.getPixelsOf(15, appContext);
                    textview.setPadding(i, 0, i, 0);
                    textview.setShadowLayer(0.0F, 0.0F, 0.0F, 0);
                    textview.setGravity(16);
                    textview.setBackgroundResource(0);
                    textview.setBackgroundColor(0);
                }

                public int textStyle() {
                    return R.style.tablet_tab_text;
                }

            };
        }

    }

    private class DefaultButton extends ButtonStyle {

        public DefaultButton(AppActivity appactivity, int tabletTextSize) {
            super(appactivity);
            this.tabletTextSize = tabletTextSize;
        }

        public DefaultButton(AppActivity appactivity) {
            super(appactivity);
        }

        public void applyTo(TextView textview) {
            super.applyTo(textview);
            textview.setShadowLayer(0.0F, 0.0F, 0.0F, 0xff000000);
        }

        public int backgroundResource() {
            return R.drawable.default_btn;
        }

        public int textStyle() {
            return 0x103004a;
        }

    }

    private abstract class LightSolidButton extends ButtonStyle {

        public LightSolidButton(AppActivity appactivity) {
            super(appactivity);
        }

        public void applyTo(TextView textview) {
            super.applyTo(textview);
            textview.setShadowLayer(1.0F, -1F, -1F, 0xff000000);
        }

        public int pressedTextStyle() {
            return R.style.vbutton_text_medium;
        }

        public int textStyle() {
            return R.style.vbutton_text_light;
        }

    }

    public class TextStyles {

        public TextStyle description() {
            return styleOf(R.style.description_text, 16);
        }

        public TextStyle emptyCategoryAdvice() {
            return new TextStyle(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    textview.setShadowLayer(1.0F, 1.0F, 1.0F, -1);
                }

                public int tabletTextSize() {
                    return 20;
                }

                public int textSize() {
                    return 18;
                }

                public int textStyle() {
                    return R.style.tile_list_subtitle_text;
                }

            };
        }

        public TextStyle emptyCategoryMessage() {
            return new TextStyle(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    textview.setShadowLayer(1.0F, 1.0F, 1.0F, -1);
                }

                public int tabletTextSize() {
                    return 20;
                }

                public int textSize() {
                    return 18;
                }

                public int textStyle() {
                    return R.style.tile_list_title_text;
                }

            };
        }

        public TextStyle listItemTitle() {
            return styleOf(R.style.list_item_title, 20);
        }

        public TextStyle listItemTitleLight() {
            return styleOf(R.style.list_item_title_light, 20);
        }

        public TextStyle progressBar() {
            return new TextStyle(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    textview.setShadowLayer(2.0F, -0.5F, -0.5F, 0xff000000);
                }

                public int tabletTextSize() {
                    return 20;
                }

                public int textStyle() {
                    return R.style.progress_bar_text;
                }

            };
        }

        public TextStyle recentSearches() {
            return styleOf(R.style.search_query_item, 20);
        }

        public TextStyle styleOf(int i) {
            return styleOf(i, 0);
        }

        public TextStyle styleOf(int resId, int j) {
            return new TextStyle(appContext, j, resId) {
            };
        }

        public TextStyle subTitle() {
            return styleOf(R.style.subtitle_text, 18);
        }

        public TextStyle subTitleLight() {
            return styleOf(R.style.subtitle_text_light, 18);
        }

        public TextStyle title() {
            return styleOf(R.style.title_text);
        }

        public TextStyle titleBarSubTitle() {
            return new TextStyle(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    textview.setShadowLayer(1.0F, -1F, -1F, 0xff000000);
                }

                public int gravity() {
                    return 5;
                }

                public int tabletTextSize() {
                    return 18;
                }

                public int textStyle() {
                    return R.style.title_bar_secondary_text;
                }

            };
        }

        public TextStyle titleBarTitle() {
            return new TextStyle(appContext) {

                public void applyTo(TextView textview) {
                    super.applyTo(textview);
                    textview.setShadowLayer(1.0F, -1F, -1F, 0xff000000);
                }

                public int textStyle() {
                    return R.style.title_bar_text;
                }

            };
        }

    }

    public class ThumnailSizes {

        public Size defaultPoster() {
            return scaledPoster(60);
        }

        public Size fixedPoster(int i) {
            int j = ViewUtils.getPixelsOf(i, appContext);
            return new Size((j * 4) / 3, j);
        }

        public Size scaledPoster(int i) {
            if (forTablet)
                i += i / 2;
            return fixedPoster(i);
        }

    }

    public StyleSheet(Context context) {
        appContext = AppActivity.getActivityOf(context);
        forTablet = appContext.useTabletSizing();
        boolean flag;
        if (forTablet)
            flag = false;
        else
            flag = true;
        forPhone = flag;
    }

    public Buttons buttons() {
        return new Buttons();
    }

    public TextStyles textStyles() {
        return new TextStyles();
    }

    public ThumnailSizes thumbnailSizes() {
        return new ThumnailSizes();
    }

    protected final AppActivity appContext;
    public final boolean forPhone;
    public final boolean forTablet;
}
