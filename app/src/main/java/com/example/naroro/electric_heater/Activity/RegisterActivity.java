package com.example.naroro.electric_heater.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naroro.electric_heater.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView title_text;

    private EditText user;
    private EditText tel;
    private EditText pwd;
    private EditText smsCode;
    private EditText repeatPw;
    private String user1,pwd1,pwd2,tel1,smsCode1;
    private Button reg;
    private Button msg;

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
                RegisterResult(ReturnInfo);
            }
        }
    };

    private void RegisterResult(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String success = jsonObject.getString("success");
            String returnMessage = jsonObject.getString("message");

            if(success.equals("true")){
                Toast.makeText(RegisterActivity.this,returnMessage,
                        Toast.LENGTH_LONG).show();
                //返回主页
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(RegisterActivity.this,returnMessage,
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

            if(result.equals("true")){
                Toast.makeText(RegisterActivity.this,returnMessage,
                        Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        title_text = findViewById(R.id.title_textView);
        title_text.setText("欢迎注册瀚清");

        /**
         * 初始化
         */
        user =  findViewById(R.id.user_name);
        tel = findViewById(R.id.tel);
        pwd = findViewById(R.id.pw);
        repeatPw = findViewById(R.id.repeatPw);
        reg = findViewById(R.id.btn_register);
        msg = findViewById(R.id.get_verfic_code);
        smsCode = findViewById(R.id.verfic_code);

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tel1 = tel.getText().toString().trim();
                if(tel1.length()!=11){
                    Toast.makeText(RegisterActivity.this,"手机号长度应为11位",Toast.LENGTH_SHORT).show();
                }
                else{
                    postMsgRequest(tel1);
                }

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取相关参数
                user1=user.getText().toString().trim();
                pwd1=pwd.getText().toString().trim();
                pwd2=repeatPw.getText().toString().trim();
                tel1=tel.getText().toString().trim();
                smsCode1=smsCode.getText().toString().trim();

                //验证输入合法性
                if(user1.length() == 0){
                    Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(user1.length() >= 11){
                    Toast.makeText(RegisterActivity.this,"用户名长度应在1-11位之间",Toast.LENGTH_SHORT).show();
                }
                else if(tel.length() != 11){
                    Toast.makeText(RegisterActivity.this,"手机号码长度应为11位",Toast.LENGTH_SHORT).show();
                }
                //这里比较两个密码是否相同
                else if((pwd1).equals(pwd2)){
                    if((pwd1.length()<=8) && (pwd1.length()!=0)){
                        //通过okhttp发起post请求
                        postRequest(user1,pwd1,tel1,smsCode1);
                        System.out.println(user1);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"密码的长度应该不多于8位",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    /**
     * post短信请求后台验证码
     * @param telephonenum
     */
    private void postMsgRequest(String telephonenum)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("telephoneNum",telephonenum)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://192.168.1.164:8080/msgvalidate?")
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
     * @param username
     * @param password
     * @param telephonenum
     */
    private void postRequest(String username,String password,String telephonenum,String smsCode)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("userName",username)
                .add("userPassword",password)
                .add("telephoneNum",telephonenum)
                .add("userRight","1")
                .add("smsCode",smsCode)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://192.168.1.164:8080/register?")
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
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
