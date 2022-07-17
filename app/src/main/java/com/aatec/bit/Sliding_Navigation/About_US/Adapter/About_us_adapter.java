package com.aatec.bit.Sliding_Navigation.About_US.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.R;

import java.util.List;

public class About_us_adapter extends RecyclerView.Adapter<About_us_adapter.About_us_holder> {

    private List<String> list;

    public About_us_adapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public About_us_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_about_us, parent, false);
        return new About_us_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull About_us_holder holder, int position) {
        holder.tv_new.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class About_us_holder extends RecyclerView.ViewHolder {
        private TextView tv_new;

        public About_us_holder(@NonNull View itemView) {
            super(itemView);
            tv_new = itemView.findViewById(R.id.tv_new);
        }
    }
}
