package com.shell.money.Bean;

public class CardBean {

    /**
     * resultCode : 999999
     * resultData : {"balance":0,"coinCode":"USDT","coinName":"Tether","createBy":8,"createTime":"2019-10-09 08:58:43","freeze":0,"id":4,"isDeleted":"N","useable":0,"userId":8,"version":1}
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
         * balance : 0
         * coinCode : USDT
         * coinName : Tether
         * createBy : 8
         * createTime : 2019-10-09 08:58:43
         * freeze : 0
         * id : 4
         * isDeleted : N
         * useable : 0
         * userId : 8
         * version : 1
         */

        private String balance;
        private String coinCode;
        private String coinName;
        private int createBy;
        private String createTime;
        private String freeze;
        private int id;
        private String isDeleted;
        private String useable;
        private int userId;
        private int version;
        private String ethAddress;

        public String getEthAddress() {
            return ethAddress;
        }

        public void setEthAddress(String ethAddress) {
            this.ethAddress = ethAddress;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getCoinCode() {
            return coinCode;
        }

        public void setCoinCode(String coinCode) {
            this.coinCode = coinCode;
        }

        public String getCoinName() {
            return coinName;
        }

        public void setCoinName(String coinName) {
            this.coinName = coinName;
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

        public String getFreeze() {
            return freeze;
        }

        public void setFreeze(String freeze) {
            this.freeze = freeze;
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

        public String getUseable() {
            return useable;
        }

        public void setUseable(String useable) {
            this.useable = useable;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
