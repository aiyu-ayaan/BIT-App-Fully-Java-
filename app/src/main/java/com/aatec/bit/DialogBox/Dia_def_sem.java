package com.aatec.bit.DialogBox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.aatec.bit.R;

public class Dia_def_sem extends AppCompatDialogFragment implements View.OnClickListener {
    private TextView[] buttons = new TextView[6];
    private sendDefault listener;
    private LinearLayout row1;
    private int i = 0;

    public Dia_def_sem() {
    }

    public Dia_def_sem(int i) {
        this.i = i;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dia_def_syllabus, null);

        row1 = v.findViewById(R.id.row1);
        for (int i = 0; i < buttons.length; i++) {
            String id = "bt_" + (i + 1);
            int resId = getResources().getIdentifier(id, "id", getActivity().getPackageName());
            buttons[i] = v.findViewById(resId);
            buttons[i].setOnClickListener(this);
        }

        if (i == 4) {
            buttons[2].setText("Sem 4");
            row1.setVisibility(View.INVISIBLE);
        }
        if (i == 3) {
            buttons[5].setVisibility(View.INVISIBLE);
        }

        builder.setView(v)
                .setTitle("");
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                listener.sendDefaultNum(0);
                dismiss();
                break;
            case R.id.bt_2:
                listener.sendDefaultNum(1);
                dismiss();
                break;
            case R.id.bt_3:
                listener.sendDefaultNum(2);
                dismiss();
                break;
            case R.id.bt_4:
                listener.sendDefaultNum(3);
                dismiss();
                break;
            case R.id.bt_5:
                listener.sendDefaultNum(4);
                dismiss();
                break;
            case R.id.bt_6:
                listener.sendDefaultNum(5);
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (sendDefault) getActivity();
        } catch (ClassCastException e) {
            Log.e("Error", "onAttach: ClassCastException : " + e.getMessage());
        }
    }

    public interface sendDefault {
        void sendDefaultNum(int position);
    }
}
