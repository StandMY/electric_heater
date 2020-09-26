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
import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.utils.AlertDialogManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class TimeModeFragment extends Fragment {


    private Button mTimeModeApply;
    private Spinner modeNumber;

    private Spinner mFirstStartHour;
    private Spinner mFirstStartMinute;
    private Spinner mFirstEndHour;
    private Spinner mFirstEndMinute;
    private Spinner mFirstTemperature;
    private Spinner mFirstDifference;

    private Spinner mSecondStartHour;
    private Spinner mSecondStartMinute;
    private Spinner mSecondEndHour;
    private Spinner mSecondEndMinute;
    private Spinner mSecondTemperature;
    private Spinner mSecondDifference;

    private Spinner mThirdStartHour;
    private Spinner mThirdStartMinute;
    private Spinner mThirdEndHour;
    private Spinner mThirdEndMinute;
    private Spinner mThirdTemperature;
    private Spinner mThirdDifference;

    private Spinner mFourthStartHour;
    private Spinner mFourthStartMinute;
    private Spinner mFourthEndHour;
    private Spinner mFourthEndMinute;
    private Spinner mFourthTemperature;
    private Spinner mFourthDifference;

    private Spinner mFifthStartHour;
    private Spinner mFifthStartMinute;
    private Spinner mFifthEndHour;
    private Spinner mFifthEndMinute;
    private Spinner mFifthTemperature;
    private Spinner mFifthDifference;

    private Spinner mSixthStartHour;
    private Spinner mSixthStartMinute;
    private Spinner mSixthEndHour;
    private Spinner mSixthEndMinute;
    private Spinner mSixthTemperature;
    private Spinner mSixthDifference;

    private Spinner mSeventhStartHour;
    private Spinner mSeventhStartMinute;
    private Spinner mSeventhEndHour;
    private Spinner mSeventhEndMinute;
    private Spinner mSeventhTemperature;
    private Spinner mSeventhDifference;

    private int firstStartHour = 0;
    private int firstStartMinute = 0;
    private int firstEndHour = 0;
    private int firstEndMinute = 0;
    private int firstTemperature = 0;
    private int firstDifference = 0;

    private int secondStartHour;
    private int secondStartMinute;
    private int secondEndHour;
    private int secondEndMinute;
    private int secondTemperature;
    private int secondDifference;

    private int thirdStartHour;
    private int thirdStartMinute;
    private int thirdEndHour;
    private int thirdEndMinute;
    private int thirdTemperature;
    private int thirdDifference;

    private int fourthStartHour;
    private int fourthStartMinute;
    private int fourthEndHour;
    private int fourthEndMinute;
    private int fourthTemperature;
    private int fourthDifference;

    private int fifthStartHour;
    private int fifthStartMinute;
    private int fifthEndHour;
    private int fifthEndMinute;
    private int fifthTemperature;
    private int fifthDifference;

    private int sixthStartHour;
    private int sixthStartMinute;
    private int sixthEndHour;
    private int sixthEndMinute;
    private int sixthTemperature;
    private int sixthDifference;

    private int seventhStartHour;
    private int seventhStartMinute;
    private int seventhEndHour;
    private int seventhEndMinute;
    private int seventhTemperature;
    private int seventhDifference;

    private List<String> timeFrag = new ArrayList<>();




    int num = 0 ;

    OkHttpClient client= new OkHttpClient();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time_model, container, false);
        mFirstStartHour = view.findViewById(R.id.first_start_hour);
        mFirstStartMinute = view.findViewById(R.id.first_start_minute);
        mFirstEndHour = view.findViewById(R.id.first_end_hour);
        mFirstEndMinute = view.findViewById(R.id.first_end_minute);
        mFirstTemperature = view.findViewById(R.id.first_temperature);
        mFirstDifference = view.findViewById(R.id.first_difference);

        mSecondStartHour = view.findViewById(R.id.second_start_hour);
        mSecondStartMinute = view.findViewById(R.id.second_start_minute);
        mSecondEndHour = view.findViewById(R.id.second_end_hour);
        mSecondEndMinute = view.findViewById(R.id.second_end_minute);
        mSecondTemperature = view.findViewById(R.id.second_temperature);
        mSecondDifference = view.findViewById(R.id.second_difference);

        mThirdStartHour = view.findViewById(R.id.third_start_hour);
        mThirdStartMinute = view.findViewById(R.id.third_start_minute);
        mThirdEndHour = view.findViewById(R.id.third_end_hour);
        mThirdEndMinute = view.findViewById(R.id.third_end_minute);
        mThirdTemperature = view.findViewById(R.id.third_temperature);
        mThirdDifference = view.findViewById(R.id.third_difference);

        mFourthStartHour = view.findViewById(R.id.fourth_start_hour);
        mFourthStartMinute = view.findViewById(R.id.fourth_start_minute);
        mFourthEndHour = view.findViewById(R.id.fourth_end_hour);
        mFourthEndMinute = view.findViewById(R.id.fourth_end_minute);
        mFourthTemperature = view.findViewById(R.id.fourth_temperature);
        mFourthDifference = view.findViewById(R.id.fourth_difference);

        mFifthStartHour = view.findViewById(R.id.fifth_start_hour);
        mFifthStartMinute = view.findViewById(R.id.fifth_start_minute);
        mFifthEndHour = view.findViewById(R.id.fifth_end_hour);
        mFifthEndMinute = view.findViewById(R.id.fifth_end_minute);
        mFifthTemperature = view.findViewById(R.id.fifth_temperature);
        mFifthDifference = view.findViewById(R.id.fifth_difference);

        mSixthStartHour = view.findViewById(R.id.sixth_start_hour);
        mSixthStartMinute = view.findViewById(R.id.sixth_start_minute);
        mSixthEndHour = view.findViewById(R.id.sixth_end_hour);
        mSixthEndMinute = view.findViewById(R.id.sixth_end_minute);
        mSixthTemperature = view.findViewById(R.id.sixth_temperature);
        mSixthDifference = view.findViewById(R.id.sixth_difference);

        mSeventhStartHour = view.findViewById(R.id.seventh_start_hour);
        mSeventhStartMinute = view.findViewById(R.id.seventh_start_minute);
        mSeventhEndHour = view.findViewById(R.id.seventh_end_hour);
        mSeventhEndMinute = view.findViewById(R.id.seventh_end_minute);
        mSeventhTemperature = view.findViewById(R.id.seventh_temperature);
        mSeventhDifference = view.findViewById(R.id.seventh_difference);

        mTimeModeApply = view.findViewById(R.id.time_mode_apply);
        modeNumber = view.findViewById(R.id.mode_number);

        String deviceId = getArguments().getString("deviceId");

        //设置显示的段数
        modeNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.e("TimeMode", "onCreateView: "+deviceId );

        if (deviceId.equals("0")) {

        } else {
            deviceSetting(deviceId);

        }


            //保存应用
            mTimeModeApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readData();
                    if(timeOrderLegal()){
                        postTimeModeSetting(deviceId);
                    }
                    else{
                        //Toast.makeText(getActivity(), "请检验时间的先后顺序", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            return view;
        }

    /**
     * 检验是否存在不合法的时间，例如起始时间大于结束时间
     */
    private boolean timeOrderLegal() {

        boolean flag = true;//是否重叠标识
        int[][] timeMatrix = new int[timeFrag.size()][2];
        for(int i=1; i<timeFrag.size(); i++) {
            String[] str = timeFrag.get(i).split("-");
            timeMatrix[i][0] = Integer.parseInt(str[0]);
            timeMatrix[i][1] = Integer.parseInt(str[1]);
            if(timeMatrix[i][0] > timeMatrix[i][1]){
                Toast.makeText(getActivity(),
                        "第"+(i+1)+"段时间中，开始时间大于结束时间",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        for(int i=0; i<timeFrag.size(); i++) {
            for(int j=i+1; j<timeFrag.size(); j++) {
                if(timeMatrix[j][0] < timeMatrix[i][1]){
                    Toast.makeText(getActivity(),
                            "第"+(j+1)+"段时间的开始时间小于第"+(i+1)+"段时间的结束时间",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }


        return flag;

    }


    /**
     * 提交修改的分段模式参数至后台
     */
    private void postTimeModeSetting(String deviceId) {
        //建立请求表单，添加上传服务器的参数
        Log.e("TimeMode", "postTimeModeSetting: "+firstStartHour +" "+firstStartMinute);
        RequestBody formBody = new FormBody.Builder()
                .add("token",newToken)
                .add("deviceId",deviceId)
                .add("startTime1",firstStartHour+":"+firstStartMinute)
                .add("endTime1",firstEndHour+":"+firstEndMinute)
                .add("temp1",String.valueOf(firstTemperature))
                .add("difference1",String.valueOf(firstDifference))
                .add("startTime2",secondStartHour+":"+secondStartMinute)
                .add("endTime2",secondEndHour+":"+secondEndMinute)
                .add("temp2",String.valueOf(secondTemperature))
                .add("difference2",String.valueOf(secondDifference))
                .add("startTime3",thirdStartHour+":"+thirdStartMinute)
                .add("endTime3",thirdEndHour+":"+thirdEndMinute)
                .add("temp3",String.valueOf(thirdTemperature))
                .add("difference3",String.valueOf(thirdDifference))
                .add("startTime4",fourthStartHour+":"+fourthStartMinute)
                .add("endTime4",fourthEndHour+":"+fourthEndMinute)
                .add("temp4",String.valueOf(fourthTemperature))
                .add("difference4",String.valueOf(fourthDifference))
                .add("startTime5",fifthStartHour+":"+fifthStartMinute)
                .add("endTime5",fifthEndHour+":"+fifthEndMinute)
                .add("temp5",String.valueOf(fifthTemperature))
                .add("difference5",String.valueOf(fifthDifference))
                .add("startTime6",sixthStartHour+":"+sixthStartMinute)
                .add("endTime6",sixthEndHour+":"+sixthEndMinute)
                .add("temp6",String.valueOf(sixthTemperature))
                .add("difference6",String.valueOf(sixthDifference))
                .add("startTime7",seventhStartHour+":"+seventhStartMinute)
                .add("endTime7",seventhEndHour+":"+seventhEndMinute)
                .add("temp7",String.valueOf(seventhTemperature))
                .add("difference7",String.valueOf(seventhDifference))
                .build();

        //发起请求
        final Request request = new Request.Builder()
                .url(editdeviceUrl)
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

                    if (response.isSuccessful()) {

                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        mHandler.obtainMessage(2, response.body().string()).sendToTarget();

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
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("token",newToken)
                .add("deviceId",deviceId)
                .build();

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
                Log.i("获取的返回信息", ReturnInfo);
                parsedeviceSetting(ReturnInfo);
            }
            else if(msg.what == 2){
                String ReturnInfo = (String) msg.obj;
                Log.i("获取的返回信息2", ReturnInfo);
                parseSettingResult(ReturnInfo);
            }
        }
    };

    /**
     * 解析设备运行数据
     * @param returnInfo 服务器返回信息
     */
    private void parsedeviceSetting(String returnInfo) {
        Gson gson = new Gson();
        DeviceSetting deviceSetting= gson.fromJson(returnInfo,DeviceSetting.class);
        DeviceSetting.MessageBean settingMessage = deviceSetting.getMessage();
        if(deviceSetting.isSuccess() == true){
            if(settingMessage != null){
                //SettingMessage settingMessage = new SettingMessage();
                //时段1
                Log.e("Time1", "onCreateView: "+ settingMessage.getStartTime1());
                startTime1(settingMessage.getStartTime1());
                endTime1(settingMessage.getEndTime1());
                mFirstTemperature.setSelection(firstTemperature=settingMessage.getTemp1());
                mFirstDifference.setSelection(firstDifference=settingMessage.getDifference1());

                //时段2
                startTime2(settingMessage.getStartTime2());
                endTime2(settingMessage.getEndTime2());
                mSecondTemperature.setSelection(secondTemperature=settingMessage.getTemp2());
                mSecondDifference.setSelection(secondDifference=settingMessage.getDifference2());

                //时段3
                startTime3(settingMessage.getStartTime3());
                endTime3(settingMessage.getEndTime3());
                mThirdTemperature.setSelection(thirdTemperature=settingMessage.getTemp3());
                mThirdDifference.setSelection(thirdDifference=settingMessage.getDifference3());

                //时段4
                startTime4(settingMessage.getStartTime4());
                endTime4(settingMessage.getEndTime4());
                mFourthTemperature.setSelection(fourthTemperature=settingMessage.getTemp4());
                mFourthDifference.setSelection(fourthDifference=settingMessage.getDifference4());

                //时段5
                startTime5(settingMessage.getStartTime5());
                endTime5(settingMessage.getEndTime5());
                mFifthTemperature.setSelection(fifthTemperature=settingMessage.getTemp5());
                mFifthDifference.setSelection(fifthDifference=settingMessage.getDifference5());

                //时段6
                startTime6(settingMessage.getStartTime6());
                endTime6(settingMessage.getEndTime6());
                mSixthTemperature.setSelection(sixthTemperature=settingMessage.getTemp6());
                mSixthDifference.setSelection(sixthDifference=settingMessage.getDifference6());

                //时段7
                startTime7(settingMessage.getStartTime7());
                endTime7(settingMessage.getEndTime7());
                mSeventhTemperature.setSelection(seventhTemperature=settingMessage.getTemp7());
                mSeventhDifference.setSelection(seventhDifference=settingMessage.getDifference7());
            }
        }
    }

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
     * 成功保存后，点击应用按钮提示用户
     */
    private void showDialog() {
        AlertDialogManager.showDialog(getActivity(),
                "提示",
                "保存成功！",
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

    /**
     * 获取设置的温度以及温差的数据
     */
    private void readData() {
        Log.e("readdata", "readData: ");
        //获取时段一设置的温度
        firstStartHour = mFirstStartHour.getSelectedItemPosition();
        firstStartMinute = mFirstStartMinute.getSelectedItemPosition();
        firstEndHour = mFirstEndHour.getSelectedItemPosition();
        firstEndMinute = mFirstEndMinute.getSelectedItemPosition();
        firstTemperature = mFirstTemperature.getSelectedItemPosition();
        firstDifference = mFirstDifference.getSelectedItemPosition();

        //获取时段二的设置参数
        secondStartHour = mSecondStartHour.getSelectedItemPosition();
        secondStartMinute = mSecondStartMinute.getSelectedItemPosition();
        secondEndHour = mSecondEndHour.getSelectedItemPosition();
        secondEndMinute = mSecondEndMinute.getSelectedItemPosition();
        secondTemperature = mSecondTemperature.getSelectedItemPosition();
        secondDifference = mSecondDifference.getSelectedItemPosition();

        //获取时段三的设置参数
        thirdStartHour = mThirdStartHour.getSelectedItemPosition();
        thirdStartMinute = mThirdStartMinute.getSelectedItemPosition();
        thirdEndHour = mThirdEndHour.getSelectedItemPosition();
        thirdEndMinute = mThirdEndMinute.getSelectedItemPosition();
        thirdTemperature = mThirdTemperature.getSelectedItemPosition();
        thirdDifference = mThirdDifference.getSelectedItemPosition();

        //获取时段四的设置参数
        fourthStartHour = mFourthStartHour.getSelectedItemPosition();
        fourthStartMinute = mFourthStartMinute.getSelectedItemPosition();
        fourthEndHour = mFourthEndHour.getSelectedItemPosition();
        fourthEndMinute = mFourthEndMinute.getSelectedItemPosition();
        fourthTemperature = mFourthTemperature.getSelectedItemPosition();
        fourthDifference = mFourthDifference.getSelectedItemPosition();

        //获取时段五的设置参数
        fifthStartHour = mFifthStartHour.getSelectedItemPosition();
        fifthStartMinute = mFifthStartMinute.getSelectedItemPosition();
        fifthEndHour = mFifthEndHour.getSelectedItemPosition();
        fifthEndMinute = mFifthEndMinute.getSelectedItemPosition();
        fifthTemperature = mFifthTemperature.getSelectedItemPosition();
        fifthDifference = mFifthDifference.getSelectedItemPosition();

        //获取时段六的设置参数
        sixthStartHour = mSixthStartHour.getSelectedItemPosition();
        sixthStartMinute = mSixthStartMinute.getSelectedItemPosition();
        sixthEndHour = mSixthEndHour.getSelectedItemPosition();
        sixthEndMinute = mSixthEndMinute.getSelectedItemPosition();
        sixthTemperature = mSixthTemperature.getSelectedItemPosition();
        sixthDifference = mSixthDifference.getSelectedItemPosition();

        //获取时段七的设置参数
        seventhStartHour = mSeventhStartHour.getSelectedItemPosition();
        seventhStartMinute = mSeventhStartMinute.getSelectedItemPosition();
        seventhEndHour = mSeventhEndHour.getSelectedItemPosition();
        seventhEndMinute = mSeventhEndMinute.getSelectedItemPosition();
        seventhTemperature = mSeventhTemperature.getSelectedItemPosition();
        seventhDifference = mSeventhDifference.getSelectedItemPosition();

        timeFrag.add((firstStartHour*60+firstStartMinute)+"-"+(firstEndHour*60+firstEndMinute));
        timeFrag.add((secondStartHour*60+secondStartMinute)+"-"+(secondEndHour*60+secondEndMinute));
        timeFrag.add((thirdStartHour*60+thirdStartMinute)+"-"+(thirdEndHour*60+thirdEndMinute));
        timeFrag.add((fourthStartHour*60+fourthStartMinute)+"-"+(fourthEndHour*60+fourthEndMinute));
        timeFrag.add((fifthStartHour*60+fifthStartMinute)+"-"+(fifthEndHour*60+fifthEndMinute));
        timeFrag.add((sixthStartHour*60+sixthStartMinute)+"-"+(sixthEndHour*60+sixthEndMinute));
        timeFrag.add((seventhStartHour*60+seventhStartMinute)+"-"+(seventhEndHour*60+seventhEndMinute));
//        timeFrag.add(secondStartHour+":"+secondStartMinute+"-"+secondEndHour+":"+secondEndMinute);
//        timeFrag.add(thirdStartHour+":"+thirdStartMinute+"-"+thirdEndHour+":"+thirdEndMinute);
//        timeFrag.add(fourthStartHour+":"+fourthStartMinute+"-"+fourthEndHour+":"+fourthEndMinute);
//        timeFrag.add(fifthStartHour+":"+fifthStartMinute+"-"+fifthEndHour+":"+fifthEndMinute);
//        timeFrag.add(sixthStartHour+":"+sixthStartMinute+"-"+sixthEndHour+":"+sixthEndMinute);
//        timeFrag.add(seventhStartHour+":"+seventhStartMinute+"-"+seventhEndHour+":"+seventhEndMinute);


    }

    private void endTime1(String str) {
        String[] strs = str.split(":");
        mFirstEndHour.setSelection(Integer.valueOf(strs[0]));
        mFirstEndMinute.setSelection(Integer.valueOf(strs[1]));
    }

    private void startTime1(String str) {
        String[] strs = str.split(":");
        mFirstStartHour.setSelection(firstStartHour=Integer.valueOf(strs[0]));
        mFirstStartMinute.setSelection(firstStartMinute=Integer.valueOf(strs[1]));
    }

    private void endTime2(String str) {
        String[] strs = str.split(":");
        mSecondEndHour.setSelection(secondEndHour=Integer.valueOf(strs[0]));
        mSecondEndMinute.setSelection(secondEndMinute=Integer.valueOf(strs[1]));
    }

    private void startTime2(String str) {
        String[] strs = str.split(":");
        mSecondStartHour.setSelection(secondStartHour=Integer.valueOf(strs[0]));
        mSecondStartMinute.setSelection(secondStartMinute=Integer.valueOf(strs[1]));
    }

    private void endTime3(String str) {
        String[] strs = str.split(":");
        mThirdEndHour.setSelection(thirdEndHour=Integer.valueOf(strs[0]));
        mThirdEndMinute.setSelection(thirdEndMinute=Integer.valueOf(strs[1]));
    }

    private void startTime3(String str) {
        String[] strs = str.split(":");
        mThirdStartHour.setSelection(thirdStartHour=Integer.valueOf(strs[0]));
        mThirdStartMinute.setSelection(thirdStartMinute=Integer.valueOf(strs[1]));
    }

    private void endTime4(String str) {
        String[] strs = str.split(":");
        mFourthEndHour.setSelection(fourthEndHour=Integer.valueOf(strs[0]));
        mFourthEndMinute.setSelection(fourthEndMinute=Integer.valueOf(strs[1]));
    }

    private void startTime4(String str) {
        String[] strs = str.split(":");
        mFourthStartHour.setSelection(fourthStartHour=Integer.valueOf(strs[0]));
        mFourthStartMinute.setSelection(fourthStartMinute=Integer.valueOf(strs[1]));
    }

    private void endTime5(String str) {
        String[] strs = str.split(":");
        mFifthEndHour.setSelection(fifthEndHour=Integer.valueOf(strs[0]));
        mFifthEndMinute.setSelection(fifthEndMinute=Integer.valueOf(strs[1]));
    }

    private void startTime5(String str) {
        String[] strs = str.split(":");
        mFifthStartHour.setSelection(fifthStartHour=Integer.valueOf(strs[0]));
        mFifthStartMinute.setSelection(fifthStartMinute=Integer.valueOf(strs[1]));
    }

    private void endTime6(String str) {
        String[] strs = str.split(":");
        mSixthEndHour.setSelection(sixthEndHour=Integer.valueOf(strs[0]));
        mSixthEndMinute.setSelection(sixthEndMinute=Integer.valueOf(strs[1]));
    }

    private void startTime6(String str) {
        String[] strs = str.split(":");
        mSixthStartHour.setSelection(sixthStartHour=Integer.valueOf(strs[0]));
        mSixthStartMinute.setSelection(sixthStartMinute=Integer.valueOf(strs[1]));
    }

    private void endTime7(String str) {
        String[] strs = str.split(":");
        mSeventhEndHour.setSelection(seventhEndHour=Integer.valueOf(strs[0]));
        mSeventhEndMinute.setSelection(seventhEndMinute=Integer.valueOf(strs[1]));
    }

    private void startTime7(String str) {
        String[] strs = str.split(":");
        mSeventhStartHour.setSelection(seventhStartHour=Integer.valueOf(strs[0]));
        mSeventhStartMinute.setSelection(seventhStartMinute=Integer.valueOf(strs[1]));
    }


}
