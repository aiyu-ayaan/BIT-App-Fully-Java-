package com.aatec.bit.Sliding_Navigation.Event;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Models.Event_Model;
import com.aatec.bit.R;
import com.aatec.bit.Sliding_Navigation.Event.Adapter.Event_Adapter;
import com.aatec.bit.Sliding_Navigation.Event.Discription.Event_Discription;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class Event_section extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView showEvent;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference refEvent = db.collection("Events");
    private Event_Adapter adapter;
    private com.airbnb.lottie.LottieAnimationView anim;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_section);
        toolbar = findViewById(R.id.tootbarEvent);
        showEvent = findViewById(R.id.show_event);
        anim = findViewById(R.id.empty_anim);


//        Setting toolbar
        setSupportActionBar(toolbar);
        setTitle("Events");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Recycler view
        setUprecyclerView();

    }

    private void setUprecyclerView() {
        Query query = refEvent.orderBy("date", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Event_Model> options = new FirestoreRecyclerOptions.Builder<Event_Model>()
                .setQuery(query, Event_Model.class)
                .build();
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots.isEmpty()) {
                    anim.setVisibility(View.VISIBLE);
                } else {
                    anim.setVisibility(View.INVISIBLE);
                }
            }
        });
        adapter = new Event_Adapter(options);
        showEvent.setLayoutManager(new LinearLayoutManager(this));
        showEvent.setAdapter(adapter);

//        SetOnCLickListener
        adapter.setOnClickListener(new Event_Adapter.clickListener() {
            @Override
            public void setOnClickListener(int postion) {
                Event_Model model = options.getSnapshots().get(postion);
                Intent intent = new Intent(Event_section.this, Event_Discription.class);
                intent.putExtra("dis", model);
                startActivity(intent);
            }
        });


//        Ad Mob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}