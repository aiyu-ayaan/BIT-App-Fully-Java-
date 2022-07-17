package com.aatec.bit.Sliding_Navigation.Society.SoicetyDis;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aatec.bit.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.platform.MaterialSharedAxis;

public class Society_Discription extends AppCompatActivity {

    private Toolbar toolbar;
    private AppBarLayout app_bar_layout;
    private CollapsingToolbarLayout title;
    private ImageView icon, placeholder;
    private FloatingActionButton linkInsta;

    private WebView dis;

    private static String replaceNewLineWithBreak(String source) {
        return source != null ? source.replaceAll("(?:\n|\r\n)", "<br>") : "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
// set an exit transition
        getWindow().setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soicety_discription);
        toolbar = findViewById(R.id.toolbar3);
        title = findViewById(R.id.title);
        app_bar_layout = findViewById(R.id.app_bar_layout);
        icon = findViewById(R.id.society_Icon);
        placeholder = findViewById(R.id.placeholder);
        linkInsta = findViewById(R.id.linkInsta);
        dis = findViewById(R.id.dis);


//        Setting Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Getting and Setting data from Intent
        GettingandSettingData();

//        App bar
        title.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        title.setCollapsedTitleTextColor(Color.rgb(0, 0, 0));
    }

    private void GettingandSettingData() {
        if (getIntent().hasExtra("societyName") && getIntent().hasExtra("societyDis")
                && getIntent().hasExtra("societyIns") && getIntent().hasExtra("societyImg")
                && getIntent().hasExtra("societyPost")) {

            icon.setImageResource(getIntent().getIntExtra("societyImg", -1));
            placeholder.setImageResource(getIntent().getIntExtra("societyPost", -1));
            title.setTitle(getIntent().getStringExtra("societyName"));
            String Notice = String.valueOf(Html
                    .fromHtml("<![CDATA[<body style=\"text-align:justify;\">"
                            + replaceNewLineWithBreak(getIntent().getStringExtra("societyDis"))
                            + "</body>]]>"));
            dis.loadData(Notice, "text/html; charset=utf-8", "utf-8");
            dis.setBackgroundColor(Color.TRANSPARENT);

//            Link
            linkInsta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(getIntent().getStringExtra("societyIns"));
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("com.instagram.android");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(Society_Discription.this, "Install instagram App ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (!(app_bar_layout.getHeight() - app_bar_layout.getBottom() == 0)) {
            app_bar_layout.setExpanded(true, true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }
}