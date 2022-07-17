package com.aatec.bit.Sliding_Navigation.Holiday;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.aatec.bit.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Holiday extends AppCompatActivity {
    private static Holiday_Container pager = new Holiday_Container();
    private Toolbar toolbar;
    private FragmentManager fm;
    private CollapsingToolbarLayout title;
    private AppBarLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        title = findViewById(R.id.title);
        layout = findViewById(R.id.app_bar_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//      Collapsing toolbar
        title.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        title.setCollapsedTitleTextColor(Color.rgb(0, 0, 0));

//        Fragment
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.showHol, pager, "PagerPage").commit();


//        When theme Change
        whenThemeChange();
    }

    //    When theme change
    private void whenThemeChange() {
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    fm.beginTransaction().remove(fragment).commit();
                }
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    fm.beginTransaction().remove(fragment).commit();
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!(layout.getHeight() - layout.getBottom() == 0)) {
            layout.setExpanded(true, true);
            return;
        }
        super.onBackPressed();
    }
}