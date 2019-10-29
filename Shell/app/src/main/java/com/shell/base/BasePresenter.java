package com.shell.base;

import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.shell.constant.AppUrl;
import com.shell.net.HttpResponseListener;
import com.shell.net.IHttpListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/3/9/009.
 */

public abstract class BasePresenter {
    protected RequestQueue mmQueue;//请求队列。
    protected Object object = new Object();//用来标记取消。
    protected BaseActivity activity;
    protected Gson mGson = new GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
            .create();
    protected boolean isCanCancel;//可以取消
    protected boolean isLoading;//显示loading
    protected Request request;
    protected Toast mToast;

    /**
     * 发起请求。
     *
     * @param what          what.
     * @param chileUrl      请求行的后部分。
     * @param iHttpListener 回调函数。
     * @param hashMap       所有参数都放在里边。
     * @param canCancel     是否能被用户取消。
     * @param isLoading     实现显示加载框。
     * @param <T>           想请求到的数据类型。
     */
    public <T> void sendRequest(BaseActivity activity, int what, String chileUrl, HashMap<String, Object> hashMap, IHttpListener<T> iHttpListener, boolean canCancel, boolean isLoading) {
        this.mmQueue = activity.mQueue;
        if (mmQueue == null) {
            Log.i("BasePresenter","activity的mmQueue为null");
            return;
        }
        this.activity = activity;
        request = NoHttp.createStringRequest(AppUrl.BASE_URL + chileUrl, RequestMethod.POST);
        if (hashMap != null) {
            request.add(hashMap);
        }
        request.setCancelSign(object);
        //添加固定5个参数
        mmQueue.add(what, request, new HttpResponseListener<>(activity, request, iHttpListener, canCancel, isLoading));
    }

    /**
     * 发起请求。
     *
     * @param what          what.
     * @param chileUrl      请求行的后部分。
     * @param iHttpListener 回调函数。
     * @param hashMap       所有参数都放在里边。
     * @param canCancel     是否能被用户取消。
     * @param isLoading     实现显示加载框。
     * @param <T>           想请求到的数据类型。
     */
    public <T> void sendRequestOfStringMao(BaseActivity activity, int what, String chileUrl, HashMap<String, String> hashMap, IHttpListener<T> iHttpListener, boolean canCancel, boolean isLoading) {
        this.mmQueue = activity.mQueue;
        if (mmQueue == null) {
            Log.i("BasePresenter","activity的mmQueue为null");
            return;
        }
        this.activity = activity;
        request = NoHttp.createStringRequest(AppUrl.BASE_URL + chileUrl, RequestMethod.POST);
        if (hashMap != null) {
            request.add(hashMap);
        }
        request.setCancelSign(object);
        //添加固定5个参数
        mmQueue.add(what, request, new HttpResponseListener<>(activity, request, iHttpListener, canCancel, isLoading));
    }


    //图片上传，这里的SIGNATURE 和PWDINDEX拼接在请求头上
    public <T> void sendRequest2(BaseActivity activity, int what, String chileUrl, HashMap<String, Object> hashMap, IHttpListener<T> iHttpListener, boolean canCancel, boolean isLoading) {
        this.mmQueue = activity.mQueue;
        if (mmQueue == null) {
            Log.i("BasePresenter","activity的mmQueue为null");
            return;
        }
        this.activity = activity;
        request = NoHttp.createStringRequest(AppUrl.BASE_URL + chileUrl, RequestMethod.POST);
        if (hashMap != null) {
            request.add(hashMap);
        }
        request.setCancelSign(object);
        //添加固定5个参数
        mmQueue.add(what, request, new HttpResponseListener<>(activity, request, iHttpListener, canCancel, isLoading));
    }





    //设置是否可以取消请求
    protected void setIsCanCancel(boolean b) {
        isCanCancel = b;

    }

    //设置是否显示Loading
    protected void setIsLoading(boolean b) {
        isLoading = b;

    }

    protected void cancelAll() {
        if (mmQueue != null) {
            mmQueue.cancelAll();
        }
    }

    protected void cancelBySign(Object object) {
        if (mmQueue != null) {
            mmQueue.cancelBySign(object);
        }
    }

    //销毁队列
    protected void stop(Object object) {
        if (mmQueue != null) {
            // 和声明周期绑定，退出时取消这个队列中的所有请求，当然可以在你想取消的时候取消也可以，不一定和声明周期绑定。
            mmQueue.cancelBySign(object);
            // 因为回调函数持有了activity，所以退出activity时请停止队列。
            mmQueue.stop();
        }
    }

    //销毁队列
    public void stop() {
        if (mmQueue != null) {
            // 和声明周期绑定，退出时取消这个队列中的所有请求，当然可以在你想取消的时候取消也可以，不一定和声明周期绑定。
            mmQueue.cancelAll();
            // 因为回调函数持有了activity，所以退出activity时请停止队列。
            mmQueue.stop();
        }
    }

    protected void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    protected void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}
