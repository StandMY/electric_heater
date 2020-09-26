package com.example.naroro.electricHeater.Bean;

/**
 * 接受来自服务器的数据：设备运行详情
 * 数据储存在DeviceRunningInfo
 */

public class DeviceShow {

    /**
     * 2020.8.21更新
     * deviceId":"000100000003",
     * "deviceName":"000100000003",
     * "nowStatus":"0",
     * "pumpStatus":"0",
     * "activation":"1",
     * "isActivated":"0",
     * "activeDuration":"100",
     * "nowTime":null,
     * "deviceAlias":null,
     * "updateTime":null,
     * "nowTemp":null,
     * "nowHum":null,
     * "nowMode":null,
     * "operatingVoltage":null,
     * "operatingCurrent":null,
     * "highTempAlarm":null,
     * "lowTempAlarm":null,
     * "waterAlarm":null,
     * "electricAlarm":null,
     * "ip":null,
     * "errorCode":null,
     * "workState":null,
     * "workMode":"0",
     * "heatLevel":"3",
     * "powerSwitch":"0",
     * "currentTemperature":"32"
     * }
     */

    private boolean success;
    private MessageBean message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * deviceId":"000100000003",
         * "deviceName":"000100000003",
         * "nowStatus":"0",   //当前加热状态,0待机，1加热
         * "pumpStatus":"0",  //泵状态
         * "activation":"1",
         * "isActivated":"0",
         * "activeDuration":"100",  //激活时间
         * "nowTime":null,
         * "deviceAlias":null,    //设备名称
         * "updateTime":null,
         * "nowTemp":null,
         * "nowHum":null,
         * "nowMode":null,
         * "operatingVoltage":null,  //电压
         * "operatingCurrent":null,  //
         * "highTempAlarm":null,
         * "lowTempAlarm":null,
         * "waterAlarm":null,
         * "electricAlarm":null,
         * "ip":null,
         * "errorCode":null,
         * "workState":null,  //没有用，nowStatus代替
         * "workMode":"0",     //运行模式：自动和分段模式
         * "heatLevel":"3",    //加热档次
         * "powerSwitch":"0",  //功率模式
         * "currentTemperature":"32"   //目前温度
         */

        private String deviceId;
        private int nowTemp;
        private Object nowHum;
        private int workMode;
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

        public int getWorkMode() {
            return workMode;
        }

        public void setWorkMode(int workMode) {
            this.workMode = workMode;
        }



        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getNowTemp() {
            return nowTemp;
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
}
