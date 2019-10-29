package com.shell.money.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.ClipboardManager;
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
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.money.Bean.CardBean;
import com.shell.money.Bean.CardUnderBean;
import com.shell.money.Bean.TiBiBean;
import com.shell.money.Bean.TiBiResult;
import com.shell.money.adapter.TiBiAdapter;
import com.shell.money.adapter.TiBiRecordAdapter;
import com.shell.money.adapter.UnderCardAdapter;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leefeng.viewlibrary.PEditTextView;

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
    private List<TiBiBean.ResultDataBean> firstList;
    private String tiBiAddress;
    private String tiBiCount;
    private float inputCount;
    private String inputPwd;
    private String myBalance;
    private int finalGet;
    private PopupWindow window;


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
        tvTitle.setText("USDT提币");
        tvRightTitle.setVisibility(View.GONE);

        etTiBiCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                     //用户最终输入的数量
                finalGet = Integer.parseInt(s.toString())-5;
               if(finalGet <0){
                  tvTwoRight.setText("0"+"USDT");
               }else{
                   tvTwoRight.setText(finalGet +"USDT");
               }
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        myBalance = getIntent().getStringExtra("myBalance");
        getTiBiData();
    }

    private void getTiBiData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.CardUnderUrl, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        request.add("busiCode", "withdraw");
        request.add("status", "");
        request.add("pageNum", page);
        mQueue.add(1, request, responseListener);
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
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "提币数据返回值" + String.valueOf(response));
                    TiBiBean tiBiBean= gson.fromJson(response.get().toString(), TiBiBean.class);
                    if(tiBiBean!=null && tiBiBean.getResultData().size()>0){
                        firstList = tiBiBean.getResultData();
                        TiBiRecordAdapter underCardAdapter = new TiBiRecordAdapter(firstList,TiBiActivity.this);
                        listView.setAdapter(underCardAdapter);
                    }else{

                    }
                    break;

                case 3:
                    Log.i("song", "提币结果的返回值" + String.valueOf(response));
                    TiBiResult tiBiResult= gson.fromJson(response.get().toString(), TiBiResult.class);
                    if(tiBiResult.getResultCode().equals("999999")){
                        window.dismiss();
                        Toast.makeText(TiBiActivity.this,tiBiResult.getResultDesc(),Toast.LENGTH_SHORT).show();
                        getTiBiData();
                    }else{
                        window.dismiss();
                        Toast.makeText(TiBiActivity.this,tiBiResult.getResultDesc(),Toast.LENGTH_SHORT).show();
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
    }

    @OnClick({R.id.rl_back, R.id.et_account, R.id.iv_sao, R.id.tv_all, R.id.btn_TiXian})
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
                                //扫码成功
                                break;
                            case MNScanManager.RESULT_FAIL:
                                //扫码失败
                                String resultError = data.getStringExtra(MNScanManager.INTENT_KEY_RESULT_ERROR);
                                break;
                            case MNScanManager.RESULT_CANCLE:
                                showToast("取消扫码");
                                break;
                        }
                    }
                });
                break;
            case R.id.tv_all:
                //全部提现按钮,把币圈的数值传递过来
                int allBi=Integer.parseInt(myBalance);
                finalGet=allBi-5;
                if(finalGet <0){
                    tvTwoRight.setText("0"+"USDT");
                }else{
                    tvTwoRight.setText(finalGet +"USDT");
                }

                break;
            case R.id.btn_TiXian:
                //提现
                tiBiAddress = etAccount.getText().toString().trim();
                tiBiCount = etTiBiCount.getText().toString().trim();
                inputCount = Float.parseFloat(tiBiCount);
                if(TextUtils.isEmpty(tiBiAddress) || TextUtils.isEmpty(tiBiCount)){

                }else{
                    showPopuwindow();
                }
                break;
        }
    }




    private void showPopuwindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_newtibi, null, false);
        window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);


        Button btnNo= inflate.findViewById(R.id.btn_canle);
        Button btnOK= inflate.findViewById(R.id.btn_ok);

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
                float userInput=Float.parseFloat(inputCount);

                request = NoHttp.createJsonObjectRequest(AppUrl.TIBIURL, RequestMethod.POST);
                request.addHeader("token", token);
                request.add("token", token);
                request.add("address", tiBiAddress);
                request.add("amount", userInput);
                request.add("payPassword", inputPwd);
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
