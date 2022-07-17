package com.aatec.bit.Bottom_Navigation.Attendance.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Attendance_ViewModel extends AndroidViewModel {
    private AttendanceRepository repository;
    private LiveData<List<Attendance>> getAllAttendance;

    public Attendance_ViewModel(@NonNull Application application) {
        super(application);
        repository = new AttendanceRepository(application);
        getAllAttendance = repository.getGetAllAttendance();
    }

    public int insert(Attendance attendance) {
        int i = repository.insert(attendance);
        return i;
    }

    public void update(Attendance attendance) {
        repository.update(attendance);
    }

    public void delete(Attendance attendance) {
        repository.delete(attendance);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Attendance>> getGetAllAttendance() {
        return getAllAttendance;
    }
}
