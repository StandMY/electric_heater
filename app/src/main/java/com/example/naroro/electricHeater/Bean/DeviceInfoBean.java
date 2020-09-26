package com.example.naroro.electricHeater.Bean;

import org.litepal.crud.DataSupport;

/**
 * 本地数据库，用于存放主界面的设备列表
 * 信息来源于DeviceList
 */
public class DeviceInfoBean extends DataSupport {

        /**
         * "userId":165,
         * "deviceId":"000000000001",
         * "deviceAddress":"亚洲中国北京市海淀区",
         * deviceAlias":"客厅",
         * "activation":null,
         * "isActivated":null,
         * "activeDuration":15
         */

        private int userId;
        private String deviceId;
        private String deviceAddress;
        private String deviceAlias;
        private String activation;
        private String isActivated;
        private String activeDuration;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceAddress() {
            return deviceAddress;
        }

        public void setDeviceAddress(String deviceAddress) {
            this.deviceAddress = deviceAddress;
        }

    public String getDeviceAlias() {
        return deviceAlias;
    }

    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(String isActivated) {
        this.isActivated = isActivated;
    }

    public String getActiveDuration() {
        return activeDuration;
    }

    public void setActiveDuration(String activeDuration) {
        this.activeDuration = activeDuration;
    }
}
