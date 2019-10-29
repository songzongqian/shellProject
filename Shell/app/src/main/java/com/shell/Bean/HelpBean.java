package com.shell.Bean;

public class HelpBean {

    /**
     * resultCode : 999999
     * resultData : {"descText":"1、很喜欢的是查询","inviteUrl":"http://www.tritonlab.net/toRegister?inviteCode=CXWIDB","myinviteCode":"CXWIDB"}
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
         * descText : 1、很喜欢的是查询
         * inviteUrl : http://www.tritonlab.net/toRegister?inviteCode=CXWIDB
         * myinviteCode : CXWIDB
         */

        private String descText;
        private String inviteUrl;
        private String myinviteCode;

        public String getDescText() {
            return descText;
        }

        public void setDescText(String descText) {
            this.descText = descText;
        }

        public String getInviteUrl() {
            return inviteUrl;
        }

        public void setInviteUrl(String inviteUrl) {
            this.inviteUrl = inviteUrl;
        }

        public String getMyinviteCode() {
            return myinviteCode;
        }

        public void setMyinviteCode(String myinviteCode) {
            this.myinviteCode = myinviteCode;
        }
    }
}
