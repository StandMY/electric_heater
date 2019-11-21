package com.example.naroro.electric_heater.Activity;

import android.app.ActionBar;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.naroro.electric_heater.Class.BaseActivity;
import com.example.naroro.electric_heater.R;
import com.example.naroro.electric_heater.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mBinding;
    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        getSupportActionBar().hide();
        //mActionBar = getActionBar();
        //mActionBar.hide();



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
