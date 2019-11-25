package com.shell.utils;

import java.text.DecimalFormat;

public class GetTwoLetter {
    public static String getTwo(String num){
        if ("0".equals(num)||"0.00".equals(num)||"0.0".equals(num)||isNumeric(num)){
            return num;
        }else {
            Double d= Double.parseDouble(num);
            DecimalFormat df = new DecimalFormat("0.00");
            String s = df.format(d);
            return s;
        }
    }
    public static String getFour(String num){
        if ("0".equals(num)||"0.00".equals(num)||"0.0".equals(num)||isNumeric(num)){
            return num;
        }else {
            Double d= Double.parseDouble(num);
            DecimalFormat df = new DecimalFormat("0.0000");
            String s = df.format(d);
            return s;
        }
    }
    /**
     * 是否为整数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
