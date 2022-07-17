package com.aatec.bit.DialogBox;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.aatec.bit.R;

public class Dia_Notice_swipe extends AppCompatDialogFragment {
    private RelativeLayout dia_swipe;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dia_notice_swipe, null);
        builder.setTitle("").setView(v);
        dia_swipe = v.findViewById(R.id.dia_swipe);
        dia_swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },500);
        return builder.create();
    }
}
