package com.shell.home.Bean;

import java.util.List;

public class JiangLiBean {


    /**
     * resultCode : 999999
     * resultDesc : 成功！
     * resultData : [{"id":5,"userId":2,"amount":0.042,"hashRate":250,"isDeleted":"N","createBy":2,"createTime":"2019-10-02 19:20:22","modifyBy":null,"modifyTime":null},{"id":4,"userId":2,"amount":0.05,"hashRate":300,"isDeleted":"N","createBy":2,"createTime":"2019-10-02 19:21:22","modifyBy":null,"modifyTime":null},{"id":3,"userId":2,"amount":0.03,"hashRate":200,"isDeleted":"N","createBy":2,"createTime":"2019-10-02 19:23:22","modifyBy":null,"modifyTime":null}]
     * pageNum : 1
     * pageSize : 3
     * pages : 2
     * total : 5
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
         * id : 5
         * userId : 2
         * amount : 0.042
         * hashRate : 250.0
         * isDeleted : N
         * createBy : 2
         * createTime : 2019-10-02 19:20:22
         * modifyBy : null
         * modifyTime : null
         */

        private int id;
        private int userId;
        private String amount;
        private String hashRate;
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getHashRate() {
            return hashRate;
        }

        public void setHashRate(String hashRate) {
            this.hashRate = hashRate;
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
