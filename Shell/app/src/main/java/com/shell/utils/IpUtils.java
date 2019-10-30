package com.shell.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.yanzhenjie.nohttp.Logger;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

public class IpUtils {

    public static String GetNetIp() {
        String IP = "";
        try {
            String address = "http://ip.taobao.com/service/getIpInfo2.php?ip=myip";
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();


               // 将流转化为字符串
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                String tmpString = "";
                StringBuilder retJSON = new StringBuilder();
                while ((tmpString = reader.readLine()) != null) {
                    retJSON.append(tmpString + "");
                }


                JSONObject jsonObject = new JSONObject(retJSON.toString());
                String code = jsonObject.getString("code");
                if (code.equals("0")) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    IP = data.getString("ip");
                    Log.i("song","ip地址是"+IP);
                } else {
                    IP="";
                }
            } else {
                IP="";
            }
        } catch (Exception e) {
            IP="";
        }
        return IP;
    }


}
