package com.shell.Bean;

import java.util.List;

public class OnLineBean {


    /**
     * resultCode : 999999
     * resultData : [{"bankCoversionPrice":784.05,"createBy":0,"createTime":"2019-10-17 11:00:00","fbuyPrice":783.79,"fsellPrice":789.57,"id":289,"isDeleted":"N","mbuyPrice":759.44,"msellPrice":791.32,"name":"eur","updateTime":"2019-10-17 10:55:02","version":28},{"bankCoversionPrice":90.25,"createBy":0,"createTime":"2019-10-17 11:00:00","fbuyPrice":90.31,"fsellPrice":90.67,"id":290,"isDeleted":"N","mbuyPrice":89.59,"msellPrice":90.67,"name":"hkd","updateTime":"2019-10-17 10:55:02","version":28},{"bankCoversionPrice":6.5111,"createBy":0,"createTime":"2019-10-17 11:00:00","fbuyPrice":6.5042,"fsellPrice":6.552,"id":291,"isDeleted":"N","mbuyPrice":6.3021,"msellPrice":6.5556,"name":"jpy","updateTime":"2019-10-17 10:55:02","version":28},{"bankCoversionPrice":907.71,"createBy":0,"createTime":"2019-10-17 11:00:00","fbuyPrice":907.46,"fsellPrice":914.15,"id":292,"isDeleted":"N","mbuyPrice":879.27,"msellPrice":916.37,"name":"gbp","updateTime":"2019-10-17 10:55:02","version":28},{"bankCoversionPrice":478.55,"createBy":0,"createTime":"2019-10-17 11:00:00","fbuyPrice":480.2,"fsellPrice":483.73,"id":293,"isDeleted":"N","mbuyPrice":465.28,"msellPrice":484.91,"name":"aud","updateTime":"2019-10-17 10:55:02","version":28},{"bankCoversionPrice":536.22,"createBy":0,"createTime":"2019-10-17 11:00:00","fbuyPrice":535.88,"fsellPrice":539.83,"id":294,"isDeleted":"N","mbuyPrice":518.96,"msellPrice":541.13,"name":"cad","updateTime":"2019-10-17 10:55:02","version":28},{"bankCoversionPrice":516.59,"createBy":0,"createTime":"2019-10-17 11:00:00","fbuyPrice":516.59,"fsellPrice":520.21,"id":295,"isDeleted":"N","mbuyPrice":500.64,"msellPrice":521.77,"name":"sgd","updateTime":"2019-10-17 10:55:02","version":28},{"bankCoversionPrice":0.5969,"createBy":0,"createTime":"2019-10-17 11:00:00","fbuyPrice":0.5963,"fsellPrice":0.6011,"id":296,"isDeleted":"N","mbuyPrice":0.5754,"msellPrice":0.6229,"name":"krw","updateTime":"2019-10-17 10:55:02","version":28}]
     * resultDesc : 成功！
     */

    private String resultCode;
    private String resultDesc;
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
         * bankCoversionPrice : 784.05
         * createBy : 0
         * createTime : 2019-10-17 11:00:00
         * fbuyPrice : 783.79
         * fsellPrice : 789.57
         * id : 289
         * isDeleted : N
         * mbuyPrice : 759.44
         * msellPrice : 791.32
         * name : eur
         * updateTime : 2019-10-17 10:55:02
         * version : 28
         */

        private String bankCoversionPrice;
        private int createBy;
        private String createTime;
        private String fbuyPrice;
        private String fsellPrice;
        private int id;
        private String isDeleted;
        private String mbuyPrice;
        private String msellPrice;
        private String name;
        private String updateTime;
        private int version;

        public String getBankCoversionPrice() {
            return bankCoversionPrice;
        }

        public void setBankCoversionPrice(String bankCoversionPrice) {
            this.bankCoversionPrice = bankCoversionPrice;
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

        public String getFbuyPrice() {
            return fbuyPrice;
        }

        public void setFbuyPrice(String fbuyPrice) {
            this.fbuyPrice = fbuyPrice;
        }

        public String getFsellPrice() {
            return fsellPrice;
        }

        public void setFsellPrice(String fsellPrice) {
            this.fsellPrice = fsellPrice;
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

        public String getMbuyPrice() {
            return mbuyPrice;
        }

        public void setMbuyPrice(String mbuyPrice) {
            this.mbuyPrice = mbuyPrice;
        }

        public String getMsellPrice() {
            return msellPrice;
        }

        public void setMsellPrice(String msellPrice) {
            this.msellPrice = msellPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
