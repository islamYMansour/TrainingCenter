package com.example.islam.trainingcenter.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.islam.trainingcenter.DB.Tables.TAdmin;
import com.example.islam.trainingcenter.DB.Tables.TCenter;


/**
 * Created by islam on 12/7/2017.
 */

public class AdminDB extends DB {

    public AdminDB(Context context) {
        super(context);

    }

    //insert new admin to the system
    //you can add all data fields
    //return long number which is the id number
    @Override
    protected long insert(TCenter center) {
        TAdmin admin = (TAdmin) center;
        ContentValues values = new ContentValues();
        values.put(sqlHelper.ADMIN_NAME_COLUMN, admin.getName());
        values.put(sqlHelper.ADMIN_PHONE_COULMN, admin.getPhone());
        values.put(sqlHelper.ADMIN_EMAIL_COULMN, admin.getEmail());
        values.put(sqlHelper.ADMIN_PASSWORD_COULMN, admin.getPassword());
        values.put(SQLiteHelper.ADMIN_IMAGE_PATH, admin.getImagePath());
        return db.insert(sqlHelper.ADMIN_TABLE, null, values);
    }

//boolean isExist(String tableName, String tableCoulmn, String tableField)
    //you can use to find is the user register before
    //return true if he register

    public boolean isExist(String tableName, String tableCoulmn, String tableField) {
        open();
        Cursor cursor = db.rawQuery("select * from " + tableName + " where " + tableCoulmn + "=?", new String[]{tableField});
        if (cursor.getCount() <= 0) {
            close();
            return false;
        } else {
            close();
            return true;
        }
    }

    @Override
    protected int update(TCenter center, String id, String table) {
        return 0;
    }
}
