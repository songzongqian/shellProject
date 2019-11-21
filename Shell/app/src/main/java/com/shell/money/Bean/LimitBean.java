package com.shell.money.Bean;

import java.io.Serializable;

/**
 * Created by wangjungang on 19/11/2019.
 * E-Mail:811832241@qq.com
 */
public class LimitBean implements Serializable {

    /**
     * resultCode : 999999
     * resultDesc : Success
     * resultData : {"minWithdrawAmount":10,"fee":5}
     */

    private String resultCode;
    private String resultDesc;
    private ResultDataBean resultData;

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

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * minWithdrawAmount : 10
         * fee : 5
         */

        private int minWithdrawAmount;
        private int fee;

        public int getMinWithdrawAmount() {
            return minWithdrawAmount;
        }

        public void setMinWithdrawAmount(int minWithdrawAmount) {
            this.minWithdrawAmount = minWithdrawAmount;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }
    }
}
