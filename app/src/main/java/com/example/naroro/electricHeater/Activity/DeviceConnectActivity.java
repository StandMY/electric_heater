package com.example.naroro.electricHeater.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aliyun.alink.business.devicecenter.api.discovery.IOnDeviceTokenGetListener;
import com.aliyun.alink.business.devicecenter.api.discovery.LocalDeviceMgr;
import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.base.BaseActivity;
import com.example.naroro.electricHeater.utils.WifiConnector;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.Bundle;
import com.aliyun.iot.aep.component.router.Router;


public class DeviceConnectActivity extends BaseActivity {
    private TextView title_text;
    private TextView wifi_account;
    private TextView deviceName;
    private EditText wifiPw;
    private Button back;
    private Button connect;
    private String ssid;
    private String pw;
    private String tokenId;
    //获取环境Wifi名称
    WifiManager mWifiManager;
    WifiConnector mWifiConnector;

    //连接暖气设备
    private Context mContext;
    private boolean isConnecting = false;

    private Thread mThreadClient = null;
    private Thread mThreadServer = null;
    private Socket mSocketServer = null;
    private Socket mSocketClient = null;
    static BufferedReader mBufferedReaderServer	= null;
    static PrintWriter mPrintWriterServer = null;
    static BufferedReader mBufferedReaderClient	= null;
    static PrintWriter mPrintWriterClient = null;
    private String recvMessageClient = "";
    private String recvMessageServer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_device_connect);
        getSupportActionBar().hide();

        mContext = this;
        title_text = findViewById(R.id.device_connect);
        wifi_account = findViewById(R.id.wifi_account);
        back = findViewById(R.id.bt_back_to_detail);
        wifiPw = findViewById(R.id.wifi_pw);
        connect = findViewById(R.id.connect);
        title_text.setText(R.string.connect_device);
        deviceName = findViewById(R.id.device_name);

        //接收来自设备列表的数据（设备ID）
        Intent intent = getIntent();
        String deviceId = intent.getStringExtra("deviceId");



        //获取环境wifi名称及密码
        mWifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        mWifiConnector = new WifiConnector(mWifiManager);

        try{
            ssid = getConnectWifiSsid();
            wifi_account.setText(ssid);
            pw = wifiPw.getText().toString();
        }catch(Exception e){
            wifi_account.setText("当前并未连接WiFi");
            Toast.makeText(DeviceConnectActivity.this,
                    "请连接无线网络",Toast.LENGTH_SHORT).show();
        }


        mWifiConnector.mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // 操作界面
                Toast.makeText(DeviceConnectActivity.this,""+msg.obj,
                        Toast.LENGTH_LONG).show();
                super.handleMessage(msg);
            }

        };

//        //连接暖气设备
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()   // or .detectAll() for all detectable problems
//                .penaltyLog()
//                .build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects()
//                .penaltyLog()
//                .penaltyDeath()
//                .build());

        //连接设备
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mWifiConnector.connect("EHD201", "12345678",
                            WifiConnector.WifiCipherType.WIFICIPHER_WPA);
                } catch (Exception e) {
                    Toast.makeText(DeviceConnectActivity.this,e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceConnectActivity.this,DetailsOfDeviceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //获取token代码
        LocalDeviceMgr.getInstance().getDeviceToken("a1Qys7q9UXL", deviceId, 60*1000, new IOnDeviceTokenGetListener() {
            @Override
            public void onSuccess(String token) {
                // 拿到绑定需要的token
                //TODO 用户根据具体业务场景调用
                tokenId = token;
            }

            @Override
            public void onFail(String reason) {
            }
        });

        //启动插件
//        String code = "link://router/connectConfig";
//        Bundle bundle = new Bundle();
//        bundle.putString("productKey","a1Qys7q9UXL"); // 传入productKey和deviceName（使用deviceID代替）
//        bundle.putString("deviceName",deviceId);
//        bundle.putString("token",tokenId);
//        bundle.putString("addDeviceFrom","ROUTER");
//        Router.getInstance().toUrlForResult(this, code, 1, bundle);

    }

    // 接收配网结果
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == 1 && null != data) {
//            if (Activity.RESULT_OK != resultCode) {
//                // 配网失败
//                Toast.makeText(DeviceConnectActivity.this,"配网失败",Toast.LENGTH_LONG).show();
//                return;
//            }
//
//            String productKey = data.getStringExtra("productKey");
//            String deviceName = data.getStringExtra("deviceName");
//            Toast.makeText(DeviceConnectActivity.this,"配网成功"+resultCode+" "+resultCode+" "+data,Toast.LENGTH_LONG).show();
//        }
//
//    }

    /**
     * 获取当前所连接Wifi的名称
     * @return
     */
    private String getConnectWifiSsid() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        String wifiName = wifiInfo.getExtraInfo().replace("\"","")
                .replace("\"","");
        return wifiName;
    }
}
