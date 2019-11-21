package com.shell.money.Bean;

import java.io.Serializable;
import java.util.List;

public class TiBiBean implements Serializable {


    /**
     * resultCode : 999999
     * resultDesc : Success
     * resultData : [{"id":6102,"userId":1,"walletId":1,"busiCode":"withdraw","coinCode":"USDT","coinName":"Tether","address":"1QAT14WTUd2TUSMn98w5GvjeFfn6APNh9g","operateAmount":10,"beforeBalance":278.80413404,"afterBalance":278.80413404,"beforeFreeze":0,"afterFreeze":10,"beforeUseable":278.80413404,"afterUseable":268.80413404,"status":"10","remark":null,"version":7,"isDeleted":"N","createBy":1,"createTime":"2019-11-19 19:16:27","modifyBy":null,"modifyTime":null}]
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * total : 1
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
         * id : 6102
         * userId : 1
         * walletId : 1
         * busiCode : withdraw
         * coinCode : USDT
         * coinName : Tether
         * address : 1QAT14WTUd2TUSMn98w5GvjeFfn6APNh9g
         * operateAmount : 10
         * beforeBalance : 278.80413404
         * afterBalance : 278.80413404
         * beforeFreeze : 0
         * afterFreeze : 10
         * beforeUseable : 278.80413404
         * afterUseable : 268.80413404
         * status : 10
         * remark : null
         * version : 7
         * isDeleted : N
         * createBy : 1
         * createTime : 2019-11-19 19:16:27
         * modifyBy : null
         * modifyTime : null
         */

        private int id;
        private int userId;
        private int walletId;
        private String busiCode;
        private String coinCode;
        private String coinName;
        private String address;
        private String operateAmount;
        private double beforeBalance;
        private double afterBalance;
        private int beforeFreeze;
        private int afterFreeze;
        private double beforeUseable;
        private double afterUseable;
        private String status;
        private Object remark;
        private String version;
        private String isDeleted;
        private String createBy;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOperateAmount() {
            return operateAmount;
        }

        public void setOperateAmount(String operateAmount) {
            this.operateAmount = operateAmount;
        }

        public double getBeforeBalance() {
            return beforeBalance;
        }

        public void setBeforeBalance(double beforeBalance) {
            this.beforeBalance = beforeBalance;
        }

        public double getAfterBalance() {
            return afterBalance;
        }

        public void setAfterBalance(double afterBalance) {
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

        public double getBeforeUseable() {
            return beforeUseable;
        }

        public void setBeforeUseable(double beforeUseable) {
            this.beforeUseable = beforeUseable;
        }

        public double getAfterUseable() {
            return afterUseable;
        }

        public void setAfterUseable(double afterUseable) {
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

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
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
