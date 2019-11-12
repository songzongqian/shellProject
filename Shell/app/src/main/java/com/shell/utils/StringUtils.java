package com.shell.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.shell.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangjungang on 8/11/2019.
 * E-Mail:811832241@qq.com
 */
public class StringUtils  {



    public static  boolean updateApp(Activity activity, String newVersion) {
        String localVersion = getVersionName(activity);
        String[] localVersionArray = localVersion.split("\\.");
        String[] newVersionArray = newVersion.split("\\.");
        if (localVersionArray.length < newVersionArray.length) {
            int cha = newVersionArray.length - localVersionArray.length;
            for (int i = 0; i < cha; i++) {
                localVersion = localVersion + ".0";
            }
            localVersionArray = localVersion.split("\\.");
        }
        try {
            for (int i = 0; i < newVersionArray.length; i++) {
                int temp = Integer.parseInt(newVersionArray[i]);
                int compar = Integer.parseInt(localVersionArray[i]);
                if (temp > compar) {
                    return true;
                }else if (temp < compar){
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getVersionName(Activity activity){
        PackageManager packageManager = activity.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(activity.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String currentVersion = packInfo.versionName;

        return currentVersion;
    }

    //对时间进行更改
    @SuppressLint("SimpleDateFormat")
    public static String getTime(Activity activity, Date rel_time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        String backStr = "";

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = rel_time;
            d2 = date;
            // 毫秒ms
            long diff = d2.getTime() - d1.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays != 0) {
                //if (diffDays < 30) {
       /*         if (1 < diffDays && diffDays < 2) {
                    backStr = "昨天";
                } else if (1 < diffDays && diffDays < 2) {
                    backStr = "前天";
                } else {*/
                    backStr = String.valueOf(diffDays) + activity.getString(R.string.Days_ago);
              //  }
                //                } else {
                //                    backStr = "很久以前";
                //                }
            } else if (diffHours != 0) {
                backStr = String.valueOf(diffHours) + activity.getString(R.string.Hours_before);
            } else if (diffMinutes != 0) {
                backStr = String.valueOf(diffMinutes) + activity.getString(R.string.Minutes_ago);
            } else {
                backStr = activity.getString(R.string.just);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return backStr;

    }
    public static String date2TimeStamp(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return String.valueOf(sdf.parse(date).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
