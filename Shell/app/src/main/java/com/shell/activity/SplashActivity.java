package com.shell.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.utils.IpUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

public class SplashActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;

    private LottieAnimationView lottieAnimationView;

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
        GetIP();
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
                Intent intent= new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

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
                Log.i("song","当前IP地址是"+ipAddress);
                String IpAddressUrl="http://apis.juhe.cn/ip/ipNew?" +"ip="+ipAddress+"&key=e2a0ddd8aa7463579a1d18da808868dc";
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
                Log.i("song", "ip地址接口返回的参数" + String.valueOf(response));
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                Intent intent= new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFinish(int what) {

            }
        });

    }
}
