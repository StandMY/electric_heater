package com.example.naroro.electric_heater.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
import com.example.naroro.electric_heater.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mBinding;

    private EditText tel;
    private EditText pwd;
    private String pwd1,tel1;
    private Button login;


    final OkHttpClient client = new OkHttpClient();


    private  Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String ReturnInfo = (String) msg.obj;
                Log.i("获取的返回信息", ReturnInfo);
                loginResult(ReturnInfo);
//                final UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
//                final String AA = userBean.getMsg();
                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 */
            }
        }
    };

    private void loginResult(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            int returnCode = jsonObject.getInt("code");
            String returnMessage = jsonObject.getString("message");
            JSONObject returnContent = jsonObject.getJSONObject("content");
            int userId = returnContent.getInt("userId");
            String token = returnContent.getString("token");


            if(returnCode == 100){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(LoginActivity.this,"用户名或密码输入错误",
                        Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        getSupportActionBar().hide();

        mBinding.verficLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.naroro.electric_heater.Activity.VerficationLoginActivity.class);
                startActivity(intent);

            }
        });
        mBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mBinding.forgetPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.naroro.electric_heater.Activity.RetrievePasswordActivity.class);
                startActivity(intent);
            }
        });
        mBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = mBinding.account.getText().toString();
                String password = mBinding.password.getText().toString();

//                if(account.equals("2819") && password.equals("123456")){
//                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else {
//                    Toast.makeText(LoginActivity.this,"Invalid",
//                            Toast.LENGTH_LONG).show();
//                }
            }
        });

        /**
         * 初始化
         */
        tel = (EditText) findViewById(R.id.account);
        pwd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tel1 = tel.getText().toString().trim();
                pwd1 = pwd.getText().toString().trim();
                postRequest(tel1,pwd1);
            }
        });

    }
    /**
     * post请求后台
     * @param telephonenum
     * @param password
     */
    private void postRequest(String telephonenum,String password)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("telephoneNum",telephonenum)
                .add("userPassword",password)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://192.168.1.164:8080/tokens/login1?")
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
