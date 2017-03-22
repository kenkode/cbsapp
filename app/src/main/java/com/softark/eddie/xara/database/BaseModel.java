package com.softark.eddie.xara.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.softark.eddie.xara.database.Database;

/**
 * Created by Eddie on 3/21/2017.
 */

public class BaseModel {

    public Context context;
    public SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper dbHelper;
    private Database database;

    public BaseModel(Context context) {
        this.context = context;
        dbHelper = new Database(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void open() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
