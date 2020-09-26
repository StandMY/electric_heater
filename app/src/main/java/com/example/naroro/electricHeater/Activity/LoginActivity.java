package com.example.naroro.electricHeater.Activity;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.naroro.electricHeater.base.BaseActivity;
import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.databinding.ActivityLoginBinding;

import org.json.JSONObject;

import java.io.IOException;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.naroro.electricHeater.utils.Url.loginUrl;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding mBinding;
    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor mEditor;
    private String account,password;

    public static String newToken;

    OkHttpClient client= new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        mSharedPreferences = this.getSharedPreferences("userInfo",MODE_PRIVATE);
        getSupportActionBar().hide();


        //判断是否自动登录
        String token = mSharedPreferences.getString("token","");
        if(!TextUtils.isEmpty(token)){
            newToken = mSharedPreferences.getInt("userId",0)
                    +"_" +mSharedPreferences.getString("token","");
            Log.e("Login", "newToken: "+ newToken);
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("token",newToken);
            startActivity(intent);
            finish();
        }

        mBinding.verficLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.naroro.electricHeater.Activity.VerficationLoginActivity.class);
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
                Intent intent = new Intent(LoginActivity.this, com.example.naroro.electricHeater.Activity.RetrievePasswordActivity.class);
                startActivity(intent);
            }
        });

        mBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = mBinding.account.getText().toString().trim();
                password = mBinding.password.getText().toString().trim();
                postRequest(account,password);
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
                .url(loginUrl)
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调new
                    response = client.newCall(request).execute();
                    Log.e("111", Integer.toString(response.code()));
                    if (response.isSuccessful()) {

                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();

                    } else {
                        //mHandler.obtainMessage(1, response.body().string()).sendToTarget();
                        //throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private  Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String ReturnInfo = (String) msg.obj;
                Log.i("获取的返回信息", ReturnInfo);
                loginResult(ReturnInfo);
            }
        }
    };

    private void loginResult(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            int returnCode = jsonObject.getInt("code");
            String returnMessage = jsonObject.getString("message");
            String content = jsonObject.getString("content");
            Log.e("LoginActivity", "loginResult: ");
            if(returnCode == 100){
                JSONObject returnContent = new JSONObject(content);
                int userId = returnContent.getInt("userId");
                String token = returnContent.getString("token");
                mEditor = mSharedPreferences.edit();
                mEditor.putBoolean("isLogin",true);
                mEditor.putString("account",account);
                mEditor.putString("password",password);
                mEditor.putInt("userId",userId);
                mEditor.putString("token",token);
                mEditor.commit();
                newToken = userId +"_" +token;
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(LoginActivity.this,returnMessage,
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
