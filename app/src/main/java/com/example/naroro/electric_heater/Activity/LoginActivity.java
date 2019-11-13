package com.example.naroro.electric_heater.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.naroro.electric_heater.Class.BaseActivity;
import com.example.naroro.electric_heater.R;

public class LoginActivity extends BaseActivity {

    //获取界面上的控件
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    private TextView verfic_login;
    private TextView register;
    private TextView forget_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取登录信息控件的实例
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        //获取登录界面其他信息的控件的实例
        verfic_login = findViewById(R.id.verfic_login);
        register = findViewById(R.id.register);
        forget_pw = findViewById(R.id.forget_pw);


        verfic_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.naroro.electric_heater.Activity.VerficationLoginActivity.class);
                startActivity(intent);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forget_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.naroro.electric_heater.Activity.RetrievePasswordActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.equals("2819") && password.equals("123456")){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"Invalid",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
