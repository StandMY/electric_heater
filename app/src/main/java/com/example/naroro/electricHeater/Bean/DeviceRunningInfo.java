package com.example.naroro.electricHeater.Bean;

import org.litepal.crud.DataSupport;

/**
 * 本地数据库，用于存放设备的运行参数
 * 数据来源于DeviceShow
 */
public class DeviceRunningInfo extends DataSupport {
    /**
     * deviceId : 000000000001
     * nowTemp : 28
     * nowHum : null
     * nowMode : 3
     * nowStatus : 1
     * pumpStatus : 1
     * operatingVoltage : null
     * operatingCurrent : null
     * highTempAlarm : 0
     * lowTempAlarm : 0
     * waterAlarm : 0
     * electricAlarm : 0
     * updateTime : 18:45
     * ip : 192.168.123.255:12345
     */

    private String deviceId;
    private int nowTemp;
    private String deviceName;
    private Object nowHum;
    private int nowMode;
    private int nowStatus;
    private int pumpStatus;
    private Object operatingVoltage;
    private Object operatingCurrent;
    private int highTempAlarm;
    private int lowTempAlarm;
    private int waterAlarm;
    private int electricAlarm;
    private String updateTime;
    private String ip;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getNowTemp() {
        return nowTemp;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setNowTemp(int nowTemp) {
        this.nowTemp = nowTemp;
    }

    public Object getNowHum() {
        return nowHum;
    }

    public void setNowHum(Object nowHum) {
        this.nowHum = nowHum;
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

    public int getPumpStatus() {
        return pumpStatus;
    }

    public void setPumpStatus(int pumpStatus) {
        this.pumpStatus = pumpStatus;
    }

    public Object getOperatingVoltage() {
        return operatingVoltage;
    }

    public void setOperatingVoltage(Object operatingVoltage) {
        this.operatingVoltage = operatingVoltage;
    }

    public Object getOperatingCurrent() {
        return operatingCurrent;
    }

    public void setOperatingCurrent(Object operatingCurrent) {
        this.operatingCurrent = operatingCurrent;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
