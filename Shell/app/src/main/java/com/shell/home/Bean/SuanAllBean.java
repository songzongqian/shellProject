package com.shell.home.Bean;

import java.util.List;

public class SuanAllBean {

    /**
     * resultCode : 999999
     * resultData : {"data":[{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.01,"id":1,"isDeleted":"N","pledgeAmount":3000.23,"statisticalTime":"2019-10-01 12:55:22","tradingAmount":2200.32},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.011,"id":2,"isDeleted":"N","pledgeAmount":7233.0234,"statisticalTime":"2019-10-02 12:55:22","tradingAmount":1303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.007,"id":3,"isDeleted":"N","pledgeAmount":5233.0234,"statisticalTime":"2019-10-03 12:55:22","tradingAmount":3303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.009,"id":4,"isDeleted":"N","pledgeAmount":3233.0234,"statisticalTime":"2019-10-04 12:55:22","tradingAmount":2303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.008,"id":5,"isDeleted":"N","pledgeAmount":2233.0234,"statisticalTime":"2019-10-05 12:55:22","tradingAmount":3303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.01,"id":6,"isDeleted":"N","pledgeAmount":6233.0234,"statisticalTime":"2019-10-06 12:55:22","tradingAmount":5303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.011,"id":7,"isDeleted":"N","pledgeAmount":4233.0234,"statisticalTime":"2019-10-07 12:55:22","tradingAmount":4303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.015,"id":8,"isDeleted":"N","pledgeAmount":2233.0234,"statisticalTime":"2019-10-08 12:55:22","tradingAmount":3303.0032}],"descText":"1. The "}
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
         * data : [{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.01,"id":1,"isDeleted":"N","pledgeAmount":3000.23,"statisticalTime":"2019-10-01 12:55:22","tradingAmount":2200.32},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.011,"id":2,"isDeleted":"N","pledgeAmount":7233.0234,"statisticalTime":"2019-10-02 12:55:22","tradingAmount":1303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.007,"id":3,"isDeleted":"N","pledgeAmount":5233.0234,"statisticalTime":"2019-10-03 12:55:22","tradingAmount":3303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.009,"id":4,"isDeleted":"N","pledgeAmount":3233.0234,"statisticalTime":"2019-10-04 12:55:22","tradingAmount":2303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.008,"id":5,"isDeleted":"N","pledgeAmount":2233.0234,"statisticalTime":"2019-10-05 12:55:22","tradingAmount":3303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.01,"id":6,"isDeleted":"N","pledgeAmount":6233.0234,"statisticalTime":"2019-10-06 12:55:22","tradingAmount":5303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.011,"id":7,"isDeleted":"N","pledgeAmount":4233.0234,"statisticalTime":"2019-10-07 12:55:22","tradingAmount":4303.0032},{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.015,"id":8,"isDeleted":"N","pledgeAmount":2233.0234,"statisticalTime":"2019-10-08 12:55:22","tradingAmount":3303.0032}]
         * descText : 1. The
         */

        private String descText;
        private List<DataBean> data;

        public String getDescText() {
            return descText;
        }

        public void setDescText(String descText) {
            this.descText = descText;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * createBy : 0
             * createTime : 2019-10-15 12:55:28
             * hashRate : 0.01
             * id : 1
             * isDeleted : N
             * pledgeAmount : 3000.23
             * statisticalTime : 2019-10-01 12:55:22
             * tradingAmount : 2200.32
             */

            private int createBy;
            private String createTime;
            private String hashRate;
            private int id;
            private String isDeleted;
            private double pledgeAmount;
            private String statisticalTime;
            private double tradingAmount;

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

            public String getHashRate() {
                return hashRate;
            }

            public void setHashRate(String hashRate) {
                this.hashRate = hashRate;
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

            public double getPledgeAmount() {
                return pledgeAmount;
            }

            public void setPledgeAmount(double pledgeAmount) {
                this.pledgeAmount = pledgeAmount;
            }

            public String getStatisticalTime() {
                return statisticalTime;
            }

            public void setStatisticalTime(String statisticalTime) {
                this.statisticalTime = statisticalTime;
            }

            public double getTradingAmount() {
                return tradingAmount;
            }

            public void setTradingAmount(double tradingAmount) {
                this.tradingAmount = tradingAmount;
            }
        }
    }
}
