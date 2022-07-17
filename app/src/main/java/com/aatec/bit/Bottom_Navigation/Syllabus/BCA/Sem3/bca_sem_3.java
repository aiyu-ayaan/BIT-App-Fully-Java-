package com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem2.Books.bca_2_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem2.Books.bca_2_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem2.Books.bca_2_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem2.Books.bca_2_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem2.Books.bca_2_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem2.Books.bca_2_6;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem2.Books.bca_2_7;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3.Books.bca_3_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3.Books.bca_3_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3.Books.bca_3_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3.Books.bca_3_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3.Books.bca_3_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3.Books.bca_3_6;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem3.Books.bca_3_7;
import com.aatec.bit.R;

public class bca_sem_3 extends Fragment implements View.OnClickListener {
    private LinearLayout[] buttons = new LinearLayout[7];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_bca_sem_3, container, false);
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
                getActivity().startActivity(new Intent(getContext(), bca_3_1.class));
                break;
            case R.id.book2:
                getActivity().startActivity(new Intent(getContext(), bca_3_2.class));
                break;
            case R.id.book3:
                getActivity().startActivity(new Intent(getContext(), bca_3_3.class));
                break;
            case R.id.book4:
                getActivity().startActivity(new Intent(getContext(), bca_3_4.class));
                break;
            case R.id.book5:
                getActivity().startActivity(new Intent(getContext(), bca_3_5.class));
                break;
            case R.id.book6:
                getActivity().startActivity(new Intent(getContext(), bca_3_6.class));
                break;
            case R.id.book7:
                getActivity().startActivity(new Intent(getContext(), bca_3_7.class));
                break;
        }
    }
}
