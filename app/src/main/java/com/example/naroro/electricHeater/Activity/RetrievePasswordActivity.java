package com.example.naroro.electricHeater.Activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.base.BaseActivity;
import com.example.naroro.electricHeater.databinding.ActivityLoginBinding;

public class RetrievePasswordActivity extends BaseActivity {

    ActivityLoginBinding mBinding;

    private TextView title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_retrieve_password);
        getSupportActionBar().hide();

        title_text = findViewById(R.id.title_textView);

    }

}
