package com.example.naroro.electricHeater.Bean;

import java.util.List;

/**
 * 用于处理主页界面收到的服务器端数据
 * success：是否请求成功
 * message：设备列表，放在DeviceInfoBean中
 *
 */
public class DeviceList {

    /**
     * success : true
     * message : [
     * {
     * "userId":165,
     * "deviceId":"000000000001",
     * "deviceAddress":"亚洲中国北京市海淀区",
     * deviceAlias":"客厅",
     * "activation":null,
     * "isActivated":null,
     * "activeDuration":15
     *
     * }]
     */

    private boolean success;
    private List<MessageBean> message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DeviceList.MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<DeviceList.MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
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

        public int getActiveDuration() {
            return activeDuration;
        }

        public void setActiveDuration(int activeDuration) {
            this.activeDuration = activeDuration;
        }

        private String isActivated;
        private int activeDuration;


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

    }

}
