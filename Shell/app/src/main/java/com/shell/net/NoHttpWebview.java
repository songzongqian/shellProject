package com.shell.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.CookieManager;

import com.yanzhenjie.nohttp.NoHttp;

import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc
 * author  785917397@qq.com
 * create time  2018/6/13 0013 10:51
 * Reference link
 */

public class NoHttpWebview extends android.webkit.WebView {
    public NoHttpWebview(Context context) {
        super(context);
    }

    public NoHttpWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoHttpWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    public void loadUrl(String url, Map<String, String> httpHeader) {
        if (httpHeader == null) {
            httpHeader = new HashMap<>();
        }

        // 这里你还可以添加一些自定头。
        httpHeader.put("AppVersion", "1.0.0"); // 比如添加app版本信息，当然实际开发中要自动获取哦。
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (uri != null) {
            List<HttpCookie> cookies = NoHttp.getInitializeConfig().getCookieStore().get(uri);
            // 同步到WebView。
            Log.i("NoHttpWeb_cookies：" ,cookies.toString());
            CookieManager webCookieManager = CookieManager.getInstance();
            webCookieManager.setAcceptCookie(true);
            for (HttpCookie cookie : cookies) {
                String cookieUrl = cookie.getDomain();
                String cookieValue = cookie.getName() + "=" + cookie.getValue()
                        + "; path=" + cookie.getPath()
                        + "; domain=" + cookie.getDomain();
                webCookieManager.setCookie(cookieUrl, cookieValue);
                Log.i("NoHttpWeb_cookieUrl：" ,cookieUrl.toString());
                Log.i("NoHttpWeb_cookieValue：" ,cookieValue.toString());
            }


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                webCookieManager.flush();
            } else {
                android.webkit.CookieSyncManager.createInstance(NoHttp.getContext()).sync();
            }
        }
        super.loadUrl(url, httpHeader);
    }
}
