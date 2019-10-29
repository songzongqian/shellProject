/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shell.net;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.shell.MyApplication;
import com.shell.R;
import com.shell.dialog.MyWaitDialog;
import com.shell.utils.CommonUntil;
import com.vondear.rxtool.RxAppTool;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;


/**
 * Created in Nov 4, 2015 12:02:55 PM.
 *
 * @author Yan Zhenjie.
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {
    private Gson mGson;
    private Activity mActivity;
    private boolean canCancel;//是否可以取消掉请求
    private boolean isLoading;//是否显示Loading
    private AlertDialog.Builder builder = null; //全局的
    private AlertDialog dialog = null;//全局的

    /**
     * Dialog.
     */
    private MyWaitDialog mWaitDialog;
    /**
     * Request.
     */
    private Request<?> mRequest;
    /**
     * 结果回调.
     */
    private IHttpListener<T> iHttpListener;


    /**
     * @param activity      context用来实例化dialog.
     * @param request       请求对象.
     * @param iHttpListener 回调对象.
     * @param canCancel     是否允许用户取消请求.
     * @param isLoading     是否显示dialog.
     */
    public HttpResponseListener(Activity activity, Request<?> request, IHttpListener<T> iHttpListener, boolean canCancel, boolean isLoading) {
        this.mActivity = activity;
        this.mRequest = request;
        if (activity != null && isLoading) {
            mWaitDialog = new MyWaitDialog(activity);
            Dialog dialog = mWaitDialog.getmDialog();
            // mWaitDialog.setCancelable(canCancel);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mRequest.cancel();
                }
            });
        }
        //初始化解析
        if (mGson == null) {
            mGson = new GsonBuilder()
                    .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
                    .create();
        }
        this.iHttpListener = iHttpListener;
    }


    /**
     * 开始请求, 这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {
        if (mWaitDialog != null && !mActivity.isFinishing() && !mWaitDialog.isShowing())
            mWaitDialog.show();
    }

    /**
     * 结束请求, 这里关闭dialog.
     */
    @Override
    public void onFinish(int what) {
        if (mWaitDialog != null && mWaitDialog.isShowing())
            mWaitDialog.cancel();
    }

    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        try {
            //  List cookies = response.getHeaders().getCookies();
            if (iHttpListener != null) {
                Log.i("请求返回码", response.responseCode() + "");
                // 这里判断一下http响应码，这个响应码问下你们的服务端你们的状态有几种，一般是200成功。
                // w3c标准http响应码：http://www.w3school.com.cn/tags/html_ref_httpmessages.asp
//                if (response.responseCode() == 200) {
//                    iHttpListener.onSucceed(what, response);
//                } else {
//                    iHttpListener.onFailed(what, response);
//                }
                if (response.responseCode() == 200) {
                    JSONObject obj = new JSONObject((String) response.get());
                    Log.i("网络返回结果", obj.toString());
                    if (!TextUtils.isEmpty(obj.optString("STATE"))) {
                        if ("00005".equals(obj.getString("STATE"))) {//被其他设备顶了
                            if (!RxAppTool.isAppBackground(MyApplication.getAppInstance())) {//判断APP是否在前台
                                if (MyApplication.canShow) {
                                    showLogoutDialog("提示", obj.optString("MSG"));
                                    MyApplication.canShow = false;
                                    NoHttp.getInitializeConfig().getCookieStore().removeAll();
                                    CommonUntil.clearWebViewCache();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            MyApplication.canShow = true;
                                        }
                                    }, 5000); // 延时1秒
                                }
                            }
                        } else {
                            iHttpListener.onSucceed(what, response);
                        }
                    } else {
                        iHttpListener.onSucceed(what, response);
                    }
                } else {
                    iHttpListener.onFailed(what, response);
                }
            }
        } catch (Exception e) {
            Log.i("服务器返回数据解析异常", e.toString());
        }
    }

    /**
     * 失败回调.
     */
    @Override
    public void onFailed(int what, Response<T> response) {
        Exception exception = response.getException();
        if (exception instanceof NetworkError) {// 网络不好
            Toast.makeText(mActivity, R.string.error_please_check_network, Toast.LENGTH_SHORT).show();
        } else if (exception instanceof TimeoutError) {// 请求超时
            Toast.makeText(mActivity, R.string.error_timeout, Toast.LENGTH_SHORT).show();
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            Toast.makeText(mActivity, R.string.error_not_found_server, Toast.LENGTH_SHORT).show();
        } else if (exception instanceof URLError) {// URL是错的
            Toast.makeText(mActivity, R.string.error_url_error, Toast.LENGTH_SHORT).show();
        } else if (exception instanceof NotFoundCacheError) {
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
            // 没有缓存一般不提示用户，如果需要随你。
        } else {
            Toast.makeText(mActivity, R.string.error_unknow, Toast.LENGTH_SHORT).show();
            //  ToastUtil.show(mActivity, R.string.error_unknow);
        }
        Logger.e("错误：" + exception.getMessage());
        Logger.e("错误what：" + what + "");
        if (iHttpListener != null) {//上边已经处理过了，后边可以不用再处理了，特殊情况除外
            iHttpListener.onFailed(what, response);
        }
    }

    public boolean isCanCancel() {
        return canCancel;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showLogoutDialog(CharSequence title, CharSequence message) {
        if ((builder != null) || (dialog != null)) {
            return;
        }
        Log.i("需要登录", "需要登录");
        builder = new AlertDialog.Builder(mActivity);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*Intent intent = new Intent(mActivity, LoginActivity.class);
                mActivity.startActivity(intent);*/
            }
        });
        if (dialog == null) {
            dialog = builder.create();
        }
        builder.show();
    }
}
