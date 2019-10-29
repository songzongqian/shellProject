package com.shell.net;


import com.yanzhenjie.nohttp.rest.Response;

/**
 *@author jinliang
 *@time 2018/4/16/016 16:40
 *email 785917397@qq.com
 *desc:20180416   重新定义的接收网络数据的回调
 *datum:
 */
public interface IHttpListener<T> {
    void onSucceed(int what, Response<T> response);
    void onFailed(int what, Response<T> response);
}
