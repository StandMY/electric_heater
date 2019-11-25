package com.example.naroro.electric_heater.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.naroro.electric_heater.R;
import com.example.naroro.electric_heater.databinding.ActivityLoginBinding;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {


    private static final int GET =1;
    ActivityLoginBinding mBinding;
    private OkHttpClient client = new OkHttpClient();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET:
                    //获取数据
                    Toast.makeText(LoginActivity.this,(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;

            }
        }
    };
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
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
                getMethodTest();
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

    }

    private void getMethodTest() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String result =get("https://raw.githubusercontent.com/square/okhttp/master/README.md");
                    Log.e(TAG, result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    //get请求
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


}
