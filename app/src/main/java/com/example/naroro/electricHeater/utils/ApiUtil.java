package com.example.naroro.electricHeater.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * OKHttp的封装：所有请求的基类
 * 处理服务器端的回调
 */
public abstract class ApiUtil {

    private ApiListener mApiListener  = null;

    private OKHttpCallback mOKHttpCallback = new OKHttpCallback() {
        @Override
        public void onSuccess(Call call, JSONObject jsonObject) throws Exception {
            parseData(jsonObject);
            mApiListener.success(ApiUtil.this);
        }

        @Override
        public void onFailure(Call call, IOException e) {
            mApiListener.failure(ApiUtil.this);
            Log.e("ApiUtil", "onFailure: ");
        }
    };

    protected abstract void parseData(JSONObject jsonObject) throws Exception;

    protected abstract String getUrl();

    public HashMap<String,String> mHashMap = new HashMap<>();

    public void addParam(String key,String value){
        mHashMap.put(key,value);
    }

    /**
     * 发送get请求
     * @param apiListener 告诉请求是否成功
     */
    public void get(ApiListener apiListener){
        mApiListener = apiListener;
        OKHttpUtil.get(getUrl(),mOKHttpCallback);
    }

    /**
     * 发送post请求
     * @param apiListener
     */
    public void post(ApiListener apiListener){
        mApiListener = apiListener;
        OKHttpUtil.post(getUrl(),mOKHttpCallback,mHashMap);
    }
}
