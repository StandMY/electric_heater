package com.example.naroro.electricHeater.utils;

import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * OkHttp的封装：回调类，自定义事件的监听
 */

public abstract class OKHttpCallback implements Callback {

    /**
     * 将response转化为json格式
     * @param call
     * @param jsonObject response的json格式
     * @throws Exception
     */
    public abstract void onSuccess(final Call call, JSONObject jsonObject) throws Exception;

    @Override
    public void onFailure(Call call, IOException e) {
        if(e instanceof SocketTimeoutException){
        }
    }

    @Override
    public void onResponse(Call call, Response response) {
        if(response != null){
            if(response.isSuccessful()){
                try {
                    Log.e("OKHttpCallback", "onResponse: isSuccessful"+response);
                    final String str = response.body().string().trim();
                    Log.e("OKHttpCallback", "String "+str);
                    JSONObject jsonObject = (JSONObject) new JSONTokener(str).nextValue();

                    if(jsonObject != null) {
                        Log.e("OKHttpCallback", "BeforeJson");
                        //JSONObject jsonObject = new JSONObject(str);
                        onSuccess(call, jsonObject);
                    }else {
                        onFailure(call,null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                Log.e("OKHttpCallback", "Response is not success");
                onFailure(call,null);
            }
        }
    }
}
