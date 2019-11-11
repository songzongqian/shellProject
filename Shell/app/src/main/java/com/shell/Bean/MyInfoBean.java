package com.shell.Bean;

public class MyInfoBean {

    /**
     * resultCode : 999999
     * resultDesc : 成功！
     * resultData : {"name":"3501222500","email":"3501222500@qq.com","portrait":"https://shelllab.oss-cn-hongkong.aliyuncs.com/portrait/cfab21481486491da7aa290b39308935.jpg","myinviteCode":"CXWIDB"}
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
         * name : 3501222500
         * email : 3501222500@qq.com
         * portrait : https://shelllab.oss-cn-hongkong.aliyuncs.com/portrait/cfab21481486491da7aa290b39308935.jpg
         * myinviteCode : CXWIDB
         */

        private String name;
        private String email;
        private String portrait;
        private String myinviteCode;
        private boolean payPasswordFlag;

        public boolean isPayPasswordFlag() {
            return payPasswordFlag;
        }

        public void setPayPasswordFlag(boolean payPasswordFlag) {
            this.payPasswordFlag = payPasswordFlag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getMyinviteCode() {
            return myinviteCode;
        }

        public void setMyinviteCode(String myinviteCode) {
            this.myinviteCode = myinviteCode;
        }
    }
}
