package com.ek.mobileapp.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ek.mobilebapp.R;

public class HomeImageButton extends FrameLayout {
    private FrameLayout baseLayout;
    private ImageView imageView;
    private TextView textView;
    private View localView;

    public HomeImageButton(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        localView = LayoutInflater.from(paramContext).inflate(R.layout.home_grid_btn_layout, this, true);

        if (!isInEditMode()) {
            setClickable(true);
            setFocusable(true);
            this.baseLayout = ((FrameLayout) localView.findViewById(R.id.home_grid_btn_layout));
            this.textView = ((TextView) localView.findViewById(R.id.home_grid_btn_title_textview));
            this.imageView = ((ImageView) localView.findViewById(R.id.home_grid_btn_imageview));
            //baseLayout.setPadding(5, 5, 0, 0);

        }
    }

    public FrameLayout getBaseLayout() {
        return this.baseLayout;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public void setOnClickListener(View.OnClickListener paramOnClickListener) {
        this.baseLayout.setOnClickListener(paramOnClickListener);
    }

    public void setText(String s) {
        this.textView.setText(s);
    }

    public void setSrc(Drawable localDrawable1) {
        this.imageView.setImageDrawable(localDrawable1);
    }

    public void setSrc(int rid) {
        this.baseLayout.setBackgroundResource(rid);
    }

    public void setSize(int width, int height) {
        this.baseLayout.setLayoutParams(new LayoutParams(width, height));
    }

    @Override
    public void setId(int id) {
        super.setId(id);

        //配合触发onClick
        this.baseLayout.setId(id);

    }
}