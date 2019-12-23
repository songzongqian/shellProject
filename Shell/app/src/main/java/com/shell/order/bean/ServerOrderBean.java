package com.shell.order.bean;

public class ServerOrderBean {

    /**
     * data : {"allowed":"Y","awardUsdt":0.08,"code":"cc201ab0-6bcf-46c4-a002-c3aa603eb803","createBy":0,"createTime":"2019-10-21 00:34:00","exchangeRate":7,"id":15781,"isDeleted":"N","orderAmount":100,"orderCurrency":"USD","standardAmount":700,"standardCurrency":"HKD","targetAddress":"tjy5lFhvraIA7MRk","type":"in","userEmail":"shelvenone@vip.qq.com","userId":1}
     * type : order
     */

    private DataBean data;
    private String type;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class DataBean {
        /**
         * allowed : Y
         * awardUsdt : 0.08
         * code : cc201ab0-6bcf-46c4-a002-c3aa603eb803
         * createBy : 0
         * createTime : 2019-10-21 00:34:00
         * exchangeRate : 7
         * id : 15781
         * isDeleted : N
         * orderAmount : 100
         * orderCurrency : USD
         * standardAmount : 700
         * standardCurrency : HKD
         * targetAddress : tjy5lFhvraIA7MRk
         * type : in
         * userEmail : shelvenone@vip.qq.com
         * userId : 1
         */

        private String allowed;
        private double awardUsdt;
        private String code;
        private int createBy;
        private String createTime;
        private String exchangeRate;
        private int id;
        private String isDeleted;
        private String orderAmount;
        private String orderCurrency;
        private String standardAmount;
        private String standardCurrency;
        private String targetAddress;
        private String type;
        private String userEmail;
        private int userId;

        public String getAllowed() {
            return allowed;
        }

        public void setAllowed(String allowed) {
            this.allowed = allowed;
        }

        public double getAwardUsdt() {
            return awardUsdt;
        }

        public void setAwardUsdt(double awardUsdt) {
            this.awardUsdt = awardUsdt;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(String exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getOrderCurrency() {
            return orderCurrency;
        }

        public void setOrderCurrency(String orderCurrency) {
            this.orderCurrency = orderCurrency;
        }

        public String getStandardAmount() {
            return standardAmount;
        }

        public void setStandardAmount(String standardAmount) {
            this.standardAmount = standardAmount;
        }

        public String getStandardCurrency() {
            return standardCurrency;
        }

        public void setStandardCurrency(String standardCurrency) {
            this.standardCurrency = standardCurrency;
        }

        public String getTargetAddress() {
            return targetAddress;
        }

        public void setTargetAddress(String targetAddress) {
            this.targetAddress = targetAddress;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
