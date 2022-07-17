package com.aatec.bit;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.aatec.bit.Bottom_Navigation.Attendance.Attendance_frag;
import com.aatec.bit.Bottom_Navigation.Erp.Erp;
import com.aatec.bit.Bottom_Navigation.Notice.Frag_Notice;
import com.aatec.bit.Bottom_Navigation.Syllabus.Syllabus;
import com.aatec.bit.DialogBox.Dia_Home;
import com.aatec.bit.FCM.FirebaseService;
import com.aatec.bit.Sliding_Navigation.About_US.AboutUs;
import com.aatec.bit.Sliding_Navigation.Event.Event_section;
import com.aatec.bit.Sliding_Navigation.Holiday.Holiday;
import com.aatec.bit.Sliding_Navigation.Society.Society;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.messaging.FirebaseMessaging;

public class Home_Screen extends AppCompatActivity implements Attendance_frag.setIntValue {
    private static final String TAG = "Error";
    //    Fragment and Components
    private static Syllabus syllabus = new Syllabus();
    private static Erp erp = new Erp();
    private static Frag_Notice notice = new Frag_Notice();
    private static Attendance_frag attendance = new Attendance_frag();
    private static Fragment active;
    private FragmentManager manager;
    private LottieAnimationView anim;


    //  Activity Component
    private Toolbar toolbar;
    private NavigationView sliderNavView;
    private DrawerLayout drawer;
    private FrameLayout frame;
    private BottomNavigationView nav_view;
    private Vibrator vibrator;


    //    Variables
    private long back_pressed = 0;


    //    Firebase and Dialog
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ref = db.collection("Event_dia");
    private String title, subtitle, dis, min;
    private int enable = 0, post = 0, select = 0, reset = 0, limit = 1;
    private FirebaseAnalytics mFirebaseAnalytics;


    private ReviewManager reviewManager;
    private ReviewInfo reviewInfo = null;

    public static void showBadge(Context context, BottomNavigationView bottomNavigationView, @IdRes int itemId, String value, int i) {
        removeBadge(bottomNavigationView, itemId);
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        View badge = LayoutInflater.from(context).inflate(R.layout.layout_badge, bottomNavigationView, false);
        TextView text = badge.findViewById(R.id.badge_text_view);
        text.setText(value);
        itemView.addView(badge);
        text.setVisibility(View.VISIBLE);
        if (i == 0) {
            text.setVisibility(View.INVISIBLE);
        }
    }

    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        if (itemView.getChildCount() == 3) {
            itemView.removeViewAt(2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        toolbar = findViewById(R.id.tootbar);
        sliderNavView = findViewById(R.id.sliderNavView);
        drawer = findViewById(R.id.drawer);
        frame = findViewById(R.id.frame);
        nav_view = findViewById(R.id.nav_bar);
        anim = findViewById(R.id.iconBell);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        FirebaseMessaging.getInstance().subscribeToTopic("news");
//        Setting up Toolbar
        setSupportActionBar(toolbar);
        setTitle("Syllabus");

//        setUpDrawerLayout
        setDrawerLayout();


//      Setting bottom Fragment
        setBottomFragment();


//      Theme Change
        whenThemeChange();


//        Notice Icon
        anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(50);
                Toast.makeText(Home_Screen.this, "This Section Contain Notice Based On BIT Website \n 'https://bitmesra.ac.in'", Toast.LENGTH_SHORT).show();
            }
        });

//        Notification Handel for Notice Fragment
        openNotice();



    }

    //    Set up  Drawer Layout
    private void setDrawerLayout() {
        try {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            sliderNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_event:
                            startActivity(new Intent(Home_Screen.this, Event_section.class));
                            break;
                        case R.id.nav_soc:
                            startActivity(new Intent(Home_Screen.this, Society.class));
                            break;
                        case R.id.nav_hol:
                            startActivity(new Intent(Home_Screen.this, Holiday.class));
                            break;
                        case R.id.nav_bug:
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            String[] recipients = {"ayaan35200@gmail.com"};
                            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                            intent.putExtra(Intent.EXTRA_SUBJECT, "BUG Report 😢");
                            intent.setType("text/html");
                            intent.setPackage("com.google.android.gm");
                            startActivity(Intent.createChooser(intent, "Send mail"));
                            break;
                        case R.id.nav_share:
                            String contain = "Check out BIT Lalput App :";
                            String link = "https://play.google.com/store/apps/details?id=" + getPackageName();
                            Intent share = new Intent();
                            share.setAction(Intent.ACTION_SEND);
                            share.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share BIT APp");
                            share.putExtra(Intent.EXTRA_TEXT, contain + "\n" + link);
                            share.setType("text/plain");
                            startActivity(share);
                            break;
                        case R.id.nav_connect:
                            String linkIns = "https://www.instagram.com/bit_lalpur_app/";
                            Uri uri = Uri.parse(linkIns);
                            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                            likeIng.setPackage("com.instagram.android");
                            try {
                                startActivity(likeIng);
                            } catch (ActivityNotFoundException e) {
                                startActivity(new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("http://instagram.com/xxx")));
                            }
                            break;
                        case R.id.nav_about:
                            startActivity(new Intent(Home_Screen.this, AboutUs.class));
                            break;
                        case R.id.nav_review:
                            reviewManager = ReviewManagerFactory.create(Home_Screen.this);
                            Task<ReviewInfo> request = reviewManager.requestReviewFlow();
                            request.addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    reviewInfo = task.getResult();
                                } else {
                                    Toast.makeText(Home_Screen.this, "Error in Review Info", Toast.LENGTH_SHORT).show();
                                }
                            });
                            open();
                    }

                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //          Setting bottom Fragment
    private void setBottomFragment() {
        try {
//            start up
            active = syllabus;
            manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.frame, syllabus, "1").commit();
            manager.beginTransaction()
                    .add(R.id.frame, erp, "2").hide(erp).commit();
            manager.beginTransaction()
                    .add(R.id.frame, notice, "3").hide(notice).commit();
            manager.beginTransaction()
                    .add(R.id.frame, attendance, "4").hide(attendance).commit();
//             OnClick
            nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_syllabus:
                            anim.setVisibility(View.INVISIBLE);
                            setTitle("Syllabus");
                            manager.beginTransaction()
                                    .hide(active).show(syllabus).commit();
                            active = syllabus;
                            break;
                        case R.id.nav_erp:
                            setTitle("Erp");
                            anim.setVisibility(View.INVISIBLE);
                            manager.beginTransaction()
                                    .hide(active).show(erp).commit();
                            active = erp;
                            break;
                        case R.id.nav_notice:
                            setTitle("Notice");
                            anim.setVisibility(View.VISIBLE);
                            manager.beginTransaction()

                                    .hide(active).show(notice).commit();
                            active = notice;
                            break;
                        case R.id.nav_attendance:
                            setTitle("Attendance Manager");
                            anim.setVisibility(View.INVISIBLE);
                            manager.beginTransaction()
                                    .hide(active).show(attendance).commit();
                            active = attendance;
                    }
                    return true;
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //    When theme change
    private void whenThemeChange() {
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    manager.beginTransaction().remove(fragment).commit();
                }
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    manager.beginTransaction().remove(fragment).commit();
                }
                break;
        }
    }

//    Shared preference

    private void Dialog_handle() {
        try {
            ref.document("HrW1z4fwl4McOjUgHoBS").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    title = documentSnapshot.get("topic").toString();
                    subtitle = documentSnapshot.get("society").toString();
                    dis = documentSnapshot.get("event").toString();
                    select = Integer.parseInt(documentSnapshot.get("select").toString());
                    enable = Integer.parseInt(documentSnapshot.get("enable").toString());
                    post = Integer.parseInt(documentSnapshot.get("post").toString());
                    reset = Integer.parseInt(documentSnapshot.get("reset").toString());
                    min = documentSnapshot.get("min").toString();
                    try {
                        setDialog(title, subtitle, dis, select, enable, post, reset, min);
                    } catch (Exception exception) {
                        Log.d("Error", exception.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error in Dialog", Toast.LENGTH_SHORT).show();
        }

    }

    //    Setting Dialog
    private void setDialog(String title, String subtitle, String dis, int select, int enable, int post, int reset, String min) {
        Dia_Home home = new Dia_Home(title, subtitle, dis, select);
        try {
            if (!TextUtils.isEmpty(min)) {
                int o = Integer.parseInt(min);
                if (o == BuildConfig.VERSION_CODE) {
                    if (post == 1) {
                        loadData();
                        if (limit != enable) {
                            home.show(getSupportFragmentManager(), "Event");
                            limit++;
                            saveData();
                        }
                    } else if (reset == 1) {
                        limit = 1;
                        saveData();
                    }
                } else if (o == 1) {
                    if (post == 1) {
                        loadData();
                        if (limit != enable) {
                            home.show(getSupportFragmentManager(), "Event");
                            limit++;
                            saveData();
                        }
                    } else if (reset == 1) {
                        limit = 1;
                        saveData();
                    }
                }
            }
        } catch (NumberFormatException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveLimit", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("limit", limit);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveLimit", MODE_PRIVATE);
        limit = sharedPreferences.getInt("limit", MODE_PRIVATE);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nav_view.setSelectedItemId(savedInstanceState.getInt("SelectedItemId"));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SelectedItemId", nav_view.getSelectedItemId());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (erp.myweb.canGoBack()) {
            erp.myweb.goBack();
        } else if (active != syllabus) {
            manager.beginTransaction().hide(active).show(syllabus).commit();
            active = syllabus;
            nav_view.setSelectedItemId(R.id.nav_syllabus);
            return;
        } else if   (back_pressed + 1000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(),
                    "Press once again to exit!", Toast.LENGTH_SHORT)
                    .show();
        }
        back_pressed = System.currentTimeMillis();
    }

    private void openNotice() {
        if (getIntent().hasExtra(FirebaseService.REQUEST_CODE_NOTICE)) {
            setTitle("Notice");
            anim.setVisibility(View.VISIBLE);
            manager.beginTransaction().hide(active).show(notice).commit();
            active = notice;
            nav_view.setSelectedItemId(R.id.nav_notice);
        }
    }

    @Override
    public void sendIntValue(int i) {
        //            Setting badge

        try {
            showBadge(this, nav_view, R.id.nav_attendance, String.valueOf(i), i);
        } catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void open() {
        if (reviewInfo != null) {
            Task<Void> flow = reviewManager.launchReviewFlow(Home_Screen.this, reviewInfo);
            flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Home_Screen.this, "This is Experimental Feature for In App Reviews", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Home_Screen.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}