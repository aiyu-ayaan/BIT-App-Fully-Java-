package com.aatec.bit.Bottom_Navigation.Attendance.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AttendanceRepository {
    private AttendanceDau attendanceDau;
    private LiveData<List<Attendance>> getAllAttendance;

    public AttendanceRepository(Application application) {
        AttendanceDatabase database = AttendanceDatabase.getInstance(application);
        attendanceDau = database.attendanceDau();
        getAllAttendance = attendanceDau.getAllNote();
    }

    public int insert(Attendance attendance) {
        try {
            new insetAttendanceAsync(attendanceDau).doInBackground(attendance);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public void update(Attendance attendance) {
        new updateAttendanceAsync(attendanceDau).doInBackground(attendance);
    }

    public void delete(Attendance attendance) {
        new deleteAttendanceAsync(attendanceDau).doInBackground(attendance);
    }

    public void deleteAll() {
        new deleteAllAttendanceAsync(attendanceDau).doInBackground();
    }

    public LiveData<List<Attendance>> getGetAllAttendance() {
        return getAllAttendance;
    }

    private static class insetAttendanceAsync extends AsyncTask<Attendance, Void, Void> {

        private AttendanceDau attendanceDau;

        public insetAttendanceAsync(AttendanceDau attendanceDau) {
            this.attendanceDau = attendanceDau;
        }

        @Override
        protected Void doInBackground(Attendance... attendances) {
            attendanceDau.insert(attendances[0]);
            return null;
        }
    }

    private static class updateAttendanceAsync extends AsyncTask<Attendance, Void, Void> {
        private AttendanceDau attendanceDau;

        public updateAttendanceAsync(AttendanceDau attendanceDau) {
            this.attendanceDau = attendanceDau;
        }

        @Override
        protected Void doInBackground(Attendance... attendances) {
            attendanceDau.update(attendances[0]);
            return null;
        }
    }

    private static class deleteAttendanceAsync extends AsyncTask<Attendance, Void, Void> {
        private AttendanceDau attendanceDau;

        public deleteAttendanceAsync(AttendanceDau attendanceDau) {
            this.attendanceDau = attendanceDau;
        }

        @Override
        protected Void doInBackground(Attendance... attendances) {
            attendanceDau.delete(attendances[0]);
            return null;
        }
    }

    private static class deleteAllAttendanceAsync extends AsyncTask<Void, Void, Void> {
        private AttendanceDau attendanceDau;

        public deleteAllAttendanceAsync(AttendanceDau attendanceDau) {
            this.attendanceDau = attendanceDau;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            attendanceDau.deleteAll();
            return null;
        }
    }
}
