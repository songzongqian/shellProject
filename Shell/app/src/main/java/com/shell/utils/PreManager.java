package com.shell.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;
import com.shell.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * desc
 * author  785917397@qq.com
 * create time  2018/5/29 0029 17:03
 * Reference link
 */

public class PreManager {
    private static PreManager preManager;
    private SharedPreferences mShare = null;
    private Gson mGson = new GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
            .create();

    public SharedPreferences getShare() {
        return mShare;
    }

    private PreManager() {
        mShare = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppInstance());
    }

    public static synchronized PreManager instance() {
        if (preManager == null) {
            preManager = new PreManager();
        }
        return preManager;
    }
    //   保存录音结果
    public void putLuyinResult(String value) {
        putString("LuyinResult", value);
    }

    public String getLuyinResult() {
        return getString("LuyinResult");
    }

    ////////1、设置y有没有打开过//////////////////////////////////////////////////
    public void putIsOpened(boolean b) {
        putBoolean("isOpened", b);
    }

    public Boolean getIsOpened() {
        return getBoolean("isOpened");
    }

    ////////2、打开APP的时候获取一个签名//////////////////////////////////////////
    public void putSignature(String value) {
        putString("signature", value);
    }

    public String getSignature() {
        return getString("signature");
    }

    ////////3、记住密码//////////////////////////////////////////////////
    public void putIsRemberPsd(boolean b) {
        putBoolean("isRemberPsd", b);
    }

    public Boolean getIsRemberPsd() {
        return getBoolean("isRemberPsd");
    }

    ////////4、自动登录//////////////////////////////////////////////////
    public void putIsAutoLogin(boolean b) {
        putBoolean("isAutoLogin", b);
    }

    public Boolean getIsAutoLogin() {
        return getBoolean("isAutoLogin");
    }


    /////////////登录信息处理   以下
    ///////5、最后一次登录成功时的用户名和密码，加密过得////////////////////
    // 登录时的用户名
    public void putLoginUserName(String value) {
        putString("loginUserName", value);
    }

    public String getLoginUserName() {
        return getString("loginUserName");
    }

    //登录时的密码
    public void putLoginUserPsd(String value) {
        putString("loginUserPsd", value);
    }

    public String getLoginUserPsd() {
        return getString("loginUserPsd");
    }

    ///////6、登录成功后保存的参数////////////////////
    //参数1：除了"DATAS"之外的参数
//        "PWDINDEX": "C834DAA7009DD291F4DE453CA29E731E",
//                "STATE": "00000",
//                "USER_ID": "4F1769269DF647A89BB70F0B9D9E3F48",
//                "USER_NAME": "刘加玉",
//                "USER_PHONE": "13723023278"
    public void putUserCommonInfor(String value) { //保存除了 "DATAS"之外的其他参数
        putString("userInforByLogin", value);
    }




    public HashMap<String, String> getUserCommonInfor() {
        HashMap<String, String> hashMap = mGson.fromJson(getString("userInforByLogin"), new TypeToken<HashMap<String, String>>() {
        }.getType());
        return hashMap;
    }



    //参数2："DATAS"IDENTITYS 数组         组织信息
    public void putIdentitysInfor(String value) {
        putString("IDENTITYS", value);
    }

    public ArrayList<HashMap<String, String>> getIdentitysInfor() {
        ArrayList<HashMap<String, String>> list = mGson.fromJson(getString("IDENTITYS"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
        return list;
    }

    //参数3："DATAS"内的ROLE数组         角色信息
    public void putUserRoleInfor(String value) { //保存除了 "DATAS"之外的其他参数
        putString("ROLE", value);
    }

    public ArrayList<HashMap<String, String>> getUserRoleInfor() {
        ArrayList<HashMap<String, String>> list = mGson.fromJson(getString("ROLE"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
        return list;
    }

    //参数4：根据 ROLE 中的ROLE_ID  来判断登陆者是什么角色
    //用户可以有多个角色，领导者的权限最高，其他角色平级，所以要首先判断是不是领导者
    //权限分类 BFZRR ZCGZD   LDZ
    //一共三个角色        领导者、帮扶责任人、驻村工作队和驻村第一书记
    //12.单独保存 角色ID
    public void putRoleId(String value) {
        putString("roleId", value);
    }
    public void putUserId(String value) {
        putString("USER_ID", value);
    }

    public String getRoleId() {
        return getString("roleId");
    }

    //13.单独保存 角色名字
    public void putRoleName(String value) {
        putString("roleName", value);
    }

    public String getRoleName() {
        return getString("roleName");
    }


    //5.单独保存 PWDINDEX，因为后边每次请求都要用到
    public String getPwdIndex() {
        return getUserCommonInfor().get("PWDINDEX");
    }

    //6.获取电话
    public String getUserPhone() {
        return getUserCommonInfor().get("USER_PHONE");
    }

    //7.获取用户名
    public String getUserName() {
        return getUserCommonInfor().get("USER_NAME");
    }

    //8.获取用户名
    public String getUserID() {
        return getUserCommonInfor().get("USER_ID");
    }






    //第七大项，我的界面的语音开关
    public void putHaveVoice(boolean b) {
        putBoolean("isHaveVoice", b);
    }

    public Boolean isHaveVoice() {
        return getBoolean("isHaveVoice");
    }

    ///////////下拉选中用到的
    //1.获取性别列表
    public ArrayList<HashMap<String, String>> getXingbieList() {
        return mGson.fromJson(getString("SEX"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //2.获取民族列表
    public ArrayList<HashMap<String, String>> getMinzuList() {
        return mGson.fromJson(getString("NATION"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //3.获取贫困户属性列表
    public ArrayList<HashMap<String, String>> getPoorerAttributeList() {
        return mGson.fromJson(getString("POORER_ATTRIBUTE"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //4.获取贫困户状态列表
    public ArrayList<HashMap<String, String>> getPoorerStatusList() {
        return mGson.fromJson(getString("POORER_STATUS"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //5.获取文化程度列表
    public ArrayList<HashMap<String, String>> getWenhuaLevelList() {
        return mGson.fromJson(getString("EDUCATION"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //6.获取政治面貌列表
    public ArrayList<HashMap<String, String>> getZhengzhiMianmaoList() {
        return mGson.fromJson(getString("POLICIAL"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //7.获取劳动技能列表
    public ArrayList<HashMap<String, String>> getWorkJinengList() {
        return mGson.fromJson(getString("WORK_SKILL"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //8.获取健康状况列表
    public ArrayList<HashMap<String, String>> getJiankangStateList() {
        return mGson.fromJson(getString("HEALTH"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //9.是和否
    public ArrayList<HashMap<String, String>> getYesOrNoList() {
        return mGson.fromJson(getString("YES_NO"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //10.与户主关系
    public ArrayList<HashMap<String, String>> getYuHuzhuGuanxiList() {
        return mGson.fromJson(getString("HOLDER_RELATION"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //11.上学状况
    public ArrayList<HashMap<String, String>> getShangxueZhuangkuangList() {
        return mGson.fromJson(getString("SCHOOL_CONDITION"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //12.扶持方式
    public ArrayList<HashMap<String, String>> getFuchiFangshiList() {
        return mGson.fromJson(getString("SUPPORT_MODE"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //12.增减情况
    public ArrayList<HashMap<String, String>> getZengjianQingkuangList() {
        return mGson.fromJson(getString("POORER_ADDINFO"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //13.主要致贫原因
    public ArrayList<HashMap<String, String>> getMainCausesOfPovertyList() {
        return mGson.fromJson(getString("POORER_CAUSE"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //14，主要燃料类型
    public ArrayList<HashMap<String, String>> getMainRanliaoStypesList() {
        return mGson.fromJson(getString("ZYRL_TYPE"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    //15，入户路类型
    public ArrayList<HashMap<String, String>> getRuhuluStypesList() {
        return mGson.fromJson(getString("RHL_TYPE"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    ///// 1、  贫困村属性                              POVERTY_PROPERTY
    public ArrayList<HashMap<String, String>> getPovertyPropertyList() {
        return mGson.fromJson(getString("POVERTY_PROPERTY"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    ///// 2、  发展方向属性       DEVELOPMENT_PROPERTY
    public ArrayList<HashMap<String, String>> getDevelopmentPropertyList() {
        return mGson.fromJson(getString("DEVELOPMENT_PROPERTY"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    // 3、  地形地貌属性
    public ArrayList<HashMap<String, String>> getTerrainPropertyList() {
        return mGson.fromJson(getString("TERRAIN_PROPERTY"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    // 3、  项目类型
    public ArrayList<HashMap<String, String>> getProjectStypeList() {
        return mGson.fromJson(getString("PRO_TYPE"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
    }
    ////////////////////////////////////////


    /**
     * 存入字符串
     *
     * @param key   字符串的键
     * @param value 字符串的值
     */
    public void putString(String key, String value) {
        mShare.edit().putString(key, value).commit();
    }

    /**
     * 获取字符串
     *
     * @param key 字符串的键
     * @return 得到的字符串
     */
    public String getString(String key) {
        return mShare.getString(key, "");
    }


    /**
     * 存入boolean
     *
     * @param key 字符串的键
     * @param b   boolean的值
     */
    public void putBoolean(String key, boolean b) {
        mShare.edit().putBoolean(key, b).commit();
    }

    /**
     * 获取字符串
     *
     * @param key 字符串的键
     * @return 得到的字符串
     */
    public Boolean getBoolean(String key) {
        return mShare.getBoolean(key, false);
    }
}
