package com.shell.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shell.Bean.HistoryShouYiBean;
import com.shell.Bean.LetterBean;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.base.TodayShouBean;
import com.shell.commom.LogonFailureUtil;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.money.Bean.CardBean;
import com.shell.money.Bean.CardUnderBean;
import com.shell.money.adapter.MoneyPageAdapter;
import com.shell.utils.GetTwoLetter;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyShouYiActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    private int page = 1;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_orderAmount)
    TextView tvOrderAmount;
    @BindView(R.id.tv_orderJINE)
    TextView tvOrderJINE;
    @BindView(R.id.tv_orderShouYi)
    TextView tvOrderShouYi;
    @BindView(R.id.tv_kcAware)
    TextView tvKcAware;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_five)
    TextView tvFive;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int AllPager = 0;
    private int CurrentPager = 1;
    private List<HistoryShouYiBean.ResultDataBean> firstList = new ArrayList<>();
    private HistoryAdapter historyAdapter;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myshouyi;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.my_shouyi));
        tvRightTitle.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        getUnderList();
        getTop();
    }



    private void getTop() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getTodayShouYi, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);
    }

    private void getUnderList() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getHistoryShouYi+CurrentPager, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(2, request, responseListener);

    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(MyShouYiActivity.this);
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            LogonFailureUtil.gotoLoginActiviy(MyShouYiActivity.this,response.get().toString());
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "今日收益的返回值" + String.valueOf(response));
                    TodayShouBean todayShouBean = gson.fromJson(response.get().toString(), TodayShouBean.class);
                    String resultCode = todayShouBean.getResultCode();
                    if(resultCode.equals("999999")){
                        //成功
                        TodayShouBean.ResultDataBean dataBean = todayShouBean.getResultData();
                        tvOrderAmount.setText(GetTwoLetter.getTwo(dataBean.getOrderCount()));
                        tvOrderJINE.setText(GetTwoLetter.getTwo(dataBean.getOrderAmount()));
                        tvOrderShouYi.setText(GetTwoLetter.getTwo(dataBean.getOrderProfit()));
                        tvKcAware.setText(GetTwoLetter.getTwo(dataBean.getHashRateAward()));
                    }
                    break;
                case 2:
                    Log.i("song", "历史收益的返回值" + String.valueOf(response));
                    HistoryShouYiBean historyShouYiBean=gson.fromJson(response.get().toString(), HistoryShouYiBean.class);
                    String code = historyShouYiBean.getResultCode();
                    if(code.equals("999999")){
                        List<HistoryShouYiBean.ResultDataBean> historyList = historyShouYiBean.getResultData();

                        AllPager = historyShouYiBean.getPages();
                        if (1 == CurrentPager) {
                            firstList.clear();
                        }
                        firstList.addAll(historyList);
                        historyAdapter.notifyDataSetChanged();
                    }

                    break;
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            Log.i("song", "历史收益的返回值" + String.valueOf(response));
            myWaitDialog.cancel();


        }

        @Override
        public void onFinish(int what) {
            myWaitDialog.cancel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {

        historyAdapter = new HistoryAdapter(firstList,MyShouYiActivity.this);
        listView.setAdapter(historyAdapter);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                CurrentPager = 1;
                getUnderList();
                refreshLayout.finishRefresh(2000);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CurrentPager < AllPager) {
                    CurrentPager++;
                    getUnderList();
                    refreshLayout.finishLoadMore(2000);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_title:
                break;
        }
    }
}
