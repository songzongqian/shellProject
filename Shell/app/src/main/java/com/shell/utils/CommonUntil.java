package com.shell.utils;

import android.util.Log;


import com.shell.MyApplication;
import com.shell.constant.AppUrl;

import java.io.File;

/**
 * desc
 * author  785917397@qq.com
 * create time  2018/6/13 0013 8:52
 * Reference link
 */

public class CommonUntil {
    /**
     * 清除WebView缓存
     */
    public static void clearWebViewCache(){
        //清理Webview缓存数据库
//        try {
//            deleteDatabase("webview.db");
//            deleteDatabase("webviewCache.db");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //WebView 缓存文件
        File appCacheDir = new File(MyApplication.getAppInstance().getFilesDir().getAbsolutePath()+ AppUrl.CACAHE_DIRNAME);
        Log.e("清理缓存0", "appCacheDir path="+appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(MyApplication.getAppInstance().getFilesDir().getAbsolutePath()+ AppUrl.CACAHE_DIRNAME);
        Log.e("清理缓存1", "webviewCacheDir path="+webviewCacheDir.getAbsolutePath());

        //删除webview 缓存目录
        if(webviewCacheDir.exists()){
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if(appCacheDir.exists()){
            deleteFile(appCacheDir);
        }
    }
    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public static   void deleteFile(File file) {
        Log.i("清理缓存2", "delete file path=" + file.getAbsolutePath());
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            Log.e("清理缓存3", "delete file no exists " + file.getAbsolutePath());
        }
    }


}
