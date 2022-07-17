package com.aatec.bit.Bottom_Navigation.Attendance.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Attendance.class, version = 3)
public abstract class AttendanceDatabase extends RoomDatabase {
    private static AttendanceDatabase instance;

    public static synchronized AttendanceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AttendanceDatabase.class, "Attendance_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract AttendanceDau attendanceDau();

}
