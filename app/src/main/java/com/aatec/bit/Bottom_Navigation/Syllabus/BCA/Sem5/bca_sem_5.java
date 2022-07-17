package com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_10;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_11;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_6;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_7;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_8;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem4.Books.bca_4_9;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_10;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_11;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_6;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_7;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_8;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Sem5.Books.bca_5_9;
import com.aatec.bit.R;

public class bca_sem_5 extends Fragment implements View.OnClickListener {

    private LinearLayout[] buttons = new LinearLayout[11];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_bca_sem_5, container, false);
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
                getActivity().startActivity(new Intent(getContext(), bca_5_1.class));
                break;
            case R.id.book2:
                getActivity().startActivity(new Intent(getContext(), bca_5_2.class));
                break;
            case R.id.book3:
                getActivity().startActivity(new Intent(getContext(), bca_5_3.class));
                break;
            case R.id.book4:
                getActivity().startActivity(new Intent(getContext(), bca_5_4.class));
                break;
            case R.id.book5:
                getActivity().startActivity(new Intent(getContext(), bca_5_5.class));
                break;
            case R.id.book6:
                getActivity().startActivity(new Intent(getContext(), bca_5_6.class));
                break;
            case R.id.book7:
                getActivity().startActivity(new Intent(getContext(), bca_5_7.class));
                break;
            case R.id.book8:
                getActivity().startActivity(new Intent(getContext(), bca_5_8.class));
                break;
            case R.id.book9:
                getActivity().startActivity(new Intent(getContext(), bca_5_9.class));
                break;
            case R.id.book10:
                getActivity().startActivity(new Intent(getContext(), bca_5_10.class));
                break;
            case R.id.book11:
                getActivity().startActivity(new Intent(getContext(), bca_5_11.class));
                break;

        }
    }
}
