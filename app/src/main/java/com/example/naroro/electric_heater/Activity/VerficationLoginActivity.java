package com.example.naroro.electric_heater.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.naroro.electric_heater.R;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VerficationLoginActivity extends AppCompatActivity {

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
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息", ReturnMessage);
//                final UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
//                final String AA = userBean.getMsg();
                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 */
//                Log.i("MSGhahahha", AA);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication_login);
        getSupportActionBar().hide();

        /**
         * 初始化
         */
        tel = (EditText) findViewById(R.id.tel);
        msg = (Button) findViewById(R.id.get_verfic_code);
        smsCode = (EditText) findViewById(R.id.verfic_code);
        login = (Button) findViewById(R.id.btn_login);

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tel1 = tel.getText().toString().trim();
                postMsgRequest(tel1);
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
                    .url("http://192.168.1.164:8080/msgvalidatelogin?")
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
                .url("http://192.168.1.164:8080/tokens/login2?")
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
