package com.aatec.bit;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.google.firebase.analytics.FirebaseAnalytics;

public class StartUp extends Application {
    public static final String CHANNEL_ID_NOTICE = "Notice";
    public static final String CHANNEL_ID_EVENT = "EVENT";
    public static final String CHANNEL_NOTICE = "Notice Section";
    public static final String CHANNEL_EVENT = "Event Section";
    public static final String CHANNEL_DESC = "";
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

//            Notice
            NotificationChannel channelNotice = new NotificationChannel(CHANNEL_ID_NOTICE, CHANNEL_NOTICE, NotificationManager.IMPORTANCE_HIGH);
            channelNotice.setDescription(CHANNEL_DESC);

            NotificationChannel channelEvent = new NotificationChannel(CHANNEL_ID_EVENT, CHANNEL_EVENT, NotificationManager.IMPORTANCE_HIGH);
            channelEvent.setDescription(CHANNEL_DESC);


            NotificationManager manager = getSystemService(NotificationManager.class);
//Notice
            manager.createNotificationChannel(channelNotice);
//            Event
            manager.createNotificationChannel(channelEvent);
        }
    }
}
