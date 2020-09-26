package com.example.naroro.electricHeater;

import android.app.Application;
import android.content.Context;

import com.aliyun.iot.aep.sdk.IoTSmart;
import com.aliyun.iot.aep.sdk.framework.AApplication;
import com.example.naroro.electricHeater.utils.OKHttpUtil;

import org.litepal.LitePal;

import androidx.multidex.MultiDex;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

        //IoTSmart.init(this); //初始化，App须继承自AApplication，否则会报错

        //网络参数初始化
        OKHttpUtil.init();

        //添加LitePal数据库
        context = getApplicationContext();
        LitePal.initialize(context);

    }
}
