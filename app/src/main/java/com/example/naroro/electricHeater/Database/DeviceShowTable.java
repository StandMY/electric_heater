package com.example.naroro.electricHeater.Database;

import org.litepal.crud.DataSupport;

/**
 * 设备运行详情界面
 * deviceId：设备ID
 * deviceName：设备名称
 * nowTemp：运行温度
 * nowMode：运行模式
 * nowStatus：运行状态，是否加热
 */
public class DeviceShowTable extends DataSupport {

    private int deviceId;
    private String deviceName;

    private int nowTemp;

    private int nowMode;//运行模式
    private int nowStatus;//是否加热

    //报警状态
    private int highTempAlarm;
    private int lowTempAlarm;
    private int waterAlarm;
    private int electricAlarm;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getNowTemp() {
        return nowTemp;
    }

    public void setNowTemp(int nowTemp) {
        this.nowTemp = nowTemp;
    }

    public int getNowMode() {
        return nowMode;
    }

    public void setNowMode(int nowMode) {
        this.nowMode = nowMode;
    }

    public int getNowStatus() {
        return nowStatus;
    }

    public void setNowStatus(int nowStatus) {
        this.nowStatus = nowStatus;
    }

    public int getHighTempAlarm() {
        return highTempAlarm;
    }

    public void setHighTempAlarm(int highTempAlarm) {
        this.highTempAlarm = highTempAlarm;
    }

    public int getLowTempAlarm() {
        return lowTempAlarm;
    }

    public void setLowTempAlarm(int lowTempAlarm) {
        this.lowTempAlarm = lowTempAlarm;
    }

    public int getWaterAlarm() {
        return waterAlarm;
    }

    public void setWaterAlarm(int waterAlarm) {
        this.waterAlarm = waterAlarm;
    }

    public int getElectricAlarm() {
        return electricAlarm;
    }

    public void setElectricAlarm(int electricAlarm) {
        this.electricAlarm = electricAlarm;
    }




}
