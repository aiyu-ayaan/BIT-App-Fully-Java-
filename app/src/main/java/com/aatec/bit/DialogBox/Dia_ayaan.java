package com.aatec.bit.DialogBox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.aatec.bit.R;

public class Dia_ayaan extends AppCompatDialogFragment {
    public static final String ayaanIns = "https://www.instagram.com/__the_aiyu__/";
    public static final String ayaanFace = "theaiyu";
    private ImageView ins, face;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dia_ayaan, null);
        builder.setView(v).setTitle("");
        ins = v.findViewById(R.id.inst);
        face = v.findViewById(R.id.face);


//        Instagram
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(ayaanIns);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/__the_aiyu__")));
                }
            }
        });

//        Facebook
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    getActivity().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    String url = "https://www.facebook.com/"+ayaanFace;
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href="+url));
                } catch (Exception e) {
                    // no Facebook app, revert to browser
                    String url = "https://facebook.com/"+ayaanFace;
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent .setData(Uri.parse(url));
                }
                getActivity().startActivity(intent);
            }
        });
        return builder.create();
    }
}
