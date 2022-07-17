package com.aatec.bit.Sliding_Navigation.Event.Discription;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aatec.bit.Models.Event_Model;
import com.aatec.bit.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Event_Discription extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView dis;
    private CollapsingToolbarLayout title;
    private ImageView icon;
    private AppBarLayout AppBarLayout;
    private FloatingActionButton linkWeb, linkInsta;

    private static String replaceNewLineWithBreak(String source) {
        return source != null ? source.replaceAll("(?:\n|\r\n)", "<br>") : "";
    }

    private static void placeImage(String society, ImageView view) {
        if (society.equals("RAD")) {
            view.setImageResource(R.drawable.rad);
        } else if (society.equals("DEBSOC")) {
            view.setImageResource(R.drawable.debsoc);
        } else if (society.equals("INTERACT")) {
            view.setImageResource(R.drawable.interactsociety);
        } else if (society.equals("ITS")) {
            view.setImageResource(R.drawable.its);
        } else if (society.equals("BIZWIT")) {
            view.setImageResource(R.drawable.bezwit);
        } else if (society.equals("BITEXL")) {
            view.setImageResource(R.drawable.bit_exl);
        } else if (society.equals("NAPS")) {
            view.setImageResource(R.drawable.naps);
        } else {
            Log.d("Error", "Error Happen");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_discription);
        dis = findViewById(R.id.dis);
        title = findViewById(R.id.title);
        icon = findViewById(R.id.icon);
        AppBarLayout = findViewById(R.id.app_bar_layout);
        linkInsta = findViewById(R.id.linkInsta);
        linkWeb = findViewById(R.id.linkWeb);

//        Toolbar Setup
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


//        Collapsing toolbar Setup
        title.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        title.setCollapsedTitleTextColor(Color.rgb(0, 0, 0));

//        Getting data from intent
        if (getIntent().hasExtra("dis")) {
            Event_Model model = (Event_Model) getIntent().getSerializableExtra("dis");
            title.setTitle(model.getSociety());
            String Notice = String.valueOf(Html
                    .fromHtml("<![CDATA[<body style=\"text-align:justify;\">"
                            + replaceNewLineWithBreak(model.getEvent_body())
                            + "</body>]]>"));
            dis.loadData(Notice, "text/html; charset=utf-8", "utf-8");
            dis.setBackgroundColor(Color.TRANSPARENT);
            dis.setLongClickable(false);
            dis.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            placeImage(model.getSociety(), icon);


//            Setup for Link
            if (!model.getIns_link().isEmpty()) {
                linkInsta.setVisibility(View.VISIBLE);
                linkInsta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(model.getIns_link());
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                        likeIng.setPackage("com.instagram.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/xxx")));
                        }
                    }
                });
            }
            if (!model.getWeb_link().isEmpty()) {
                linkWeb.setVisibility(View.VISIBLE);
                linkWeb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getWeb_link()));
                            startActivity(myIntent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(Event_Discription.this, "No application can handle this request."
                                    + " Please install a webbrowser", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
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
        if (!(AppBarLayout.getHeight() - AppBarLayout.getBottom() == 0)) {
            AppBarLayout.setExpanded(true, true);
            return;
        }
        super.onBackPressed();
    }
}