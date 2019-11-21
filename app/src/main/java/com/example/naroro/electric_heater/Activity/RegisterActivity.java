package com.example.naroro.electric_heater.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.naroro.electric_heater.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
    }

}
