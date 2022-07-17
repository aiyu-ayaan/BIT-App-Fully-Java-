package com.aatec.bit.Bottom_Navigation.Attendance.List_All;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Bottom_Navigation.Attendance.Database.Attendance;
import com.aatec.bit.Bottom_Navigation.Attendance.Database.Attendance_ViewModel;
import com.aatec.bit.Bottom_Navigation.Attendance.EditList.Edit_Attendance;
import com.aatec.bit.DialogBox.Dia_Add_sub;
import com.aatec.bit.R;
import com.google.android.material.transition.platform.MaterialSharedAxis;

import java.util.List;

public class List_All extends AppCompatActivity implements Dia_Add_sub.sendData {

    public static final String EXTRA_ID = " com.aatec.bit.ID";
    public static final String EXTRA_SUBJECT = " com.aatec.bit.SUBJECT";
    public static final String EXTRA_PRESENT = " com.aatec.bit.PRESENT";
    public static final String EXTRA_TOTAL = " com.aatec.bit.TOTAL";
    public static final int UPDATE = 1;
    private Toolbar ListAllToolbar;
    private Button addSub;
    private Attendance_ViewModel viewModel;
    private RecyclerView listView;
    private List_All_adapter adapter;
    private Dia_Add_sub add;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X,true));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__all);
        ListAllToolbar = findViewById(R.id.ListAllToolbar);
        addSub = findViewById(R.id.addSub);
        listView = findViewById(R.id.ListAll);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


//        Set up Toolbar
        setTitle("List");
        setSupportActionBar(ListAllToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        Adding new Subject
        addSubject();

//        Recycler View
        adapter = new List_All_adapter();
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);


//        View model
        viewModel = ViewModelProviders.of(this).get(Attendance_ViewModel.class);
        viewModel.getGetAllAttendance().observe(this, new Observer<List<Attendance>>() {
            @Override
            public void onChanged(List<Attendance> attendances) {
                adapter.submitList(attendances);
            }
        });

//        Update
        updateAttendance();


//        Rest
        resetAttendance();


//        Delete
        deleteAttendance();
    }

    private void deleteAttendance() {
        adapter.setOnDeleteListener(new List_All_adapter.DeleteListener() {
            @Override
            public void setOnDeleteListener(Attendance attendance) {
                AlertDialog.Builder builder = new AlertDialog.Builder(List_All.this);
                builder.setTitle("Do u want to delete " + attendance.getSubject_name() + " !")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.delete(attendance);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    private void resetAttendance() {
        adapter.setOnResetListener(new List_All_adapter.ResetListener() {
            @Override
            public void setOnResetListener(Attendance attendance) {
                String subject = attendance.getSubject_name();
                int id = attendance.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(List_All.this);
                builder.setTitle("Are u sure to reset values of " + attendance.getSubject_name())
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Attendance att = new Attendance(subject, 0, 0);
                                att.setId(id);
                                viewModel.update(att);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    private void updateAttendance() {
        adapter.setOnUpdateListener(new List_All_adapter.UpdateListener() {
            @Override
            public void setOnUpdateListener(Attendance attendance) {
                Intent intent = new Intent(List_All.this, Edit_Attendance.class);
                intent.putExtra(EXTRA_ID, attendance.getId());
                intent.putExtra(EXTRA_SUBJECT, attendance.getSubject_name());
                intent.putExtra(EXTRA_PRESENT, attendance.getPresent());
                intent.putExtra(EXTRA_TOTAL, attendance.getTotal());
                startActivityForResult(intent, UPDATE, ActivityOptions.makeSceneTransitionAnimation(List_All.this).toBundle());
            }
        });
    }

    private void addSubject() {
        addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = new Dia_Add_sub();
                add.show(getSupportFragmentManager(), "Add Sub");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_all_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.deleteAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(List_All.this);
                builder.setTitle("Delete All Subject")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.deleteAll();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void send(String subject, int total, int present) {
        Attendance attendance = new Attendance(subject, total, present);
        int i = viewModel.insert(attendance);
        if (i == -1) {
            vibrator.vibrate(50);
            Toast.makeText(this, "Subject is Already Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                String subject = data.getStringExtra(EXTRA_SUBJECT);
                int total = data.getIntExtra(EXTRA_TOTAL, -1);
                int present = data.getIntExtra(EXTRA_TOTAL, -1);
                Attendance attendance = new Attendance(subject, total, present);
                attendance.setId(id);
//                Update
                viewModel.update(attendance);
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error Happen", Toast.LENGTH_SHORT).show();
            }
        }
    }
}