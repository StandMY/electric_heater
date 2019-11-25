package com.example.naroro.electric_heater.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    private TextView title_text;

    private EditText user;
    private EditText tel;
    private EditText pwd;
    private String user1,pwd1;
    private Button reg;

    final OkHttpClient client = new OkHttpClient();


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
        user = (EditText) findViewById(R.id.user);
        tel = (EditText) findViewById(R.id.tel);
        pwd = (EditText) findViewById(R.id.pw);
        reg = (Button) findViewById(R.id.btn_login);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取相关参数
                user1=user.getText().toString().trim();
                pwd1=pwd.getText().toString().trim();
                //通过okhttp发起post请求
                postRequest(user1,pwd1);

            }
        });

    }
    /**
     * post请求后台
     * @param username
     * @param password
     */
    private void postRequest(String username,String password)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("username",user1)
                .add("password",pwd1)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://192.168.1.164:8080/register?")
                .post(formBody)
                .build();
//        //新建一个线程，用于得到服务器响应的参数
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Response response = null;
//                try {
//                    //回调
//                    response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
//                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();
//
//                    } else {
//                        throw new IOException("Unexpected code:" + response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

}
