package com.shell.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.google.gson.Gson;
import com.shell.Bean.GetServerBean;
import com.shell.Bean.OutNetIpBean;
import com.shell.MyApplication;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.order.adapter.OrderPartAdapter;
import com.shell.order.bean.AllNetTopBean;
import com.shell.order.bean.CurrentOrderStatue;
import com.shell.order.bean.OrderListBean;
import com.shell.utils.GetNetIp;
import com.shell.utils.GetTwoLetter;
import com.shell.utils.LocalManageUtil;
import com.shell.utils.PreManager;
import com.shell.utils.IpUtils;
import com.shell.utils.SPUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

public class SplashActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;

    private LottieAnimationView lottieAnimationView;

    private int flag = 0;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        lottieAnimationView = findViewById(R.id.lottieAnimationView);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {


        String outnetip = PreManager.instance().getString("outnetip");
        if (TextUtils.isEmpty(outnetip)) {
            GetIP();
        }
        LottieComposition.Factory.fromAssetFileName(this, "splash.json", new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition composition) {
                lottieAnimationView.setComposition(composition);
            }
        });

        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieAnimationView.cancelAnimation();
                selectLanguage(flag);
        /*        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();*/
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void GetIP() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ipAddress = IpUtils.GetNetIp();
                Log.i("song", "当前IP地址是" + ipAddress);
                String IpAddressUrl = "http://apis.juhe.cn/ip/ipNew?" + "ip=" + ipAddress + "&key=e2a0ddd8aa7463579a1d18da808868dc";
                getMyCountry(IpAddressUrl);
            }
        }).start();

    }

    private void getMyCountry(String ipAddressUrl) {
        request = NoHttp.createJsonObjectRequest(ipAddressUrl, RequestMethod.GET);
        mQueue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                PreManager.instance().putString("outnetip", response.get().toString());
                Log.i("song", "ip地址接口返回的参数" + String.valueOf(response));
                Gson gson = new Gson();
                OutNetIpBean outNetIpBean = gson.fromJson(response.get().toString(), OutNetIpBean.class);
                if ("200".equals(outNetIpBean.getResultcode())) {
                    String country = outNetIpBean.getResult().getCountry();
                    if (country.contains("中国")) {
                        flag = 1;
                    } else if (country.contains("日本")) {
                        flag = 4;
                    } else if (country.contains("韩国")) {
                        //其他全部为英文
                        flag = 3;
                    } else {
                        flag = 2;
                    }
                } else {
                    //其他全部为英文
                    flag = 2;
                }
                if (flag == 1) {
                    //selectLanguage(1);
                    //EventBus.getDefault().post(new LanguageEvent("繁体中文"));
                    PreManager.instance().putString("language", "zh_HK");
                    PreManager.instance().putString("mychoose", "繁体中文");
                } else if (flag == 2) {
                    //selectLanguage(2);
                    //EventBus.getDefault().post(new LanguageEvent("English"));
                    PreManager.instance().putString("language", "en_US");
                    PreManager.instance().putString("mychoose", "English");
                } else if (flag == 3) {
                    // selectLanguage(3);
                    // EventBus.getDefault().post(new LanguageEvent("한국어"));
                    PreManager.instance().putString("language", "ko_KR");
                    PreManager.instance().putString("mychoose", "한국어");
                } else if (flag == 4) {
                    //selectLanguage(4);
                    //EventBus.getDefault().post(new LanguageEvent("日本語"));
                    PreManager.instance().putString("language", "ja_JP");
                    PreManager.instance().putString("mychoose", "日本語");
                }
                MyApplication.initNet();

            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                flag = 2;
                selectLanguage(flag);
        /*        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);*/
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }


    private void selectLanguage(int select) {
        if (select != 0) {
            LocalManageUtil.saveSelectLanguage(this, select);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

/*        final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //杀掉以前进程
        android.os.Process.killProcess(android.os.Process.myPid());*/
    }
}
