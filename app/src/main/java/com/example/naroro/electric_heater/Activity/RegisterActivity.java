package com.example.naroro.electric_heater.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.naroro.electric_heater.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView title_text;
    private EditText user;
    private EditText tel;
    private EditText pw;
    private EditText repeatPw;
    private EditText address;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        title_text = findViewById(R.id.title_textView);
        title_text.setText("欢迎注册瀚清");
        init();

        //点击注册的响应事件
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void init() {
        user = findViewById(R.id.user_name);
        tel = findViewById(R.id.tel);
        pw = findViewById(R.id.pw);
        repeatPw = findViewById(R.id.repeatPw);
        btn_register = findViewById(R.id.btn_register);
    }


}
