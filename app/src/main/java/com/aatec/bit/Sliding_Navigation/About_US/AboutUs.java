package com.aatec.bit.Sliding_Navigation.About_US;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.DialogBox.Dia_aasif;
import com.aatec.bit.DialogBox.Dia_ayaan;
import com.aatec.bit.DialogBox.Dia_nilay;
import com.aatec.bit.DialogBox.Dia_roushan;
import com.aatec.bit.R;
import com.aatec.bit.Sliding_Navigation.About_US.Adapter.About_us_adapter;

import java.util.ArrayList;
import java.util.List;

public class AboutUs extends AppCompatActivity {

    private RecyclerView showData;
    private List<String> list = new ArrayList<>();
    private LinearLayout ayaan, aasif, roushan, nilay;
    private ImageView insta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        showData = findViewById(R.id.showData);
        ayaan = findViewById(R.id.Ayaan);
        aasif = findViewById(R.id.Aasif);
        roushan = findViewById(R.id.Roushan);
        nilay = findViewById(R.id.Nilay);
        insta = findViewById(R.id.insta);
//        Adding value
        list.add("* Bugs Fixed");
        list.add("* Improved UI");
        list.add("Swipe down to know about Developers");


//        Setup Recycler View
        setUpView();


//        Setup of Clicks
        setUpClick();


//        Instgram link
        instaLink();
    }

    private void instaLink() {
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkIns = "https://www.instagram.com/bit_lalpur_app/";
                Uri uri = Uri.parse(linkIns);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/bit_lalpur_ap")));
                }
            }
        });
    }

    private void setUpClick() {
        ayaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dia_ayaan ayaan = new Dia_ayaan();
                ayaan.show(getSupportFragmentManager(), "Ayaan");
            }
        });
        aasif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dia_aasif aasif = new Dia_aasif();
                aasif.show(getSupportFragmentManager(), "Aasif");
            }
        });
        nilay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dia_nilay nilay = new Dia_nilay();
                nilay.show(getSupportFragmentManager(), "Aasif");
            }
        });
        roushan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dia_roushan roushan = new Dia_roushan();
                roushan.show(getSupportFragmentManager(), "Aasif");
            }
        });
    }

    private void setUpView() {
//        showData.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        showData.setLayoutManager(new LinearLayoutManager(getApplication()));
        About_us_adapter adapter = new About_us_adapter(list);
        showData.setAdapter(adapter);
    }
}