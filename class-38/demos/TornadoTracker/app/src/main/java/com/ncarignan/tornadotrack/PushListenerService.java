package com.ncarignan.tornadotrack;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushListenerService extends FirebaseMessagingService {

    static String TAG = "ntornado.pushListenerService";

//    @Override
//    public void onNewToken(String token) {
//        Log.d(TAG, "Refreshed token: " + token);
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // FCM registration token to your app server.
//        sendRegistrationToServer(token);
//    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
        Log.i(TAG, "onMessageReceived: " +  remoteMessage.toString());

//        Intent intent = new Intent("push-notification");
//        Intent intent = new Intent.
//        intent.putExtra("from", remoteMessage.getFrom());
//        intent.putExtra("data", remoteMessage.getData());
//        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationCompat.CATEGORY_REMINDER)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(remoteMessage.getFrom())
                .setContentText(remoteMessage.getSentTime() + "")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

//        notificationManager.notify(2, remoteMessage.getNotification());
    }

}
