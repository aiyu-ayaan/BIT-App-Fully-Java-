package com.aatec.bit.Sliding_Navigation.Holiday.Fragment.Holiday_Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Models.Holiday_Model;
import com.aatec.bit.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Holiday_main extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reference = db.collection("Holiday");
    private Holiday_main_adapter adapter ;
    private RecyclerView view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.holiday_main_layout, container, false);
        view = v.findViewById(R.id.holidayMainView);
//        setting Recycler View
        settingRecyclerView();
        return v;
    }

    private void settingRecyclerView() {
        Query query = reference.orderBy("sno", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Holiday_Model> options = new FirestoreRecyclerOptions.Builder<Holiday_Model>()
                .setQuery(query, Holiday_Model.class)
                .build();
        adapter = new Holiday_main_adapter(options);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        view.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        adapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {
        adapter.startListening();
        super.onStop();
    }
}
