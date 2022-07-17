package com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem3.Books.mca_3_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem3.Books.mca_3_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem3.Books.mca_3_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem3.Books.mca_3_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem3.Books.mca_3_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem5.Books.mca_5_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem5.Books.mca_5_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem5.Books.mca_5_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem5.Books.mca_5_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Sem5.Books.mca_5_5;
import com.aatec.bit.R;

public class mca_sem_5 extends Fragment implements View.OnClickListener {
    private LinearLayout[] buttons = new LinearLayout[5];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_mca_sem_5, container, false);
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
                getActivity().startActivity(new Intent(getContext(), mca_5_1.class));
                break;
            case R.id.book2:
                getActivity().startActivity(new Intent(getContext(), mca_5_2.class));
                break;
            case R.id.book3:
                getActivity().startActivity(new Intent(getContext(), mca_5_3.class));
                break;
            case R.id.book4:
                getActivity().startActivity(new Intent(getContext(), mca_5_4.class));
                break;
            case R.id.book5:
                getActivity().startActivity(new Intent(getContext(), mca_5_5.class));
                break;
        }
    }
}
