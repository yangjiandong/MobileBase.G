package com.ek.mobileapp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

import com.ek.mobileapp.AppConfig;
import com.ek.mobileapp.model.Hz;
import com.ek.mobileapp.utils.StringUtils;
import com.ek.mobileapp.utils.TimeUtils;
import com.ek.mobileapp.utils.database.Column;
import com.ek.mobileapp.utils.database.SQLiteTable;

public class HzDataHelper extends BaseDataHelper {
    public HzDataHelper(Context context) {
        super(context);
    }

    @Override
    protected Uri getContentUri() {
        return DataProvider.HZS_CONTENT_URI;
    }

    private ContentValues getContentValues(Hz shz) {
        if (StringUtils.isEmpty(shz.getCreated_at())) {
            shz.setCreated_at(TimeUtils.nowDateString(AppConfig.TIMEFORMAT));
        }
        ContentValues values = new ContentValues();
        values.put(HzDBInfo._ID, shz.getId());
        values.put(HzDBInfo.HZ, shz.getHz());
        values.put(HzDBInfo.JSON, shz.toJson());
        return values;
    }

    public Hz query(String hzString) {
        Hz hz = null;
        Cursor cursor = query(null, HzDBInfo.HZ + "=?", new String[] { String.valueOf(hzString) }, null);
        if (cursor.moveToFirst()) {
            hz = Hz.fromCursor(cursor);
        }
        cursor.close();
        return hz;
    }

    public void bulkInsert(List<Hz> hzs) {
        ArrayList<ContentValues> contentValues = new ArrayList<ContentValues>();
        for (Hz entity : hzs) {
            ContentValues values = getContentValues(entity);
            contentValues.add(values);
        }
        ContentValues[] valueArray = new ContentValues[contentValues.size()];
        bulkInsert(contentValues.toArray(valueArray));
    }

    public int deleteAll() {
        synchronized (DataProvider.DBLock) {
            DataProvider.DBHelper mDBHelper = DataProvider.getDBHelper();
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            int row = db.delete(HzDBInfo.TABLE_NAME, null, null);
            return row;
        }
    }

    public CursorLoader getCursorLoader() {
        //int page = 2;
        //String limit = String.valueOf(6 * (page - 1) + 0) + " , " + String.valueOf(6);

        return new CursorLoader(getContext(), getContentUri(), null, null,//HzDBInfo.HZ + "=?",
                null,
                //new String[] {
                //    String.valueOf(this.hz)
                //},
                HzDBInfo._ID + " ASC");
                //HzDBInfo._ID + " ASC limit " + limit);
    }

    public static final class HzDBInfo implements BaseColumns {
        private HzDBInfo() {
        }

        public static final String TABLE_NAME = "hzs";
        public static final String HZ = "hz";
        public static final String JSON = "json";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(_ID, Column.Constraint.PRIMARY_KEY, Column.DataType.INTEGER)
                .addColumn(HZ, Column.DataType.TEXT)
                .addColumn(JSON, Column.DataType.TEXT);
    }
}
