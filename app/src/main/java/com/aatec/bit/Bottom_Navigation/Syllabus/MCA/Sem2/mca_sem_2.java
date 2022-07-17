package com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem1.Books.mca_1_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem1.Books.mca_1_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem1.Books.mca_1_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem1.Books.mca_1_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem1.Books.mca_1_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem1.Books.mca_1_6;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2.Books.mca_2_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2.Books.mca_2_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2.Books.mca_2_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2.Books.mca_2_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2.Books.mca_2_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2.Books.mca_2_6;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2.Books.mca_2_7;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem2.Books.mca_2_8;
import com.aatec.bit.R;

public class mca_sem_2 extends Fragment implements View.OnClickListener {
    private LinearLayout[] buttons = new LinearLayout[8];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_mca_sem_2, container, false);
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
                getActivity().startActivity(new Intent(getContext(), mca_2_1.class));
                break;
            case R.id.book2:
                getActivity().startActivity(new Intent(getContext(), mca_2_2.class));
                break;
            case R.id.book3:
                getActivity().startActivity(new Intent(getContext(), mca_2_3.class));
                break;
            case R.id.book4:
                getActivity().startActivity(new Intent(getContext(), mca_2_4.class));
                break;
            case R.id.book5:
                getActivity().startActivity(new Intent(getContext(), mca_2_5.class));
                break;
            case R.id.book6:
                getActivity().startActivity(new Intent(getContext(), mca_2_6.class));
                break;
            case R.id.book7:
                getActivity().startActivity(new Intent(getContext(), mca_2_7.class));
                break;
            case R.id.book8:
                getActivity().startActivity(new Intent(getContext(), mca_2_8.class));
                break;
        }
    }
}
