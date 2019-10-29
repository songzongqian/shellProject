package com.shell.money.Bean;

import java.util.List;

public class ZhiYaScoreBean {

    /**
     * resultCode : 999999
     * resultData : [{"allowedPledge":"100","creditScore":"20-119","orderType":"snatch","profit":1},{"allowedPledge":"500","creditScore":"20-119","orderType":"snatch","profit":1.5},{"allowedPledge":"1000","creditScore":"20-119","orderType":"snatch","profit":2},{"allowedPledge":"3000","creditScore":"120-199","orderType":"distribute","profit":3},{"allowedPledge":"5000","creditScore":"200-399","orderType":"distribute","profit":3.5},{"allowedPledge":"10000","creditScore":"400-599","orderType":"distribute","profit":4},{"allowedPledge":"50000","creditScore":"600+","orderType":"distribute","profit":5}]
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
         * allowedPledge : 100
         * creditScore : 20-119
         * orderType : snatch
         * profit : 1
         */

        private String allowedPledge;
        private String creditScore;
        private String orderType;
        private float profit;

        public String getAllowedPledge() {
            return allowedPledge;
        }

        public void setAllowedPledge(String allowedPledge) {
            this.allowedPledge = allowedPledge;
        }

        public String getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(String creditScore) {
            this.creditScore = creditScore;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public float getProfit() {
            return profit;
        }

        public void setProfit(float profit) {
            this.profit = profit;
        }
    }
}
