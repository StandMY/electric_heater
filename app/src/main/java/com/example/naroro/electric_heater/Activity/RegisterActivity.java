package com.example.naroro.electric_heater.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.naroro.electric_heater.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        title_text = findViewById(R.id.title_textView);
        title_text.setText(R.string.welcome_register);
    }

}
