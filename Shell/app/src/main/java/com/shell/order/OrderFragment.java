package com.shell.order;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shell.Bean.GetServerBean;
import com.shell.Bean.NoticeBean;
import com.shell.Bean.OrderEvent;
import com.shell.R;
import com.shell.base.BaseFragment;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.order.activity.OrderListActivity;
import com.shell.order.adapter.OrderPartAdapter;
import com.shell.order.bean.AllNetTopBean;
import com.shell.order.bean.CurrentOrderStatue;
import com.shell.order.bean.OFFSuccessBean;
import com.shell.order.bean.OnSuccessBean;
import com.shell.order.bean.OrderListBean;
import com.shell.order.bean.ServerOrderBean;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderFragment extends BaseFragment {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    private int page = 1;
    int flag = 0;
    int titleFlag = 0;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_orderFirst)
    TextView tvOrderFirst;
    @BindView(R.id.tv_amountFirst)
    TextView tvAmountFirst;
    @BindView(R.id.tv_orderSecond)
    TextView tvOrderSecond;
    @BindView(R.id.tv_amountSecond)
    TextView tvAmountSecond;
    @BindView(R.id.tv_systemRun)
    TextView tvSystemRun;
    @BindView(R.id.tv_onlinePerson)
    TextView tvOnlinePerson;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_moneyName)
    TextView tvMoneyName;
    @BindView(R.id.tv_moneyAmount)
    TextView tvMoneyAmount;
    @BindView(R.id.tv_moneyEDU)
    TextView tvMoneyEDU;
    @BindView(R.id.tv_moneyED)
    TextView tvMoneyED;
    @BindView(R.id.tv_shouyi)
    TextView tvShouyi;
    @BindView(R.id.tv_shouyiCount)
    TextView tvShouyiCount;
    @BindView(R.id.btn_startOrder)
    Button btnStartOrder;
    @BindView(R.id.tv_orderunfinish)
    TextView tvOrderunfinish;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.ll_more)
    RelativeLayout llMore;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private String currentOrder;
    List<OrderListBean.ResultDataBean> middleList = new ArrayList<>();
    private TimeCount mTiemTimeCount;
    private TextView tvSecond;
    private PopupWindow orderWindow;
    //fragment loading只显示一次  没时间了
    private boolean isLoading = true;
    private TextView tvNoData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(getActivity());
        RelativeLayout rlback = mRootView.findViewById(R.id.rl_back);
        TextView tvTitle = mRootView.findViewById(R.id.tv_title);
        TextView tvRight = mRootView.findViewById(R.id.tv_rightTitle);
        rlback.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.or_pagetitle));
        tvRight.setVisibility(View.GONE);
        tvNoData = mRootView.findViewById(R.id.tv_NoData);
        EventBus.getDefault().register(this);
        mTiemTimeCount = new TimeCount(10000, 1000);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        getTopData();
        getMiddleData();
        getOrderData();
    }


    private void getTopData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.AllNetUrl, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);
    }

    private void getMiddleData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.GetOrderState, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(2, request, responseListener);

    }

    //获取未完成订单
    private void getOrderData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.GetToDoOrder, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        request.add("pageNum", page);
        mQueue.add(3, request, responseListener);
    }

    //关闭接单按钮
    private void PostOrderStatueOff() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getOrderStatue, RequestMethod.POST);
        request.addHeader("token", token);
        request.add("token", token);
        request.add("status", "off");
        mQueue.add(7, request, responseListener);
    }


    //打开接单按钮
    private void PostOrderStatueOn() {
        Log.i("song", "进入打开按钮");
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getOrderStatue, RequestMethod.POST);
        request.addHeader("token", token);
        request.add("token", token);
        request.add("status", "on");
        mQueue.add(4, request, responseListener);
    }


    //获取接单文案
    private void getOrderWord() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getOrderWord, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(6, request, responseListener);
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
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "抢单页面全网数据返回" + String.valueOf(response));
                    AllNetTopBean allNetTopBean = gson.fromJson(response.get().toString(), AllNetTopBean.class);
                    if (allNetTopBean.getResultCode().equals("999999")) {
                        AllNetTopBean.ResultDataBean resultData = allNetTopBean.getResultData();
                     /*   tvOrderFirst.setText(GetTwoLetter.getTwo(resultData.getOrderAll()));
                        tvAmountFirst.setText(GetTwoLetter.getTwo(resultData.getOrderAllAmount()) + " " + "USDT");
                        tvOrderSecond.setText(GetTwoLetter.getTwo(resultData.getOrderToday()));
                        tvSystemRun.setText(GetTwoLetter.getTwo(resultData.getHours()));
                        tvOnlinePerson.setText(GetTwoLetter.getTwo(resultData.getOnlineUser()));*/
                        tvOrderFirst.setText(resultData.getOrderAll());
                        tvAmountFirst.setText(resultData.getOrderAllAmount() + " " + "USDT");
                        tvOrderSecond.setText(resultData.getOrderToday());
                        tvSystemRun.setText(resultData.getHours() + " " + getString(R.string.house));
                        tvOnlinePerson.setText(resultData.getOnlineUser());
                    } else {

                    }
                    break;

                case 2:
                    Log.i("song", "抢单页面抢单状态" + String.valueOf(response));
                    CurrentOrderStatue currentOrderStatue = gson.fromJson(response.get().toString(), CurrentOrderStatue.class);
                    String resultCode = currentOrderStatue.getResultCode();
                    if (resultCode.equals("999999")) {
                        currentOrder = currentOrderStatue.getResultData();
                        if (currentOrder.equals("off")) {
                            btnStartOrder.setBackgroundColor(Color.parseColor("#22C6FE"));
                            btnStartOrder.setText(R.string.or_begin);
                        } else if (currentOrder.equals("on")) {
                            btnStartOrder.setBackgroundColor(Color.parseColor("#F4376D"));
                            btnStartOrder.setText(R.string.or_endorder);
                        }
                    }
                    break;

                case 3:
                    Log.i("song", "抢单页面订单列表" + String.valueOf(response));
                    if (middleList != null) {
                        middleList.clear();
                    }
                    OrderListBean orderListBean = gson.fromJson(response.get().toString(), OrderListBean.class);
                    if (orderListBean.getResultCode().equals("999999")) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        List<OrderListBean.ResultDataBean> resultData = orderListBean.getResultData();
                        if (resultData != null && resultData.size() >= 2) {
                            recyclerView.setVisibility(View.VISIBLE);
                            tvNoData.setVisibility(View.GONE);
                            middleList.add(resultData.get(0));
                            middleList.add(resultData.get(1));
                            OrderPartAdapter orderListAdapter = new OrderPartAdapter(middleList, getActivity());
                            recyclerView.setAdapter(orderListAdapter);
                        }else{
                            recyclerView.setVisibility(View.GONE);
                            tvNoData.setVisibility(View.VISIBLE);
                        }
                    } else {

                    }


                    break;

                case 4:
                    Log.i("song", "上传打开接单的状态" + String.valueOf(response));
                    OnSuccessBean onSuccessBean = gson.fromJson(response.get().toString(), OnSuccessBean.class);
                    String onSuccessBeanResultCode = onSuccessBean.getResultCode();
                    String resultDesc = onSuccessBean.getResultDesc();
                    if (onSuccessBeanResultCode.equals("999999")) {
                        //修改成功
                        flag = 1;
                        btnStartOrder.setBackgroundColor(Color.parseColor("#F4376D"));
                        btnStartOrder.setText(R.string.or_endorder);
                        // WebSocketHandler.getDefault().reconnect();
                    } else {
                        Toast.makeText(getActivity(), resultDesc, Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 5:
                    Log.i("song", "从服务器抢单的返回值" + String.valueOf(response));
                    GetServerBean getServerBean = gson.fromJson(response.get().toString(), GetServerBean.class);
                    String code = getServerBean.getResultCode();
                    if (code.equals("999999")) {
                        Toast.makeText(getActivity(), getServerBean.getResultDesc(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), getServerBean.getResultDesc(), Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 6:
                    Log.i("song", "获取接单的文案" + String.valueOf(response));
                    NoticeBean noticeBean = gson.fromJson(response.get().toString(), NoticeBean.class);
                    if (noticeBean.getResultCode().equals("999999")) {
                        showOrderInfo(noticeBean.getResultData());
                    }
                    break;

                case 7:
                    Log.i("song", "上传关闭接单的状态" + String.valueOf(response));
                    OFFSuccessBean offSuccessBean = gson.fromJson(response.get().toString(), OFFSuccessBean.class);
                    String offSuccessBeanResultCode = offSuccessBean.getResultCode();
                    String offdesc = offSuccessBean.getResultDesc();
                    if (offSuccessBeanResultCode.equals("999999")) {
                        //关闭成功
                        //处于停止接单状态
                        flag = 0;
                        btnStartOrder.setBackgroundColor(Color.parseColor("#22C6FE"));
                        btnStartOrder.setText(R.string.or_begin);
                        // WebSocketHandler.getDefault().disConnect();
                    } else {
                        Toast.makeText(getActivity(), offdesc, Toast.LENGTH_SHORT).show();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_startOrder, R.id.ll_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startOrder:
                //显示接单注意事项
                if (flag == 0) {
                    //1、请求文案接口
                    //WebSocketHandler.getDefault().reconnect();
                    getOrderWord();
                } else if (flag == 1) {
                    PostOrderStatueOff();
                }
                break;
            case R.id.ll_more:
                //跳转到全部订单
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void getMyWindowOrder(final ServerOrderBean currentOrderStatue) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popuwindow_qiang_order, null, false);
        orderWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tvJinE = inflate.findViewById(R.id.tv_jinEvalue);
        TextView tvOrderType = inflate.findViewById(R.id.tv_orderTypeValue);
        TextView tvMoneyType = inflate.findViewById(R.id.tv_MoneyTypeValue);
        TextView tvServer = inflate.findViewById(R.id.tv_serverJiangLiValue);
        ImageView ivClose = inflate.findViewById(R.id.iv_close);
        ProgressBar myProgressBar = inflate.findViewById(R.id.order_progress);
        LinearLayout btn_Start = inflate.findViewById(R.id.ll_btn_qiangdan);
        tvSecond = inflate.findViewById(R.id.tv_daojishi);


        tvJinE.setText(currentOrderStatue.getData().getOrderAmount() + "");
        tvOrderType.setText(currentOrderStatue.getData().getType());
        tvMoneyType.setText(currentOrderStatue.getData().getStandardCurrency());
        tvServer.setText(currentOrderStatue.getData().getAwardUsdt() + "");
        mTiemTimeCount.start();

        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = PreManager.instance().getString("token");
                request = NoHttp.createJsonObjectRequest(AppUrl.getServerOrder, RequestMethod.POST);
                String language = PreManager.instance().getString("language");
                request.addHeader("lang", language);
                request.addHeader("token", token);
                request.add("token", token);
                request.add("orderCode", currentOrderStatue.getData().getCode());
                mQueue.add(5, request, responseListener);
            }
        });


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderWindow.dismiss();
            }
        });


        backgroundAlpha(0.5f);
        orderWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        orderWindow.setBackgroundDrawable(new BitmapDrawable());
        orderWindow.setOutsideTouchable(true);
        orderWindow.setTouchable(true);
        orderWindow.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null), Gravity.BOTTOM, 0, 0);
    }


    //显示抢单提示
    private void showOrderInfo(String resultData) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popuwindow_order_info, null, false);
        final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        TextView tvContent = inflate.findViewById(R.id.tv_content);
        ImageView ivClose = inflate.findViewById(R.id.iv_close);
        TextView tvOk = inflate.findViewById(R.id.tv_OK);
        tvContent.setText(resultData);

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改接单状态
                PostOrderStatueOn();

            }
        });


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null), Gravity.CENTER, 0, 0);
    }


    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OrderEvent event) {
        String message = event.getMessage();
        String myEmail = PreManager.instance().getString("myEmail");
        Gson gson = new Gson();
        ServerOrderBean currentOrderStatue = gson.fromJson(message, ServerOrderBean.class);
        String serverEmail = currentOrderStatue.getData().getUserEmail();
        if (!TextUtils.isEmpty(myEmail) && myEmail != null && myEmail.equals(serverEmail)) {
            getMyWindowOrder(currentOrderStatue);
        } else {

        }

    }


    // 计时重发
    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvSecond.setClickable(false);
            tvSecond.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            tvSecond.setText("10s");
            tvSecond.setClickable(true);
            orderWindow.dismiss();

        }
    }
}
