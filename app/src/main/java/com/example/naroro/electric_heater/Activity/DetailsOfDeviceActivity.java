package com.example.naroro.electric_heater.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.naroro.electric_heater.R;

import java.lang.reflect.Method;

public class DetailsOfDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_device_details);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.device_function,menu);
//        menu.add(Menu.NONE, Menu.FIRST + 1, 0, "菜单1").setIcon(R.drawable.item_rename);
//        menu.add(Menu.NONE, Menu.FIRST + 2, 0, "菜单2").setIcon(R.drawable.item_wifi);
//        menu.add(Menu.NONE, Menu.FIRST + 2, 0, "菜单2").setIcon(R.drawable.item_delete);
//        menu.add(Menu.NONE, Menu.FIRST + 2, 0, "菜单2").setIcon(R.drawable.item_remote);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_rename:
                return true;

            case R.id.item_wifi:
                return true;

            case R.id.item_delete:
                return true;

            case R.id.item_remote:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

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
