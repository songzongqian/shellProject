package com.shell.money.Bean;

public class TiBiResult {

    /**
     * resultCode : 010106
     * resultDesc : 单笔提币应在100-10000之间!
     * resultData : null
     */

    private String resultCode;
    private String resultDesc;
    private Object resultData;

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

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}
