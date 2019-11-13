package com.example.naroro.electric_heater.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.naroro.electric_heater.R;

public class DeviceConnectActivity extends AppCompatActivity {
    private TextView title_text;
    private TextView wifi_account;
    private String ssid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_connect);

        title_text = findViewById(R.id.title_textView);
        wifi_account = findViewById(R.id.wifi_account);
        title_text.setText(R.string.connect_device);

        //获取wifi名称
        ssid = getConnectWifiSsid();
        wifi_account.setText(ssid);
    }

    private String getConnectWifiSsid() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        Log.d("wifiinfo-----", wifiInfo.toString());

        String wifiName = wifiInfo.getExtraInfo().replace("\"","")
                .replace("\"","");
        return wifiName;
    }
}
