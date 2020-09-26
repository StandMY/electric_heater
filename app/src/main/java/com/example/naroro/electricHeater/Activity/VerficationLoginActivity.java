package com.example.naroro.electricHeater.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.naroro.electricHeater.utils.Url.verficLoginUrl;
import static com.example.naroro.electricHeater.utils.Url.verficSmsUrl;

public class VerficationLoginActivity extends BaseActivity {

    private EditText tel;
    private EditText smsCode;
    private String tel1,smsCode1;
    private Button msg;
    private Button login;

    final OkHttpClient client = new OkHttpClient();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String ReturnInfo = (String) msg.obj;
                Log.i("获取的返回信息", ReturnInfo);
                verficationSendResult(ReturnInfo);
            }
            else if(msg.what ==2){
                String ReturnInfo = (String) msg.obj;
                Log.i("获取的返回信息", ReturnInfo);
                loginResult(ReturnInfo);
            }
        }
    };

    private void loginResult(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            int resultCode = jsonObject.getInt("code");
            String returnMessage = jsonObject.getString("message");

            if(resultCode == 100){
                Toast.makeText(VerficationLoginActivity.this,returnMessage,
                        Toast.LENGTH_LONG).show();
                //跳转至主界面
                Intent intent = new Intent(VerficationLoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(VerficationLoginActivity.this,returnMessage,
                        Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void verficationSendResult(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String result = jsonObject.getString("success");
            String returnMessage = jsonObject.getString("message");

                Toast.makeText(VerficationLoginActivity.this,returnMessage,
                        Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication_login);
        getSupportActionBar().hide();

        /**
         * 初始化
         */
        tel = findViewById(R.id.tel);
        msg = findViewById(R.id.get_verfic_code);
        smsCode = findViewById(R.id.verfic_code);
        login = findViewById(R.id.btn_login);

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tel1 = tel.getText().toString().trim();
                if(tel1.length()!=11){
                    Toast.makeText(VerficationLoginActivity.this,"手机号长度应为11位",Toast.LENGTH_SHORT).show();
                }
                else {
                    postMsgRequest(tel1);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取相关参数
                tel1=tel.getText().toString().trim();
                smsCode1=smsCode.getText().toString().trim();
                //通过okhttp发起post请求
                postRequest(tel1,smsCode1);
            }
        });

//        bt_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //获取相关参数
//                tel1=tel.getText().toString().trim();
//                smsCode1=smsCode.getText().toString().trim();
//                //通过okhttp发起post请求
//                postRequest(tel1,smsCode1);
//            }
//        });
    }
        /**
         * post请求后台
         * @param telephonenum
         */
        private void postMsgRequest(String telephonenum)  {
            //建立请求表单，添加上传服务器的参数
            RequestBody formBody = new FormBody.Builder()
                    .add("telephoneNum",telephonenum)
                    .build();
            //发起请求
            final Request request = new Request.Builder()
                    .url(verficSmsUrl)
                    .post(formBody)
                    .build();
            //新建一个线程，用于得到服务器响应的参数
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        //回调
                        response = client.newCall(request).execute();
                        if (response.isSuccessful()) {
                            //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                            mHandler.obtainMessage(1, response.body().string()).sendToTarget();
                        } else {
                            throw new IOException("Unexpected code:" + response);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    /**
     * post请求后台
     * @param telephonenum
     */
    private void postRequest(String telephonenum,String smsCode)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("telephoneNum",telephonenum)
                .add("userRight","1")
                .add("smsCode",smsCode)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url(verficLoginUrl)
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        mHandler.obtainMessage(2, response.body().string()).sendToTarget();

                    } else {
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();
                        //throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
