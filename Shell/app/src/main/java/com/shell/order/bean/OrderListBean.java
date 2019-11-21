package com.shell.order.bean;

import java.util.List;

public class OrderListBean {


    /**
     * resultCode : 999999
     * resultData : [{"awardUsdt":0.08,"code":"e0a1dc7f-45f8-4fb4-b457-fc4337b5e3f2","createTime":"2019-10-26 11:39:02","exchangeRate":7,"id":25345,"orderAmount":100,"orderCurrency":"USD","remainingSeconds":447,"standardAmount":700,"standardCurrency":"HKD","status":"10","targetAddress":"OyMzqpgt8pA5khT3","type":"in","userEmail":"347303844@qq.com","userId":2},{"awardUsdt":0.08,"code":"56c5ae87-754a-4994-a46d-a0b1447b3e8d","createTime":"2019-10-25 22:13:02","exchangeRate":7,"id":25344,"orderAmount":100,"orderCurrency":"USD","remainingSeconds":0,"standardAmount":700,"standardCurrency":"HKD","status":"20","targetAddress":"sf8cIuQ9m0FDUvzm","transTime":"2019-10-25 22:15:53","type":"in","userEmail":"347303844@qq.com","userId":2},{"awardUsdt":0.08,"code":"7452f270-fcea-48d6-b335-38d70e45177a","createTime":"2019-10-25 22:11:02","exchangeRate":7,"id":25343,"orderAmount":100,"orderCurrency":"USD","remainingSeconds":0,"standardAmount":700,"standardCurrency":"HKD","status":"20","targetAddress":"dEabYdTYS1zDBmEl","transTime":"2019-10-25 22:12:48","type":"in","userEmail":"347303844@qq.com","userId":2},{"awardUsdt":0.08,"code":"42162492-bfa3-43b6-8375-d9bceece9297","createTime":"2019-10-25 21:18:05","exchangeRate":7,"id":25342,"orderAmount":100,"orderCurrency":"USD","remainingSeconds":0,"standardAmount":700,"standardCurrency":"HKD","status":"20","targetAddress":"V7GVXGnhnxxBtHLd","transTime":"2019-10-25 21:28:11","type":"in","userEmail":"347303844@qq.com","userId":2},{"awardUsdt":0.08,"code":"d74333e4-949b-43a9-a795-634e1fc25fa0","createTime":"2019-10-25 21:06:03","exchangeRate":7,"id":25341,"orderAmount":100,"orderCurrency":"USD","remainingSeconds":0,"standardAmount":700,"standardCurrency":"HKD","status":"20","targetAddress":"FjvAzAmyyGTDyVxa","transTime":"2019-10-25 21:06:43","type":"in","userEmail":"347303844@qq.com","userId":2},{"awardUsdt":0.08,"code":"c3e5b6f5-c845-430a-8378-03de6392dbe0","createTime":"2019-10-25 20:49:09","exchangeRate":7,"id":25340,"orderAmount":100,"orderCurrency":"USD","remainingSeconds":0,"standardAmount":700,"standardCurrency":"HKD","status":"20","targetAddress":"9c19XRqFN7OE2ZN0","transTime":"2019-10-25 21:05:53","type":"in","userEmail":"347303844@qq.com","userId":2}]
     * resultDesc : Success
     */
    private int pageNum;
    private int pageSize;
    private int pages;
    private int total;
    private String resultCode;
    private String resultDesc;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private List<ResultDataBean> resultData;

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

    public List<ResultDataBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean> resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * awardUsdt : 0.08
         * code : e0a1dc7f-45f8-4fb4-b457-fc4337b5e3f2
         * createTime : 2019-10-26 11:39:02
         * exchangeRate : 7
         * id : 25345
         * orderAmount : 100
         * orderCurrency : USD
         * remainingSeconds : 447
         * standardAmount : 700
         * standardCurrency : HKD
         * status : 10
         * targetAddress : OyMzqpgt8pA5khT3
         * type : in
         * userEmail : 347303844@qq.com
         * userId : 2
         * transTime : 2019-10-25 22:15:53
         */

        private String awardUsdt;
        private String code;
        private String createTime;
        private String exchangeRate;
        private long id;
        private String orderAmount;
        private String orderCurrency;
        private int remainingSeconds;
        private String standardAmount;
        private String standardCurrency;
        private String status;
        private String targetAddress;
        private String type;
        private String userEmail;
        private int userId;
        private String transTime;

        private int awardScore;

        public int getAwardScore() {
            return awardScore;
        }

        public void setAwardScore(int awardScore) {
            this.awardScore = awardScore;
        }

        public String getAwardUsdt() {
            return awardUsdt;
        }

        public void setAwardUsdt(String awardUsdt) {
            this.awardUsdt = awardUsdt;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        public int getRemainingSeconds() {
            return remainingSeconds;
        }

        public void setRemainingSeconds(int remainingSeconds) {
            this.remainingSeconds = remainingSeconds;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getTransTime() {
            return transTime;
        }

        public void setTransTime(String transTime) {
            this.transTime = transTime;
        }
    }
}
