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

//        Intent intent = new Intent("push-notification");
//        Intent intent = new Intent.
//        intent.putExtra("from", remoteMessage.getFrom());
//        intent.putExtra("data", remoteMessage.getData());
//        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

        Log.i(TAG, "onMessageReceived: " +  remoteMessage.toString());

        // TODO 2: NOTIFICATION : build the notification and pass it your Channels' ID
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), MainActivity.TORNADO_WARNING_CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(remoteMessage.getFrom())
                .setContentText(remoteMessage.toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(9, builder.build());

//        notificationManager.notify(2, remoteMessage.getNotification());
    }

}
