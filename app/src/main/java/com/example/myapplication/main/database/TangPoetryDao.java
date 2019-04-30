package com.example.myapplication.main.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 14:43
 * Describe: ${as}
 */
public class TangPoetryDao {

    private Context context;
    private MySQLite mySQLite;
    private static SQLiteDatabase database;

    public TangPoetryDao(Context context) {
        this.context = context;
        mySQLite = new MySQLite(context);
        database = mySQLite.getWritableDatabase();
    }

    public static void insert(ContentValues values){
        database.insert("tangPoetry", null, values);
    }

    public static Cursor query(){
        Cursor cursor = database.query("tangPoetry", null, null, null, null, null, null);
        return cursor;
    }

}
