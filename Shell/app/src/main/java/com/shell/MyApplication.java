package com.shell;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import com.github.jokar.multilanguages.library.LanguageLocalListener;
import com.github.jokar.multilanguages.library.MultiLanguage;
import com.shell.utils.LocalManageUtil;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.WebSocketManager;
import com.zhangke.websocket.WebSocketSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.shell.utils.LocalManageUtil.getSetLanguageLocale;

public class MyApplication extends Application {
    private static MyApplication app;
    public static boolean canShow = true;
    public static Activity topactivity;
    public static List<Activity> activityList = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context base) {
        //第一次进入app时保存系统选择语言(为了选择随系统语言时使用，如果不保存，切换语言后就拿不到了）
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(MultiLanguage.setLocal(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //用户在系统设置页面切换语言时保存系统选择语言(为了选择随系统语言时使用，如果不保存，切换语言后就拿不到了）
        LocalManageUtil.saveSystemCurrentLanguage(getApplicationContext(), newConfig);
        MultiLanguage.onConfigurationChanged(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initNet();
        initWebSocket();

        MultiLanguage.init(new LanguageLocalListener() {
            @Override
            public Locale getSetLanguageLocale(Context context) {
                //返回自己本地保存选择的语言设置
                return LocalManageUtil.getSetLanguageLocale(context);
            }
        });
        MultiLanguage.setApplicationLanguage(this);
        ManageActivity();
    }



    public static Application getAppInstance() {
        return app;
    }




    public static void setApplicationLanguage(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = getSetLanguageLocale(context);
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(config);
            Locale.setDefault(locale);
        }
        resources.updateConfiguration(config, dm);
    }



    public static void initNet() {
        String language = PreManager.instance().getString("language");
        // 初始化nohttp 网络框架
        InitializationConfig nohttpConfig = InitializationConfig.newBuilder(MyApplication.getAppInstance())
                // 设置全局连接超时时间，单位毫秒，默认10s。
                .connectionTimeout(30 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .cacheStore(
                        new DBCacheStore(app).setEnable(true) // 如果不使用缓存，设置setEnable(false)禁用。
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现。
                .cookieStore(
                        new DBCookieStore(app).setEnable(false) // 如果不维护cookie，设置false禁用。
                )
                // 配置网络层，URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                .networkExecutor(new OkHttpNetworkExecutor())
                // 全局通用Header，add是添加，多次调用add不会覆盖上次add。
                .addHeader("lang",language)
                .retry(3) // 全局重试次数，配置后每个请求失败都会重试x次。
                .build();
        NoHttp.initialize(nohttpConfig);
        //日志
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 打印Log的tag。
    }


    private void initWebSocket(){
        WebSocketSetting setting = new WebSocketSetting();
        //连接地址，必填，例如 wss://echo.websocket.org
        //String token="a506145814f245d8897935589e4a4db8";
        String token = PreManager.instance().getString("token");
        setting.setConnectUrl("wss://t.tritonlab.net/webSocket/"+token);//必填

        //设置连接超时时间
        setting.setConnectTimeout(15 * 1000);

        //设置心跳间隔时间
        setting.setConnectionLostTimeout(60);

        //设置断开后的重连次数，可以设置的很大，不会有什么性能上的影响
        setting.setReconnectFrequency(60);

        //网络状态发生变化后是否重连，
        //需要调用 WebSocketHandler.registerNetworkChangedReceiver(context) 方法注册网络监听广播
        setting.setReconnectWithNetworkChanged(true);

        //通过 init 方法初始化默认的 WebSocketManager 对象
        WebSocketManager manager = WebSocketHandler.init(setting);
        //启动连接
        manager.start();

        //注意，需要在 AndroidManifest 中配置网络状态获取权限
        //注册网路连接状态变化广播
        WebSocketHandler.registerNetworkChangedReceiver(this);
    }

    private void ManageActivity() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                activityList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //处于栈顶的Activity
                topactivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activityList.remove(activity);
            }
        });
    }
}
