package com.aatec.bit.Sliding_Navigation.Event.Adapter;

import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Models.Event_Model;
import com.aatec.bit.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Event_Adapter extends FirestoreRecyclerAdapter<Event_Model, Event_Adapter.Event_adapter> {

    private clickListener listener;

    public Event_Adapter(@NonNull FirestoreRecyclerOptions<Event_Model> options) {
        super(options);
    }

    private static String replaceNewLineWithBreak(String source) {
        return source != null ? source.replaceAll("(?:\n|\r\n)", "<br>") : "";
    }

    private static void placeImage(String society, ImageView view) {
        if (society.equals("RAD")) {
            view.setImageResource(R.drawable.rad);
        } else if (society.equals("DEBSOC")) {
            view.setImageResource(R.drawable.debsoc);
        } else if (society.equals("INTERACT")) {
            view.setImageResource(R.drawable.interactsociety);
        } else if (society.equals("ITS")) {
            view.setImageResource(R.drawable.its);
        } else if (society.equals("BIZWIT")) {
            view.setImageResource(R.drawable.bezwit);
        } else if (society.equals("BITEXL")) {
            view.setImageResource(R.drawable.bit_exl);
        } else if (society.equals("NAPS")) {
            view.setImageResource(R.drawable.naps);
        } else {
            Log.d("Error", "Error Happen");
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull Event_adapter holder, int position, @NonNull Event_Model model) {
        String Notice = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;\">"
                        + replaceNewLineWithBreak(model.getEvent_title())
                        + "</body>]]>"));
        holder.view.loadData(Notice, "text/html; charset=utf-8", "utf-8");
        holder.view.setBackgroundColor(Color.TRANSPARENT);
        holder.date_event.setText(model.getDate());
        placeImage(model.getSociety(), holder.event);
    }

    @NonNull
    @Override
    public Event_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event, parent, false);
        return new Event_adapter(v);
    }

    public void setOnClickListener(clickListener listener) {
        this.listener = listener;
    }

    public interface clickListener {
        void setOnClickListener(int postion);
    }

    class Event_adapter extends RecyclerView.ViewHolder {
        private ImageView event;
        private WebView view;
        private TextView date_event;
        private RelativeLayout click;

        public Event_adapter(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event);
            view = itemView.findViewById(R.id.text_view_about);
            date_event = itemView.findViewById(R.id.date_event);
            click = itemView.findViewById(R.id.click);
            click.setOnClickListener(new View.OnClickListener() {
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
