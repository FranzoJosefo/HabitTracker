package com.franciscoolivero.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Habit Tracker app
 */
public final class HabitContract {
    //To prevent someone from accidentally instantiating the contract class
    //Give it an empty constructor

    public HabitContract() {
    }

    /**
     * Inner class that defines constant values for the Habits database table
     * Each entry in the table represents a single Habit.
     */
    public static final class HabitEntry implements BaseColumns{

        /**Name of the Database table for habits*/
        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;

        /** Name of the habit
         *
         * Type: TEXT
         * */
        public static final String COLUMN_HABIT_NAME = "name";

        /** Status of the habit, set to 0 (False) or 1 (True) to determine if the habit has been marked as Completed.
         *
         * Type: INTEGER (use as Boolean 0,1)
         * */
        public static final String COLUMN_HABIT_COMPLETED_STATUS = "isCompleted";

        /** Hours spent on the habit (when applicable).
         * Default is -1 to mark it as null in case we don't need it because it's not applicable.
         *
         * Type: INTEGER
         * */
        public static final String COLUMN_HABIT_HOURS = "hours";

        /** Name of the habit
         *
         * Type: INTEGER
         *
         * The only possible values are {@link #FRENQUENCY_DAILY},{@link #FREQUENCY_WEEKLY}, {@link #FREQUENCY_MONTHLY}
         * */
        public static final String COLUMN_HABIT_FREQUENCY = "frequency";

        /** Constants for possible values for chosen frequency of the habit*/
        public static final int FRENQUENCY_DAILY = 0;
        public static final int FREQUENCY_WEEKLY = 1;
        public static final int FREQUENCY_MONTHLY = 2;

    }
}
