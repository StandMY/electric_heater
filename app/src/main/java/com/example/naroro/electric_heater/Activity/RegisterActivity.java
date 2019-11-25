package com.example.naroro.electric_heater.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.naroro.electric_heater.R;
import com.google.gson.Gson;

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
    private String user1,pwd1,tel1;
    private Button reg;

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
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        title_text = findViewById(R.id.title_textView);
        title_text.setText("欢迎注册瀚清");

        /**
         * 初始化
         */
        user = (EditText) findViewById(R.id.user_name);
        tel = (EditText) findViewById(R.id.tel);
        pwd = (EditText) findViewById(R.id.pw);
        reg = (Button) findViewById(R.id.btn_login);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取相关参数
                user1=user.getText().toString().trim();
                pwd1=pwd.getText().toString().trim();
                tel1=tel.getText().toString().trim();
                //通过okhttp发起post请求
                postRequest(user1,pwd1,tel1);
                System.out.println(user1);
            }
        });

    }
    /**
     * post请求后台
     * @param username
     * @param password
     */
    private void postRequest(String username,String password,String telephonenum)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("userName",username)
                .add("userPassword",password)
                .add("telephoneNum",telephonenum)
                .add("userRight","1")
                .add("smsCode","700726")
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

}
