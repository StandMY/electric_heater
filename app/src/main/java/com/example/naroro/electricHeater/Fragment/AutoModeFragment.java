package com.example.naroro.electricHeater.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.Spinner;

import android.widget.Toast;

import com.example.naroro.electricHeater.Bean.DeviceEditSetting;
import com.example.naroro.electricHeater.Bean.DeviceSetting;
import com.example.naroro.electricHeater.Bean.SettingMessage;
import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.utils.AlertDialogManager;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.naroro.electricHeater.Activity.LoginActivity.newToken;
import static com.example.naroro.electricHeater.utils.Url.deviceSttingUrl;
import static com.example.naroro.electricHeater.utils.Url.editdeviceUrl;

public class AutoModeFragment extends Fragment {

    private Spinner mTemperaturePicker;
    private Spinner mTemperDifferPicker;
    private Button apply;


    private int newTemp;
    private int newDifference;

    private static String TAG = "AutoModeFragment";





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto_model, container, false);

        //绑定控件(ButterKnife与spinner.serSelection冲突？)
        mTemperaturePicker = view.findViewById(R.id.temperature_picker);
        mTemperDifferPicker = view.findViewById(R.id.temper_differ_picker);
        apply = view.findViewById(R.id.auto_mode_apply);

        String deviceId = getArguments().getString("deviceId");
        Log.e(TAG, "onCreateView: "+deviceId);

        if(deviceId.equals("0")){
            mTemperaturePicker.setSelection(0);
            mTemperDifferPicker.setSelection(0);
        }
        else{
            //获得该设备的设置数据
            deviceSetting(deviceId);
            //读取设置的数值
            readData();
        }


        //保存修改的设置数据
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deviceId=="0"){
                    Toast.makeText(getActivity(),"当前并没有选中的设备",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    SettingMessage settingMessage = new SettingMessage();
                    settingMessage.setAutoTemp(newTemp);
                    settingMessage.setAutoDifference(newDifference);
                    settingMessage.updateAll("deviceId = ?",deviceId);
                    Log.e(TAG, "newTemp: "+ newTemp );
                    Log.e(TAG, "newDifference: "+ newDifference);

                    postAutoModeSetting(deviceId,String.valueOf(newTemp),String.valueOf(newDifference));

                }

            }
        });

        return view;
    }

    /**
     * 提交修改的自动模式参数至后台
     * @param newTemp
     * @param newDifference
     */
    private void postAutoModeSetting(String deviceId,String newTemp, String newDifference) {
        //建立请求表单，添加上传服务器的参数
        OkHttpClient client= new OkHttpClient();
        Log.e(TAG, "difference: "+ newDifference);
        RequestBody formBody = new FormBody.Builder()
                .add("token",newToken)
                .add("deviceId",deviceId)
                .add("autoTemp",newTemp)
                .add("autoDifference",newDifference)
                .build();
        Log.e(TAG, "token: "+ newToken);

        //发起请求
        final Request request = new Request.Builder()
                .url(editdeviceUrl)
                .post(formBody)
                .build();
        Log.e(TAG, "run: 2");
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response ;
                try {
                    //回调new
                    response = client.newCall(request).execute();
                    Log.e(TAG, "run: 2"+response.isSuccessful()+response.body().toString());
                    if (response.isSuccessful()) {

                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        mHandler.obtainMessage(2, response.body().string()).sendToTarget();

                        Log.e(TAG, "run: 2"+ response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获得该设备的设置数据
     * @param deviceId 设备ID
     */
    private void deviceSetting(String deviceId) {
        OkHttpClient client= new OkHttpClient();
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("token",newToken)
                .add("deviceId",deviceId)
                .build();
        Log.e(TAG, "token: "+ newToken);
        Log.e(TAG, "deviceId: "+ deviceId);
        //发起请求
        final Request request = new Request.Builder()
                .url(deviceSttingUrl)
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response ;
                try {
                    //回调new
                    response = client.newCall(request).execute();
                    Log.e("111", Integer.toString(response.code()));
                    if (response.isSuccessful()) {

                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String ReturnInfo = (String) msg.obj;
                Log.i("获取的设置信息Automode", ReturnInfo);
                parsedeviceSetting(ReturnInfo);
            }
            else if(msg.what == 2){
                String ReturnInfo = (String) msg.obj;
                Log.i("获取的提交返回信息AutoMode", ReturnInfo);
                parseSettingResult(ReturnInfo);
            }
        }
    };

    /**
     * 提交修改设置后，获取返回结果
     * @param returnInfo
     */
    private void parseSettingResult(String returnInfo) {
        Gson gson = new Gson();
        DeviceEditSetting deviceEditSetting= gson.fromJson(returnInfo,DeviceEditSetting.class);
        //DeviceSetting.MessageBean messageBean = deviceSetting.getMessage();
        if(deviceEditSetting.isSuccess()){
            showDialog();
        }
    }


    /**
         * 解析设备运行数据
         * @param returnInfo 服务器返回信息
         */
        private void parsedeviceSetting(String returnInfo) {
            Gson gson = new Gson();
            DeviceSetting deviceSetting= gson.fromJson(returnInfo,DeviceSetting.class);
            DeviceSetting.MessageBean messageBean = deviceSetting.getMessage();
            if(deviceSetting.isSuccess() == true){
                if(messageBean != null){
//                    DataSupport.deleteAll(SettingMessage.class);
//                    SettingMessage settingMessage = new SettingMessage();
//                    settingMessage.setAutoTemp(messageBean.getAutoTemp());
//                    settingMessage.setAutoDifference(messageBean.getAutoDifference());
//
//                    settingMessage.setStartTime1(messageBean.getStartTime1());
//                    settingMessage.setEndTime1(messageBean.getEndTime1());
//                    settingMessage.setTemp1(messageBean.getTemp1());
//                    settingMessage.setDifference1(messageBean.getDifference1());
//
//                    settingMessage.setStartTime2(messageBean.getStartTime2());
//                    settingMessage.setEndTime2(messageBean.getEndTime2());
//                    settingMessage.setTemp2(messageBean.getTemp2());
//                    settingMessage.setDifference2(messageBean.getDifference2());
//
//                    settingMessage.setStartTime3(messageBean.getStartTime3());
//                    settingMessage.setEndTime3(messageBean.getEndTime3());
//                    settingMessage.setTemp3(messageBean.getTemp3());
//                    settingMessage.setDifference3(messageBean.getDifference3());
//
//                    settingMessage.setStartTime4(messageBean.getStartTime4());
//                    settingMessage.setEndTime4(messageBean.getEndTime4());
//                    settingMessage.setTemp4(messageBean.getTemp4());
//                    settingMessage.setDifference4(messageBean.getDifference4());
//
//                    settingMessage.setStartTime5(messageBean.getStartTime5());
//                    settingMessage.setEndTime5(messageBean.getEndTime5());
//                    settingMessage.setTemp5(messageBean.getTemp5());
//                    settingMessage.setDifference5(messageBean.getDifference5());
//
//                    settingMessage.setStartTime6(messageBean.getStartTime6());
//                    settingMessage.setEndTime6(messageBean.getEndTime6());
//                    settingMessage.setTemp6(messageBean.getTemp6());
//                    settingMessage.setDifference6(messageBean.getDifference6());
//
//                    settingMessage.setStartTime7(messageBean.getStartTime7());
//                    settingMessage.setEndTime7(messageBean.getEndTime7());
//                    settingMessage.setTemp7(messageBean.getTemp7());
//                    settingMessage.setDifference7(messageBean.getDifference7());
//
//                    settingMessage.save();
                    mTemperaturePicker.setSelection(messageBean.getAutoTemp());
                    mTemperDifferPicker.setSelection(messageBean.getAutoDifference());
                }
            }
        }
    
    
    /**
     * 获取设置的温度以及温差的数据
     */
    private void readData() {
        //获取设置的温度
        mTemperaturePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTemp = position;

                Log.e(TAG, "onItemSelected: " + newTemp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mTemperDifferPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                newDifference = position;
                Log.e(TAG, "onItemSelected: " + newDifference);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * 成功保存后，点击应用按钮提示用户
     */
    private void showDialog() {
        AlertDialogManager.showDialog(getActivity(),
                "提示",
                "保存成功！设置温度为"+newTemp+"℃，温差为"+newDifference+"℃",
                null,
                "确定",
                false,
                null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }

}
