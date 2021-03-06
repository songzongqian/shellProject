package com.shell.mine.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shell.Bean.LetterBean;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.commom.LogonFailureUtil;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.mine.adapter.LetterAdapter;
import com.shell.mine.adapter.MyLetterAdapter;
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

public class WebLetterActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    private int page = 1;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_noData)
    LinearLayout llNoData;
    private int AllPager = 0;
    private int CurrentPager = 1;
    private List<LetterBean.ResultDataBean> firstList = new ArrayList<>();
    private LetterAdapter letterAdapter;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_website;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.my_letter));
        tvRightTitle.setVisibility(View.GONE);


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                CurrentPager = 1;
                getLetterData();
                refreshLayout.finishRefresh(2000);
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CurrentPager < AllPager) {
                    CurrentPager++;
                    getLetterData();
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
        getLetterData();

    }

    private void getLetterData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.WebLrtterCount+CurrentPager, RequestMethod.GET);
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
                myWaitDialog = new MyWaitDialog(WebLetterActivity.this);
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            LogonFailureUtil.gotoLoginActiviy(WebLetterActivity.this,response.get().toString());
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "站内消息返回的数据" + String.valueOf(response));
                    LetterBean letterBean= gson.fromJson(response.get().toString(), LetterBean.class);
                    if(letterBean.getResultCode().equals("999999")){
                        if(letterBean.getResultData()!=null && letterBean.getResultData().size()>0){
                            llNoData.setVisibility(View.GONE);
                            List<LetterBean.ResultDataBean> resultData = letterBean.getResultData();
                            AllPager = letterBean.getPages();
                            if (1 == CurrentPager) {
                                firstList.clear();
                            }
                            firstList.addAll(resultData);
                            letterAdapter.notifyDataSetChanged();
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
        letterAdapter = new LetterAdapter(WebLetterActivity.this,firstList);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(letterAdapter);
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
