package com.broadcast_notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;

import com.broadcast_notifications.util.Constant;

/**
 * Created by Ramesh on 9/12/16.
 */
public class BroadcastManager extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isNetworkAvailable(context)) {
            Notification(context, Constant.WIFI_CONNECTION_ON);
        } else {
            Notification(context, Constant.WIFI_CONNECTION_OFF);
        }
    }

    public void Notification(Context context, String message) {
        String strtitle = context.getString(R.string.notificationtitle);
        Intent intent = new Intent(context, NotificationView.class);
        intent.putExtra(Constant.TITLE, strtitle);
        intent.putExtra(Constant.TEXT, message);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification using NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(message)
                .setContentTitle(context.getString(R.string.notificationtitle))
                .setContentText(message)
                .addAction(R.mipmap.ic_launcher, Constant.ACTION_BUTTON, pIntent)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationmanager.notify(0, builder.build());

    }

    // Check for network availability
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
