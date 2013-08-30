package com.app.example.dao;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.List;

import com.ek.mobileapp.model.Hz;
import com.ek.mobileapp.utils.Logger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HzDAO extends DAOHelper {

    public HzDAO(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Hz save(Hz team) {
        SQLiteDatabase db = getWritableDatabase();
        if (team.getId() != null) {
            return updateExistingHz(db, team);
        } else {
            return createNewHz(db, team);
        }
    }

    public Hz findById(Long id) {
        Cursor cursor = null;
        Hz team = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(HZK_TABLE_NAME, HZK_ALL_COLUMS, _ID + " = ?", new String[]{id.toString()}, null, null, null);
            if (cursor.getCount() == 1) {
                if (cursor.moveToFirst()) {
                    String name = cursor.getString(1);
                    team = new Hz(id, name);
                }
            }
        } finally {
            closeCursor(cursor);
        }

        return team;
    }

    public Hz findByName(String name) {
        Cursor cursor = null;
        Hz team = null;
        name = name.trim();

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(HZK_TABLE_NAME, HZK_ALL_COLUMS, HZK_HZ + " = ?", new String[]{name}, null, null, null);
            if (cursor.getCount() == 1) {
                if (cursor.moveToFirst()) {
                    long id = cursor.getLong(0);
                    name = cursor.getString(1);
                    team = new Hz(id, name);
                }
            }
        } finally {
            closeCursor(cursor);
        }

        Logger.d((team == null ? "Unsuccessfully" : "Successfully") + " found team with a name of '" + name + "'");
        return team;
    }

    public List<String> findAllHzNames() {
        List<String> teamNames = new ArrayList<String>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(HZK_TABLE_NAME, new String[]{HZK_HZ}, null, null, null, null, HZK_HZ);
            while (cursor.moveToNext()) {
                teamNames.add(cursor.getString(0));
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
        db.delete(HZK_TABLE_NAME, null, null);
    }

    public void delete(Hz team) {
        Logger.d("Deleting team with the name of '" + team.getHz() + "'");
        if (team.getId() != null) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(HZK_TABLE_NAME, _ID + " = ?", new String[]{team.getId().toString()});
        }
    }

    private boolean attemptingToCreateDuplicateHz(Hz team) {
        return team.getId() == null && findByName(team.getHz()) != null;
    }

    private Hz createNewHz(SQLiteDatabase db, Hz team) {
        if (team.getHz() == null || team.getHz().trim().length() == 0) {
            String msg = "Attempting to create a team with an empty name";
            Logger.w(msg);
            //throw new InvalidHzNameException(msg);
        }

        if (attemptingToCreateDuplicateHz(team)) {
            String msg = "Attempting to create duplicate team with the name " + team.getHz();
            Logger.w(msg);
            throw new DuplicateException(msg);
        }

        Logger.d("Creating new team with a name of '" + team.getHz() + "'");
        ContentValues values = new ContentValues();
        values.put(HZK_HZ, team.getHz());
        long id = db.insertOrThrow(HZK_TABLE_NAME, null, values);
        return new Hz(id, team.getHz());
    }

    private Hz updateExistingHz(SQLiteDatabase db, Hz team) {
        Logger.d("Updating team with the name of '" + team.getHz() + "'");
        ContentValues values = new ContentValues();
        values.put(HZK_HZ, team.getHz());
        long id = db.update(HZK_TABLE_NAME, values, _ID + " = ?", new String[]{team.getId().toString()});
        return new Hz(id, team.getHz());
    }
}
