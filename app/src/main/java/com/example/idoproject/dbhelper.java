package com.example.idoproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static int CID = 0; //use for the current user!
    public static String CPass = ""; //use for the current user!

    public static final String TABLE_NAME = "all_users";
    public static final String UID = "_id";
    public static final String NICKNAME = "Nickname";
    public static final String PASS = "Pass";
    public static final String EMAIL = "Email";
    public static final String PHONE = "Phone";

    public static final String TABLE_NAME2 = "all_grades";
    public static final String[] SUBJECTS =
            {"מדינות", "בעלי חיים", "ערים בישראל", "פירות"};

    String SQL_Create="";
    String SQL_Delete="";


    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        SQL_Create="CREATE TABLE "+TABLE_NAME+" (";
        SQL_Create+="_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        SQL_Create+=NICKNAME+" TEXT, ";
        SQL_Create+=PASS+" TEXT, ";
        SQL_Create+=EMAIL+" TEXT, ";
        SQL_Create+=PHONE+" TEXT);";
        sqLiteDatabase.execSQL(SQL_Create);

        SQL_Create="CREATE TABLE "+TABLE_NAME2+" (";
        SQL_Create+="_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        SQL_Create+=NICKNAME+" TEXT, ";
        SQL_Create+=PASS+" TEXT, ";
        SQL_Create+=SUBJECTS[0]+" TEXT, ";
        SQL_Create+=SUBJECTS[1]+" TEXT, ";
        SQL_Create+=SUBJECTS[2]+" TEXT, ";
        SQL_Create+=SUBJECTS[3]+" TEXT);";
        sqLiteDatabase.execSQL(SQL_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        SQL_Delete = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_Delete);
        SQL_Delete = "DROP TABLE IF EXISTS "+ TABLE_NAME2;
        sqLiteDatabase.execSQL(SQL_Delete);
        onCreate(sqLiteDatabase);
    }
}
