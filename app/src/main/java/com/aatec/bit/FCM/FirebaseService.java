package com.aatec.bit.FCM;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.aatec.bit.Home_Screen;
import com.aatec.bit.Models.Model_Device;
import com.aatec.bit.R;
import com.aatec.bit.Sliding_Navigation.Event.Event_section;
import com.aatec.bit.StartUp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class FirebaseService extends FirebaseMessagingService {

    public static final String REQUEST_CODE_NOTICE = "com.aatec.bit.FCM_NOTICE";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reference = db.collection("NotificationHistory");
    public static Bitmap Converter(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (IOException unused) {
            return null;
        }
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        reference.document(remoteMessage.getData().get("path"))
                .collection("Devices").
                document().set(new Model_Device(android.os.Build.MODEL));
        if (remoteMessage.getData().get("type").equals("Notice")) {
            createNotificationNotice(remoteMessage);
        } else if (remoteMessage.getData().get("type").equals("Event")) {
            createNotificationEvent(remoteMessage);
        }
    }

    private void createNotificationEvent(RemoteMessage remoteMessage) {
        Intent notificationEvent = new Intent(getApplicationContext(), Event_section.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationEvent, PendingIntent.FLAG_UPDATE_CURRENT
                | PendingIntent.FLAG_ONE_SHOT);
        int rad = new Random().nextInt();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, StartUp.CHANNEL_ID_EVENT)
                .setSmallIcon(R.drawable.bitnotice)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("body"))
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        String link = remoteMessage.getData().get("link");
        if (!link.isEmpty()) {
            Log.d("String ", link);
            Bitmap bitmap = Converter(remoteMessage.getData().get("link"));
            builder.setLargeIcon(bitmap);
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
        }
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(rad, builder.build());
    }

    private void createNotificationNotice(RemoteMessage remoteMessage) {
        Intent notificationNotice = new Intent(getApplicationContext(), Home_Screen.class);
        notificationNotice.putExtra(REQUEST_CODE_NOTICE, "Ayaan");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationNotice, PendingIntent.FLAG_UPDATE_CURRENT
                | PendingIntent.FLAG_ONE_SHOT);
        int rad = new Random().nextInt();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, StartUp.CHANNEL_ID_NOTICE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.bitnotice)
                .setAutoCancel(true)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentIntent(pendingIntent)
                .setContentText(remoteMessage.getData().get("body"));
        String link = remoteMessage.getData().get("link");
        if (!link.isEmpty()) {
            Log.d("String ", link);
            Bitmap bitmap = Converter(remoteMessage.getData().get("link"));
            builder.setLargeIcon(bitmap);
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
        }
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(rad, builder.build());
    }

}
