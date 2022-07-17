package com.aatec.bit.Sliding_Navigation.Holiday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aatec.bit.R;
import com.aatec.bit.Sliding_Navigation.Holiday.Fragment.Holiday_Main.Holiday_main;
import com.aatec.bit.Sliding_Navigation.Holiday.Fragment.Holiday_Res.Holiday_res;
import com.aatec.bit.Sliding_Navigation.Holiday.Pager.Holiday_pager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;

public class Holiday_Container extends Fragment {
    private static Holiday_main main = new Holiday_main();
    private static Holiday_res res = new Holiday_res();
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.holiday_pager, container, false);
        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager = v.findViewById(R.id.viewPagerHol);

//        setup
        Holiday_pager pager = new Holiday_pager(getActivity().getSupportFragmentManager());
        pager.addFragment(main, "Holidays");
        pager.addFragment(res, "Restricted Holiday");
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);

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
