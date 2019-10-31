package com.shell.order.bean;

public class OFFSuccessBean {

    /**
     * resultCode : 010105
     * resultDesc : o USDT has been pledged yet, please pledged
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
