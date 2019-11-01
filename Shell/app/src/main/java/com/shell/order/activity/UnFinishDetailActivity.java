package com.shell.order.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galenleo.widgets.CodeInputView;
import com.google.gson.Gson;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.order.adapter.OrderListAdapter;
import com.shell.order.bean.OrderDetailtBean;
import com.shell.order.bean.OrderListBean;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
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
    private PopupWindow pwdWindow;
    private OrderDetailtBean.ResultDataBean resultData;

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
        tvTitle.setText(getString(R.string.or_ordetail));
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
        request = NoHttp.createJsonObjectRequest(AppUrl.unorderDetail+orderId, RequestMethod.GET);
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
                    OrderDetailtBean orderDetailtBean= gson.fromJson(response.get().toString(), OrderDetailtBean.class);
                    setView(orderDetailtBean);
                    break;
                case 3:
                    Log.i("song", "订单详情的返回值" + String.valueOf(response));
                    String s = response.get().toString();
                    try {
                        JSONObject jsonObject = new JSONObject(response.get().toString());
                        if ("999999".equals(jsonObject.optString("resultCode"))){
                            Toast.makeText(UnFinishDetailActivity.this,jsonObject.optString("resultDesc"),Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(UnFinishDetailActivity.this,jsonObject.optString("resultDesc"),Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

    private void setView(OrderDetailtBean orderDetailtBean) {
        resultData = orderDetailtBean.getResultData();
        tvBianhaoValue.setText(resultData.getCode());
        if ("in".equals(resultData.getType())){
            tvOrderType.setText("转入");
        }else {
            tvOrderType.setText("转出");
        }
        tvOrderPrice.setText(String.valueOf(resultData.getOrderAmount()));
        //tvOrderAddress.setText(resultData.getUserAddress().toString());
        tvBenweihuobi.setText(resultData.getStandardCurrency());
        tvHuilv.setText(String.valueOf(resultData.getExchangeRate()));
        tvJine.setText(String.valueOf(resultData.getStandardAmount()));
        tvFuwujiangli.setText(String.valueOf(resultData.getAwardUsdt()));

        if ("10".equals(resultData.getStatus())){
            btnSend.setVisibility(View.VISIBLE);
        }else {
            btnSend.setVisibility(View.INVISIBLE);
        }
    }


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
                showPopuwindow();
                break;
        }
    }

    private void showPopuwindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_input, null, false);
        pwdWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        Button btnNo = inflate.findViewById(R.id.btn_canle);
        Button btnOK = inflate.findViewById(R.id.btn_ok);

        final CodeInputView editText = inflate.findViewById(R.id.editText);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwdWindow.dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPwd = editText.getText().toString().trim();
                if (TextUtils.isEmpty(inputPwd)){
                    return;
                }
                //获取输入的密码
                String token = PreManager.instance().getString("token");
                request = NoHttp.createJsonObjectRequest(AppUrl.ZhuanChu_Ordert, RequestMethod.POST);
                request.addHeader("token", token);
                request.add("payPassword", inputPwd);
                request.add("orderCode", resultData.getCode());
                mQueue.add(3, request, responseListener);
            }
        });


        backgroundAlpha(0.5f);

        pwdWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        pwdWindow.setBackgroundDrawable(new BitmapDrawable());
        pwdWindow.setOutsideTouchable(true);
        pwdWindow.setTouchable(true);
        pwdWindow.setFocusable(true);
        pwdWindow.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_zhiya, null), Gravity.CENTER, 0, 0);
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
