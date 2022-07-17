package com.aatec.bit.Bottom_Navigation.Syllabus.BCA;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem1.bca_sem_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem2.bca_sem_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3.bca_sem_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.bca_sem_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.bca_sem_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem6.bca_sem_6;
import com.aatec.bit.DialogBox.Dia_def_sem;
import com.aatec.bit.DialogBox.Dia_welcome_syllabus;
import com.aatec.bit.R;

public class Main_bca extends AppCompatActivity implements View.OnClickListener, Dia_def_sem.sendDefault {

    private HorizontalScrollView horScroll;
    private Toolbar toolbar;
    private TextView[] buttons = new TextView[6];
    private Vibrator vibrator;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bca);
        toolbar = findViewById(R.id.toolbar);
        horScroll = findViewById(R.id.horScroll);
        horScroll.setSmoothScrollingEnabled(true);
        setSupportActionBar(toolbar);
        setTitle("BCA");
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

//        Scroll
        if (number == 3 || number == 4 || number == 5) {
            horScroll.postDelayed(new Runnable() {
                @Override
                public void run() {
                    horScroll.smoothScrollTo(horScroll.getRight(), 0);
                }
            }, 500);
        }
    }


    //    Click
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
                case R.id.bt4:
                    changeColor(3);
                    break;
                case R.id.bt5:
                    changeColor(4);

                    break;
                case R.id.bt6:
                    changeColor(5);
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    //    Change Color
    private void changeColor(int pos) {
        vibrator.vibrate(10);
        setFragment(pos);
        for (int i = 0; i < buttons.length; i++) {
            if (i == pos) {
                buttons[i].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red_button));
                setTitle("BCA Sem " + (i + 1));
            } else {
                buttons[i].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            }
        }
    }


    //    Setting fragments
    private void setFragment(int pos) {
        switch (pos) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bca_sem_1()).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bca_sem_2()).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bca_sem_3()).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bca_sem_4()).commit();
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bca_sem_5()).commit();
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bca_sem_6()).commit();
                break;
        }
    }

    //    Default
    private void setDialog() {
        Dia_def_sem dia = new Dia_def_sem();
        dia.show(getSupportFragmentManager(), "BCA");
    }


    @Override
    public void sendDefaultNum(int position) {
        number = position;
        saveData();
        changeColor(number);
    }

    //    Shared Prefrence
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveLimitSyllabus", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("limitSyllabus", number);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveLimitSyllabus", MODE_PRIVATE);
        number = sharedPreferences.getInt("limitSyllabus", MODE_PRIVATE);
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