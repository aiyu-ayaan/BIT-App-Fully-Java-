package com.aatec.bit.Bottom_Navigation.Attendance.EditList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aatec.bit.Bottom_Navigation.Attendance.List_All.List_All;
import com.aatec.bit.R;
import com.google.android.material.transition.platform.MaterialSharedAxis;

public class Edit_Attendance extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText edit_text_subject, edit_text_present, edit_text_total;
    private Button bt_update;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.X,false));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_attendance);
        toolbar = findViewById(R.id.toolbar2);
        edit_text_present = findViewById(R.id.edit_text_present);
        edit_text_subject = findViewById(R.id.edit_text_subject);
        edit_text_total = findViewById(R.id.edit_text_total);
        bt_update = findViewById(R.id.bt_update);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

//        Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getIntent().getStringExtra(List_All.EXTRA_SUBJECT));

//        getting Intent data
        getIntendData();

//        Updating Part
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAttendance();
            }
        });
    }

    private void updateAttendance() {
        int id = getIntent().getIntExtra(List_All.EXTRA_ID, -1);
        if (id != -1) {
            int present = 0, total = 0;
            String subject;
//        Subject
            if (edit_text_subject.getText().toString().isEmpty()) {
                subject = edit_text_subject.getHint().toString();
            } else {
                subject = edit_text_subject.getText().toString();
            }


//        Present
            if (edit_text_present.getText().toString().isEmpty()) {
                present = Integer.parseInt(edit_text_present.getHint().toString());

            } else {
                present = Integer.parseInt(edit_text_present.getText().toString());

            }


//        Total
            if (edit_text_total.getText().toString().isEmpty()) {
                total = Integer.parseInt(edit_text_total.getHint().toString());
            } else {
                total = Integer.parseInt(edit_text_total.getText().toString());
            }

            if (total < present) {
                Toast.makeText(this, "Check your input", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(50);
                return;
            }

            Intent data = new Intent();
            data.putExtra(List_All.EXTRA_ID, id);
            data.putExtra(List_All.EXTRA_SUBJECT, subject);
            data.putExtra(List_All.EXTRA_TOTAL, total);
            data.putExtra(List_All.EXTRA_PRESENT, present);
            setResult(RESULT_OK, data);
            finishAfterTransition();
        } else {
            Toast.makeText(this, "Error Happen", Toast.LENGTH_SHORT).show();
        }
    }

    private void getIntendData() {
        if (getIntent().hasExtra(List_All.EXTRA_ID) && getIntent().hasExtra(List_All.EXTRA_SUBJECT)
                && getIntent().hasExtra(List_All.EXTRA_PRESENT) && getIntent().hasExtra(List_All.EXTRA_TOTAL)) {
            edit_text_subject.setHint(getIntent().getStringExtra(List_All.EXTRA_SUBJECT));
            edit_text_total.setHint(String.valueOf(getIntent().getIntExtra(List_All.EXTRA_TOTAL, -1)));
            edit_text_present.setHint(String.valueOf(getIntent().getIntExtra(List_All.EXTRA_PRESENT, -1)));
        } else {
            Toast.makeText(this, "Error Happen", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }
}