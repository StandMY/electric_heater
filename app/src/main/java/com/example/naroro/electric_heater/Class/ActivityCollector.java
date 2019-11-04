package com.example.naroro.electric_heater.Class;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/*
 **ActivityCollector类用于管理所有的活动
 */
public class ActivityCollector {

    //新建Activity的列表
    public static List<Activity> activities = new ArrayList<>();

    //添加活动
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    //删除活动
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    //关闭所有活动，可在强制下线功能中使用
    public static void finishAll(){
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
