package com.franciscoolivero.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.franciscoolivero.android.habittracker.data.HabitContract.HabitEntry;
import com.franciscoolivero.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For testing purposes:
        insertHabit();
        insertHabit();
        insertHabit();
        Cursor cursor = createHabitCursor();
        readHabit(cursor);
    }

    public void insertHabit() {
        //Gets the data repository in write mode
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Playing drums");
        values.put(HabitEntry.COLUMN_HABIT_FREQUENCY, HabitEntry.FREQUENCY_WEEKLY);

        //Insert a new Row, returning the primary key value of the new row
        long newRodId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    public Cursor createHabitCursor() {
        //Gets the data repository in read mode
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_COMPLETED_STATUS,
                HabitEntry.COLUMN_HABIT_FREQUENCY,
                HabitEntry.COLUMN_HABIT_HOURS,
        };

        return db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }

    public void readHabit(Cursor cursor) {
        TextView testTextView = findViewById(R.id.text_testing);

        try {

            //The Following block is for Testing Only
            testTextView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_COMPLETED_STATUS + " - " +
                    HabitEntry.COLUMN_HABIT_HOURS + " - " +
                    HabitEntry.COLUMN_HABIT_FREQUENCY + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int isCompletedColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_COMPLETED_STATUS);
            int hoursColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_HOURS);
            int frequencyColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_FREQUENCY);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentCompletedStatus = cursor.getInt(isCompletedColumnIndex);
                int currentHours = cursor.getInt(hoursColumnIndex);
                int currentFrequency = cursor.getInt(frequencyColumnIndex);
                //TODO Do something useful with the data, probably create an Habit Object? :)


                // Display the values from each column of the current row in the cursor in the TextView
                testTextView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentCompletedStatus + " - " +
                        currentHours + " - " +
                        currentFrequency));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
