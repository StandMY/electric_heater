package com.example.naroro.electric_heater.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.naroro.electric_heater.Activity.RegisterActivity;
import com.example.naroro.electric_heater.R;

public class VerficationLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication_login);


        TextView register = (TextView) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerficationLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }


}
