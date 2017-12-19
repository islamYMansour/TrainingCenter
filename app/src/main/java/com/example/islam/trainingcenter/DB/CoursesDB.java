package com.example.islam.trainingcenter.DB;

import android.content.ContentValues;
import android.content.Context;

import com.example.islam.trainingcenter.DB.Tables.TCenter;
import com.example.islam.trainingcenter.DB.Tables.TCourse;

/**
 * Created by islam on 12/7/2017.
 */

public class CoursesDB extends DB{

    public CoursesDB(Context context) {
        super(context);

    }

    @Override
    protected long insert(TCenter center) {
        ContentValues values = new ContentValues();
        TCourse course =(TCourse)center;
        values.put(sqlHelper.FORIGN_KEY_COULMN, course.getForirgn_key());
        values.put(sqlHelper.COURSE_NAME_COLUMN, course.getName());
        values.put(sqlHelper.COURSE_DATE_COULMN, course.getDate());
        values.put(sqlHelper.COURSE_IMAGE_COULMN, course.getImagePath());
        values.put(sqlHelper.COURSE_DETAIL_COLUMN, course.getDetails());
        values.put(sqlHelper.COURSE_PRICE_COLUMN, course.getPrice());
        values.put(sqlHelper.COURSE_PLACE_COLUMN, course.getPlace());
        values.put(sqlHelper.COURSE_COURSETRAINER_COLUMN, course.getCourseTrainer());
        values.put(sqlHelper.COURSE_HOURS_COLUMN, course.getHoursCount());
        return db.insert(sqlHelper.COURSES_TABLE, null, values);
    }

    @Override
    protected int update(TCenter center, String id, String table) {
        ContentValues values = new ContentValues();
        TCourse course =(TCourse)center;
        values.put(sqlHelper.COURSE_NAME_COLUMN, course.getName());
        values.put(sqlHelper.COURSE_DATE_COULMN, course.getDate());
        values.put(sqlHelper.COURSE_IMAGE_COULMN, course.getImagePath());
        values.put(sqlHelper.COURSE_DETAIL_COLUMN, course.getDetails());
        values.put(sqlHelper.COURSE_PRICE_COLUMN, course.getPrice());
        values.put(sqlHelper.COURSE_PLACE_COLUMN, course.getPlace());
        values.put(sqlHelper.COURSE_COURSETRAINER_COLUMN, course.getCourseTrainer());
        values.put(sqlHelper.COURSE_HOURS_COLUMN, course.getHoursCount());
        int rows = db.update(table, values, SQLiteHelper.PK_ID_COULMN + "=?", new String[]{id});
        return rows;
    }

}
