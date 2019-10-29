package com.shell.base;

public class TodayShouBean  {

    /**
     * resultCode : 999999
     * resultData : {"createBy":2,"createTime":"2019-10-24 18:36:41","hashRateAward":1.3,"id":2,"isDeleted":"N","lastUpdate":"2019-10-24 18:36:37","orderAmount":823.2518,"orderCount":11,"orderProfit":0.82,"settlDate":"2019-10-25","userId":2}
     * resultDesc : 成功！
     */

    private String resultCode;
    private ResultDataBean resultData;
    private String resultDesc;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public static class ResultDataBean {
        /**
         * createBy : 2
         * createTime : 2019-10-24 18:36:41
         * hashRateAward : 1.3
         * id : 2
         * isDeleted : N
         * lastUpdate : 2019-10-24 18:36:37
         * orderAmount : 823.2518
         * orderCount : 11
         * orderProfit : 0.82
         * settlDate : 2019-10-25
         * userId : 2
         */

        private int createBy;
        private String createTime;
        private String hashRateAward;
        private int id;
        private String isDeleted;
        private String lastUpdate;
        private String orderAmount;
        private String orderCount;
        private String orderProfit;
        private String settlDate;
        private int userId;

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

        public String getHashRateAward() {
            return hashRateAward;
        }

        public void setHashRateAward(String hashRateAward) {
            this.hashRateAward = hashRateAward;
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

        public String getLastUpdate() {
            return lastUpdate;
        }

        public void setLastUpdate(String lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
        }

        public String getOrderProfit() {
            return orderProfit;
        }

        public void setOrderProfit(String orderProfit) {
            this.orderProfit = orderProfit;
        }

        public String getSettlDate() {
            return settlDate;
        }

        public void setSettlDate(String settlDate) {
            this.settlDate = settlDate;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
