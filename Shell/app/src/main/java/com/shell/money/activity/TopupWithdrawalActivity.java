package com.shell.money.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.shell.R;
import com.shell.base.BasePresenter;
import com.shell.commom.ActivityCollector;
import com.shell.money.fragment.ChongZhiFragment;
import com.shell.money.fragment.TiBiFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopupWithdrawalActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_viewPager_one)
    TextView tvViewPagerOne;
    @BindView(R.id.tv_viewPager_twe)
    TextView tvViewPagerTwe;
    @BindView(R.id.topup_withrawal_viewpager)
    ViewPager topupWithrawalViewpager;
    private List<Fragment> dataFragment = new ArrayList<>();
    protected ImmersionBar mImmersionBar;
    protected BasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_withdrawal);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        ChongZhiFragment zhiFragment = new ChongZhiFragment();
        TiBiFragment biFragment = new TiBiFragment();
        dataFragment.add(zhiFragment);
        dataFragment.add(biFragment);
        Adapter adapter = new Adapter(getSupportFragmentManager());
        topupWithrawalViewpager.setAdapter(adapter);
    }

    private void initView() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        //默认充币
        tvTitle.setText(getString(R.string.usdtchar));
        topupWithrawalViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                changTitleViewP(i);
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.tv_viewPager_one, R.id.tv_viewPager_twe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_viewPager_one:
                changTitleViewP(0);
                break;
            case R.id.tv_viewPager_twe:
                changTitleViewP(1);
                break;
        }
    }

    private void changTitleViewP(int size) {
        if (0 == size) {
            tvTitle.setText(getString(R.string.usdtchar));
            topupWithrawalViewpager.setCurrentItem(0);
            tvViewPagerOne.setBackgroundResource(R.drawable.card_home_country);
            tvViewPagerTwe.setBackgroundResource(R.drawable.omni_283040_bg);
        } else {
            tvTitle.setText("USDT" + getString(R.string.rmb_tibi));
            topupWithrawalViewpager.setCurrentItem(1);
            tvViewPagerOne.setBackgroundResource(R.drawable.omni_283040_bg);
            tvViewPagerTwe.setBackgroundResource(R.drawable.card_home_country);
        }
    }

    class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return dataFragment.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    protected void onDestroy() {
        ActivityCollector.removeActivity(this);
        Log.i("ActivityCollector,size", ActivityCollector.getActivityCollectorSize() + "");
        //取消掉所有的请求
        if (presenter != null) {
            presenter.stop();
        }
        //沉浸式状态栏    /必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        if (mImmersionBar != null)
            mImmersionBar.destroy();
        super.onDestroy();
    }
}
