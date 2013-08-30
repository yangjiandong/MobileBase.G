package com.ek.mobileapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 *
 * android circle imageView
 * @author Block Cheng
 *
 */
//http://my.oschina.net/chengliqun/blog/149171
// 说明：该类对图片的裁剪，导致了轻微的毛边出来，采用网络上的方法处理了，效果不佳，详见代码中的filter。
//但如果服务器的图片是纯色背景，这个问题就不是问题了
public class CircleImageView extends ImageView {

    Path path;
    public PaintFlagsDrawFilter mPaintFlagsDrawFilter;// 毛边过滤
    Paint paint;

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public CircleImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public void init() {
        mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);

    }

    @Override
    protected void onDraw(Canvas cns) {
        // TODO Auto-generated method stub
        float h = getMeasuredHeight() - 3.0f;
        float w = getMeasuredWidth() - 3.0f;
        if (path == null) {
            path = new Path();
            path.addCircle(w / 2.0f, h / 2.0f, (float) Math.min(w / 2.0f, (h / 2.0)), Path.Direction.CCW);
            path.close();
        }
        cns.drawCircle(w / 2.0f, h / 2.0f, Math.min(w / 2.0f, h / 2.0f) + 1.5f, paint);
        int saveCount = cns.getSaveCount();
        cns.save();
        cns.setDrawFilter(mPaintFlagsDrawFilter);
        cns.clipPath(path, Region.Op.REPLACE);
        cns.setDrawFilter(mPaintFlagsDrawFilter);
        cns.drawColor(Color.WHITE);
        super.onDraw(cns);
        cns.restoreToCount(saveCount);
    }

}