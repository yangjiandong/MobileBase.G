package com.app.example;

import com.app.example.adapter.LetterlistAdapter;
import com.ek.mobilebapp.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class LetterSideBar extends View {
    private char[] letters;
    private SectionIndexer sectionIndexter = null;
    private ListView list;
    private TextView mDialogText;
    Bitmap mbitmap;
    private int type = 1;
    private int color = 0xff858c94;

    public LetterSideBar(Context context) {
        super(context);
        init();
    }

    public LetterSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        letters = new char[] { '!','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
         'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
          'W', 'X', 'Y', 'Z','#'};
        mbitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.scroll_bar_search_icon);
    }

    public LetterSideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    public void setListView(ListView _list) {
        list = _list;
        HeaderViewListAdapter ha = (HeaderViewListAdapter) _list
                .getAdapter();
        LetterlistAdapter ad = (LetterlistAdapter)ha.getWrappedAdapter();
        sectionIndexter = (SectionIndexer) ad;

    }

    public void setTextView(TextView mDialogText) {
        this.mDialogText = mDialogText;
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int y = (int) event.getY();
        int idy = y / (getMeasuredHeight() / letters.length);
        if (idy >= letters.length) {
            idy = letters.length - 1;
        } else if (idy < 0) {
            idy = 0;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN
                || event.getAction() == MotionEvent.ACTION_MOVE) {
            setBackgroundResource(R.drawable.scrollbar_bg);

            mDialogText.setVisibility(View.VISIBLE);
            if (idy == 0) {
                mDialogText.setText("Search");
                mDialogText.setTextSize(16);
            } else {
                mDialogText.setText(String.valueOf(letters[idy]));
                mDialogText.setTextSize(34);
            }
            if (sectionIndexter == null) {
                sectionIndexter = (SectionIndexer) list.getAdapter();

            }
            int position = sectionIndexter.getPositionForSection(letters[idy]);

            if (position == -1) {
                return true;
            }
            list.setSelection(position);
        } else {
            mDialogText.setVisibility(View.INVISIBLE);

        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            setBackgroundDrawable(new ColorDrawable(0x00000000));
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setTextSize(12);
        paint.setStyle(Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        float widthCenter = getMeasuredWidth() / 2;
        if (letters.length > 0) {
            float height = getMeasuredHeight() / letters.length;
            for (int i = 0; i < letters.length; i++) {
                if (i == 0 && type != 2) {
                    canvas.drawBitmap(mbitmap, widthCenter - 7, (i + 1)
                            * height - height / 2, paint);
                } else
                    canvas.drawText(String.valueOf(letters[i]), widthCenter,
                            (i + 1) * height, paint);
            }
        }
        this.invalidate();
        super.onDraw(canvas);
    }
}
