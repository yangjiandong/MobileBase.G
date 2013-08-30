package com.ek.mobileapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class RoundRectLayout extends RelativeLayout {
    float[] a = new float[8];
    Path path = new Path();
    RectF rect = new RectF();
    private int top = 0;
    private int right = 0;
    private int left = 0;
    private int buttom = 0;

    public RoundRectLayout(Context paramContext) {
        super(paramContext);
        setRoundRectLayout(paramContext);
    }

    public RoundRectLayout(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        setRoundRectLayout(paramContext);
    }

    public void a() {
        invalidate();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        this.top = paramInt1;
        this.right = paramInt2;
        this.left = paramInt3;
        this.buttom = paramInt4;
    }

    public void setRoundRectLayout(Context paramContext) {
        this.top = getPaddingTop();
        this.left = getPaddingLeft();
        this.right = getPaddingRight();
        this.buttom = getPaddingBottom();
        setPadding(0, 0, 0, 0);
    }

    public int b() {
        return this.right;
    }

    public int c() {
        return this.left;
    }

    public int d() {
        return this.buttom;
    }

    protected void dispatchDraw(Canvas paramCanvas) {
        if (!paramCanvas.getClass().getName().equals("android.graphics.Canvas"))
            super.dispatchDraw(paramCanvas);

        paramCanvas.save();
        this.a[0] = this.left;
        this.a[1] = this.left;
        this.a[2] = this.top;
        this.a[3] = this.top;
        this.a[4] = this.right;
        this.a[5] = this.right;
        this.a[6] = this.buttom;
        this.a[7] = this.buttom;
        this.rect.set(0.0F, 0.0F, getWidth(), getHeight());
        this.path.addRoundRect(this.rect, this.a, Path.Direction.CCW);
        paramCanvas.clipPath(this.path);
        super.dispatchDraw(paramCanvas);
        paramCanvas.restore();

    }

    public int e() {
        return this.top;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //a.a(this);
    }
}