package com.ek.mobileapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ek.mobilebapp.R;

//import tw.com.bicom.VGHTPE.R.styleable;

public class CustomImageButton extends FrameLayout {
    private FrameLayout baseLayout;
    private ImageView imageView;
    private TextView textView;

    public CustomImageButton(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        View localView = LayoutInflater.from(paramContext).inflate(R.layout.custom_image_btn_layout, this, true);

        if (!isInEditMode()) {
            setClickable(true);
            setFocusable(true);
            this.baseLayout = ((FrameLayout) localView.findViewById(R.id.custom_image_btn_layout));
            this.textView = ((TextView) localView.findViewById(R.id.custom_image_btn_title_textview));
            this.imageView = ((ImageView) localView.findViewById(R.id.custom_image_btn_imageview));

//            TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet,
//                    R.styleable.CustomImageBtn);
//            int color = localTypedArray.getColor(R.attr.textColor, -1);
//            Drawable backDrawable = localTypedArray.getDrawable(R.attr.backGround);
//            Drawable imageDrawable = localTypedArray.getDrawable(R.attr.imageDrawable);
//            String str = (String) localTypedArray.getText(3);
//
//            if (backDrawable == null)
//                this.baseLayout.setBackgroundResource(R.drawable.menu_btn_background);
//            this.baseLayout.setBackgroundDrawable(backDrawable);
//
//            if (str == null)
//                this.textView.setText("");
//            this.textView.setText(str);
//
//            if (color == -1)
//                this.textView.setTextColor(getResources().getColor(2131230723));
//            this.textView.setTextColor(getResources().getColor(color));
//
//            if (imageDrawable == null)
//                this.imageView.setImageResource(R.drawable.ico1a);
//            this.imageView.setImageDrawable(imageDrawable);
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
        this.textView.setOnClickListener(paramOnClickListener);
        this.imageView.setOnClickListener(paramOnClickListener);
    }

    public void setText(String s) {
        this.textView.setText(s);
    }

    public void setSrc(Drawable localDrawable1) {
        this.imageView.setImageDrawable(localDrawable1);
    }

    public void setSrc(int rid) {
        this.imageView.setImageResource(rid);
    }

    @Override
    public void setId(int id) {
        super.setId(id);

        //配合触发onClick
        this.textView.setId(id);
        this.imageView.setId(id);
    }
}