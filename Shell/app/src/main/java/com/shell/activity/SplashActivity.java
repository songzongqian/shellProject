package com.shell.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.shell.R;
import com.shell.base.BaseActivity;

public class SplashActivity extends BaseActivity {

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
}
