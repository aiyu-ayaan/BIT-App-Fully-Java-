package com.aatec.bit.Bottom_Navigation.Notice.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Models.Notice_Model;
import com.aatec.bit.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoticeAdapter extends FirestoreRecyclerAdapter<Notice_Model, NoticeAdapter.NoticeHolder> {

    private clickListener listener;

    public NoticeAdapter(@NonNull FirestoreRecyclerOptions<Notice_Model> options) {
        super(options);
    }

    public static String replaceNewLineWithBreak(String source) {
        return source != null ? source.replaceAll("(?:\n|\r\n)", "<br>") : "";
    }

    @Override
    protected void onBindViewHolder(@NonNull NoticeHolder holder, int position, @NonNull Notice_Model model) {
        holder.date.setText(model.getDate());
        holder.view.setText(model.getTitle());
    }

    @NonNull
    @Override
    public NoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notice, parent, false);
        return new NoticeHolder(v);
    }

    public void setOnClickListener(clickListener listener) {
        this.listener = listener;
    }

    public interface clickListener {
        void setOnClickListener(int position);
    }

    class NoticeHolder extends RecyclerView.ViewHolder {
        private TextView view;
        private TextView date;
        private CardView cardNotice;

        public NoticeHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.tv_notice);
            date = itemView.findViewById(R.id.date_notice);
            cardNotice = itemView.findViewById(R.id.cardNotice);
            cardNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION && listener != null) {
                        listener.setOnClickListener(getAdapterPosition());
                    }
                }
            });
        }
    }
}
