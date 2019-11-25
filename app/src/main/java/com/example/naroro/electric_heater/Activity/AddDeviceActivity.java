package com.example.naroro.electric_heater.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.naroro.electric_heater.R;

public class AddDeviceActivity extends AppCompatActivity {

    private TextView title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        getSupportActionBar().hide();

        title_text = findViewById(R.id.title_textView);
        title_text.setText("添加设备");
    }
}
