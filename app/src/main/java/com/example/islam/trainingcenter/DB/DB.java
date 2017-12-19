package com.example.islam.trainingcenter.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.islam.trainingcenter.DB.Tables.TCenter;


/**
 * Created by islam on 12/7/2017.
 */

public abstract class DB {

    protected SQLiteDatabase db;
    protected SQLiteHelper sqlHelper;
    protected Context context;

    public DB(Context context) {
        this.context = context;
        sqlHelper = SQLiteHelper.getInstance(context);
    }

    protected void open() {
        db = sqlHelper.getWritableDatabase();
    }

    protected void close() {
        db.close();
    }

    protected abstract long insert(TCenter center);

    protected Cursor getAll(String tableName) {
        return db.rawQuery("select * from " + tableName, null);
    }

    protected Cursor getByColumn(String coulmnName, String tableName, String fieldValue) {
        return db.rawQuery("select * from " + tableName + " where " + coulmnName + " =?", new String[]{fieldValue});

    }

    protected abstract int update(TCenter center, String id,String table);

    protected int delete(String id, String tableName) {
        return db.delete(tableName, SQLiteHelper.PK_ID_COULMN + "=?", new String[]{id});
    }


}
