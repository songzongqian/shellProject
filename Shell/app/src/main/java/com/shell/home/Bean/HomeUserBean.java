package com.shell.home.Bean;

public class HomeUserBean {


    /**
     * resultCode : 999999
     * resultDesc : 成功！
     * resultData : {"id":2,"userId":2,"address":null,"pledgeAmount":948,"creditScore":20,"hashRate":0,"currencyType":"HKD","currencyBalance":1000,"quota":100,"miningAward":200,"version":15,"isDeleted":"N","createBy":2,"createTime":"2019-10-08 13:17:18","modifyBy":2,"modifyTime":"2019-10-21 09:32:50"}
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
         * userId : 2
         * address : null
         * pledgeAmount : 948.0
         * creditScore : 20.0
         * hashRate : 0.0
         * currencyType : HKD
         * currencyBalance : 1000.0
         * quota : 100.0
         * miningAward : 200.0
         * version : 15
         * isDeleted : N
         * createBy : 2
         * createTime : 2019-10-08 13:17:18
         * modifyBy : 2
         * modifyTime : 2019-10-21 09:32:50
         */

        private String profit;
        private int id;
        private int userId;
        private Object address;
        private String pledgeAmount;
        private String creditScore;
        private String hashRate;
        private String currencyType;
        private double currencyBalance;
        private double quota;
        private String miningAward;
        private int version;
        private String isDeleted;
        private int createBy;
        private String createTime;
        private int modifyBy;
        private String modifyTime;

        public String getProfit() {
            return profit;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }

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

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getPledgeAmount() {
            return pledgeAmount;
        }

        public void setPledgeAmount(String pledgeAmount) {
            this.pledgeAmount = pledgeAmount;
        }

        public String getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(String creditScore) {
            this.creditScore = creditScore;
        }

        public String getHashRate() {
            return hashRate;
        }

        public void setHashRate(String hashRate) {
            this.hashRate = hashRate;
        }

        public String getCurrencyType() {
            return currencyType;
        }

        public void setCurrencyType(String currencyType) {
            this.currencyType = currencyType;
        }

        public double getCurrencyBalance() {
            return currencyBalance;
        }

        public void setCurrencyBalance(double currencyBalance) {
            this.currencyBalance = currencyBalance;
        }

        public double getQuota() {
            return quota;
        }

        public void setQuota(double quota) {
            this.quota = quota;
        }

        public String getMiningAward() {
            return miningAward;
        }

        public void setMiningAward(String miningAward) {
            this.miningAward = miningAward;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
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

        public int getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(int modifyBy) {
            this.modifyBy = modifyBy;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }
    }
}
