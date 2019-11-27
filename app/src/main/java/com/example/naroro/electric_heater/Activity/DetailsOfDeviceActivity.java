package com.example.naroro.electric_heater.Activity;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.naroro.electric_heater.Class.TempCircleProgress;
import com.example.naroro.electric_heater.R;

import java.lang.reflect.Method;

public class DetailsOfDeviceActivity extends AppCompatActivity {

    private TempCircleProgress mTempCircleProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);

        //添加ActionBar中的返回按钮
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置标题的内容
        getSupportActionBar().setTitle("客厅");

        //显示实时温度
        mTempCircleProgress = findViewById(R.id.circle_progress);
        mTempCircleProgress.setValue(25);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.device_function,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
                break;
            case R.id.item_rename:
                break;

            case R.id.item_wifi:
                break;

            case R.id.item_delete:
                break;

            case R.id.item_remote:
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }



}
