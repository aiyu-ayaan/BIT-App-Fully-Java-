package com.aatec.bit.Bottom_Navigation.Attendance.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AttendanceDau {

    @Insert
    void insert(Attendance attendance);

    @Update
    void update(Attendance attendance);

    @Delete
    void delete(Attendance attendance);

    @Query("DELETE FROM attendance_table")
    void deleteAll();

    @Query("SELECT * FROM attendance_table ORDER BY id DESC")
    LiveData<List<Attendance>> getAllNote();

}
