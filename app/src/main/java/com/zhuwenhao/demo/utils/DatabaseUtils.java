package com.zhuwenhao.demo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhuwenhao.demo.entity.Bandwagon;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    /**
     * 查询搬瓦工主机列表
     *
     * @param context context
     * @return List
     */
    public static List<Bandwagon> getBandwagonList(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.VERSION);
        List<Bandwagon> bandwagonList = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_BANDWAGON, new String[]{"id", "title", "ve_id", "api_key", "hostname", "node_location", "os", "ip_addresses", "sort"}, null, null, null, null, "sort");
        Bandwagon bandwagon;
        while (cursor.moveToNext()) {
            bandwagon = new Bandwagon();
            bandwagon.setId(cursor.getInt(cursor.getColumnIndex("id")));
            bandwagon.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            bandwagon.setVeId(cursor.getString(cursor.getColumnIndex("ve_id")));
            bandwagon.setApiKey(cursor.getString(cursor.getColumnIndex("api_key")));
            bandwagon.setHostname(cursor.getString(cursor.getColumnIndex("hostname")));
            bandwagon.setNodeLocation(cursor.getString(cursor.getColumnIndex("node_location")));
            bandwagon.setOs(cursor.getString(cursor.getColumnIndex("os")));
            bandwagon.setIpAddresses(cursor.getString(cursor.getColumnIndex("ip_addresses")));
            bandwagon.setSort(cursor.getInt(cursor.getColumnIndex("sort")));
            bandwagonList.add(bandwagon);
        }
        cursor.close();
        db.close();

        return bandwagonList;
    }

    /**
     * 添加搬瓦工主机
     *
     * @param context   context
     * @param bandwagon bandwagon
     */
    public static boolean addBandwagon(Context context, Bandwagon bandwagon) {
        DatabaseHelper helper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", bandwagon.getTitle());
        values.put("ve_id", bandwagon.getVeId());
        values.put("api_key", bandwagon.getApiKey());
        values.put("sort", bandwagon.getSort());
        long id = db.insert(DatabaseHelper.TABLE_BANDWAGON, null, values);

        db.close();
        return id != -1;
    }
}
