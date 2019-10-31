package com.shell.Bean;

public class NoticeBean  {

    /**
     * resultCode : 999999
     * resultData : After the order is received, the system will start to distribute the order. If the pledge quantity reaches 3000USDT, the order will be directly sent to your order details and processed directly;  After you stop working, be sure to click Stop Order, Distribute An unprocessed order will trigger a penalty mechanism;
     * resultDesc : Success
     */

    private String resultCode;
    private String resultData;
    private String resultDesc;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
