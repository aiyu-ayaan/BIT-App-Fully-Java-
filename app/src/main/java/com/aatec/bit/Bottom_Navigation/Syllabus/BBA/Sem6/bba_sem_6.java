package com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_1;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_10;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_11;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_12;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_13;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_14;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_15;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_16;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_17;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_18;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_19;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_2;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_20;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_21;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_3;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_4;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_5;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_6;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_7;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_8;
import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Sem6.Books.bba_6_9;
import com.aatec.bit.R;

public class bba_sem_6 extends Fragment implements View.OnClickListener {
    private LinearLayout[] buttons = new LinearLayout[21];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_bba_sem_6, container, false);
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
                getActivity().startActivity(new Intent(getContext(), bba_6_1.class));
                break;
            case R.id.book2:
                getActivity().startActivity(new Intent(getContext(), bba_6_2.class));
                break;
            case R.id.book3:
                getActivity().startActivity(new Intent(getContext(), bba_6_3.class));
                break;
            case R.id.book4:
                getActivity().startActivity(new Intent(getContext(), bba_6_4.class));
                break;
            case R.id.book5:
                getActivity().startActivity(new Intent(getContext(), bba_6_5.class));
                break;
            case R.id.book6:
                getActivity().startActivity(new Intent(getContext(), bba_6_6.class));
                break;
            case R.id.book7:
                getActivity().startActivity(new Intent(getContext(), bba_6_7.class));
                break;
            case R.id.book8:
                getActivity().startActivity(new Intent(getContext(), bba_6_8.class));
                break;
            case R.id.book9:
                getActivity().startActivity(new Intent(getContext(), bba_6_9.class));
                break;
            case R.id.book10:
                getActivity().startActivity(new Intent(getContext(), bba_6_10.class));
                break;
            case R.id.book11:
                getActivity().startActivity(new Intent(getContext(), bba_6_11.class));
                break;
            case R.id.book12:
                getActivity().startActivity(new Intent(getContext(), bba_6_12.class));
                break;
            case R.id.book13:
                getActivity().startActivity(new Intent(getContext(), bba_6_13.class));
                break;
            case R.id.book14:
                getActivity().startActivity(new Intent(getContext(), bba_6_14.class));
                break;
            case R.id.book15:
                getActivity().startActivity(new Intent(getContext(), bba_6_15.class));
                break;
            case R.id.book16:
                getActivity().startActivity(new Intent(getContext(), bba_6_16.class));
                break;
            case R.id.book17:
                getActivity().startActivity(new Intent(getContext(), bba_6_17.class));
                break;
            case R.id.book18:
                getActivity().startActivity(new Intent(getContext(), bba_6_18.class));
                break;
            case R.id.book19:
                getActivity().startActivity(new Intent(getContext(), bba_6_19.class));
                break;
            case R.id.book20:
                getActivity().startActivity(new Intent(getContext(), bba_6_20.class));
                break;
            case R.id.book21:
                getActivity().startActivity(new Intent(getContext(), bba_6_21.class));
                break;
        }
    }
}
