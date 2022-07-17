package com.aatec.bit.Sliding_Navigation.Society;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aatec.bit.R;
import com.aatec.bit.Sliding_Navigation.Society.Adapter.SocietyAdapter;
import com.aatec.bit.Sliding_Navigation.Society.SoicetyDis.Society_Discription;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Society extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView showSociety;
    private String[] SocietiesName;
    private String[] SocietiesDis;
    private String[] InstaLink;
    private AdView mAdView;
    private int[] imgRes = {R.drawable.rad, R.drawable.debsoc, R.drawable.interactsociety, R.drawable.its,
            R.drawable.bezwit, R.drawable.bit_exl, R.drawable.naps};

    private int[] imgPost = {R.drawable.rad_p, R.drawable.debsoc_p, R.drawable.interact_p, R.drawable.its_p,
            R.drawable.bezwit_p, R.drawable.bitexl_p, R.drawable.naps_p};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society);
        toolbar = findViewById(R.id.toolbar2);
        showSociety = findViewById(R.id.societyView);

//        Setup Toolbar
        setSupportActionBar(toolbar);
        setTitle("Societies");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Add values to Array List
        SocietiesName = getResources().getStringArray(R.array.Society);
        SocietiesDis = getResources().getStringArray(R.array.dis);
        InstaLink = getResources().getStringArray(R.array.link_society);

//        Recyclerview Part
        setUpRecyclerView();


//        Ad mob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void setUpRecyclerView() {
        SocietyAdapter adapter = new SocietyAdapter(SocietiesName, imgRes);
        showSociety.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        showSociety.setAdapter(adapter);

//        OnClickListener
        adapter.setOnClickListener(new SocietyAdapter.onClickListener() {
            @Override
            public void setOnClickListener(int position, ImageView card) {
                Intent i = new Intent(Society.this, Society_Discription.class);
                i.putExtra("societyName", SocietiesName[position]);
                i.putExtra("societyDis", SocietiesDis[position]);
                i.putExtra("societyIns", InstaLink[position]);
                i.putExtra("societyImg", imgRes[position]);
                i.putExtra("societyPost", imgPost[position]);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Society.this,
                        Pair.create(card, "society_img"));
                startActivity(i, options.toBundle());
//                ActivityOptions.makeSceneTransitionAnimation(Society.this).toBundle()
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}