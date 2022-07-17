package com.aatec.bit.Bottom_Navigation.Syllabus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aatec.bit.Bottom_Navigation.Syllabus.BBA.Main_bba;
import com.aatec.bit.Bottom_Navigation.Syllabus.BCA.Main_bca;
import com.aatec.bit.Bottom_Navigation.Syllabus.MBA.Main_mba;
import com.aatec.bit.Bottom_Navigation.Syllabus.MCA.Main_mca;
import com.aatec.bit.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.transition.platform.MaterialSharedAxis;

public class Syllabus extends Fragment {
    private LinearLayout bca, bba, mba, mca;
    private AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
        View v = inflater.inflate(R.layout.activity_syllabus, container, false);
        bca = v.findViewById(R.id.BCA);
        bba = v.findViewById(R.id.BBA);
        mba = v.findViewById(R.id.MBA);
        mca = v.findViewById(R.id.MCA);



        bca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), Main_bca.class));
            }
        });

        bba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), Main_bba.class));
            }
        });

        mba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), Main_mba.class));
            }
        });

        mca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), Main_mca.class));
            }
        });


//        Ad mob
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return v;
    }
}
