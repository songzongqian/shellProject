package com.shell.constant;

public class AppUrl {

    public static final String CACAHE_DIRNAME = "/webcache";

    public static final String BASE_URL = "https://api.tritonlab.net";


    /**获取邮箱验证码*/
    public static final String getEmailCode = BASE_URL + "/sendRegisterEmail?";
    /**注册*/
    public static final String RegisterUrl = BASE_URL + "/register?";
    /**登录*/
    public static final String LoginUrl = BASE_URL + "/login?";
    /**上传图片获取url*/
    public static final String PostImage = BASE_URL + "/components/file/uploadImg?";
    /**上传imageurl*/
    public static final String PostURLImage = BASE_URL + "/user/updatePortrait?";

    /**交易的密码获取验证码*/
    public static final String getJiaoYiCode = BASE_URL + "/user/sendResetPayPasswordEmail?";

    /**交易密码*/
    public static final String JiaoYiURL = BASE_URL + "/user/resetPayPassword?";

    /**个人中心我的资料*/
    public static final String MyInforUrl = BASE_URL + "/user/readInfo?";

    /**忘记密码【重置密码】邮件*/
    public static final String ForgetCode = BASE_URL + "/sendResetPasswordEmail?";

    /**忘记密码【重置密码】*/
    public static final String ResetCode = BASE_URL + "/resetPasswordForget?";

    /**我的好友*/
    public static final String MyFriendUrl = BASE_URL + "/user/queryMyFriends/1?";

    /**我的邀请信息*/
    public static final String MyHelpUrl = BASE_URL + "/user/getMyInviteInfo?";

    /**未读消息数量*/
    public static final String UnReadCount = BASE_URL + "/message/getUnReadeMsgCount?";
    /**修改登录密码*/
    public static final String ChangeLoginPwd = BASE_URL + "/resetPassword?";

    /**钱包上方卡片数据*/
    public static final String CardDataUrl = BASE_URL + "/wallet/readWalletInfo?";

    /**钱包下方数据*/
    public static final String CardUnderUrl = BASE_URL + "/wallet/queryWalletDetail/1?";

    /**质押页面数据*/
    public static final String ZhiYaUrl = BASE_URL + "/wallet/getPledgePreInfo?";

    /**获取用户消息数量*/
    public static final String WebLrtterCount = BASE_URL + "/message/queryMsgList/1?";

    /**上传消息已读标识*/
    public static final String PostLetterID = BASE_URL + "/message/readMsg?";

    /**钱包充值的地址*/
    public static final String GiveMoneyUrl= BASE_URL + "/wallet/getWalletAddress?";

    /**抢单页面全网数据*/
    public static final String AllNetUrl= BASE_URL + "/order/getSettlData?";

    /**获取接单状态*/
    public static final String GetOrderState= BASE_URL + "/order/getStatus?";

    /**抢单页面的未完成订单*/
    public static final String GetToDoOrder= BASE_URL + "/order/queryOrderList/1?";

    /**提币接口*/
    public static final String TIBIURL= BASE_URL + "/wallet/withdraw?";

    /**质押页面权益列表*/
    public static final String ZhiYaScore= BASE_URL + "/wallet/getPledgeProfitInfo?";

    /**获取首页静态数据*/
    public static final String HomeStaticUrl= BASE_URL + "/getStaticInfo?";

    /**获取首页用户数据*/
    public static final String HomeUserData= BASE_URL + "/account/readAccount?";

    /**获取首页矿池奖励数据*/
    public static final String KuangChiJiangLi= BASE_URL + "/account/queryHashAwardList/1?";

    /**修改昵称*/
    public static final String ChangNick= BASE_URL + "/user/saveName?";

    /**全球汇率*/
    public static final String OnLineHuiLv= BASE_URL + "/exchangeRate/getLastestVersion?";

    /**质押操作的Url*/
    public static final String ZhiYaDataLv= BASE_URL + "/wallet/pledge?";

    /**上传用户操作的接单状态*/
    public static final String getOrderStatue= BASE_URL + "/order/updateStatus?";

    /**用户节点页面*/
    public static final String getUserJieDian= BASE_URL + "/user/readUserLevelInfo?";

    /**获取用户今日收益*/
    public static final String getTodayShouYi= BASE_URL + "/wallet/getTodayProfit?";

    /**获取用户历史收益*/
    public static final String getHistoryShouYi= BASE_URL + "/wallet/queryUserProfit/1?";

    /**获取全网算力xy*/
    public static final String getAllInternet= BASE_URL + "/hashRate/queryNetworkHashRateList?";

    /**去抢单*/
    public static final String getServerOrder= BASE_URL + "/order/scramble?";

    /**清算文案*/
    public static final String getQingSuanTitle= BASE_URL + "/wallet/getClearTextDesc?";

    /**开始清算*/
    public static final String QingSuanURL= BASE_URL + "/order/clear?";

    /**根据id获取订单详情*/
    public static final String unorderDetail= BASE_URL + "/order/readOrderInfo/";


    /**
     * 获取外网IP
     */
    public static final String Get_IP_Address = "http://apis.juhe.cn/ip/ipNew?ip=40.113.200.201&key=e2a0ddd8aa7463579a1d18da808868dc";
    /**
     * 版本是否升级
     */
    public static final String CheckUpdateVersion = BASE_URL + "/checkUpdates/android?";

    /**
     * 获取接单文案
     */
    public static final String getOrderWord = BASE_URL + "/order/getUserStatusTextDesc?";




    /**
     * 转出订单
     */
    public static final String ZhuanChu_Ordert = BASE_URL + "/order/process";






}
