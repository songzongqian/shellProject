package com.shell.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shell.Bean.MyInfoBean;
import com.shell.R;
import com.shell.activity.LoginActivity;
import com.shell.activity.MyShouYiActivity;
import com.shell.base.BaseFragment;
import com.shell.commom.LogonFailureUtil;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.money.Bean.CardBean;
import com.shell.money.Bean.CardUnderBean;
import com.shell.money.activity.ChongZhiActivity;
import com.shell.money.activity.QingSuanActivity;
import com.shell.money.activity.TiBiActivity;
import com.shell.money.activity.ZhiYaActivity;
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
import butterknife.Unbinder;

public class MoneyFragment extends BaseFragment  {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);

    @BindView(R.id.tv_myshouyi)
    TextView tvMyshouyi;
    @BindView(R.id.ll_shouyi)
    RelativeLayout llShouyi;
    private Request<JSONObject> request;
    private int page = 1;
    @BindView(R.id.tv_chiyouCount)
    TextView tvChiyouCount;
    @BindView(R.id.tv_qingsuan)
    TextView tvQingsuan;
    @BindView(R.id.tv_tibi)
    TextView tvTibi;
    @BindView(R.id.tv_dongjieCount)
    TextView tvDongjieCount;
    @BindView(R.id.tv_keyongCount)
    TextView tvKeyongCount;
    @BindView(R.id.btn_chongzhi)
    Button btnChongzhi;
    @BindView(R.id.btn_zhiya)
    Button btnZhiya;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder;
    private String balance;
    //fragment loading只显示一次  没时间了
    private boolean isLoading = true;
    private int AllPager = 0;
    private int CurrentPager = 1;
    private List<CardUnderBean.ResultDataBean> resultList = new ArrayList<>();
    private MoneyPageAdapter underCardAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(getActivity());
        RelativeLayout rlback = mRootView.findViewById(R.id.rl_back);
        TextView tvTitle = mRootView.findViewById(R.id.tv_title);
        TextView tvRight = mRootView.findViewById(R.id.tv_rightTitle);
        rlback.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.rmb_name));
        tvRight.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        Boolean isLogin = PreManager.instance().getBoolean("ISLogin");
        if (isLogin) {
            getCardData();
            getUnderData();
            getUserInfo();
        }


    }

    //获取卡片中的数据
    private void getCardData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.CardDataUrl, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);


    }

    //获取卡片下方的数据
    private void getUnderData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.CardUnderUrl+CurrentPager, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        request.add("busiCode", "");
        request.add("status", "");
        request.add("pageNum", page);
        mQueue.add(2, request, responseListener);
    }


    //获取用户资料
    private void getUserInfo(){
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.MyInforUrl, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(3, request, responseListener);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {
        underCardAdapter = new MoneyPageAdapter(resultList, getActivity());
        listView.setAdapter(underCardAdapter);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                CurrentPager = 1;
                getUnderData();
                refreshLayout.finishRefresh(2000);
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CurrentPager < AllPager){
                    CurrentPager++;
                    getUnderData();
                    refreshLayout.finishLoadMore(2000);
                }else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_qingsuan, R.id.tv_tibi, R.id.btn_chongzhi, R.id.btn_zhiya, R.id.ll_shouyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_qingsuan:
                Intent intent = new Intent(getActivity(), QingSuanActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_tibi:
                Intent intent1 = new Intent(getActivity(), TiBiActivity.class);
                intent1.putExtra("myBalance", balance);
                startActivity(intent1);
                break;
            case R.id.btn_chongzhi:
                Intent intent2 = new Intent(getActivity(), ChongZhiActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_zhiya:
                Intent intent3 = new Intent(getActivity(), ZhiYaActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_shouyi: //我的收益
                Intent intent4 = new Intent(getActivity(), MyShouYiActivity.class);
                startActivity(intent4);
                break;
        }
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (isLoading) {
                if (myWaitDialog == null) {
                    myWaitDialog = new MyWaitDialog(getActivity());
                    myWaitDialog.show();
                } else {
                    myWaitDialog.show();
                }
                isLoading = false;
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            LogonFailureUtil.gotoLoginActiviy(getActivity(), response.get().toString());
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "卡片数据返回值" + String.valueOf(response));
                    CardBean cardBean = gson.fromJson(response.get().toString(), CardBean.class);
                    if (cardBean.getResultCode().equals("999999")) {
                        //赋值数据
                        CardBean.ResultDataBean resultData = cardBean.getResultData();
                        balance = resultData.getBalance();
                        tvChiyouCount.setText(GetTwoLetter.getTwo(resultData.getBalance()));
                        tvDongjieCount.setText(GetTwoLetter.getTwo(resultData.getFreeze()));
                        tvKeyongCount.setText(GetTwoLetter.getTwo(resultData.getUseable()));
                    }


                    break;
                case 2:
                    Log.i("song", "卡片下方数据返回值" + String.valueOf(response));
                    CardUnderBean cardUnderBean = gson.fromJson(response.get().toString(), CardUnderBean.class);
                    if (cardUnderBean.getResultCode().equals("999999")) {
                        if (cardUnderBean != null && cardUnderBean.getResultData().size() > 0) {
                            List<CardUnderBean.ResultDataBean> resultDatas = cardUnderBean.getResultData();
                            AllPager = cardUnderBean.getPages();
                            if (1 == CurrentPager){
                                resultList.clear();
                            }
                            resultList.addAll(resultDatas);
                            underCardAdapter.notifyDataSetChanged();
                        } else {

                        }
                    }

                    break;
                case 3:
                    Log.i("song", "我的资料的返回值" + String.valueOf(response));
                    MyInfoBean myInfoBean = gson.fromJson(response.get().toString(), MyInfoBean.class);
                    String resultCode = myInfoBean.getResultCode();
                    if (resultCode.equals("999999")) {
                        boolean payPasswordFlag = myInfoBean.getResultData().isPayPasswordFlag();
                        PreManager.instance().putBoolean(AppUrl.isSetPayPwd, payPasswordFlag);
                        String myEmail = myInfoBean.getResultData().getEmail();
                        PreManager.instance().putString("myEmail", myEmail);
                    }
                    break;
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            Log.i("song", "卡片下方数据返回值错误" + String.valueOf(response));
            myWaitDialog.cancel();


        }

        @Override
        public void onFinish(int what) {
            // myWaitDialog.cancel();
        }
    };


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //可见
            Boolean isLogin = PreManager.instance().getBoolean("ISLogin");
            if (isLogin) {
                Log.i("song", "MoneyFragment可见");
                getCardData();
                getUnderData();
                getUserInfo();
            }

        } else {
            //不可见
            Log.i("song", "MoneyFragment不可见");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        String token = PreManager.instance().getString("token");
        Log.i("song", "我的页面的token值" + token);
        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            return;
        }

        Boolean isLogin = PreManager.instance().getBoolean("ISLogin");
        if (isLogin) {
            getCardData();
            getUnderData();
            getUserInfo();
        }
    }

}
