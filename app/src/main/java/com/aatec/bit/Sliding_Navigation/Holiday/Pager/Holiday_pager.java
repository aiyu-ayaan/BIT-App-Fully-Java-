package com.aatec.bit.Sliding_Navigation.Holiday.Pager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Holiday_pager extends FragmentPagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    public Holiday_pager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    public void addFragment(Fragment fragment,String title) {
        fragments.add(fragment);
        titles.add(title);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
