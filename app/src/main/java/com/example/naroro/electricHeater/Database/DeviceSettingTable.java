package com.example.naroro.electricHeater.Database;

import org.litepal.crud.DataSupport;

/**
 * 设备运行模式：模式界面
 * deviceID
 * deviceName
 * nowMode：运行模式
 * 自动模式的温度、温差
 * 七段模式的起始时间及温度、温差
 */
public class DeviceSettingTable extends DataSupport {

    private int deviceId;
    private String deviceName;

    private int nowMode;//运行模式

    private int autoTemp;
    private int autoDifference;

    private int startTime1_Hour;
    private int startTime1_min;
    private int endTime1_Hour;
    private int endTime1_min;
    private int temp1;
    private int difference1;

    private int startTime2_Hour;
    private int startTime2_min;
    private int endTime2_Hour;
    private int endTime2_min;
    private int temp2;
    private int difference2;

    private int startTime3_Hour;
    private int startTime3_min;
    private int endTime3_Hour;
    private int endTime3_min;
    private int temp3;
    private int difference3;

    private int startTime4_Hour;
    private int startTime4_min;
    private int endTime4_Hour;
    private int endTime4_min;
    private int temp4;
    private int difference4;

    private int startTime5_Hour;
    private int startTime5_min;
    private int endTime5_Hour;
    private int endTime5_min;
    private int temp5;
    private int difference5;

    private int startTime6_Hour;
    private int startTime6_min;
    private int endTime6_Hour;
    private int endTime6_min;
    private int temp6;
    private int difference6;

    private int startTime7_Hour;
    private int startTime7_min;
    private int endTime7_Hour;
    private int endTime7_min;
    private int temp7;
    private int difference7;

    public int getStartTime1_Hour() {
        return startTime1_Hour;
    }

    public void setStartTime1_Hour(int startTime1_Hour) {
        this.startTime1_Hour = startTime1_Hour;
    }

    public int getStartTime1_min() {
        return startTime1_min;
    }

    public void setStartTime1_min(int startTime1_min) {
        this.startTime1_min = startTime1_min;
    }

    public int getEndTime1_Hour() {
        return endTime1_Hour;
    }

    public void setEndTime1_Hour(int endTime1_Hour) {
        this.endTime1_Hour = endTime1_Hour;
    }

    public int getEndTime1_min() {
        return endTime1_min;
    }

    public void setEndTime1_min(int endTime1_min) {
        this.endTime1_min = endTime1_min;
    }

    public int getStartTime2_Hour() {
        return startTime2_Hour;
    }

    public void setStartTime2_Hour(int startTime2_Hour) {
        this.startTime2_Hour = startTime2_Hour;
    }

    public int getStartTime2_min() {
        return startTime2_min;
    }

    public void setStartTime2_min(int startTime2_min) {
        this.startTime2_min = startTime2_min;
    }

    public int getEndTime2_Hour() {
        return endTime2_Hour;
    }

    public void setEndTime2_Hour(int endTime2_Hour) {
        this.endTime2_Hour = endTime2_Hour;
    }

    public int getEndTime2_min() {
        return endTime2_min;
    }

    public void setEndTime2_min(int endTime2_min) {
        this.endTime2_min = endTime2_min;
    }

    public int getStartTime3_Hour() {
        return startTime3_Hour;
    }

    public void setStartTime3_Hour(int startTime3_Hour) {
        this.startTime3_Hour = startTime3_Hour;
    }

    public int getStartTime3_min() {
        return startTime3_min;
    }

    public void setStartTime3_min(int startTime3_min) {
        this.startTime3_min = startTime3_min;
    }

    public int getEndTime3_Hour() {
        return endTime3_Hour;
    }

    public void setEndTime3_Hour(int endTime3_Hour) {
        this.endTime3_Hour = endTime3_Hour;
    }

    public int getEndTime3_min() {
        return endTime3_min;
    }

    public void setEndTime3_min(int endTime3_min) {
        this.endTime3_min = endTime3_min;
    }

    public int getStartTime4_Hour() {
        return startTime4_Hour;
    }

    public void setStartTime4_Hour(int startTime4_Hour) {
        this.startTime4_Hour = startTime4_Hour;
    }

    public int getStartTime4_min() {
        return startTime4_min;
    }

    public void setStartTime4_min(int startTime4_min) {
        this.startTime4_min = startTime4_min;
    }

    public int getEndTime4_Hour() {
        return endTime4_Hour;
    }

    public void setEndTime4_Hour(int endTime4_Hour) {
        this.endTime4_Hour = endTime4_Hour;
    }

    public int getEndTime4_min() {
        return endTime4_min;
    }

    public void setEndTime4_min(int endTime4_min) {
        this.endTime4_min = endTime4_min;
    }

    public int getStartTime5_Hour() {
        return startTime5_Hour;
    }

    public void setStartTime5_Hour(int startTime5_Hour) {
        this.startTime5_Hour = startTime5_Hour;
    }

    public int getStartTime5_min() {
        return startTime5_min;
    }

    public void setStartTime5_min(int startTime5_min) {
        this.startTime5_min = startTime5_min;
    }

    public int getEndTime5_Hour() {
        return endTime5_Hour;
    }

    public void setEndTime5_Hour(int endTime5_Hour) {
        this.endTime5_Hour = endTime5_Hour;
    }

    public int getEndTime5_min() {
        return endTime5_min;
    }

    public void setEndTime5_min(int endTime5_min) {
        this.endTime5_min = endTime5_min;
    }

    public int getStartTime6_Hour() {
        return startTime6_Hour;
    }

    public void setStartTime6_Hour(int startTime6_Hour) {
        this.startTime6_Hour = startTime6_Hour;
    }

    public int getStartTime6_min() {
        return startTime6_min;
    }

    public void setStartTime6_min(int startTime6_min) {
        this.startTime6_min = startTime6_min;
    }

    public int getEndTime6_Hour() {
        return endTime6_Hour;
    }

    public void setEndTime6_Hour(int endTime6_Hour) {
        this.endTime6_Hour = endTime6_Hour;
    }

    public int getEndTime6_min() {
        return endTime6_min;
    }

    public void setEndTime6_min(int endTime6_min) {
        this.endTime6_min = endTime6_min;
    }

    public int getStartTime7_Hour() {
        return startTime7_Hour;
    }

    public void setStartTime7_Hour(int startTime7_Hour) {
        this.startTime7_Hour = startTime7_Hour;
    }

    public int getStartTime7_min() {
        return startTime7_min;
    }

    public void setStartTime7_min(int startTime7_min) {
        this.startTime7_min = startTime7_min;
    }

    public int getEndTime7_Hour() {
        return endTime7_Hour;
    }

    public void setEndTime7_Hour(int endTime7_Hour) {
        this.endTime7_Hour = endTime7_Hour;
    }

    public int getEndTime7_min() {
        return endTime7_min;
    }

    public void setEndTime7_min(int endTime7_min) {
        this.endTime7_min = endTime7_min;
    }



    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getNowMode() {
        return nowMode;
    }

    public void setNowMode(int nowMode) {
        this.nowMode = nowMode;
    }
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getAutoTemp() {
        return autoTemp;
    }

    public void setAutoTemp(int autoTemp) {
        this.autoTemp = autoTemp;
    }

    public int getAutoDifference() {
        return autoDifference;
    }

    public void setAutoDifference(int autoDifference) {
        this.autoDifference = autoDifference;
    }



    public int getTemp1() {
        return temp1;
    }

    public void setTemp1(int temp1) {
        this.temp1 = temp1;
    }

    public int getDifference1() {
        return difference1;
    }

    public void setDifference1(int difference1) {
        this.difference1 = difference1;
    }



    public int getTemp2() {
        return temp2;
    }

    public void setTemp2(int temp2) {
        this.temp2 = temp2;
    }

    public int getDifference2() {
        return difference2;
    }

    public void setDifference2(int difference2) {
        this.difference2 = difference2;
    }


    public int getTemp3() {
        return temp3;
    }

    public void setTemp3(int temp3) {
        this.temp3 = temp3;
    }

    public int getDifference3() {
        return difference3;
    }

    public void setDifference3(int difference3) {
        this.difference3 = difference3;
    }



    public int getTemp4() {
        return temp4;
    }

    public void setTemp4(int temp4) {
        this.temp4 = temp4;
    }

    public int getDifference4() {
        return difference4;
    }

    public void setDifference4(int difference4) {
        this.difference4 = difference4;
    }



    public int getTemp5() {
        return temp5;
    }

    public void setTemp5(int temp5) {
        this.temp5 = temp5;
    }

    public int getDifference5() {
        return difference5;
    }

    public void setDifference5(int difference5) {
        this.difference5 = difference5;
    }



    public int getTemp6() {
        return temp6;
    }

    public void setTemp6(int temp6) {
        this.temp6 = temp6;
    }

    public int getDifference6() {
        return difference6;
    }

    public void setDifference6(int difference6) {
        this.difference6 = difference6;
    }



    public int getTemp7() {
        return temp7;
    }

    public void setTemp7(int temp7) {
        this.temp7 = temp7;
    }

    public int getDifference7() {
        return difference7;
    }

    public void setDifference7(int difference7) {
        this.difference7 = difference7;
    }



}
