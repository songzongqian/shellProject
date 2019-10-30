package com.shell.Bean;

public class VersionBean {

    /**
     * resultCode : 999999
     * resultData : {"createBy":0,"createTime":"2019-04-09 15:40:55","id":2,"isDeleted":"N","remark":"1、全新版本发布.\r2\n、更多功能待探索.","type":"android","url":" https://shelllab.oss-cn-hongkong.aliyuncs.com/app/shell.apk","version":"v1.0.0"}
     * resultDesc : Success
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
         * createTime : 2019-04-09 15:40:55
         * id : 2
         * isDeleted : N
         * remark : 1、全新版本发布.2
         、更多功能待探索.
         * type : android
         * url :  https://shelllab.oss-cn-hongkong.aliyuncs.com/app/shell.apk
         * version : v1.0.0
         */

        private int createBy;
        private String createTime;
        private int id;
        private String isDeleted;
        private String remark;
        private String type;
        private String url;
        private String version;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
