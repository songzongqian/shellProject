package com.shell.home.Bean;

import java.io.Serializable;
import java.util.List;

public class TopStaticBean implements Serializable{

    /**
     * resultCode : 999999
     * resultData : {"allMilepost":[{"desc":"Triton Block Chain Laboratory Established","time":"2018-05-28"},{"desc":"Shell, the first laboratory product","time":"2018-10-29"},{"desc":"Signing Technical Cooperation Agreement with Tether","time":"2019-02-15"},{"desc":"Signing a Strategic Cooperation Agreement with McKinsey","time":"2019-05-06"},{"desc":"Shell releases built-in version","time":"2019-08-07"},{"desc":"Shell releases public beta version","time":"2019-09-25"},{"desc":"Shell officially launched, opening up Europe and the United States","time":"2019-09-27"},{"desc":"Shell Opens Korea","time":"2019-10-10"}],"countryData":[{"id":4,"name":"European Union","orderAmount":528303,"orderCount":221200,"superNodeCount":3,"userCount":3865},{"id":5,"name":"U.S.A","orderAmount":121800,"orderCount":251800,"superNodeCount":2,"userCount":2583},{"id":2,"name":"Korea","orderAmount":0,"orderCount":0,"superNodeCount":0,"userCount":0},{"id":3,"name":"Japan","orderAmount":0,"orderCount":0,"superNodeCount":0,"userCount":0},{"id":1,"name":"China","orderAmount":0,"orderCount":0,"superNodeCount":0,"userCount":0}],"creditScoreDesc":"1、抵押数量升级、分配大额订单","hashRateDesc":"算力是由节点的下级节点质押USDT数量以及信用分通过公式计算出来的，算力大小决定挖矿奖励，节点奖励由节点算力与全网算力及信用分以一定比例产出！","networkHashRate":{"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.015,"id":8,"isDeleted":"N","pledgeAmount":2233.0234,"statisticalTime":"2019-10-08 12:55:22","tradingAmount":3303.0032}}
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

    public static class ResultDataBean implements Serializable{
        /**
         * allMilepost : [{"desc":"Triton Block Chain Laboratory Established","time":"2018-05-28"},{"desc":"Shell, the first laboratory product","time":"2018-10-29"},{"desc":"Signing Technical Cooperation Agreement with Tether","time":"2019-02-15"},{"desc":"Signing a Strategic Cooperation Agreement with McKinsey","time":"2019-05-06"},{"desc":"Shell releases built-in version","time":"2019-08-07"},{"desc":"Shell releases public beta version","time":"2019-09-25"},{"desc":"Shell officially launched, opening up Europe and the United States","time":"2019-09-27"},{"desc":"Shell Opens Korea","time":"2019-10-10"}]
         * countryData : [{"id":4,"name":"European Union","orderAmount":528303,"orderCount":221200,"superNodeCount":3,"userCount":3865},{"id":5,"name":"U.S.A","orderAmount":121800,"orderCount":251800,"superNodeCount":2,"userCount":2583},{"id":2,"name":"Korea","orderAmount":0,"orderCount":0,"superNodeCount":0,"userCount":0},{"id":3,"name":"Japan","orderAmount":0,"orderCount":0,"superNodeCount":0,"userCount":0},{"id":1,"name":"China","orderAmount":0,"orderCount":0,"superNodeCount":0,"userCount":0}]
         * creditScoreDesc : 1、抵押数量升级、分配大额订单
         * hashRateDesc : 算力是由节点的下级节点质押USDT数量以及信用分通过公式计算出来的，算力大小决定挖矿奖励，节点奖励由节点算力与全网算力及信用分以一定比例产出！
         * networkHashRate : {"createBy":0,"createTime":"2019-10-15 12:55:28","hashRate":0.015,"id":8,"isDeleted":"N","pledgeAmount":2233.0234,"statisticalTime":"2019-10-08 12:55:22","tradingAmount":3303.0032}
         */

        private String creditScoreDesc;
        private String hashRateDesc;
        private NetworkHashRateBean networkHashRate;
        private List<AllMilepostBean> allMilepost;
        private List<CountryDataBean> countryData;

        public String getCreditScoreDesc() {
            return creditScoreDesc;
        }

        public void setCreditScoreDesc(String creditScoreDesc) {
            this.creditScoreDesc = creditScoreDesc;
        }

        public String getHashRateDesc() {
            return hashRateDesc;
        }

        public void setHashRateDesc(String hashRateDesc) {
            this.hashRateDesc = hashRateDesc;
        }

        public NetworkHashRateBean getNetworkHashRate() {
            return networkHashRate;
        }

        public void setNetworkHashRate(NetworkHashRateBean networkHashRate) {
            this.networkHashRate = networkHashRate;
        }

        public List<AllMilepostBean> getAllMilepost() {
            return allMilepost;
        }

        public void setAllMilepost(List<AllMilepostBean> allMilepost) {
            this.allMilepost = allMilepost;
        }

        public List<CountryDataBean> getCountryData() {
            return countryData;
        }

        public void setCountryData(List<CountryDataBean> countryData) {
            this.countryData = countryData;
        }

        public static class NetworkHashRateBean {
            /**
             * createBy : 0
             * createTime : 2019-10-15 12:55:28
             * hashRate : 0.015
             * id : 8
             * isDeleted : N
             * pledgeAmount : 2233.0234
             * statisticalTime : 2019-10-08 12:55:22
             * tradingAmount : 3303.0032
             */

            private int createBy;
            private String createTime;
            private double hashRate;
            private int id;
            private String isDeleted;
            private double pledgeAmount;
            private String statisticalTime;
            private double tradingAmount;

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

            public double getHashRate() {
                return hashRate;
            }

            public void setHashRate(double hashRate) {
                this.hashRate = hashRate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(String isDeleted) {
                this.isDeleted = isDeleted;
            }

            public double getPledgeAmount() {
                return pledgeAmount;
            }

            public void setPledgeAmount(double pledgeAmount) {
                this.pledgeAmount = pledgeAmount;
            }

            public String getStatisticalTime() {
                return statisticalTime;
            }

            public void setStatisticalTime(String statisticalTime) {
                this.statisticalTime = statisticalTime;
            }

            public double getTradingAmount() {
                return tradingAmount;
            }

            public void setTradingAmount(double tradingAmount) {
                this.tradingAmount = tradingAmount;
            }
        }

        public static class AllMilepostBean implements Serializable {
            /**
             * desc : Triton Block Chain Laboratory Established
             * time : 2018-05-28
             */

            private String desc;
            private String time;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class CountryDataBean {
            /**
             * id : 4
             * name : European Union
             * orderAmount : 528303
             * orderCount : 221200
             * superNodeCount : 3
             * userCount : 3865
             */

            private int id;
            private String name;
            private int orderAmount;
            private int orderCount;
            private int superNodeCount;
            private int userCount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(int orderAmount) {
                this.orderAmount = orderAmount;
            }

            public int getOrderCount() {
                return orderCount;
            }

            public void setOrderCount(int orderCount) {
                this.orderCount = orderCount;
            }

            public int getSuperNodeCount() {
                return superNodeCount;
            }

            public void setSuperNodeCount(int superNodeCount) {
                this.superNodeCount = superNodeCount;
            }

            public int getUserCount() {
                return userCount;
            }

            public void setUserCount(int userCount) {
                this.userCount = userCount;
            }
        }
    }

}
