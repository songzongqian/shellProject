package com.shell.commom;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.shell.activity.LoginActivity;
import com.shell.utils.PreManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangungang on 5/11/2019.
 * E-Mail:811832241@qq.com
 */
public class LogonFailureUtil {
    public static void gotoLoginActiviy(Context context,String string) {
        try {
            JSONObject jsonObject = new JSONObject(string);
            String resultCode = jsonObject.getString("resultCode");
            if (!TextUtils.isEmpty(resultCode) && resultCode.equals("200004")){
                PreManager.instance().putBoolean("ISLogin", false);
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*if (string.contains("200004")){
            PreManager.instance().putBoolean("ISLogin", false);
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }*/
    }
}
