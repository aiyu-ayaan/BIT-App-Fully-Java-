package com.aatec.bit.Bottom_Navigation.Notice.NoticeOld;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aatec.bit.Bottom_Navigation.Notice.Adapter.NoticeAdapter;
import com.aatec.bit.Bottom_Navigation.Notice.NoticeDis.Notice_dis;
import com.aatec.bit.Bottom_Navigation.Notice.NoticeNew.Notice_New;
import com.aatec.bit.Models.Notice_Model;
import com.aatec.bit.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Notice_Old extends Fragment {
    private RecyclerView notice_show;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference refNotice = db.collection("Notice_Old");
    private NoticeAdapter adapter;
    private com.airbnb.lottie.LottieAnimationView anim;
    private List<Notice_Model> models = new ArrayList<>();
    private FirestoreRecyclerOptions<Notice_Model> options;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notice_old, container, false);
        notice_show = v.findViewById(R.id.notice_show);
        anim = v.findViewById(R.id.img);

//        Setting Recycler View
        setUpRecyclerView();
        return v;
    }

    //    Setting Recycler View
    private void setUpRecyclerView() {
        Query query = refNotice.orderBy("date", Query.Direction.DESCENDING);
        options = new FirestoreRecyclerOptions.Builder<Notice_Model>()
                .setQuery(query, Notice_Model.class)
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
        adapter = new NoticeAdapter(options);
        notice_show.setLayoutManager(new StaggeredGridLayoutManager(1, 1));
        notice_show.setAdapter(adapter);

//        ClickListener
        adapter.setOnClickListener(new NoticeAdapter.clickListener() {
            @Override
            public void setOnClickListener(int position) {
                setList();
                Intent intent = new Intent(getContext(), Notice_dis.class);
                intent.putExtra(Notice_New.EXTRA_LIST, (Serializable) models);
                intent.putExtra(Notice_New.EXTRA_POS, position);
                getActivity().startActivity(intent);
            }
        });
    }

    private void setList() {
        models.clear();
        for (int i = 0; i < options.getSnapshots().size(); i++) {
            models.add(options.getSnapshots().get(i));
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        setList();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        setList();
    }
}
