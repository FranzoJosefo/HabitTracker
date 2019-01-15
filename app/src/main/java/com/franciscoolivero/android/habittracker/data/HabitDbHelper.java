package com.franciscoolivero.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.franciscoolivero.android.habittracker.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper{

    private final String LOG_TAG = this.getClass().getName();

    private static final String DATABASE_NAME = "trackerhabit.db";
    private static final int DATABASE_VERSION = 1;


//    Schema SQL:
//    CREATE TABLE habit (
//    _ID INTEGER PRIMARY KEY AUTOINCREMENT,
//    name TEXT NOT NULL,
//    isCompleted INTEGER NOT NULL DEFAULT 0,
//    hours INTEGER DEFAULT -1,
//    frequency INTEGER NOT NULL);

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + "("
            + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
            + HabitEntry.COLUMN_HABIT_COMPLETED_STATUS+ " INTEGER NOT NULL DEFAULT 0, "
            + HabitEntry.COLUMN_HABIT_HOURS + " INTEGER DEFAULT -1, "
            + HabitEntry.COLUMN_HABIT_FREQUENCY + " INTEGER NOT NULL);";

    private static final String SQL_DELETE_TABLE = "DELETE FROM " + HabitEntry.TABLE_NAME + ";";
    //Why not use DROP TABLE pets?; Because DROP deletes the table, DELETE eliminate FROM the table its contents.


    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
        Log.v(LOG_TAG, SQL_CREATE_TABLE + "\nTable Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

}
