package com.example.naroro.electricHeater.Fragment;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naroro.electricHeater.Activity.AddDeviceActivity;
import com.example.naroro.electricHeater.Activity.DetailsOfDeviceActivity;
import com.example.naroro.electricHeater.Activity.DeviceConnectActivity;
import com.example.naroro.electricHeater.Bean.DeviceInfoBean;
import com.example.naroro.electricHeater.Bean.DeviceList;
import com.example.naroro.electricHeater.Bean.DeviceRunningInfo;
import com.example.naroro.electricHeater.Bean.DeviceShow;
import com.example.naroro.electricHeater.Bean.SettingMessage;
import com.example.naroro.electricHeater.Database.ModeTable;
import com.example.naroro.electricHeater.R;
import com.google.gson.Gson;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.naroro.electricHeater.Activity.LoginActivity.newToken;
import static com.example.naroro.electricHeater.utils.Url.deviceListUrl;
import static com.example.naroro.electricHeater.utils.Url.deviceShowUrl;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private ListView mListView;
    private CheckBox mCheckBox;
    private TextView device_light;
    private TextView device_name;
    private TextView device_model;
    private TextView device_current_temperature;
    private ImageView add_device;

    private boolean isSuccess;
    private List<DeviceList.MessageBean> mMessageBeans;
    private DeviceShow.MessageBean mShowMessageBeans;
    private int userId;
    private String deviceId;
    private String deviceAddress;
    private String deviceName;


    private String TAG = "HomeFragment";
    OkHttpClient okHttpClient = new OkHttpClient();


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: " );
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        LitePal.getDatabase();
        //1. 获取ListView对象
        mListView = root.findViewById(R.id.device_item_lv);
        //mCheckBox = root.findViewById(R.id.device_running_light);
        //device_light = root.findViewById(R.id.device_running_light);
//        device_light.setText("He");
        //device_light.setBackgroundResource(R.drawable.not_running_light);
        device_name = root.findViewById(R.id.device_name);
        device_model = root.findViewById(R.id.device_model);
        device_current_temperature = root.findViewById(R.id.device_current_temperature);

        add_device = root.findViewById(R.id.image_add_device);

        if(newToken!=null){
            getDeviceList();
        }

        //跳转至添加设备界面
        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDeviceActivity.class);
                intent.putExtra("newToken",newToken);
                startActivity(intent);
            }
        });

        //跳转至设备详情界面
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListView listView = (ListView) parent;
                HashMap<String, Object> map = (HashMap<String, Object>) listView.getItemAtPosition(position);
                String deviceId = (String) map.get("device_id");
                String deviceName = (String) map.get("device_name");
                String deviceModeName = (String) map.get("device_mode");
                try{
                    int deviceNowTemp = (int) map.get("deviceNowTemp");
                    Intent intent = new Intent(getActivity(), DetailsOfDeviceActivity.class);
                    intent.putExtra("deviceId",deviceId);
                    intent.putExtra("deviceNowTemp",deviceNowTemp);
                    intent.putExtra("deviceName",deviceName);
                    intent.putExtra("deviceMode",deviceModeName);
                    Log.e(TAG, "onItemClick: + devieName"+ deviceName);
                    startActivity(intent);
                }catch (Exception e){
                    Intent intent = new Intent(getActivity(), DeviceConnectActivity.class);
                    intent.putExtra("deviceId",deviceId);
                    intent.putExtra("deviceName",deviceName);
                    Log.e(TAG, "onItemClick: + devieName"+ deviceName);
                    startActivity(intent);
                }

            }
        });

        return root;

    }

    private void notifyDeviceList() {

        //2. 配置LitView数据，数据源
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        List<DeviceInfoBean> devices = DataSupport.findAll(DeviceInfoBean.class);
        //DeviceInfoBean device = DataSupport.findLast(DeviceInfoBean.class);

        DataSupport.deleteAll(DeviceInfoBean.class);
        for(DeviceInfoBean device : devices){
            String deviceId = device.getDeviceId();
            String deviceName = device.getDeviceAlias();
            getDeviceRunningInfo(deviceId);
            List<DeviceRunningInfo> deviceRunningInfos = DataSupport.where("deviceId == ?",deviceId)
                    .find(DeviceRunningInfo.class);
            Map<String,Object> map = new HashMap<>();
            if(deviceRunningInfos.size() != 0) {
                DeviceRunningInfo deviceRunningInfo = deviceRunningInfos.get(0);
//
//                int status = deviceRunningInfo.getNowStatus();
                //TextView mon=root.findViewById(R.id.device_running_light);
//                if(status == 0){
//                    mon.setBackgroundResource(R.drawable.not_running_light);
//                }
//                else if(status == 1){
//                    mon.setBackgroundResource(R.drawable.running_light);
//                }
                //mon.setBackgroundResource(R.drawable.not_running_light);
                map.put("device_id",device.getDeviceId());
                map.put("device_name",deviceName);
                Log.e(TAG, "nowStatus: "+ deviceRunningInfo.getNowStatus());

                map.put("deviceNowTemp",deviceRunningInfo.getNowTemp());
                map.put("deviceNowStatus",deviceRunningInfo.getNowStatus());
                map.put("device_mode",new ModeTable().Mode(deviceRunningInfo.getNowMode()));
                map.put("device_current_temperature",deviceRunningInfo.getNowTemp()+"℃");
            }
            else{
                map.put("device_id",device.getDeviceId());
                map.put("device_name",deviceName);
                map.put("device_current_temperature","连接WiFi");
            }
            list.add(map);
        }

        //3. 配置适配器
        Myadaper adapter = new Myadaper(
                getActivity(),
                list,
                R.layout.device_preview_item,
                new String[] {"deviceNowStatus","device_name","device_mode","device_current_temperature"},
                new int[] {R.id.device_running_light,R.id.device_name,R.id.device_model,R.id.device_current_temperature}

        );

        //4. 将适配器关联到ListView
        mListView.setAdapter(adapter);

    }

    public class Myadaper extends SimpleAdapter{
        public Myadaper(Context context, List<Map<String, Object>> items, int resource, String[] from, int[] to) {
            super(context, items, resource, from, to);

        }
        public View getView(int position, View convertView, ViewGroup parent){
            convertView=super.getView(position, convertView, parent);//获得当前生成的Item
//            List<DeviceRunningInfo> deviceRunningInfos = DataSupport.where("deviceId == ?",deviceId)
//                    .find(DeviceRunningInfo.class);
//            DeviceRunningInfo deviceRunningInfo = deviceRunningInfos.get(0);
//            int status = deviceRunningInfo.getNowStatus();
            TextView mode=(TextView)convertView.findViewById(R.id.device_model);
            TextView running_light=(TextView)convertView.findViewById(R.id.device_running_light);
            if(running_light.getText().toString().equals("1")){
                running_light.setText("");
                running_light.setBackgroundResource(R.drawable.running_light);
            }
            else{
                running_light.setText("");
                running_light.setBackgroundResource(R.drawable.not_running_light);
            }

//            TextView num=(TextView)convertView.findViewById(R.id.num);
//            TextView
//            //判断收支类型并设置数字颜色
//            if(mon.getText().toString().equals("支出"))num.setTextColor(Color.RED);
//            else num.setTextColor(Color.GREEN);


            return convertView;
        }

    }


    /**
     * 获取设备列表
     */
    private void getDeviceList() {

        RequestBody formBody = new FormBody.Builder()
                .add("token",newToken)
                .build();
        Request request = new Request.Builder()
                .url(deviceListUrl)
                .post(formBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调new
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();

                    } else {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获取设备实时运行信息
     */
    private void getDeviceRunningInfo(String deviceId) {
        //OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("token",newToken)
                .add("deviceId",deviceId)
                .build();

        Request request = new Request.Builder()
                .url(deviceShowUrl)
                .post(formBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调new
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        mHandler.obtainMessage(2, response.body().string()).sendToTarget();

                    } else {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String ReturnInfo = (String) msg.obj;
                Log.e(TAG, "handleMessage1: "+ ReturnInfo);
                parseDeviceInfoData(ReturnInfo);
            }else if(msg.what == 2){
                String ReturnInfo = (String) msg.obj;
                Log.e(TAG, "handleMessage2: "+ ReturnInfo);
                parseDeviceRunningInfo(ReturnInfo);
            }
        }
    };

    /**
     * 解析运行数据
     */
    private void parseDeviceRunningInfo(String returnInfo) {
        Gson gson = new Gson();
        DeviceShow deviceShow = gson.fromJson(returnInfo,DeviceShow.class);
        isSuccess = deviceShow.isSuccess();
        mShowMessageBeans =  deviceShow.getMessage();
        if(isSuccess == true){
            if(mShowMessageBeans!= null){
                DeviceRunningInfo deviceRunningInfo = new DeviceRunningInfo();
                deviceRunningInfo.setNowStatus(mShowMessageBeans.getNowStatus());
                deviceRunningInfo.setNowTemp(mShowMessageBeans.getNowTemp());
                deviceRunningInfo.setDeviceId(mShowMessageBeans.getDeviceId());
                deviceRunningInfo.setNowMode(mShowMessageBeans.getWorkMode());
                Log.e("HomeFragment", "running:" +mShowMessageBeans.getNowStatus());
                deviceRunningInfo.save();
            }
            //新添加的设备，未连接wifi
            else{
                Log.e(TAG, "parseDeviceRunningInfo: +else" );
                DeviceRunningInfo deviceRunningInfo = new DeviceRunningInfo();
                deviceRunningInfo.setNowTemp(0);
                deviceRunningInfo.setNowMode(0);
                deviceRunningInfo.save();
            }

        }else {
            Toast.makeText(getActivity(),
                    "登录信息失效，请重新登录",Toast.LENGTH_LONG).show();

        }
    }


    /**
     * 解析设备列表信息
     * @param data
     */
    private void parseDeviceInfoData(String data) {
        Gson gson = new Gson();
        try {
            DeviceList deviceList = gson.fromJson(data,DeviceList.class);
            //取出message中的值
            isSuccess = deviceList.isSuccess();
            mMessageBeans = deviceList.getMessage();

            if(isSuccess == true){
                if(mMessageBeans.size() == 0){
                    Toast.makeText(getContext(),
                            "您所登录的账户暂时没有设备",
                            Toast.LENGTH_LONG).show();

                }else{
                    DataSupport.deleteAll(DeviceInfoBean.class);
                    for (DeviceList.MessageBean messageBean : mMessageBeans){
                        DeviceInfoBean deviceInfo = new DeviceInfoBean();
                        deviceInfo.setUserId(messageBean.getUserId());
                        deviceInfo.setDeviceId(messageBean.getDeviceId());
                        deviceInfo.setDeviceAddress(messageBean.getDeviceAddress());
                        deviceInfo.setDeviceAlias(messageBean.getDeviceAlias());

                        deviceInfo.save();
                        Log.e("HomeFragment", "parseData: "+ messageBean.getUserId());
                    }
                    notifyDeviceList();

                }
            }else {
                Toast.makeText(getActivity(),
                        "登录信息失效，请重新登录",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(),
                    "网络连接异常",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }


}
