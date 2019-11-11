package com.shell.order.bean;

import java.io.Serializable;

public class OrderDetailtBean implements Serializable {


    /**
     * resultCode : 999999
     * resultDesc : 成功
     * resultData : {"id":2,"userId":3,"userEmail":"347303844@qq.com","userAddress":null,"code":"9bff58e3-e173-4102-bc28-80b01cc29c5c","type":"in","orderCurrency":"USD","orderAmount":100,"targetUserId":null,"targetEmail":null,"targetAddress":"l4yvceU2YeT2nkdK","standardCurrency":"HKD","exchangeRate":7,"standardAmount":700,"awardUsdt":0.08,"awardScore":null,"status":"10","transTime":null,"settlTime":null,"createTime":"2019-10-31 14:41:12","remainingSeconds":0}
     */

    private String resultCode;
    private String resultDesc;
    private ResultDataBean resultData;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * id : 2
         * userId : 3
         * userEmail : 347303844@qq.com
         * userAddress : null
         * code : 9bff58e3-e173-4102-bc28-80b01cc29c5c
         * type : in
         * orderCurrency : USD
         * orderAmount : 100
         * targetUserId : null
         * targetEmail : null
         * targetAddress : l4yvceU2YeT2nkdK
         * standardCurrency : HKD
         * exchangeRate : 7
         * standardAmount : 700
         * awardUsdt : 0.08
         * awardScore : null
         * status : 10
         * transTime : null
         * settlTime : null
         * createTime : 2019-10-31 14:41:12
         * remainingSeconds : 0
         */

        private int id;
        private int userId;
        private String userEmail;
        private Object userAddress;
        private String code;
        private String type;
        private String orderCurrency;
        private String orderAmount;
        private Object targetUserId;
        private Object targetEmail;
        private String targetAddress;
        private String standardCurrency;
        private String exchangeRate;
        private String standardAmount;
        private String awardUsdt;
        private Object awardScore;
        private String status;
        private Object transTime;
        private Object settlTime;
        private String createTime;
        private int remainingSeconds;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public Object getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(Object userAddress) {
            this.userAddress = userAddress;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrderCurrency() {
            return orderCurrency;
        }

        public void setOrderCurrency(String orderCurrency) {
            this.orderCurrency = orderCurrency;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public Object getTargetUserId() {
            return targetUserId;
        }

        public void setTargetUserId(Object targetUserId) {
            this.targetUserId = targetUserId;
        }

        public Object getTargetEmail() {
            return targetEmail;
        }

        public void setTargetEmail(Object targetEmail) {
            this.targetEmail = targetEmail;
        }

        public String getTargetAddress() {
            return targetAddress;
        }

        public void setTargetAddress(String targetAddress) {
            this.targetAddress = targetAddress;
        }

        public String getStandardCurrency() {
            return standardCurrency;
        }

        public void setStandardCurrency(String standardCurrency) {
            this.standardCurrency = standardCurrency;
        }

        public String getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(String exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getStandardAmount() {
            return standardAmount;
        }

        public void setStandardAmount(String standardAmount) {
            this.standardAmount = standardAmount;
        }

        public String getAwardUsdt() {
            return awardUsdt;
        }

        public void setAwardUsdt(String awardUsdt) {
            this.awardUsdt = awardUsdt;
        }

        public Object getAwardScore() {
            return awardScore;
        }

        public void setAwardScore(Object awardScore) {
            this.awardScore = awardScore;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getTransTime() {
            return transTime;
        }

        public void setTransTime(Object transTime) {
            this.transTime = transTime;
        }

        public Object getSettlTime() {
            return settlTime;
        }

        public void setSettlTime(Object settlTime) {
            this.settlTime = settlTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getRemainingSeconds() {
            return remainingSeconds;
        }

        public void setRemainingSeconds(int remainingSeconds) {
            this.remainingSeconds = remainingSeconds;
        }
    }
}
