package com.example.archek.fitshedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "treningDb";
    public static final String TABLE_TRENINGS = "trenings";

    public static final String KEY_ID = "_ID";

    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_DESC = "KEY_DESC";
    public static final String KEY_WEEKDAY = "KEY_WEEKDAY";
    public static final String KEY_START_TIME = "KEY_START_TIME";
    public static final String KEY_END_TIME = "KEY_END_TIME";
    public static final String KEY_PLACE = "KEY_PLACE";
    public static final String KEY_NAME_COACH = "KEY_NAME_COACH";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_TRENINGS + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text," + KEY_DESC +
                " text," + KEY_WEEKDAY + " text," + KEY_START_TIME + " text," + KEY_END_TIME +
                " text," + KEY_PLACE + " text," + KEY_NAME_COACH + " text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_TRENINGS);

        onCreate(db);

    }
}