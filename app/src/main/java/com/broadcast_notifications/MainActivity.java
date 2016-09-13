package com.broadcast_notifications;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.broadcast_notifications.util.Constant;


public class MainActivity extends AppCompatActivity {
    ToggleButton wifitoggle;
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifitoggle = (ToggleButton) findViewById(R.id.wifitoggle);
        checkbox = (CheckBox) findViewById(R.id.checkbox);

        // WifiManager to control the Wifi Service
        final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        // Capture ToggleButton clicks
        wifitoggle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (wifitoggle.isChecked()) {
                    // Switch On Wifi
                    wifiManager.setWifiEnabled(true);
                } else {
                    // Switch Off Wifi
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        // Capture CheckBox clicks
        checkbox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    // Switch On Broadcast Receiver
                    PackageManager pm = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this, BroadcastManager.class);
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), Constant.BROADCAST_RECEIVER_STARTED, Toast.LENGTH_LONG).show();
                } else {
                    // Switch Off Broadcast Receiver
                    PackageManager pm = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this, BroadcastManager.class);
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), Constant.BROADCAST_RECEIVER_STOPPED, Toast.LENGTH_LONG).show();
                }
            }
        });
        // If Wifi already turned on switch ToggleButton to on
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            wifitoggle.setChecked(true);
        }
    }

    // menu isn't used
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
