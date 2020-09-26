package com.example.naroro.electricHeater.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.naroro.electricHeater.Class.TempCircleProgress;
import com.example.naroro.electricHeater.Database.DeviceSettingTable;
import com.example.naroro.electricHeater.Database.DeviceShowTable;
import com.example.naroro.electricHeater.Database.ModeTable;
import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.base.BaseActivity;
import com.example.naroro.electricHeater.utils.AlertDialogManager;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Method;
import java.util.List;

public class DetailsOfDeviceActivity extends BaseActivity {

    private TempCircleProgress mTempCircleProgress;
    private TextView runningTemp;
    private TextView runningDifference;
    private TextView runningMode;
    private TextView device_running_state;
    private Button onOff;

    private boolean on = true;
    private int nowTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);
        mTempCircleProgress = findViewById(R.id.circle_progress);
        runningTemp = findViewById(R.id.running_temp);
        runningDifference = findViewById(R.id.running_difference);
        runningMode = findViewById(R.id.running_mode);
        onOff = findViewById(R.id.device_toggle_button);
        device_running_state = findViewById(R.id.device_running_state);


        //接收来自设备列表的数据（设备ID）
        Intent intent = getIntent();
        //String deviceId = intent.getStringExtra("deviceId");
        int deviceNowTemp = intent.getIntExtra("deviceNowTemp",0);
        String deviceName = intent.getStringExtra("deviceName");
        String deviceMode = intent.getStringExtra("deviceMode");
        Log.e("Details", "onCreate: deviceName"+ deviceName);
        //Log.e("DetailsOfDevice", "onCreate: "+deviceId );
        int deviceId = 0;

        //添加ActionBar中的返回按钮
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(deviceName);
        runningMode.setText(deviceMode);
        //nowTemp = deviceShowTable.getNowTemp();
        mTempCircleProgress.setValue(deviceNowTemp);
        //设置标题的内容及运行模式的数据
        List<DeviceSettingTable> devices = DataSupport.findAll(DeviceSettingTable.class);
        for (DeviceSettingTable device : devices) {
            if (device.getDeviceId()==deviceId) {
                getSupportActionBar().setTitle("Hello");

                Log.e("Details", "onCreate: "+device.getAutoTemp()+"℃");
                runningTemp.setText(device.getAutoTemp()+"℃");
                runningDifference.setText(device.getAutoDifference()+"℃");


                List<DeviceShowTable> deviceShowTables = DataSupport.findAll(DeviceShowTable.class);
                for(DeviceShowTable deviceShowTable : deviceShowTables){
                    if(deviceShowTable.getDeviceId() == deviceId){
                        runningMode.setText(new ModeTable().Mode(deviceShowTable.getNowMode()));
                        //nowTemp = deviceShowTable.getNowTemp();
                        mTempCircleProgress.setValue(deviceNowTemp);
                    }
                }

                break;
            }
        }

        //开、关机的效果
        onOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(on){
                    on = false;
                    onOff.setText("开始加热");
                    device_running_state.setText("待机中");
                }else {
                    on = true;
                    onOff.setText("停止加热");
                    device_running_state.setText("加热中");

            }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.device_function,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(DetailsOfDeviceActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.item_rename:
                break;

            case R.id.item_wifi:
                Intent intent1 = new Intent(DetailsOfDeviceActivity.this,DeviceConnectActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.item_delete:
                deleteDeviceConfirm();
                break;

            case R.id.item_remote:

                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }

    private void deleteDeviceConfirm() {
        AlertDialogManager.showDialog(DetailsOfDeviceActivity.this,
                "提示",
                "确认删除此设备吗",
                "取消",
                "确定",
                false,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TO DO：删除设备
                        dialog.dismiss();
                    }
                });
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }



}
