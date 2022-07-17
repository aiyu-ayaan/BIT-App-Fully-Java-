package com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem4.Books.bba_4_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem4.Books.bba_4_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem5.Books.bba_5_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem5.Books.bba_5_2;
import com.aatec.bit.R;

public class bba_sem_5 extends Fragment implements View.OnClickListener {
    private LinearLayout[] buttons = new LinearLayout[2];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_bba_sem_5, container, false);
        for (int i = 0; i < buttons.length; i++) {
            String id = "book" + (i + 1);
            int resId = getResources().getIdentifier(id, "id", getContext().getPackageName());
            buttons[i] = v.findViewById(resId);
            buttons[i].setOnClickListener(this);
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book1:
                getActivity().startActivity(new Intent(getContext(), bba_5_1.class));
                break;
            case R.id.book2:
                getActivity().startActivity(new Intent(getContext(), bba_5_2.class));
                break;
        }
    }
}
