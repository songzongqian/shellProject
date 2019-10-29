package com.shell.utils;

import java.text.DecimalFormat;

public class GetTwoLetter {

    public static String getTwo(String num){
        Double d= Double.parseDouble(num);
        DecimalFormat df = new DecimalFormat("0.00");
        String s = df.format(d);
        return s;

    }
}
