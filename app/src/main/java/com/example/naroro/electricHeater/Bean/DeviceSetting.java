package com.example.naroro.electricHeater.Bean;

/**
 * 接受来自服务器的数据：模式设置数据
 * 数据储存在SettingMessage中
 */
public class DeviceSetting {

    /**
     * success : true
     * message : {"deviceId":"000000000001",
     * "autoTemp":35,
     * "autoDifference":5,
     * "autoPower":3,
     * "startTime1":"18:15",
     * "endTime1":"19:25",
     * "temp1":38,
     * "difference1":5,
     * "power1":3,
     * "startTime2":"19:45",
     * "endTime2":"20:25",
     * "temp2":35,"difference2":5,
     * "power2":2,
     * "startTime3":"20:45",
     * "endTime3":"21:45",
     * "temp3":45,"difference3":5,
     * "power3":3,
     * "startTime4":"20:15",
     * "endTime4":"20:57",
     * "temp4":35,
     * "difference4":5,
     * "power4":3,
     * "startTime5":"20:35",
     * "endTime5":"20:45",
     * "temp5":38,
     * "difference5":5,
     * "power5":3,
     * "startTime6":"20:55",
     * "endTime6":"21:25",
     * "temp6":35,
     * "difference6":5,
     * "power6":2,
     * "startTime7":"21:45",
     * "endTime7":"22:45",
     * "temp7":45,
     * "difference7":5,
     * "power7":3,
     * "highTempThreshold":85,
     * "lowTempThreshold":5,
     * "waterThreshold":28305,
     * "electricThreshold":28305}
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
         * deviceId : 000000000001
         * autoTemp : 35
         * autoDifference : 5
         * autoPower : 3
         * startTime1 : 18:15
         * endTime1 : 19:25
         * temp1 : 38
         * difference1 : 5
         * power1 : 3
         * startTime2 : 19:45
         * endTime2 : 20:25
         * temp2 : 35
         * difference2 : 5
         * power2 : 2
         * startTime3 : 20:45
         * endTime3 : 21:45
         * temp3 : 45
         * difference3 : 5
         * power3 : 3
         * startTime4 : 20:15
         * endTime4 : 20:57
         * temp4 : 35
         * difference4 : 5
         * power4 : 3
         * startTime5 : 20:35
         * endTime5 : 20:45
         * temp5 : 38
         * difference5 : 5
         * power5 : 3
         * startTime6 : 20:55
         * endTime6 : 21:25
         * temp6 : 35
         * difference6 : 5
         * power6 : 2
         * startTime7 : 21:45
         * endTime7 : 22:45
         * temp7 : 45
         * difference7 : 5
         * power7 : 3
         * highTempThreshold : 85
         * lowTempThreshold : 5
         * waterThreshold : 28305
         * electricThreshold : 28305
         */

        private String deviceId;
        private int autoTemp;
        private int autoDifference;
        private int autoPower;
        private String startTime1;
        private String endTime1;
        private int temp1;
        private int difference1;
        private int power1;
        private String startTime2;
        private String endTime2;
        private int temp2;
        private int difference2;
        private int power2;
        private String startTime3;
        private String endTime3;
        private int temp3;
        private int difference3;
        private int power3;
        private String startTime4;
        private String endTime4;
        private int temp4;
        private int difference4;
        private int power4;
        private String startTime5;
        private String endTime5;
        private int temp5;
        private int difference5;
        private int power5;
        private String startTime6;
        private String endTime6;
        private int temp6;
        private int difference6;
        private int power6;
        private String startTime7;
        private String endTime7;
        private int temp7;
        private int difference7;
        private int power7;
        private int highTempThreshold;
        private int lowTempThreshold;
        private int waterThreshold;
        private int electricThreshold;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
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

        public int getAutoPower() {
            return autoPower;
        }

        public void setAutoPower(int autoPower) {
            this.autoPower = autoPower;
        }

        public String getStartTime1() {
            return startTime1;
        }

        public void setStartTime1(String startTime1) {
            this.startTime1 = startTime1;
        }

        public String getEndTime1() {
            return endTime1;
        }

        public void setEndTime1(String endTime1) {
            this.endTime1 = endTime1;
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

        public int getPower1() {
            return power1;
        }

        public void setPower1(int power1) {
            this.power1 = power1;
        }

        public String getStartTime2() {
            return startTime2;
        }

        public void setStartTime2(String startTime2) {
            this.startTime2 = startTime2;
        }

        public String getEndTime2() {
            return endTime2;
        }

        public void setEndTime2(String endTime2) {
            this.endTime2 = endTime2;
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

        public int getPower2() {
            return power2;
        }

        public void setPower2(int power2) {
            this.power2 = power2;
        }

        public String getStartTime3() {
            return startTime3;
        }

        public void setStartTime3(String startTime3) {
            this.startTime3 = startTime3;
        }

        public String getEndTime3() {
            return endTime3;
        }

        public void setEndTime3(String endTime3) {
            this.endTime3 = endTime3;
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

        public int getPower3() {
            return power3;
        }

        public void setPower3(int power3) {
            this.power3 = power3;
        }

        public String getStartTime4() {
            return startTime4;
        }

        public void setStartTime4(String startTime4) {
            this.startTime4 = startTime4;
        }

        public String getEndTime4() {
            return endTime4;
        }

        public void setEndTime4(String endTime4) {
            this.endTime4 = endTime4;
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

        public int getPower4() {
            return power4;
        }

        public void setPower4(int power4) {
            this.power4 = power4;
        }

        public String getStartTime5() {
            return startTime5;
        }

        public void setStartTime5(String startTime5) {
            this.startTime5 = startTime5;
        }

        public String getEndTime5() {
            return endTime5;
        }

        public void setEndTime5(String endTime5) {
            this.endTime5 = endTime5;
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

        public int getPower5() {
            return power5;
        }

        public void setPower5(int power5) {
            this.power5 = power5;
        }

        public String getStartTime6() {
            return startTime6;
        }

        public void setStartTime6(String startTime6) {
            this.startTime6 = startTime6;
        }

        public String getEndTime6() {
            return endTime6;
        }

        public void setEndTime6(String endTime6) {
            this.endTime6 = endTime6;
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

        public int getPower6() {
            return power6;
        }

        public void setPower6(int power6) {
            this.power6 = power6;
        }

        public String getStartTime7() {
            return startTime7;
        }

        public void setStartTime7(String startTime7) {
            this.startTime7 = startTime7;
        }

        public String getEndTime7() {
            return endTime7;
        }

        public void setEndTime7(String endTime7) {
            this.endTime7 = endTime7;
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

        public int getPower7() {
            return power7;
        }

        public void setPower7(int power7) {
            this.power7 = power7;
        }

        public int getHighTempThreshold() {
            return highTempThreshold;
        }

        public void setHighTempThreshold(int highTempThreshold) {
            this.highTempThreshold = highTempThreshold;
        }

        public int getLowTempThreshold() {
            return lowTempThreshold;
        }

        public void setLowTempThreshold(int lowTempThreshold) {
            this.lowTempThreshold = lowTempThreshold;
        }

        public int getWaterThreshold() {
            return waterThreshold;
        }

        public void setWaterThreshold(int waterThreshold) {
            this.waterThreshold = waterThreshold;
        }

        public int getElectricThreshold() {
            return electricThreshold;
        }

        public void setElectricThreshold(int electricThreshold) {
            this.electricThreshold = electricThreshold;
        }
    }
}
