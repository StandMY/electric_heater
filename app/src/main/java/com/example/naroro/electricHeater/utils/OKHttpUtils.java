package com.example.naroro.electricHeater.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OKHttpUtils {

    private static OkHttpClient client;

    public OKHttpUtils(){
            if(client == null){
                OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                        .connectTimeout(5000, TimeUnit.MILLISECONDS)
                        .readTimeout(5000, TimeUnit.MILLISECONDS)
                        .writeTimeout(5000, TimeUnit.MILLISECONDS);
                client = builder.build();
            }
    }


}
