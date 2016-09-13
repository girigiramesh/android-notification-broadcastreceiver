package com.broadcast_notifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.broadcast_notifications.util.Constant;

/**
 * Created by Ramesh on 9/12/16.
 */
public class NotificationView extends Activity {
    String title;
    String text;
    TextView txttitle;
    TextView txttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificationview);

        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Dismiss Notification
        notificationmanager.cancel(0);

        // Retrive the data from MainActivity.java
        Intent i = getIntent();

        title = i.getStringExtra(Constant.TITLE);
        text = i.getStringExtra(Constant.TEXT);

        txttitle = (TextView) findViewById(R.id.title);
        txttext = (TextView) findViewById(R.id.text);

        txttitle.setText(title);
        txttext.setText(text);
    }
}
