package com.shell.utils;

import com.shell.R;

import java.util.Date;

public class GetWillTime {

    public static String getTimeFormatText(Date date) {
        long minute = 60 * 1000;// 1分钟
        long hour = 60 * minute;// 1小时
        long day = 24 * hour;// 1天
        long month = 31 * day;// 月
        long year = 12 * month;// 年

        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "d";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "h";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "m";
        }
        return "g";
    }
}
