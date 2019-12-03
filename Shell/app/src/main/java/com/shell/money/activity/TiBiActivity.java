package com.shell.money.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galenleo.widgets.CodeInputView;
import com.google.gson.Gson;
import com.google.zxing.client.android.MNScanManager;
import com.google.zxing.client.android.other.MNScanCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.commom.LogonFailureUtil;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.mine.activity.JiaoYiActivity;
import com.shell.money.Bean.LimitBean;
import com.shell.money.Bean.TiBiBean;
import com.shell.money.Bean.TiBiResult;
import com.shell.money.adapter.TiBiRecordAdapter;
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

public class TiBiActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    private int page = 1;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.iv_sao)
    ImageView ivSao;
    @BindView(R.id.tv_TiBiCount)
    TextView tvTiBiCount;
    @BindView(R.id.et_tiBiCount)
    EditText etTiBiCount;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_oneLeft)
    TextView tvOneLeft;
    @BindView(R.id.tv_oneRight)
    TextView tvOneRight;
    @BindView(R.id.tv_twoLeft)
    TextView tvTwoLeft;
    @BindView(R.id.tv_twoRight)
    TextView tvTwoRight;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.btn_TiXian)
    Button btnTiXian;
    @BindView(R.id.tv_shuliang)
    TextView tvShuliang;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.tv_viewPager_one)
    TextView tvViewPagerOne;
    @BindView(R.id.tv_viewPager_twe)
    TextView tvViewPagerTwe;
    private int AllPager = 0;
    private int CurrentPager = 1;
    private List<TiBiBean.ResultDataBean> firstList = new ArrayList<>();
    private String tiBiAddress;
    private String tiBiCount;
    private float inputCount;
    private String inputPwd;
    private String myBalance;
    private Double finalGet;
    private PopupWindow window;
    private TiBiRecordAdapter underCardAdapter;
    private int fee = 0;
    private int minWithdrawAmount = 0;
    private String coinCode = "ETH_USDT";

    @Override
    protected void initToolBar() {
    }

    @Override
    protected void initStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tibi;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.rmb_tibi));
        tvRightTitle.setVisibility(View.GONE);

        etTiBiCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etTiBiCount.getText().toString())) {
                    Double allBi = Double.parseDouble(etTiBiCount.getText().toString());
                    if (allBi < Double.parseDouble(myBalance)) {
                        finalGet = allBi - fee;
                        if (finalGet < 0) {
                            tvTwoRight.setText("0" + "USDT");
                        } else {
                            tvTwoRight.setText(finalGet + "USDT");
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        underCardAdapter = new TiBiRecordAdapter(firstList, TiBiActivity.this);
        listView.setAdapter(underCardAdapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        myBalance = getIntent().getStringExtra("myBalance");
        tvTiBiCount.setText(getString(R.string.available) + myBalance);
        getTiBiData();
        tvTiBiCount.setText(myBalance);
    }

    private void getTiBiData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.CardUnderUrl + CurrentPager, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        request.add("busiCode", "withdraw");
        request.add("status", "");
        request.add("pageNum", page);
        mQueue.add(1, request, responseListener);


        request = NoHttp.createJsonObjectRequest(AppUrl.getWalletLimit, RequestMethod.GET);
        mQueue.add(2, request, responseListener);
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(TiBiActivity.this);
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            LogonFailureUtil.gotoLoginActiviy(TiBiActivity.this, response.get().toString());
            String s = response.get().toString();
            System.out.println("------"+s);
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "提币数据返回值" + String.valueOf(response));
                    TiBiBean tiBiBean = gson.fromJson(response.get().toString(), TiBiBean.class);
                    List<TiBiBean.ResultDataBean> resultData = tiBiBean.getResultData();
                    AllPager = tiBiBean.getPages();
                    if (1 == CurrentPager) {
                        firstList.clear();
                    }
                    firstList.addAll(resultData);
                    underCardAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    LimitBean limitBean = gson.fromJson(response.get().toString(), LimitBean.class);
                    if ("999999".equals(limitBean.getResultCode())) {
                        fee = limitBean.getResultData().getFee();
                        tvOneRight.setText(String.valueOf(fee)+" USDT");
                        minWithdrawAmount = limitBean.getResultData().getMinWithdrawAmount();
                        String newString = tvThree.getText().toString().replace("100", String.valueOf(minWithdrawAmount));
                        tvThree.setText(newString);
                    }
                    break;
                case 3:
                    Log.i("song", "提币结果的返回值" + String.valueOf(response));
                    TiBiResult tiBiResult = gson.fromJson(response.get().toString(), TiBiResult.class);
                    if (tiBiResult.getResultCode().equals("999999")) {
                        window.dismiss();
                        Toast.makeText(TiBiActivity.this, tiBiResult.getResultDesc(), Toast.LENGTH_SHORT).show();
                        getTiBiData();
                    } else {
                        window.dismiss();
                        Toast.makeText(TiBiActivity.this, tiBiResult.getResultDesc(), Toast.LENGTH_SHORT).show();
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
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                CurrentPager = 1;
                getTiBiData();
                refreshLayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CurrentPager < AllPager) {
                    CurrentPager++;
                    getTiBiData();
                    refreshLayout.finishLoadMore(2000);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.et_account, R.id.iv_sao, R.id.tv_all, R.id.btn_TiXian, R.id.tv_viewPager_one, R.id.tv_viewPager_twe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.et_account:
                break;
            case R.id.iv_sao:
                //扫一扫

                MNScanManager.startScan(this, new MNScanCallback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        switch (resultCode) {
                            case MNScanManager.RESULT_SUCCESS:
                                String resultSuccess = data.getStringExtra(MNScanManager.INTENT_KEY_RESULT_SUCCESS);
                                etAccount.setText(resultSuccess);
                                //扫码成功
                                break;
                            case MNScanManager.RESULT_FAIL:
                                //扫码失败
                                String resultError = data.getStringExtra(MNScanManager.INTENT_KEY_RESULT_ERROR);
                                break;
                            case MNScanManager.RESULT_CANCLE:
                              //  showToast("取消扫码");
                                break;
                        }
                    }
                });
                break;
            case R.id.tv_all:
                //全部提现按钮,把币圈的数值传递过来
                etTiBiCount.setText(myBalance);
                Double allBi = Double.parseDouble(myBalance);
                finalGet = allBi - fee;
                if (finalGet < 0) {
                    tvTwoRight.setText("0" + "USDT");
                } else {
                    tvTwoRight.setText(finalGet + "USDT");
                }

                break;
            case R.id.btn_TiXian:
                //提现
                tiBiAddress = etAccount.getText().toString().trim();
                tiBiCount = etTiBiCount.getText().toString().trim();
                if (TextUtils.isEmpty(tiBiAddress) || TextUtils.isEmpty(tiBiCount)) {

                } else {
                    Boolean aBoolean = PreManager.instance().getBoolean(AppUrl.isSetPayPwd);
                    if (aBoolean) {
                        inputCount = Float.parseFloat(tiBiCount);
                        showPopuwindow();
                    } else {
                        gotosetPayPwd();
                    }
                }
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
            coinCode = "ETH_USDT";
            tvViewPagerOne.setBackgroundResource(R.drawable.card_home_country);
            tvViewPagerTwe.setBackgroundResource(R.drawable.omni_283040_bg);
        } else {
            coinCode = "USDT";
            tvViewPagerOne.setBackgroundResource(R.drawable.omni_283040_bg);
            tvViewPagerTwe.setBackgroundResource(R.drawable.card_home_country);
        }
    }
    private void gotosetPayPwd() {
        View inflate = LayoutInflater.from(TiBiActivity.this).inflate(R.layout.popuwindow_set_pay_pwd, null, false);
        final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        TextView tvContent = inflate.findViewById(R.id.tv_content);
        TextView tvOk = inflate.findViewById(R.id.tv_OK);

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiBiActivity.this, JiaoYiActivity.class);
                startActivity(intent);
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
        window.showAtLocation(LayoutInflater.from(TiBiActivity.this).inflate(R.layout.fragment_home, null), Gravity.CENTER, 0, 0);
    }

    private void showPopuwindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_newtibi, null, false);
        window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);


        Button btnNo = inflate.findViewById(R.id.btn_canle);
        Button btnOK = inflate.findViewById(R.id.btn_ok);

        final CodeInputView editText = inflate.findViewById(R.id.editText);


        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的密码
                String inputPwd = editText.getText().toString().trim();
                String token = PreManager.instance().getString("token");
                String inputCount = etTiBiCount.getText().toString().trim();
                float userInput = Float.parseFloat(inputCount);

                request = NoHttp.createJsonObjectRequest(AppUrl.TIBIURL, RequestMethod.POST);
                request.addHeader("token", token);
                request.add("token", token);
                request.add("address", tiBiAddress);
                request.add("amount", userInput);
                request.add("payPassword", inputPwd);
                request.add("coinCode", coinCode);
                mQueue.add(3, request, responseListener);
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
        window.setFocusable(true);
        window.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_zhiya, null), Gravity.CENTER, 0, 0);
    }


    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
