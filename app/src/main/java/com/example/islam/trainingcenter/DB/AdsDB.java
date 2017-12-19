package com.example.islam.trainingcenter.DB;

import android.content.ContentValues;
import android.content.Context;

import com.example.islam.trainingcenter.DB.Tables.TAdmin;
import com.example.islam.trainingcenter.DB.Tables.TAds;
import com.example.islam.trainingcenter.DB.Tables.TCenter;


/**
 * Created by islam on 12/7/2017.
 */

public class AdsDB extends DB {

    public AdsDB(Context context) {
        super(context);

    }

    @Override
    protected long insert(TCenter center) {
        TAds admin = (TAds) center;
        ContentValues values = new ContentValues();
        values.put(sqlHelper.ADS_NAME_COLUMN, admin.getTitle());
//        values.put(sqlHelper.ADMIN_PHONE_COULMN, admin.getPhone());
//        values.put(sqlHelper.ADMIN_EMAIL_COULMN, admin.getEmail());
//        values.put(sqlHelper.ADMIN_PASSWORD_COULMN, admin.getPassword());
        values.put(sqlHelper.ADS_IMAGE_COULMN, admin.getImagePath());
        return db.insert(sqlHelper.ADS_TABLE, null, values);
    }

    @Override
    protected int update(TCenter center, String id, String table) {
        return 0;
    }


}
