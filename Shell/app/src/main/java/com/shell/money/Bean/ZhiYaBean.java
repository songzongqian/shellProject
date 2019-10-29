package com.shell.money.Bean;

public class ZhiYaBean {

    /**
     * resultCode : 999999
     * resultData : {"allowed":800,"creditScore":20,"descText":"注意; 1.您质押的USDT，将通过Shell平台拆借给全球的企业;2.您质押的USDT只能通过一次性全部清算来进行退出，清算中的订单不再享受任何收益，且信誉分会回归至初始状态（20分）; 3.矿池收益的USDT可用于质押，且不收取矿工费; 4.可质押的USDT的上限与您当前的信誉分相关;","pledgeAmount":"100,1000,3000,5000,10000,30000"}
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
         * allowed : 800
         * creditScore : 20
         * descText : 注意; 1.您质押的USDT，将通过Shell平台拆借给全球的企业;2.您质押的USDT只能通过一次性全部清算来进行退出，清算中的订单不再享受任何收益，且信誉分会回归至初始状态（20分）; 3.矿池收益的USDT可用于质押，且不收取矿工费; 4.可质押的USDT的上限与您当前的信誉分相关;
         * pledgeAmount : 100,1000,3000,5000,10000,30000
         */

        private int allowed;
        private int creditScore;
        private String descText;
        private String pledgeAmount;

        public int getAllowed() {
            return allowed;
        }

        public void setAllowed(int allowed) {
            this.allowed = allowed;
        }

        public int getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(int creditScore) {
            this.creditScore = creditScore;
        }

        public String getDescText() {
            return descText;
        }

        public void setDescText(String descText) {
            this.descText = descText;
        }

        public String getPledgeAmount() {
            return pledgeAmount;
        }

        public void setPledgeAmount(String pledgeAmount) {
            this.pledgeAmount = pledgeAmount;
        }
    }
}
