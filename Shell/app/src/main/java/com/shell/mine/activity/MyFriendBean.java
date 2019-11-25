package com.shell.mine.activity;

import java.util.List;

public class MyFriendBean {

    /**
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * resultCode : 999999
     * resultData : [{"creditScore":20,"email":"347303841@qq.com","hashRate":0,"invitedCount":0,"name":"347303841","userId":9},{"creditScore":20,"email":"1342885750@qq.com","hashRate":0,"invitedCount":0,"name":"1342885750","userId":10},{"creditScore":20,"email":"347303842@qq.com","hashRate":0,"invitedCount":0,"name":"347303842","userId":11}]
     * resultDesc : 成功！
     * total : 3
     */

    private int pageNum;
    private int pageSize;
    private int pages;
    private String resultCode;
    private String resultDesc;
    private int total;
    private List<ResultDataBean> resultData;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ResultDataBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean> resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * creditScore : 20
         * email : 347303841@qq.com
         * hashRate : 0
         * invitedCount : 0
         * name : 347303841
         * userId : 9
         */

        private String creditScore;
        private String email;
        private String hashRate;
        private String invitedCount;
        private String name;
        private int userId;
        private String portrait;
        private String pledgeAmount;
        private String showLevel;
        private int level;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getShowLevel() {
            return showLevel;
        }

        public void setShowLevel(String showLevel) {
            this.showLevel = showLevel;
        }

        public String getPledgeAmount() {
            return pledgeAmount;
        }

        public void setPledgeAmount(String pledgeAmount) {
            this.pledgeAmount = pledgeAmount;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(String creditScore) {
            this.creditScore = creditScore;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHashRate() {
            return hashRate;
        }

        public void setHashRate(String hashRate) {
            this.hashRate = hashRate;
        }

        public String getInvitedCount() {
            return invitedCount;
        }

        public void setInvitedCount(String invitedCount) {
            this.invitedCount = invitedCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
