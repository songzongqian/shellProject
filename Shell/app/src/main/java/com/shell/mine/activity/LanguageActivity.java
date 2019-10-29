package com.shell.mine.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shell.Bean.LanguageEvent;
import com.shell.Bean.MessageEvent;
import com.shell.MyApplication;
import com.shell.R;
import com.shell.activity.LoginActivity;
import com.shell.activity.MainActivity;
import com.shell.base.BaseActivity;
import com.shell.utils.LocalManageUtil;
import com.shell.utils.PreManager;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LanguageActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.iv_one)
    ImageView ivOne;
    @BindView(R.id.llOne)
    RelativeLayout llOne;
    @BindView(R.id.iv_two)
    ImageView ivTwo;
    @BindView(R.id.llTwo)
    RelativeLayout llTwo;
    @BindView(R.id.iv_three)
    ImageView ivThree;
    @BindView(R.id.llThree)
    RelativeLayout llThree;
    @BindView(R.id.iv_four)
    ImageView ivFour;
    @BindView(R.id.llFour)
    RelativeLayout llFour;
    int flag=0;
    private static final String[] language = {"TW", "en", "ja", "ko"};
    public static final String CHINESE = language[0];
    public static final String ENGLISH = language[1];
    public static final String JAPANESE = language[2];
    public static final String KOREA = language[3];
    private String type;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_language;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("选择语言");
        tvRightTitle.setText("完成");
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_rightTitle, R.id.llOne, R.id.llTwo, R.id.llThree, R.id.llFour,R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rightTitle:
                //完成切换语言
                if(flag==0){
                    Toast.makeText(LanguageActivity.this,"请先选择语言",Toast.LENGTH_SHORT).show();
                }else{
                   if(flag==1){
                       selectLanguage(1);
                       //EventBus.getDefault().post(new LanguageEvent("繁体中文"));
                       PreManager.instance().putString("language", "zh_HK");
                       PreManager.instance().putString("mychoose", "繁体中文");
                       finish();
                   }else if(flag==2){
                       selectLanguage(2);
                       //EventBus.getDefault().post(new LanguageEvent("English"));
                       PreManager.instance().putString("language", "en_US");
                       PreManager.instance().putString("mychoose", "English");
                       finish();
                   }else if(flag==3){
                       selectLanguage(3);
                      // EventBus.getDefault().post(new LanguageEvent("한국어"));
                       PreManager.instance().putString("language", "ko_KR");
                       PreManager.instance().putString("mychoose", "한국어");
                       finish();
                   }else if(flag==4){
                       selectLanguage(4);
                       //EventBus.getDefault().post(new LanguageEvent("日本語"));
                       PreManager.instance().putString("language", "ja_JP");
                       PreManager.instance().putString("mychoose", "日本語");
                       finish();
                   }
                }

                break;
            case R.id.llOne:
                ivOne.setVisibility(View.VISIBLE);
                ivTwo.setVisibility(View.GONE);
                ivThree.setVisibility(View.GONE);
                ivFour.setVisibility(View.GONE);
                flag=1;
                break;
            case R.id.llTwo:
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.VISIBLE);
                ivThree.setVisibility(View.GONE);
                ivFour.setVisibility(View.GONE);
                flag=2;
                break;
            case R.id.llThree:
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.GONE);
                ivThree.setVisibility(View.VISIBLE);
                ivFour.setVisibility(View.GONE);
                flag=3;
                break;
            case R.id.llFour:
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.GONE);
                ivThree.setVisibility(View.GONE);
                ivFour.setVisibility(View.VISIBLE);
                flag=4;
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }


    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(this, select);
        final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //杀掉以前进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }



}
