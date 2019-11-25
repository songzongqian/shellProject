package com.shell.order.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shell.Bean.HistoryShouYiBean;
import com.shell.R;
import com.shell.activity.ForgetActivity;
import com.shell.base.BaseActivity;
import com.shell.commom.LogonFailureUtil;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.mine.activity.MyFriendActivity;
import com.shell.mine.activity.MyFriendBean;
import com.shell.mine.adapter.FriendAdapter;
import com.shell.order.adapter.OrderListAdapter;
import com.shell.order.bean.OrderListBean;
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

public class OrderListActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    private int page = 1;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_noData)
    LinearLayout llNoData;
    private int AllPager = 0;
    private int CurrentPager = 1;
    private List<OrderListBean.ResultDataBean> firstList = new ArrayList<>();
    private OrderListAdapter orderListAdapter;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_orderlist;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.order));
        tvRightTitle.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                CurrentPager = 1;
                getOrderList();
                refreshLayout.finishRefresh(2000);
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout){
                if (CurrentPager < AllPager) {
                    CurrentPager++;
                    getOrderList();
                    refreshLayout.finishLoadMore(2000);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });



    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        getOrderList();

    }

    private void getOrderList() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.GetToDoOrder+CurrentPager, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("pageNum", page);
        request.add("token", token);
        mQueue.add(1, request, responseListener);
    }

    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(OrderListActivity.this);
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            String s = response.get().toString();
            System.out.println("---------" +s);
            LogonFailureUtil.gotoLoginActiviy(OrderListActivity.this,response.get().toString());
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "我的未完成订单返回的参数" + String.valueOf(response));
                    OrderListBean orderListBean= gson.fromJson(response.get().toString(), OrderListBean.class);
                    if(orderListBean.getResultCode().equals("999999")){
                        //OrderListAdapter
                       if(orderListBean.getResultData()!=null && orderListBean.getResultData().size()>0){
                            llNoData.setVisibility(View.GONE);

                           List<OrderListBean.ResultDataBean> resultData = orderListBean.getResultData();
                           AllPager = orderListBean.getPages();
                           if (1 == CurrentPager) {
                               firstList.clear();
                           }
                           firstList.addAll(resultData);
                           orderListAdapter.notifyDataSetChanged();
                        }else{
                            llNoData.setVisibility(View.VISIBLE);
                        }
                    }else{

                    }
                    break;
            }
        }


        @Override
        public void onFailed(int what, Response<JSONObject> response) {
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
        orderListAdapter = new OrderListAdapter(firstList,OrderListActivity.this);
        recyclerView.setAdapter(orderListAdapter);
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
