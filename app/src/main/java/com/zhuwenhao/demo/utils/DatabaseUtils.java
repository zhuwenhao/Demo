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
        Cursor cursor = db.query(DatabaseHelper.TABLE_BANDWAGON, new String[]{"id", "title", "ve_id", "api_key", "node_location", "os", "ip_addresses", "sort"}, null, null, null, null, "sort");
        Bandwagon bandwagon;
        while (cursor.moveToNext()) {
            bandwagon = new Bandwagon();
            bandwagon.setId(cursor.getInt(cursor.getColumnIndex("id")));
            bandwagon.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            bandwagon.setVeId(cursor.getString(cursor.getColumnIndex("ve_id")));
            bandwagon.setApiKey(cursor.getString(cursor.getColumnIndex("api_key")));
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
     * @return boolean
     */
    public static boolean addBandwagon(Context context, Bandwagon bandwagon) {
        DatabaseHelper helper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", bandwagon.getTitle());
        values.put("ve_id", bandwagon.getVeId());
        values.put("api_key", bandwagon.getApiKey());
        values.put("sort", getLastSort(context));
        long id = db.insert(DatabaseHelper.TABLE_BANDWAGON, null, values);

        db.close();
        return id != -1;
    }

    /**
     * 获取最后一个Sort
     *
     * @param context context
     * @return int
     */
    private static int getLastSort(Context context) {
        int sort = 0;
        DatabaseHelper helper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_BANDWAGON, new String[]{"sort"}, null, null, null, null, "sort desc", "1");
        if (cursor.moveToNext()) {
            sort = cursor.getInt(cursor.getColumnIndex("sort")) + 1;
        }
        cursor.close();
        db.close();
        return sort;
    }

    /**
     * 更新搬瓦工主机
     *
     * @param context   context
     * @param bandwagon bandwagon
     * @return boolean
     */
    public static boolean updateBandwagon(Context context, Bandwagon bandwagon) {
        DatabaseHelper helper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", bandwagon.getTitle());
        values.put("ve_id", bandwagon.getVeId());
        values.put("api_key", bandwagon.getApiKey());
        values.put("node_location", "");
        values.put("os", "");
        values.put("ip_addresses", "");
        int number = db.update(DatabaseHelper.TABLE_BANDWAGON, values, "id=?", new String[]{String.valueOf(bandwagon.getId())});

        db.close();
        return number > 0;
    }

    /**
     * 删除搬瓦工主机
     *
     * @param context context
     * @param id      id
     * @return boolean
     */
    public static boolean deleteBandwagon(Context context, int id) {
        DatabaseHelper helper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();
        int number = db.delete(DatabaseHelper.TABLE_BANDWAGON, "id=?", new String[]{String.valueOf(id)});

        db.close();
        return number != 0;
    }

    /**
     * 更新排序
     *
     * @param context context
     * @param id      id
     * @param sort    sort
     * @param id1     id1
     * @param sort1   sort1
     */
    public static void updateSort(Context context, int id, int sort, int id1, int sort1) {
        DatabaseHelper helper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sort", sort);
        db.update(DatabaseHelper.TABLE_BANDWAGON, values, "id=?", new String[]{String.valueOf(id)});

        values.put("sort", sort1);
        db.update(DatabaseHelper.TABLE_BANDWAGON, values, "id=?", new String[]{String.valueOf(id1)});

        db.close();
    }
}
