package com.shell.Bean;

import java.util.List;

public class JieDianBean  {

    /**
     * resultCode : 999999
     * resultData : {"allHashRate":0,"brokerComplete":0,"brokerNeed":10,"hashRate":0,"level":1,"levelTextDesc":"用户等级分为 B lv1- S lv5 ，对直属节点以及所有下属节点算力有要求.","lstLevelProfit":[{"allHashRate":"--","award":"5级节点，20%起","levelDesc":"B lv1","ownBroker":"--"},{"allHashRate":"--","award":"5级节点，30%起","levelDesc":"B lv2","ownBroker":"10"},{"allHashRate":"--","award":"6级节点，40%起","levelDesc":"B lv3","ownBroker":"15"},{"allHashRate":"--","award":"6级节点，50%起","levelDesc":"B lv4","ownBroker":"20"},{"allHashRate":"500,000","award":"B lv4+额外所有节点1%","levelDesc":"S lv1","ownBroker":"50"},{"allHashRate":"800,000","award":"B lv4+额外所有节点3%","levelDesc":"S lv2","ownBroker":"50"},{"allHashRate":"1,000,000","award":"B lv4+额外所有节点5%","levelDesc":"S lv3","ownBroker":"50"},{"allHashRate":"2,000,000","award":"B lv4+额外所有节点7%","levelDesc":"S lv4","ownBroker":"80"},{"allHashRate":"5,000,000","award":"B lv4+额外所有节点10%","levelDesc":"S lv5","ownBroker":"100"}]}
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
         * allHashRate : 0
         * brokerComplete : 0
         * brokerNeed : 10
         * hashRate : 0
         * level : 1
         * levelTextDesc : 用户等级分为 B lv1- S lv5 ，对直属节点以及所有下属节点算力有要求.
         * lstLevelProfit : [{"allHashRate":"--","award":"5级节点，20%起","levelDesc":"B lv1","ownBroker":"--"},{"allHashRate":"--","award":"5级节点，30%起","levelDesc":"B lv2","ownBroker":"10"},{"allHashRate":"--","award":"6级节点，40%起","levelDesc":"B lv3","ownBroker":"15"},{"allHashRate":"--","award":"6级节点，50%起","levelDesc":"B lv4","ownBroker":"20"},{"allHashRate":"500,000","award":"B lv4+额外所有节点1%","levelDesc":"S lv1","ownBroker":"50"},{"allHashRate":"800,000","award":"B lv4+额外所有节点3%","levelDesc":"S lv2","ownBroker":"50"},{"allHashRate":"1,000,000","award":"B lv4+额外所有节点5%","levelDesc":"S lv3","ownBroker":"50"},{"allHashRate":"2,000,000","award":"B lv4+额外所有节点7%","levelDesc":"S lv4","ownBroker":"80"},{"allHashRate":"5,000,000","award":"B lv4+额外所有节点10%","levelDesc":"S lv5","ownBroker":"100"}]
         */

        private String allHashRate;
        private int brokerComplete;
        private int brokerNeed;
        private String hashRate;
        private int level;
        private String levelTextDesc;
        private List<LstLevelProfitBean> lstLevelProfit;

        public String getAllHashRate() {
            return allHashRate;
        }

        public void setAllHashRate(String allHashRate) {
            this.allHashRate = allHashRate;
        }

        public int getBrokerComplete() {
            return brokerComplete;
        }

        public void setBrokerComplete(int brokerComplete) {
            this.brokerComplete = brokerComplete;
        }

        public int getBrokerNeed() {
            return brokerNeed;
        }

        public void setBrokerNeed(int brokerNeed) {
            this.brokerNeed = brokerNeed;
        }

        public String getHashRate() {
            return hashRate;
        }

        public void setHashRate(String hashRate) {
            this.hashRate = hashRate;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLevelTextDesc() {
            return levelTextDesc;
        }

        public void setLevelTextDesc(String levelTextDesc) {
            this.levelTextDesc = levelTextDesc;
        }

        public List<LstLevelProfitBean> getLstLevelProfit() {
            return lstLevelProfit;
        }

        public void setLstLevelProfit(List<LstLevelProfitBean> lstLevelProfit) {
            this.lstLevelProfit = lstLevelProfit;
        }

        public static class LstLevelProfitBean {
            /**
             * allHashRate : --
             * award : 5级节点，20%起
             * levelDesc : B lv1
             * ownBroker : --
             */

            private String allHashRate;
            private String award;
            private String levelDesc;
            private String ownBroker;

            public String getAllHashRate() {
                return allHashRate;
            }

            public void setAllHashRate(String allHashRate) {
                this.allHashRate = allHashRate;
            }

            public String getAward() {
                return award;
            }

            public void setAward(String award) {
                this.award = award;
            }

            public String getLevelDesc() {
                return levelDesc;
            }

            public void setLevelDesc(String levelDesc) {
                this.levelDesc = levelDesc;
            }

            public String getOwnBroker() {
                return ownBroker;
            }

            public void setOwnBroker(String ownBroker) {
                this.ownBroker = ownBroker;
            }
        }
    }
}
