package com.aatec.bit.DialogBox;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.aatec.bit.R;
import com.aatec.bit.Sliding_Navigation.Event.Event_section;

public class Dia_Home extends AppCompatDialogFragment {
    private TextView text_view_about, society_Name, text_event;
    private String title, subtitle, dis;
    private int event ;
    private RelativeLayout dia_event;
    public Dia_Home(String title, String subtitle, String dis,int event) {
        this.title = title;
        this.subtitle = subtitle;
        this.dis = dis;
        this.event = event;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dia_home_screen_dia, null);
        builder.setView(v).setTitle("");
        dia_event =v.findViewById(R.id.dia_event);
        text_view_about = v.findViewById(R.id.text_view_about);
        society_Name = v.findViewById(R.id.society_Name);
        text_event = v.findViewById(R.id.text_event);
        text_view_about.setText(title);
        society_Name.setText(subtitle);
        text_event.setText(dis);
        if(event ==1){
            dia_event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), Event_section.class));
                    dismiss();
                }
            });
        }else {
            dia_event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        return builder.create();
    }
}
