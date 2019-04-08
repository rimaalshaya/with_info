package com.example.pro2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class MyDBHandler extends SQLiteOpenHelper {

    // Variables for database info
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "info.db";
    private static final String TABLE_USER = "user";

    public static final String TABLE_NAME = "tblFreinds";
    public static final String COLUMN_RECID = "recID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_Pas = "pas";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_CREDIT = "credit";




    public MyDBHandler (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Log.d("DB","constructor");
    }


    @Override
    public void onCreate(SQLiteDatabase db){

        // To create all tables
        String sqlStmt = " CREATE TABLE " + TABLE_NAME+ " (" +
                COLUMN_RECID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_Pas+ " TEXT NOT NULL, " +
                COLUMN_PHONE+ " TEXT NOT NULL, " +
                COLUMN_EMAIL+ " TEXT NOT NULL, " +
                COLUMN_CREDIT + " TEXT);" ;

        Log.d("DB", "Created");
        db.execSQL(sqlStmt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        Log.d("DB","Tha table has been dropped");
        onCreate(db);
    }







}