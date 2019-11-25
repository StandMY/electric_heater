package com.example.naroro.electric_heater.Fragment;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.naroro.electric_heater.Activity.AddDeviceActivity;
import com.example.naroro.electric_heater.Activity.DetailsOfDeviceActivity;
import com.example.naroro.electric_heater.Activity.RegisterActivity;
import com.example.naroro.electric_heater.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private ListView mListView;
    private CheckBox mCheckBox;
    private TextView device_light;
    private TextView device_name;
    private TextView device_model;
    private TextView device_current_temperature;
    private ImageView add_device;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        mListView = root.findViewById(R.id.device_item_lv);
        mCheckBox = root.findViewById(R.id.device_checked);
        device_light = root.findViewById(R.id.device_light);
        device_name = root.findViewById(R.id.device_name);
        device_model = root.findViewById(R.id.device_model);
        device_current_temperature = root.findViewById(R.id.device_current_temperature);

        add_device = root.findViewById(R.id.image_add_device);

        //配置LitView数据
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("device_name","客厅");
        map.put("device_model","自动模式");
        map.put("device_current_temperature","69℃");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("device_name","卧室");
        map.put("device_model","自动模式");
        map.put("device_current_temperature","60℃");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("device_name","餐厅");
        map.put("device_model","自动模式");
        map.put("device_current_temperature","72℃");
        list.add(map);

        //3. 配饰适配器
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                list,
                R.layout.device_preview_item,
                new String[] {"device_name","device_model","device_current_temperature"},
                new int[] {R.id.device_name,R.id.device_model,R.id.device_current_temperature}

        );

        mListView.setAdapter(adapter);

        //跳转至添加设备界面
        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDeviceActivity.class);
                startActivity(intent);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailsOfDeviceActivity.class);
                startActivity(intent);
            }
        });

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }


}