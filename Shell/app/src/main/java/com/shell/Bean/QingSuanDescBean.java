package com.shell.Bean;

public class QingSuanDescBean  {

    /**
     * resultCode : 999999
     * resultDesc : Success
     * resultData : 1. You are about to liquidate the pledge funds;
     2. The current unfinished orders no longer calculate the revenue;
     3. The system will match your order first, please continue to pay attention and deal with it in time;
     4.All orders are completed After that, all your pledge funds will be refunded to the USDT wallet account after deducting 3% of the service fee;
     5. After the liquidation, all credits will be restored to 20 points, and the next re-pledge will no longer enjoy all current credits. rights and interests;
     */

    private String resultCode;
    private String resultDesc;
    private String resultData;

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

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }
}
