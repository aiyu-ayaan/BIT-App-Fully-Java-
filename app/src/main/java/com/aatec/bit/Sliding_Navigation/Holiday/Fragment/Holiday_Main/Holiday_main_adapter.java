package com.aatec.bit.Sliding_Navigation.Holiday.Fragment.Holiday_Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Models.Holiday_Model;
import com.aatec.bit.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Holiday_main_adapter extends FirestoreRecyclerAdapter<Holiday_Model, Holiday_main_adapter.Holiday_main_holder> {
    public Holiday_main_adapter(@NonNull FirestoreRecyclerOptions<Holiday_Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holiday_main_holder holder, int position, @NonNull Holiday_Model model) {
        holder.occasion.setText(model.getOccasion());
        holder.date.setText(model.getDate());
        holder.day.setText(model.getDay());
    }

    @NonNull
    @Override
    public Holiday_main_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_holiday, parent, false);
        return new Holiday_main_holder(v);
    }

    public class Holiday_main_holder extends RecyclerView.ViewHolder {
        private TextView occasion, date, day;

        public Holiday_main_holder(@NonNull View itemView) {
            super(itemView);
            occasion = itemView.findViewById(R.id.occasion);
            date = itemView.findViewById(R.id.date);
            day = itemView.findViewById(R.id.day);
        }
    }
}
