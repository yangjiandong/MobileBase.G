package com.ek.mobileapp.widget.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.ek.mobileapp.utils.ViewUtils;
import com.ek.mobileapp.widget.common.LightBackground;
import com.ek.mobilebapp.R;

public class DashboardMenuGrid extends FrameLayout {

    private int dividerColor;
    private TableLayout grid;
    private int maxColumns;

    private class GridLines extends Drawable {

        public void draw(Canvas canvas) {
            int left = grid.getLeft();
            int top = grid.getTop();
            int width = grid.getMeasuredWidth();
            int height = grid.getMeasuredHeight();
            int gridRows = grid.getChildCount();//grid 行数
            int tablerowChildCount = 0;//一行的item个数
            int dColor;
            int piex;
            int lpiex;

            LinearGradient lineargradient;
            LinearGradient lineargradient1;

            Paint paint = new Paint();
            paint.setStyle(android.graphics.Paint.Style.STROKE);
            paint.setStrokeWidth(1.0F);
            dColor = dividerColor;
            piex = ViewUtils.getPixelsOf(50, getContext());
            lpiex = piex;

            TableRow tablerow = null;
            if (gridRows > 0) {
                tablerow = (TableRow) grid.getChildAt(0);
                tablerowChildCount = tablerow.getChildCount();

                createTableRow(canvas, left, top, height, tablerowChildCount, dColor, piex, tablerow, paint);
            }else{
                return;
            }

            if (width - piex * 2 < 0)
                lpiex = 0;
            lineargradient = new LinearGradient(left, 0.0F, left + lpiex, 0.0F, 0, dColor,
                    android.graphics.Shader.TileMode.REPEAT);
            lineargradient1 = new LinearGradient((left + width) - lpiex, 0.0F, left + width, 0.0F, dColor, 0,
                    android.graphics.Shader.TileMode.REPEAT);
            int tableRow = 1;

            //开始第二行
            if (gridRows > 1) {

                while (tableRow < gridRows) {
                    tablerow = (TableRow) grid.getChildAt(tableRow);
                    tablerowChildCount = tablerow.getChildCount();
                    createTableRow(canvas, left, top, height, tablerowChildCount, dColor, piex, tablerow, paint);

                    int k2 = top + ((TableRow) grid.getChildAt(tableRow)).getTop();
                    paint.setShader(null);
                    paint.setColor(dividerColor);
                    canvas.drawLine(left + lpiex, k2, (left + width) - lpiex, k2, paint);
                    if (lpiex > 0) {
                        paint.setColor(0xff000000);
                        paint.setShader(lineargradient);
                        canvas.drawLine(left, k2, left + lpiex, k2, paint);
                        paint.setShader(lineargradient1);
                        canvas.drawLine((left + width) - lpiex, k2, left + width, k2, paint);
                    }
                    tableRow++;
                }
            }

        }

        private void createTableRow(Canvas canvas, int left, int top, int height, int tablerowChildCount, int dColor,
                int piex, TableRow tablerow, Paint paint) {
            int lpiex2;
            LinearGradient lineargradient2;
            LinearGradient lineargradient3;
            int i3;
            lpiex2 = piex;
            if (height - piex * 2 < 0)
                lpiex2 = 0;
            lineargradient2 = new LinearGradient(0.0F, top, 0.0F, top + lpiex2, 0, dColor,
                    android.graphics.Shader.TileMode.REPEAT);
            lineargradient3 = new LinearGradient(0.0F, (top + height) - lpiex2, 0.0F, top + height, dColor, 0,
                    android.graphics.Shader.TileMode.REPEAT);
            i3 = 1;

            while (i3 < tablerowChildCount) {
                int j3 = -1 + (left + tablerow.getChildAt(i3).getLeft());
                paint.setShader(null);
                paint.setColor(dividerColor);
                canvas.drawLine(j3, top + lpiex2, j3, (top + height) - lpiex2, paint);
                if (lpiex2 > 0) {
                    paint.setColor(0xff000000);
                    paint.setShader(lineargradient2);
                    canvas.drawLine(j3, top, j3, top + lpiex2, paint);
                    paint.setShader(lineargradient3);
                    canvas.drawLine(j3, (top + height) - lpiex2, j3, top + height, paint);
                }
                i3++;
            }
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorfilter) {
        }

        private GridLines() {
            super();
        }

        GridLines(GridLines gridlines) {
            super();
        }
    }

    public DashboardMenuGrid(Context context) {
        this(context, 3);
    }

    public DashboardMenuGrid(Context context, int maxCms) {
        this(context, maxCms, context.getResources().getColor(R.color.divider));
    }

    public DashboardMenuGrid(Context context, int maxCms, int dColor) {
        super(context);

        grid = new TableLayout(context);
        int k = 0;
        do {
            if (k >= -1 + maxCms * 2) {
                addView(grid, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);//-1, -2);
                maxColumns = maxCms;
                dividerColor = dColor;
                setBackgroundDrawable(new LightBackground());
                setForeground(new GridLines(null));
                return;
            }
            grid.setColumnStretchable(k, true);
            k++;
        } while (true);
    }

    public void addItem(DashboardAction dashboardaction) {
        Context context;
        int childCount = grid.getChildCount();
        context = getContext();

        TableRow tablerow;

        if (childCount != 0) {
            tablerow = (TableRow) grid.getChildAt(childCount - 1);
            if (tablerow.getChildCount() == maxColumns) {
                tablerow = new TableRow(context);
                grid.addView(tablerow);
            }
        } else {
            tablerow = new TableRow(context);
            grid.addView(tablerow);
        }

        tablerow.addView(dashboardaction, getItemLayout());
        return;
    }

    protected android.widget.TableRow.LayoutParams getItemLayout() {
        android.widget.TableRow.LayoutParams layoutparams = new android.widget.TableRow.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);//-2, -2);
        layoutparams.setMargins(1, 1, 1, 1);
        return layoutparams;
    }
}
