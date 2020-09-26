package com.example.naroro.electricHeater.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * OKHttp封装：核心请求部分
 */
public class OKHttpUtil {

    //静态变量，全局使用
    private static OkHttpClient client = null;

    //初始化OKHttpClient相关参数,在Application文件中调用，可全局使用
    public static void init(){
        if(client == null){
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .writeTimeout(5000, TimeUnit.MILLISECONDS);
            client = builder.build();
        }
    }

    /**
     * 重写get方法
     * @param url
     * @param okHttpCallback
     */
    public static void get(String url, OKHttpCallback okHttpCallback){
        Call call = null;
        try {
            Request request = new Request.Builder().url(url).build();
            call = client.newCall(request);
            call.enqueue(okHttpCallback);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * post请求
     * @param url
     * @param bodyMap 表单内容
     * @param okHttpCallback
     */
    public static void post(String url, OKHttpCallback okHttpCallback,
                            HashMap<String,String> bodyMap){
        Log.e("OKHttpUtil", "post: " );

        FormBody.Builder builder = new FormBody.Builder();
        Log.e("OKHttpUtil", bodyMap.toString());

        for (HashMap.Entry<String,String> entry:bodyMap.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(okHttpCallback);
    }

}
