// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi lnc 

package com.ek.mobileapp.widget.common;

import android.graphics.*;
import android.graphics.drawable.Drawable;

public class LightBackground extends Drawable
{

    public LightBackground()
    {
    }

    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        int i = canvas.getWidth();
        int j = canvas.getHeight();
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setColor(0xffc5ccd4);
        canvas.drawRect(0.0F, 0.0F, i, j, paint);
        paint.setShader(new LinearGradient(0.0F, 0.0F, 0.0F, 75, 0xffacb3bb, 0xffc5ccd4, android.graphics.Shader.TileMode.REPEAT));
        canvas.drawRect(0.0F, 0.0F, i, 75, paint);
    }

    public int getOpacity()
    {
        return -1;
    }

    public void setAlpha(int i)
    {
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }
}
