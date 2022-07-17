package com.aatec.bit.Bottom_Navigation.Notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aatec.bit.Bottom_Navigation.Notice.NoticeNew.Notice_New;
import com.aatec.bit.Bottom_Navigation.Notice.NoticeOld.Notice_Old;
import com.aatec.bit.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;

public class Frag_Notice extends Fragment {
    private static final Notice_New notice_new = new Notice_New();
    private static final Notice_Old notice_old = new Notice_Old();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_notice, container, false);

        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager = v.findViewById(R.id.viewPagerHol);

        //        Setting View Pager
        setViewPager();


//Ad mob
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

    private void setViewPager() {
        NoticePager pager = new NoticePager(getActivity().getSupportFragmentManager());
        pager.addFragment(notice_new, "New");
        pager.addFragment(notice_old, "Old");
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);
    }
}
