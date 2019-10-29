package com.shell.Bean;

public class MessageBean {

    /**
     * resultCode : 999999
     * resultData : 0
     * resultDesc : 成功！
     */

    private String resultCode;
    private int resultData;
    private String resultDesc;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultData() {
        return resultData;
    }

    public void setResultData(int resultData) {
        this.resultData = resultData;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
