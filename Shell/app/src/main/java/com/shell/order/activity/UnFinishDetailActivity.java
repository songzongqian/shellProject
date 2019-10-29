package com.shell.order.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnFinishDetailActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    private int page = 1;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.tv_bianhaoValue)
    TextView tvBianhaoValue;
    @BindView(R.id.tv_orderType)
    TextView tvOrderType;
    @BindView(R.id.tv_orderPrice)
    TextView tvOrderPrice;
    @BindView(R.id.tv_orderAddress)
    TextView tvOrderAddress;
    @BindView(R.id.tv_benweihuobi)
    TextView tvBenweihuobi;
    @BindView(R.id.tv_huilv)
    TextView tvHuilv;
    @BindView(R.id.tv_jine)
    TextView tvJine;
    @BindView(R.id.tv_fuwujiangli)
    TextView tvFuwujiangli;
    @BindView(R.id.btn_Send)
    Button btnSend;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unfinish;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("订单详情");
        tvRightTitle.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        long orderId = getIntent().getLongExtra("orderId",0);
        String orderStatue = getIntent().getStringExtra("orderStatue");

        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.unorderDetail, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("orderId", orderId);
        request.add("token", token);
        mQueue.add(1, request, responseListener);
    }

    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(UnFinishDetailActivity.this);
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "订单详情的返回值" + String.valueOf(response));
                   // OrderListBean orderListBean= gson.fromJson(response.get().toString(), OrderListBean.class);


                    break;
            }
        }


        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            Log.i("song", "订单详情的返回值失败" + String.valueOf(response));
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
    }

    @OnClick({R.id.rl_back, R.id.btn_Send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_Send:
                break;
        }
    }
}
