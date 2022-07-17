package com.aatec.bit.Bottom_Navigation.Syllabus.MBA;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem1.mba_sem_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.mba_sem_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem4.mba_sem_4;
import com.aatec.bit.DialogBox.Dia_def_sem;
import com.aatec.bit.DialogBox.Dia_welcome_syllabus;
import com.aatec.bit.R;

public class Main_mba extends AppCompatActivity implements View.OnClickListener, Dia_def_sem.sendDefault {

    private Toolbar toolbar;
    private TextView[] buttons = new TextView[3];
    private Vibrator vibrator;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mba);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("MBA");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "bt" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this);
        }

//        Set default
        loadData();
        changeColor(number);


        //      Dialog
        SharedPreferences pref = getSharedPreferences("enable", MODE_PRIVATE);
        Boolean enable = pref.getBoolean("firstStart", true);
        if (enable) {
            show_welcome_dia();
        }

    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.bt1:
                    changeColor(0);
                    break;
                case R.id.bt2:
                    changeColor(1);
                    break;
                case R.id.bt3:
                    changeColor(2);
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }


    //    Change Color
    private void changeColor(int pos) {
        setFragment(pos);
        vibrator.vibrate(10);
        for (int i = 0; i < buttons.length; i++) {
            if (i == pos) {
                buttons[i].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red_button));
                if (pos == 2) {
                    setTitle("MBA Sem " + (i + 2));
                } else {
                    setTitle("MBA Sem " + (i + 1));
                }
            } else {
                buttons[i].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            }
        }
    }

    //    Show Syllabus
    private void setFragment(int pos) {
        switch (pos) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.showSalabus, new mba_sem_1()).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.showSalabus, new mba_sem_2()).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.showSalabus, new mba_sem_4()).commit();
                break;
        }
    }


    //    Default
    private void setDialog() {
        Dia_def_sem dia = new Dia_def_sem(4);
        dia.show(getSupportFragmentManager(), "MBA");
    }


    @Override
    public void sendDefaultNum(int position) {
        number = position;
        saveData();
        changeColor(number);
    }

    //    Shared Prefrence
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveLimit", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("limitMBA", number);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveLimit", MODE_PRIVATE);
        number = sharedPreferences.getInt("limitMBA", MODE_PRIVATE);
    }

    private void show_welcome_dia() {
        Dia_welcome_syllabus welcome_syllabus = new Dia_welcome_syllabus();
        welcome_syllabus.show(getSupportFragmentManager(), "Welcome");
        SharedPreferences pref = getSharedPreferences("enable", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.syllabus_default, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.setDefault:
                setDialog();
        }
        return super.onOptionsItemSelected(item);
    }
}