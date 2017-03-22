package com.softark.eddie.xara.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eddie on 3/21/2017.
 */

public class Database extends SQLiteOpenHelper {

    public static final String DB_FILE = "xara_loans.db";
    public static final int DB_VERSION = 1;

    public Database(Context context) {
        super(context, DB_FILE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserTable.DROP_QUERY);
        onCreate(db);
    }

}
