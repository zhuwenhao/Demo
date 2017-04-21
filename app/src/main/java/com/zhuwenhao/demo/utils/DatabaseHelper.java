package com.zhuwenhao.demo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {

    static final int VERSION = 1;

    static final String DATABASE_NAME = "app";

    static final String TABLE_BANDWAGON = "t_bandwagon";

    DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_BANDWAGON + "(id integer primary key autoincrement, title varchar(100), ve_id varchar(100), api_key varchar(100), hostname varchar(100), node_location varchar(100), os varchar(100), ip_addresses varchar(100), sort integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
