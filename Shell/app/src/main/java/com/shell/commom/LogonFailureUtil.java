package com.shell.commom;

import android.content.Context;
import android.content.Intent;

import com.shell.activity.LoginActivity;
import com.shell.utils.PreManager;

/**
 * Created by wangungang on 5/11/2019.
 * E-Mail:811832241@qq.com
 */
public class LogonFailureUtil {
    public static void gotoLoginActiviy(Context context,String string) {
        if (string.contains("200004")){
            PreManager.instance().putBoolean("ISLogin", false);
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }
}
