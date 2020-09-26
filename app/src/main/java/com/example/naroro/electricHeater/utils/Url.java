package com.example.naroro.electricHeater.utils;

/**
 * 向服务器端发起请求的Url
 */
public class Url {
    //服务器地址
    public static final String BaseUrl = "http://192.168.1.105:8081";
    //public static final String BaseUrl = "http://123.56.121.205:8081";
    //public static final String BaseUrl = "http://112.126.70.217:8081";
    //public static final String BaseUrl = "http://15i1x21910.iok.la/";


    //发起登录
    public static final String loginUrl = BaseUrl + "/tokens/login1?";

    //注册时发送验证码
    public static final String registerSmsUrl = BaseUrl + "/msgvalidate?";

    //发起注册
    public static final String registerUrl = BaseUrl + "/register?";

    //验证码登录时发送验证码
    public static final String verficSmsUrl = BaseUrl + "/msgvalidatelogin?";

    //验证码登录
    public static final String verficLoginUrl = BaseUrl + "/tokens/login2?";

    //请求设备运行信息
    public static final String deviceShowUrl = BaseUrl + "/deviceshow?";

    //获取用户的设备列表
    public static final String deviceListUrl = BaseUrl + "/getdevices?";

    //获取设备模式
    public static final String deviceSttingUrl = BaseUrl + "/devicesetting?";

    //修改设备模式后提交
    public static final String editdeviceUrl = BaseUrl + "/editdevice?";

    //用户添加设备，提交新设备信息
    public static final String addeviceUrl = BaseUrl + "/bind?";

}