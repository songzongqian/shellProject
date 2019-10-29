package com.shell.order.bean;

public class AllNetTopBean {


    /**
     * resultCode : 999999
     * resultData : {"createBy":0,"createTime":"2019-10-15 12:47:34","hours":994,"id":1,"isDeleted":"N","onlineUser":255,"orderAll":1000203,"orderAllAmount":243394392,"orderToday":10203,"orderTodayAmount":200402}
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
         * createBy : 0
         * createTime : 2019-10-15 12:47:34
         * hours : 994
         * id : 1
         * isDeleted : N
         * onlineUser : 255
         * orderAll : 1000203
         * orderAllAmount : 243394392
         * orderToday : 10203
         * orderTodayAmount : 200402
         */

        private int createBy;
        private String createTime;
        private String hours;
        private int id;
        private String isDeleted;
        private String onlineUser;
        private String orderAll;
        private String orderAllAmount;
        private String orderToday;
        private String orderTodayAmount;

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

        public String getHours() {
            return hours;
        }

        public void setHours(String hours) {
            this.hours = hours;
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

        public String getOnlineUser() {
            return onlineUser;
        }

        public void setOnlineUser(String onlineUser) {
            this.onlineUser = onlineUser;
        }

        public String getOrderAll() {
            return orderAll;
        }

        public void setOrderAll(String orderAll) {
            this.orderAll = orderAll;
        }

        public String getOrderAllAmount() {
            return orderAllAmount;
        }

        public void setOrderAllAmount(String orderAllAmount) {
            this.orderAllAmount = orderAllAmount;
        }

        public String getOrderToday() {
            return orderToday;
        }

        public void setOrderToday(String orderToday) {
            this.orderToday = orderToday;
        }

        public String getOrderTodayAmount() {
            return orderTodayAmount;
        }

        public void setOrderTodayAmount(String orderTodayAmount) {
            this.orderTodayAmount = orderTodayAmount;
        }
    }
}
