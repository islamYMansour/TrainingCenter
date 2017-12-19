package com.example.islam.trainingcenter.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by islam on 11/8/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    //admin table name phone email password
    private static SQLiteHelper sInstance;

    private static final String DATABASE_NAME = "Taining_center";
    private static final int DATABASE_VERSION = 4;

    public static final String ADMIN_TABLE = "admin_table";
    public static final String ADS_TABLE = "ads_table";
    public static final String COURSES_TABLE = "courses_table";

    public static final String PK_ID_COULMN = "PK_ID_COULMN";
    public static final String FORIGN_KEY_COULMN = "FORIGN_KEY_COULMN";

    public static final String ADMN_ADDRESS_COULMN = "ADMN_ADDRESS_COULMN";
    public static final String ADMIN_NAME_COLUMN = "ADMIN_NAME_COLUMN";
    public static final String ADMIN_PHONE_COULMN = "ADMIN_PHONE_COULMN";
    public static final String ADMIN_EMAIL_COULMN = "ADMIN_EMAIL_COULMN";
    public static final String ADMIN_PASSWORD_COULMN = "ADMIN_PASSWORD_COULMN";
    public static final String ADMIN_IMAGE_PATH = "ADMIN_IMAGE_PATH";

    public static final String ADS_NAME_COLUMN = "ADS_NAME_COLUMN";
    public static final String ADS_DATE_COULMN = "ADS_DATE_COULMN";
    public static final String ADS_IMAGE_COULMN = "ADS_IMAGE_COULMN";
    public static final String ADS_DETAIL_COLUMN = "ADS_DETAIL_COLUMN";

    public static final String COURSE_NAME_COLUMN = "COURSE_NAME_COLUMN";
    public static final String COURSE_DATE_COULMN = "COURSE_DATE_COULMN";
    public static final String COURSE_IMAGE_COULMN = "COURSE_IMAGE_COULMN";
    public static final String COURSE_PLACE_COLUMN = "COURSE_PLACE_COLUMN";
    public static final String COURSE_HOURS_COLUMN = "COURSE_HOURS_COLUMN";
    public static final String COURSE_PRICE_COLUMN = "COURSE_PRICE_COLUMN";
    public static final String COURSE_COURSETRAINER_COLUMN = "COURSE_COURSETRAINER_COLUMN";
    public static final String COURSE_DETAIL_COLUMN = "COURSE_DETAIL_COLUMN";


    private SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static synchronized SQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SQLiteHelper(context);
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL
                ("create table " + ADMIN_TABLE + " ( " + PK_ID_COULMN + " INTEGER PRIMARY KEY AUTOINCREMENT ," + ADMIN_NAME_COLUMN + " TEXT ," + ADMIN_PHONE_COULMN + " TEXT ," + ADMIN_EMAIL_COULMN + " TEXT ," + ADMIN_IMAGE_PATH + " TEXT ," + ADMIN_PASSWORD_COULMN + " TEXT );");
        sqLiteDatabase.execSQL
                ("create table " + ADS_TABLE + " ( " + PK_ID_COULMN + " INTEGER PRIMARY KEY AUTOINCREMENT ," + FORIGN_KEY_COULMN + " TEXT ," + ADS_NAME_COLUMN + " TEXT ," + ADS_IMAGE_COULMN + " blob ," + ADS_DATE_COULMN + " TEXT ," + ADS_DETAIL_COLUMN + " TEXT );");
        sqLiteDatabase.execSQL
                ("create table " + COURSES_TABLE + " ( " + PK_ID_COULMN + " INTEGER PRIMARY KEY AUTOINCREMENT ," + FORIGN_KEY_COULMN + " TEXT ," + COURSE_NAME_COLUMN + " TEXT ," + COURSE_DATE_COULMN + " TEXT ," + COURSE_IMAGE_COULMN + " TEXT ," + COURSE_PRICE_COLUMN + " TEXT ," + COURSE_PLACE_COLUMN + " TEXT ," + COURSE_HOURS_COLUMN + " TEXT ,"
                        + COURSE_COURSETRAINER_COLUMN + " TEXT ," + COURSE_DETAIL_COLUMN + " TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ADS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE);

        onCreate(sqLiteDatabase);
    }
}
