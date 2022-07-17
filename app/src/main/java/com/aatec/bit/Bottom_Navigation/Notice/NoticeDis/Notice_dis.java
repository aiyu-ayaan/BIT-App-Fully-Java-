package com.aatec.bit.Bottom_Navigation.Notice.NoticeDis;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.aatec.bit.Bottom_Navigation.Notice.Adapter.NoticeAdapter2;
import com.aatec.bit.Bottom_Navigation.Notice.NoticeNew.Notice_New;
import com.aatec.bit.DialogBox.Dia_Notice_swipe;
import com.aatec.bit.Models.Notice_Model;
import com.aatec.bit.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Notice_dis extends AppCompatActivity {
    private CollapsingToolbarLayout title;
    private ImageView icon;
    private AppBarLayout AppBarLayout;
    private Toolbar toolbar;
    private ViewPager2 pager;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference refNotice = db.collection("Notice_New");
    private NoticeAdapter2 adapter;

    private static String replaceNewLineWithBreak(String source) {
        return source != null ? source.replaceAll("(?:\n|\r\n)", "<br>") : "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_dis);
        title = findViewById(R.id.title);
        icon = findViewById(R.id.icon);
        AppBarLayout = findViewById(R.id.app_bar_layout);
        pager = findViewById(R.id.pager);

//        Toolbar Setup
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //        Collapsing toolbar Setup
        title.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        title.setCollapsedTitleTextColor(Color.rgb(0, 0, 0));
        List<Notice_Model> Notices = (List<Notice_Model>) getIntent().getSerializableExtra(Notice_New.EXTRA_LIST);
        int i = getIntent().getIntExtra(Notice_New.EXTRA_POS, -1);
        adapter = new NoticeAdapter2(Notices, this);
        pager.setAdapter(adapter);
        pager.setCurrentItem(i, true);
        toNotifyDatasetChanged();


        //      Dialog
        SharedPreferences pref = getSharedPreferences("enableNotice", MODE_PRIVATE);
        Boolean enable = pref.getBoolean("firstStartNotice", true);
        if (enable) {
            show_welcome_dia();
        }
    }

    private void show_welcome_dia() {

        Dia_Notice_swipe swipe = new Dia_Notice_swipe();
        swipe.show(getSupportFragmentManager(), "Swipe");
        SharedPreferences pref = getSharedPreferences("enableNotice", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("firstStartNotice", false);
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void toNotifyDatasetChanged() {
        if (pager != null && pager != null) {
            pager.setAdapter(null);
            pager.setAdapter(adapter);
        }
    }

}