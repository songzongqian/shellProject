package com.shell.Bean;

import java.util.List;

public class LetterBean {


    /**
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * resultCode : 999999
     * resultData : [{"content":"尊敬的Shell用户，恭喜你注册成功！","createBy":1,"createTime":"2019-10-16 15:09:16","id":8,"isDeleted":"N","readFlag":"N","title":"注册成功","userId":8}]
     * resultDesc : 成功！
     * total : 1
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
         * content : 尊敬的Shell用户，恭喜你注册成功！
         * createBy : 1
         * createTime : 2019-10-16 15:09:16
         * id : 8
         * isDeleted : N
         * readFlag : N
         * title : 注册成功
         * userId : 8
         */

        private String content;
        private int createBy;
        private String createTime;
        private long id;
        private String isDeleted;
        private String readFlag;
        private String title;
        private int userId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getReadFlag() {
            return readFlag;
        }

        public void setReadFlag(String readFlag) {
            this.readFlag = readFlag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
