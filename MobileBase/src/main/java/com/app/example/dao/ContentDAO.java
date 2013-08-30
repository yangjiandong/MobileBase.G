package com.app.example.dao;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.example.model.Content;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.Utils;

public class ContentDAO extends DAOHelper {
    protected static final String TABLE_NAME = "t_content";

    static final String[] OWN_COLUMNS = { _ID, "letter", "name" };
    static final String[] OWN_COLUMNS_TYPE = { " INTEGER PRIMARY KEY AUTOINCREMENT", "varchar(20)", "varchar(20)" };
    static final String[] COLUMNS = Utils.arrayMerge(OWN_COLUMNS);
    static final String[] COLUMNS_TYPE = Utils.arrayMerge(OWN_COLUMNS_TYPE);
    static String DROP_TABLE_DOWNDICT_LOG;

    //删除早期版本
    static {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("drop table if exists " + TABLE_NAME);
        DROP_TABLE_DOWNDICT_LOG = buffer.toString();
    }

    //版本更新历史
    final private static Map<Integer, String[]> UPGRADE_SQL;
    //升级脚本
    static {
        UPGRADE_SQL = new HashMap<Integer, String[]>();
        //UPGRADE_SQL.put(1, new String[] { DROP_TABLE_DOWNDICT_LOG });
    }

    public ContentDAO(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable(TABLE_NAME, COLUMNS, COLUMNS_TYPE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        for (int i = oldVersion + 1; i <= newVersion; i++) {
            Logger.d("db_oldVersion:" + i + "");

            final String[] sqls = UPGRADE_SQL.get(i);
            if (sqls != null) {
                for (final String sql : sqls) {
                    db.execSQL(sql);
                }
            }
        }

        //调整字段
        //checkTableColumns(database, Item.TABLE_NAME, Item.COLUMNS, Item.COLUMNS_TYPE);

        Logger.d("db_onUpgrade:createTables");
        onCreate(db);
    }

    public Content save(Content team) {
        SQLiteDatabase db = getWritableDatabase();
        if (team.getId() != null) {
            return updateExistingContent(db, team);
        } else {
            return createNewContent(db, team);
        }
    }

    public int count() {
        Cursor cursor = null;
        int c = 0;
        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
            cursor.moveToNext();
            c = cursor.getInt(0);
        } finally {
            closeCursor(cursor);
        }
        return c;
    }

    public Content findByName(String name) {
        Cursor cursor = null;
        Content team = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(TABLE_NAME, COLUMNS, "name = ?", new String[] { name }, null, null, null);
            if (cursor.getCount() == 1) {
                if (cursor.moveToFirst()) {
                    team = new Content(cursor.getString(1), cursor.getString(2));
                }
            }
        } finally {
            closeCursor(cursor);
        }
        return team;
    }

    public Content findById(Long id) {
        Cursor cursor = null;
        Content team = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(TABLE_NAME, COLUMNS, _ID + " = ?", new String[] { id.toString() }, null, null, null);
            if (cursor.getCount() == 1) {
                if (cursor.moveToFirst()) {
                    team = new Content(cursor.getString(1), cursor.getString(2));
                }
            }
        } finally {
            closeCursor(cursor);
        }
        return team;
    }

    public List<Content> findAllContent() {
        List<Content> teamNames = new ArrayList<Content>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Content c = new Content(cursor.getString(1), cursor.getString(2));
                c.setId(cursor.getLong(0));
                teamNames.add(c);
            }
        } finally {
            closeCursor(cursor);
        }

        Logger.d("Found " + teamNames.size() + " teams");
        return teamNames;
    }

    public List<Content> findAllContentByLetter(String letter) {
        List<Content> teamNames = new ArrayList<Content>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(TABLE_NAME, COLUMNS, "letter = ?", new String[] { letter }, null, null, null);
            while (cursor.moveToNext()) {
                Content c = new Content(cursor.getString(1), cursor.getString(2));
                c.setId(cursor.getLong(0));
                teamNames.add(c);
            }
        } finally {
            closeCursor(cursor);
        }

        Logger.d("Found " + teamNames.size() + " teams");
        return teamNames;
    }

    public void deleteAll() {
        Logger.d("Deleting all teams");
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    private boolean attemptingToCreateDuplicateContent(Content team) {
        return team.getId() == null && findByName(team.getName()) != null;
    }

    private Content createNewContent(SQLiteDatabase db, Content team) {
        if (team.getName() == null || team.getName().trim().length() == 0) {
            String msg = "Attempting to create a team with an empty name";
            Logger.w(msg);

            throw new InvalidException(msg);
        }

        if (attemptingToCreateDuplicateContent(team)) {
            String msg = "Attempting to create duplicate team with the name " + team.getName();
            Logger.w(msg);
            throw new DuplicateException(msg);
        }

        Logger.d("Creating new team with a name of '" + team.getName() + "'");
        ContentValues values = new ContentValues();
        values.put("name", team.getName());
        values.put("letter", team.getLetter());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        return new Content(id, team.getLetter(), team.getName());
    }

    private Content updateExistingContent(SQLiteDatabase db, Content team) {
        Logger.d("Updating team with the name of '" + team.getName() + "'");
        ContentValues values = new ContentValues();
        values.put("name", team.getName());
        values.put("letter", team.getLetter());
        long id = db.update(TABLE_NAME, values, _ID + " = ?", new String[] { team.getId().toString() });
        return new Content(id, team.getLetter(), team.getName());
    }
}
