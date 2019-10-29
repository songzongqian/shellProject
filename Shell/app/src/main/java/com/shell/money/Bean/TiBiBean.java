package com.shell.money.Bean;

import java.util.List;

public class TiBiBean {

    /**
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * resultCode : 999999
     * resultData : [{"address":"19jCdkA2QcazwbRnSTNrYcBmoBSE8S39DU","afterBalance":9980,"afterFreeze":110,"afterUseable":9870,"beforeBalance":9980,"beforeFreeze":10,"beforeUseable":9970,"busiCode":"withdraw","coinCode":"USDT","coinName":"Tether","createBy":2,"createTime":"2019-10-11 22:51:10","id":4,"isDeleted":"N","operateAmount":100,"status":"10","userId":2,"version":4,"walletId":2},{"address":"19jCdkA2QcazwbRnSTNrYcBmoBSE8S39DU","afterBalance":9980,"afterFreeze":10,"afterUseable":9970,"beforeBalance":9980,"beforeFreeze":0,"beforeUseable":9980,"busiCode":"withdraw","coinCode":"USDT","coinName":"Tether","createBy":2,"createTime":"2019-10-11 22:40:07","id":3,"isDeleted":"N","operateAmount":10,"status":"10","userId":2,"version":3,"walletId":2}]
     * resultDesc : 成功！
     * total : 2
     */

    private int pageNum;
    private int pageSize;
    private int pages;
    private String resultCode;
    private String resultDesc;
    private int total;
    private List<ResultDataBean> resultData;

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
         * address : 19jCdkA2QcazwbRnSTNrYcBmoBSE8S39DU
         * afterBalance : 9980
         * afterFreeze : 110
         * afterUseable : 9870
         * beforeBalance : 9980
         * beforeFreeze : 10
         * beforeUseable : 9970
         * busiCode : withdraw
         * coinCode : USDT
         * coinName : Tether
         * createBy : 2
         * createTime : 2019-10-11 22:51:10
         * id : 4
         * isDeleted : N
         * operateAmount : 100
         * status : 10
         * userId : 2
         * version : 4
         * walletId : 2
         */

        private String address;
        private int afterBalance;
        private int afterFreeze;
        private int afterUseable;
        private int beforeBalance;
        private int beforeFreeze;
        private int beforeUseable;
        private String busiCode;
        private String coinCode;
        private String coinName;
        private int createBy;
        private String createTime;
        private int id;
        private String isDeleted;
        private int operateAmount;
        private String status;
        private int userId;
        private int version;
        private int walletId;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAfterBalance() {
            return afterBalance;
        }

        public void setAfterBalance(int afterBalance) {
            this.afterBalance = afterBalance;
        }

        public int getAfterFreeze() {
            return afterFreeze;
        }

        public void setAfterFreeze(int afterFreeze) {
            this.afterFreeze = afterFreeze;
        }

        public int getAfterUseable() {
            return afterUseable;
        }

        public void setAfterUseable(int afterUseable) {
            this.afterUseable = afterUseable;
        }

        public int getBeforeBalance() {
            return beforeBalance;
        }

        public void setBeforeBalance(int beforeBalance) {
            this.beforeBalance = beforeBalance;
        }

        public int getBeforeFreeze() {
            return beforeFreeze;
        }

        public void setBeforeFreeze(int beforeFreeze) {
            this.beforeFreeze = beforeFreeze;
        }

        public int getBeforeUseable() {
            return beforeUseable;
        }

        public void setBeforeUseable(int beforeUseable) {
            this.beforeUseable = beforeUseable;
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

        public int getOperateAmount() {
            return operateAmount;
        }

        public void setOperateAmount(int operateAmount) {
            this.operateAmount = operateAmount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public int getWalletId() {
            return walletId;
        }

        public void setWalletId(int walletId) {
            this.walletId = walletId;
        }
    }
}
