package com.example.naroro.electricHeater.Fragment;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
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
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naroro.electricHeater.Bean.DeviceInfoBean;
import com.example.naroro.electricHeater.Bean.DeviceSetting;
import com.example.naroro.electricHeater.Bean.SettingMessage;
import com.example.naroro.electricHeater.R;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.naroro.electricHeater.Activity.LoginActivity.newToken;
import static com.example.naroro.electricHeater.utils.Url.deviceSttingUrl;

public class ModelFragment extends Fragment {

    private ModelViewModel mViewModel;
    private TextView runningMode;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private List<Fragment> pages;
    private RadioButton rb_auto_mode;
    private RadioButton rb_time_mode;
    private int mposition;
    private int number;


    OkHttpClient client= new OkHttpClient();

    public static ModelFragment newInstance() {
        return new ModelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.model_fragment, container, false);

        //绑定控件
        mRadioGroup = view.findViewById(R.id.radio_group);
        mViewPager = view.findViewById(R.id.model_viewpager);
        rb_auto_mode = view.findViewById(R.id.auto_mode);
        rb_time_mode = view.findViewById(R.id.time_mode);
        runningMode = view.findViewById(R.id.running_mode);
        final Spinner spinner = view.findViewById(R.id.device_spinner);

        //设置运行模式

        List<DeviceInfoBean> deviceInfos = DataSupport.findAll(DeviceInfoBean.class);
        if(deviceInfos == null){
            number = 0;
            Toast.makeText(getActivity(), "当前账号下没有设备", Toast.LENGTH_SHORT).show();
        }



        //设备名称下拉框的选择
        List<String> devices_list = new ArrayList<String>();
        List<String> devicesId = new ArrayList<>();
        int i = 0;
        for(DeviceInfoBean deviceInfoBean : deviceInfos){
            String str = deviceInfoBean.getDeviceAlias();
            if(str!=null && !str.equals("") && i< 3){
                devices_list.add(str);
                i++;
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_layout, devices_list);  //创建一个数组适配器
        adapter.setDropDownViewResource(R.layout.spinner_item);     //设置下拉列表框的下拉选项样式
        spinner.setBackgroundColor(0x0);
        spinner.setAdapter(adapter);

        //Spinner加载监听事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text = spinner.getSelectedItem().toString();
                List<DeviceInfoBean> deviceInfoBeans = DataSupport.where("deviceAlias == ?",text)
                        .find(DeviceInfoBean.class);
                DeviceInfoBean deviceInfoBean = deviceInfoBeans.get(0);

                String deviceId = deviceInfoBean == null ? "0" : deviceInfoBean.getDeviceId();
                deviceSetting(deviceId);
                Log.e("ModeFragment", "onItemSelected: "+deviceId );
                //向自动模式Fragment中传递数据
                AutoModeFragment autoModeFragment = new AutoModeFragment();
                Bundle autoBundle = new Bundle();
                autoBundle.putString("deviceId", deviceId);
                autoModeFragment.setArguments(autoBundle);

                //向分段模式Fragment中传递数据
                TimeModeFragment timeModeFragment = new TimeModeFragment();
                Bundle timeBundle = new Bundle();
                timeBundle.putString("deviceId",deviceId);
                timeModeFragment.setArguments(timeBundle);


                //实现了滑动时显示不同的页面
                pages = new ArrayList<>();
                pages.add(autoModeFragment);
                pages.add(timeModeFragment);

                mViewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return pages.get(position);
                    }

                    @Override
                    public int getCount() {
                        return pages.size();
                    }
                });

                //滑动页面时改变RadioButton(被动改变)
                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override//页面滑动后的状态
                    public void onPageSelected(int position) {
                        switch (position) {
                            case 0:
                                rb_auto_mode.setChecked(true);
                                break;
                            case 1:
                                rb_time_mode.setChecked(true);
                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                //点击RadioButton，实现切换ViewPager效果
                mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.auto_mode:
                                mViewPager.setCurrentItem(0, true);
                                break;
                            case R.id.time_mode:
                                mViewPager.setCurrentItem(1, true);
                                break;
                        }
                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pages = new ArrayList<>();
                pages.add(new TimeModeFragment());
                //   pages.add(new TimeModeFragment());
                Log.e("Mode", "onNothingSelected: "+"Hi");
            }
        });




        return view;
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
                Log.i("获取的返回信息", ReturnInfo);
                parsedeviceSetting(ReturnInfo);
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
        DeviceSetting.MessageBean messageBean = deviceSetting.getMessage();
        if(deviceSetting.isSuccess() == true){
            if(messageBean != null){
                DataSupport.deleteAll(SettingMessage.class);
                SettingMessage settingMessage = new SettingMessage();
                settingMessage.setAutoTemp(messageBean.getAutoTemp());
                settingMessage.setAutoDifference(messageBean.getAutoDifference());

                settingMessage.setStartTime1(messageBean.getStartTime1());
                settingMessage.setEndTime1(messageBean.getEndTime1());
                settingMessage.setTemp1(messageBean.getTemp1());
                settingMessage.setDifference1(messageBean.getDifference1());

                settingMessage.setStartTime2(messageBean.getStartTime2());
                settingMessage.setEndTime2(messageBean.getEndTime2());
                settingMessage.setTemp2(messageBean.getTemp2());
                settingMessage.setDifference2(messageBean.getDifference2());

                settingMessage.setStartTime3(messageBean.getStartTime3());
                settingMessage.setEndTime3(messageBean.getEndTime3());
                settingMessage.setTemp3(messageBean.getTemp3());
                settingMessage.setDifference3(messageBean.getDifference3());

                settingMessage.setStartTime4(messageBean.getStartTime4());
                settingMessage.setEndTime4(messageBean.getEndTime4());
                settingMessage.setTemp4(messageBean.getTemp4());
                settingMessage.setDifference4(messageBean.getDifference4());

                settingMessage.setStartTime5(messageBean.getStartTime5());
                settingMessage.setEndTime5(messageBean.getEndTime5());
                settingMessage.setTemp5(messageBean.getTemp5());
                settingMessage.setDifference5(messageBean.getDifference5());

                settingMessage.setStartTime6(messageBean.getStartTime6());
                settingMessage.setEndTime6(messageBean.getEndTime6());
                settingMessage.setTemp6(messageBean.getTemp6());
                settingMessage.setDifference6(messageBean.getDifference6());

                settingMessage.setStartTime7(messageBean.getStartTime7());
                settingMessage.setEndTime7(messageBean.getEndTime7());
                settingMessage.setTemp7(messageBean.getTemp7());
                settingMessage.setDifference7(messageBean.getDifference7());

                settingMessage.save();

            }
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ModelViewModel.class);
        // TODO: Use the ViewModel
        //owner:有lifecycle的对象，监听到变化后，view自动刷新

    }

}
