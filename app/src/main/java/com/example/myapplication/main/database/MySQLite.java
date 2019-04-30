package com.example.myapplication.main.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 14:41
 * Describe: ${as}
 */
public class MySQLite extends SQLiteOpenHelper {

    public MySQLite(Context context) {
        super(context, "text.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tangPoetry(title text,content text,authors text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
