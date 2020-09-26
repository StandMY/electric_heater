package com.example.naroro.electricHeater.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.base.BaseActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.naroro.electricHeater.Activity.LoginActivity.newToken;
import static com.example.naroro.electricHeater.utils.Url.addeviceUrl;


public class AddDeviceActivity extends BaseActivity {

    private TextView title_text;
    private EditText deviceId;
    private EditText deviceName;
    private Button back;
    private Button addDevice;
    private ImageView scanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        title_text = findViewById(R.id.title_textView);
        back = findViewById(R.id.bt_back);
        scanner = findViewById(R.id.scanner_device_id);
        deviceId = findViewById(R.id.new_device_id);
        deviceName = findViewById(R.id.new_device_name);
        addDevice = findViewById(R.id.add_device_submit);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDeviceActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        title_text.setText("添加设备");

        //扫描条形码功能
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(AddDeviceActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCaptureActivity(ScannerActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描设备ID"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });

        addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("AddDevice", "onClick: ");
                String id = deviceId.getText().toString().trim();
                String name = deviceName.getText().toString().trim();
                Log.e("AddDevice", "onClick: "+id+name+newToken);
                //验证输入合法性
                if(id.length() == 0){
                    Toast.makeText(AddDeviceActivity.this,"请填写设备ID号",Toast.LENGTH_SHORT).show();
                }
                else if(id.length() != 12){
                    Toast.makeText(AddDeviceActivity.this,"设备ID号为12位数字",Toast.LENGTH_SHORT).show();
                }
                else if(name.length() == 0){
                    Toast.makeText(AddDeviceActivity.this,"请填写设备名称",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(newToken.length() == 0){
                        Toast.makeText(AddDeviceActivity.this,"登录信息失效，请重新登录",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.e("AddDevice", "onClick: "+"beforePost");
                        postAddDevice(id,name);
                    }

                }

            }
        });
    }

    /**
     * 向服务器提交设备信息
     * @param id
     * @param name
     */
    private void postAddDevice(String id, String name) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("token",newToken)
                .add("deviceId",id)
                .add("deviceAlias",name)
                .build();
        Log.e("AddDevice", "onClick: "+"duringPost");

        final Request request = new Request.Builder()
                .url(addeviceUrl)
                .post(formBody)
                .build();
        Log.e("AddDevice", "onClick: "+"afterRequest");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调new
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.e("Home+RunningInfo", "run: "+response );
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();

                    } else {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String ReturnInfo = (String) msg.obj;
                Log.e("AddDevice", "handleMessage: "+ReturnInfo);
                addResult(ReturnInfo);
            }
        }
    };

    private void addResult(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String success = jsonObject.getString("success");
            String returnMessage = jsonObject.getString("message");

            if(success.equals("true")){
                Toast.makeText(AddDeviceActivity.this,returnMessage,
                        Toast.LENGTH_LONG).show();
                //返回主页
                Intent intent = new Intent(AddDeviceActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(AddDeviceActivity.this,returnMessage,
                        Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            deviceId.setText(result);
        }


    }
}
