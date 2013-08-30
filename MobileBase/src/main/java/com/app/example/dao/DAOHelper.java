package com.app.example.dao;

import static android.provider.BaseColumns._ID;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public abstract class DAOHelper extends SQLiteOpenHelper implements DatabaseConstants {

    static final String HZK_TABLE_NAME = "t_hzk";
    static final String HZK_HZ = "hz";
    static final String[] HZK_ALL_COLUMS = { _ID, HZK_HZ };

    public DAOHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + HZK_TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HZK_HZ + " TEXT NOT NULL" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HZK_TABLE_NAME);
        onCreate(db);
    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    protected String createTable(final String tableName, final String[] columns, final String[] columnsCreate) {
        final StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS ");
        builder.append(tableName);
        builder.append('(');
        for (int i = 0; i < columns.length; i++) {
            builder.append(columns[i]);
            builder.append(' ');
            builder.append(columnsCreate[i]);
            if (i + 1 < columns.length) {
                builder.append(',');
            }
        }
        builder.append(')');
        return builder.toString();
    }

    protected String sqlCreateIndex(final String tableName, final String[] columnNames) {
        final StringBuilder buff = new StringBuilder(128);
        buff.append("CREATE INDEX IF NOT EXISTS idx_");
        buff.append(tableName);
        for (int i = 0; i < columnNames.length; i++) {
            buff.append('_');
            buff.append(columnNames[i]);
        }
        buff.append(" ON ");
        buff.append(tableName);
        buff.append('(');
        for (int i = 0; i < columnNames.length; i++) {
            if (i > 0) {
                buff.append(',');
            }
            buff.append(columnNames[i]);
        }
        buff.append(')');
        return buff.toString();
    }
}
