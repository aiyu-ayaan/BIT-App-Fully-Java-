package com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_10;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_6;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_7;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_8;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Sem2.Books.mba_2_9;
import com.aatec.bit.R;

public class mba_sem_2 extends Fragment implements View.OnClickListener {
    private LinearLayout[] buttons = new LinearLayout[10];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_mba_sem_2, container, false);
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
                getActivity().startActivity(new Intent(getContext(), mba_2_1.class));
                break;
            case R.id.book2:
                getActivity().startActivity(new Intent(getContext(), mba_2_2.class));
                break;
            case R.id.book3:
                getActivity().startActivity(new Intent(getContext(), mba_2_3.class));
                break;
            case R.id.book4:
                getActivity().startActivity(new Intent(getContext(), mba_2_4.class));
                break;
            case R.id.book5:
                getActivity().startActivity(new Intent(getContext(), mba_2_5.class));
                break;
            case R.id.book6:
                getActivity().startActivity(new Intent(getContext(), mba_2_6.class));
                break;
            case R.id.book7:
                getActivity().startActivity(new Intent(getContext(), mba_2_7.class));
                break;
            case R.id.book8:
                getActivity().startActivity(new Intent(getContext(), mba_2_8.class));
                break;
            case R.id.book9:
                getActivity().startActivity(new Intent(getContext(), mba_2_9.class));
                break;
            case R.id.book10:
                getActivity().startActivity(new Intent(getContext(), mba_2_10.class));
                break;
        }
    }
}
