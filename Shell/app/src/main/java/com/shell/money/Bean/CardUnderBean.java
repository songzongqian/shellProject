package com.shell.money.Bean;

import java.util.List;

public class CardUnderBean {


    /**
     * resultCode : 999999
     * resultDesc : 成功！
     * resultData : [{"id":1,"userId":2,"walletId":2,"busiCode":"pledge","coinCode":"USDT","coinName":"Tether","address":null,"operateAmount":10,"beforeBalance":10000,"afterBalance":9990,"beforeFreeze":0,"afterFreeze":0,"beforeUseable":10000,"afterUseable":9990,"status":"10","remark":null,"version":1,"isDeleted":"N","createBy":2,"createTime":"2019-10-11 22:34:05","modifyBy":null,"modifyTime":null},{"id":2,"userId":2,"walletId":2,"busiCode":"pledge","coinCode":"USDT","coinName":"Tether","address":null,"operateAmount":10,"beforeBalance":9990,"afterBalance":9980,"beforeFreeze":0,"afterFreeze":0,"beforeUseable":9990,"afterUseable":9980,"status":"10","remark":null,"version":2,"isDeleted":"N","createBy":2,"createTime":"2019-10-11 22:35:03","modifyBy":null,"modifyTime":null},{"id":3,"userId":2,"walletId":2,"busiCode":"withdraw","coinCode":"USDT","coinName":"Tether","address":"19jCdkA2QcazwbRnSTNrYcBmoBSE8S39DU","operateAmount":10,"beforeBalance":9980,"afterBalance":9980,"beforeFreeze":0,"afterFreeze":10,"beforeUseable":9980,"afterUseable":9970,"status":"10","remark":null,"version":3,"isDeleted":"N","createBy":2,"createTime":"2019-10-11 22:40:07","modifyBy":null,"modifyTime":null},{"id":4,"userId":2,"walletId":2,"busiCode":"withdraw","coinCode":"USDT","coinName":"Tether","address":"19jCdkA2QcazwbRnSTNrYcBmoBSE8S39DU","operateAmount":100,"beforeBalance":9980,"afterBalance":9980,"beforeFreeze":10,"afterFreeze":110,"beforeUseable":9970,"afterUseable":9870,"status":"10","remark":null,"version":4,"isDeleted":"N","createBy":2,"createTime":"2019-10-11 22:51:10","modifyBy":null,"modifyTime":null},{"id":5,"userId":2,"walletId":2,"busiCode":"pledge","coinCode":"USDT","coinName":"Tether","address":null,"operateAmount":10,"beforeBalance":9980,"afterBalance":9970,"beforeFreeze":110,"afterFreeze":110,"beforeUseable":9870,"afterUseable":9860,"status":"10","remark":null,"version":5,"isDeleted":"N","createBy":2,"createTime":"2019-10-15 12:03:01","modifyBy":null,"modifyTime":null},{"id":6,"userId":2,"walletId":2,"busiCode":"pledge","coinCode":"USDT","coinName":"Tether","address":null,"operateAmount":10,"beforeBalance":9970,"afterBalance":9960,"beforeFreeze":110,"afterFreeze":110,"beforeUseable":9860,"afterUseable":9850,"status":"10","remark":null,"version":6,"isDeleted":"N","createBy":2,"createTime":"2019-10-15 12:45:39","modifyBy":null,"modifyTime":null},{"id":7,"userId":2,"walletId":2,"busiCode":"pledge","coinCode":"USDT","coinName":"Tether","address":null,"operateAmount":100,"beforeBalance":9960,"afterBalance":9860,"beforeFreeze":110,"afterFreeze":110,"beforeUseable":9850,"afterUseable":9750,"status":"10","remark":null,"version":7,"isDeleted":"N","createBy":2,"createTime":"2019-10-15 12:47:17","modifyBy":null,"modifyTime":null},{"id":8,"userId":2,"walletId":2,"busiCode":"pledge","coinCode":"USDT","coinName":"Tether","address":null,"operateAmount":500,"beforeBalance":9860,"afterBalance":9360,"beforeFreeze":110,"afterFreeze":110,"beforeUseable":9750,"afterUseable":9250,"status":"10","remark":null,"version":8,"isDeleted":"N","createBy":2,"createTime":"2019-10-15 12:50:07","modifyBy":null,"modifyTime":null},{"id":9,"userId":2,"walletId":2,"busiCode":"pledge","coinCode":"USDT","coinName":"Tether","address":null,"operateAmount":100,"beforeBalance":9360,"afterBalance":9260,"beforeFreeze":110,"afterFreeze":110,"beforeUseable":9250,"afterUseable":9150,"status":"10","remark":null,"version":9,"isDeleted":"N","createBy":2,"createTime":"2019-10-16 13:26:42","modifyBy":null,"modifyTime":null}]
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * total : 9
     */

    private String resultCode;
    private String resultDesc;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int total;
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

    public List<ResultDataBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean> resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * id : 1
         * userId : 2
         * walletId : 2
         * busiCode : pledge
         * coinCode : USDT
         * coinName : Tether
         * address : null
         * operateAmount : 10
         * beforeBalance : 10000
         * afterBalance : 9990
         * beforeFreeze : 0
         * afterFreeze : 0
         * beforeUseable : 10000
         * afterUseable : 9990
         * status : 10
         * remark : null
         * version : 1
         * isDeleted : N
         * createBy : 2
         * createTime : 2019-10-11 22:34:05
         * modifyBy : null
         * modifyTime : null
         */

        private int id;
        private int userId;
        private int walletId;
        private String busiCode;
        private String coinCode;
        private String coinName;
        private Object address;
        private int operateAmount;
        private int beforeBalance;
        private int afterBalance;
        private int beforeFreeze;
        private int afterFreeze;
        private int beforeUseable;
        private int afterUseable;
        private String status;
        private Object remark;
        private int version;
        private String isDeleted;
        private int createBy;
        private String createTime;
        private Object modifyBy;
        private Object modifyTime;

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

        public int getWalletId() {
            return walletId;
        }

        public void setWalletId(int walletId) {
            this.walletId = walletId;
        }

        public String getBusiCode() {
            return busiCode;
        }

        public void setBusiCode(String busiCode) {
            this.busiCode = busiCode;
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

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public int getOperateAmount() {
            return operateAmount;
        }

        public void setOperateAmount(int operateAmount) {
            this.operateAmount = operateAmount;
        }

        public int getBeforeBalance() {
            return beforeBalance;
        }

        public void setBeforeBalance(int beforeBalance) {
            this.beforeBalance = beforeBalance;
        }

        public int getAfterBalance() {
            return afterBalance;
        }

        public void setAfterBalance(int afterBalance) {
            this.afterBalance = afterBalance;
        }

        public int getBeforeFreeze() {
            return beforeFreeze;
        }

        public void setBeforeFreeze(int beforeFreeze) {
            this.beforeFreeze = beforeFreeze;
        }

        public int getAfterFreeze() {
            return afterFreeze;
        }

        public void setAfterFreeze(int afterFreeze) {
            this.afterFreeze = afterFreeze;
        }

        public int getBeforeUseable() {
            return beforeUseable;
        }

        public void setBeforeUseable(int beforeUseable) {
            this.beforeUseable = beforeUseable;
        }

        public int getAfterUseable() {
            return afterUseable;
        }

        public void setAfterUseable(int afterUseable) {
            this.afterUseable = afterUseable;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
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

        public Object getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(Object modifyBy) {
            this.modifyBy = modifyBy;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }
    }
}
