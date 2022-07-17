package com.aatec.bit.Sliding_Navigation.Society.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.R;

public class SocietyAdapter extends RecyclerView.Adapter<SocietyAdapter.SocietyHolder> {

    private String[] SocietiesName;
    private int[] imgRes;
    private onClickListener listener;

    public SocietyAdapter(String[] societiesName, int[] imgRes) {
        SocietiesName = societiesName;
        this.imgRes = imgRes;
    }

    @NonNull
    @Override
    public SocietyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_society, parent, false);
        return new SocietyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SocietyHolder holder, int position) {
        holder.iv.setImageResource(imgRes[position]);
        holder.tv.setText(SocietiesName[position]);
    }

    @Override
    public int getItemCount() {
        return imgRes.length;
    }

    class SocietyHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;
        private CardView cardView;

        public SocietyHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            cardView = itemView.findViewById(R.id.card);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=RecyclerView.NO_POSITION && listener != null){
                        listener.setOnClickListener(getAdapterPosition(),iv);
                    }
                }
            });
        }
    }
    public void setOnClickListener(onClickListener listener){
        this.listener = listener;
    }
    public interface onClickListener{
        void setOnClickListener(int position,ImageView card);
    }
}
