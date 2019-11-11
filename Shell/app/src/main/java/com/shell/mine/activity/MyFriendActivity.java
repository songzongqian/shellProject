package com.shell.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shell.Bean.LoginBean;
import com.shell.R;
import com.shell.activity.ForgetActivity;
import com.shell.activity.LoginActivity;
import com.shell.activity.MainActivity;
import com.shell.base.BaseActivity;
import com.shell.commom.LogonFailureUtil;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.mine.adapter.FriendAdapter;
import com.shell.money.Bean.ChongZhiRecordBean;
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

public class MyFriendActivity extends BaseActivity {
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
    private LinearLayout llNoData;
    private int AllPager = 0;
    private int CurrentPager = 1;
    private List<MyFriendBean.ResultDataBean> firstList = new ArrayList<>();
    private FriendAdapter friendAdapter;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myfriend;

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        llNoData = findViewById(R.id.ll_noData);
        tvTitle.setText(R.string.my_myfriend);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                CurrentPager = 1;
                initDatas();
                refreshLayout.finishRefresh(2000);
            }
        });


       smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
           @Override
           public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
               if (CurrentPager < AllPager) {
                   CurrentPager++;
                   initDatas();
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
        initDatas();
    }

    private void initDatas() {
        Log.i("song", "进入我的好友");
        String token = PreManager.instance().getString("token");
        Log.i("song", "进入我的好友token"+token);
        request = NoHttp.createJsonObjectRequest(AppUrl.MyFriendUrl+CurrentPager, RequestMethod.GET);
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
                myWaitDialog = new MyWaitDialog(MyFriendActivity.this);
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            LogonFailureUtil.gotoLoginActiviy(MyFriendActivity.this,response.get().toString());

            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "我的好友返回的参数" + String.valueOf(response));
                    MyFriendBean loginBean= gson.fromJson(response.get().toString(), MyFriendBean.class);
                    if(loginBean.getResultCode().equals("999999")){
                        if(loginBean.getResultData()!=null && loginBean.getResultData().size()>0){
                            llNoData.setVisibility(View.GONE);
                            List<MyFriendBean.ResultDataBean> resultData = loginBean.getResultData();
                            AllPager = loginBean.getPages();
                            if (1 == CurrentPager) {
                                firstList.clear();
                            }
                            firstList.addAll(resultData);
                            friendAdapter.notifyDataSetChanged();
                        }else{
                            llNoData.setVisibility(View.VISIBLE);
                        }

                    }else{

                    }
                   // FriendAdapter  friendAdapter=new FriendAdapter();
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
        friendAdapter = new FriendAdapter(firstList,MyFriendActivity.this);
        recyclerView.setAdapter(friendAdapter);
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
