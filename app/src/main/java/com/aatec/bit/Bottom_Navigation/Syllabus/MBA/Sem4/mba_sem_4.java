package com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem4.Books.mba_4_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem4.Books.mba_4_2;
import com.aatec.bit.R;

public class mba_sem_4 extends Fragment implements View.OnClickListener {
    private LinearLayout[] buttons = new LinearLayout[2];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_mba_sem_4, container, false);
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
                getActivity().startActivity(new Intent(getContext(), mba_4_1.class));
                break;
            case R.id.book2:
                getActivity().startActivity(new Intent(getContext(), mba_4_2.class));
                break;
        }
    }
}
