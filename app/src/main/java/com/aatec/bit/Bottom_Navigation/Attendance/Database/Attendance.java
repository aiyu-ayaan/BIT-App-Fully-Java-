package com.aatec.bit.Bottom_Navigation.Attendance.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "attendance_table",
        indices = {@Index(value = {"subject_name"}, unique = true)})
public class Attendance {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "subject_name")
    private String subject_name;
    private int total;
    private int present;

    public Attendance(String subject_name, int total, int present) {
        this.subject_name = subject_name;
        this.total = total;
        this.present = present;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public int getTotal() {
        return total;
    }

    public int getPresent() {
        return present;
    }
}
