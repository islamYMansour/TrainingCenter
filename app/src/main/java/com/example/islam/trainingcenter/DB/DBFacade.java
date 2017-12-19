package com.example.islam.trainingcenter.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.islam.trainingcenter.DB.Tables.TCenter;


/**
 * Created by islam on 12/7/2017.
 */

public class DBFacade {

    DB courseDb;
    DB adsDb;
    DB adminDb;

    public DBFacade(Context context) {
        courseDb = new CoursesDB(context);
        adsDb = new AdsDB(context);
        adminDb = new AdminDB(context);

    }
    public void open(){
        adminDb.open();
        courseDb.open();
        adsDb.open();
    }

    public void close(){
        adminDb.close();
        courseDb.close();
        adsDb.close();
    }
    public long insertCourse(TCenter center) {
        return courseDb.insert(center);
    }

    public long insertAds(TCenter center) {
        return adsDb.insert(center);
    }

    public long inserAdmin(TCenter center) {
        return adminDb.insert(center);
    }

    public Cursor getCourseByColumn(String coulmnName, String fieldValue) {
        return courseDb.getByColumn(coulmnName, SQLiteHelper.COURSES_TABLE, fieldValue);
    }

    public Cursor getAllCourses() {
        return courseDb.getAll(SQLiteHelper.COURSES_TABLE);
    }

    public int updateCourse(TCenter values, String id) {
        return courseDb.update(values, id,SQLiteHelper.COURSES_TABLE);
    }

    public int deleteCourse(String id) {
        return courseDb.delete(id, SQLiteHelper.COURSES_TABLE);
    }


    public Cursor getAminByCoulmn(String coulmnName, String tableName, String fieldValue) {
        return adminDb.getByColumn(coulmnName, tableName, fieldValue);
    }

    public Cursor getAllAdmins(String tableName) {
        return adminDb.getAll(tableName);
    }

    public int updateAdminData(TCenter values, String id, String table) {
        return adminDb.update(values, id, table);
    }

    public int deleteAdmin(String id, String tableName) {
        return adminDb.delete(id, tableName);
    }

    public Cursor getAdsByCoulmn(String coulmnName, String tableName, String fieldValue) {
        return adsDb.getByColumn(coulmnName, tableName, fieldValue);
    }

    public Cursor getAllAds() {
        return adsDb.getAll(SQLiteHelper.ADS_TABLE);
    }

    public int updateAds(TCenter values, String id, String table) {
        return adsDb.update(values, id, table);
    }

    public int deleteAds(String id, String tableName) {
        return adsDb.delete(id, tableName);
    }

    public boolean isExist(String tableName, String tableCoulmn, String tableField) {
        return ((AdminDB)adminDb).isExist(tableName,tableCoulmn,tableField);
    }
//    public boolean isAdminExist (String tableName, String tableCoulmn, String tableField){
//        return adminDb.isExist(String tableName, String tableCoulmn, String tableField);
//    }



}
