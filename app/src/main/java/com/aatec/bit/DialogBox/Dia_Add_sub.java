package com.aatec.bit.DialogBox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.aatec.bit.R;

public class Dia_Add_sub extends AppCompatDialogFragment {
    private EditText edit_text_subject, edit_text_total, edit_text_present;
    private Button button_cancel, button_add;
    private String subject;
    private int total, present;
    private Vibrator vibrator;
    private sendData listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dia_add_sub, null);
        builder.setView(v).setTitle("");
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        edit_text_subject = v.findViewById(R.id.edit_text_subject);
        edit_text_total = v.findViewById(R.id.edit_text_total);
        edit_text_present = v.findViewById(R.id.edit_text_present);
        button_add = v.findViewById(R.id.button_add);
        button_cancel = v.findViewById(R.id.button_cancel);

//        Add button
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      declaration and validation
                subject = edit_text_subject.getText().toString();
                if (edit_text_present.getText().toString().isEmpty() || edit_text_total.getText().toString().isEmpty()) {
                    total = Integer.parseInt(edit_text_total.getHint().toString());
                    present = Integer.parseInt(edit_text_present.getHint().toString());
                } else {
                    total = Integer.parseInt(edit_text_total.getText().toString());
                    present = Integer.parseInt(edit_text_present.getText().toString());
                }
                if (TextUtils.isEmpty(subject)) {
                    Toast.makeText(getContext(), "Can't be Empty", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(50);
                    return;
                }
                if (total < present) {
                    Toast.makeText(getContext(), "Check your Input !!", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(50);
                } else {
                    listener.send(subject, total, present);
                    dismiss();
                }
            }
        });

//        Cancel
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (sendData) context;
        } catch (ClassCastException e) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public interface sendData {
        void send(String subject, int total, int present);
    }
}
