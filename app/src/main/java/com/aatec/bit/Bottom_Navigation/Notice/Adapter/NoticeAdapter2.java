package com.aatec.bit.Bottom_Navigation.Notice.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Models.Notice_Model;
import com.aatec.bit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoticeAdapter2 extends RecyclerView.Adapter<NoticeAdapter2.NoticeViewHolder2> {


    private List<Notice_Model> notices;
    private Context context;

    public NoticeAdapter2(List<Notice_Model> notices,Context context) {
        this.notices = notices;
        this.context = context;
    }

    public static String replaceNewLineWithBreak(String source) {
        return source != null ? source.replaceAll("(?:\n|\r\n)", "<br>") : "";
    }

    @NonNull
    @Override
    public NoticeViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notice_dis, parent, false);
        return new NoticeViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder2 holder, int position) {
        Notice_Model model = notices.get(position);
        String Body = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;\">"
                        + replaceNewLineWithBreak(model.getBody())
                        + "</body>]]>"));
        holder.view.loadData(Body, "text/html; charset=utf-8", "utf-8");
        holder.view.setBackgroundColor(Color.TRANSPARENT);

//        Link Open
        if (!model.getLink().isEmpty()) {
            holder.linkWeb.setVisibility(View.VISIBLE);
            holder.linkWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
                        context.startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "No application can handle this request."
                                + " Please install a webbrowser", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    class NoticeViewHolder2 extends RecyclerView.ViewHolder {

        private WebView view;
        private FloatingActionButton linkWeb;
        public NoticeViewHolder2(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.dis);
            linkWeb = itemView.findViewById(R.id.linkWeb);
        }
    }

}
