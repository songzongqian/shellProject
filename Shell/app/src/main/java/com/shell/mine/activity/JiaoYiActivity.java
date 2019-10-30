package com.shell.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galenleo.widgets.CodeInputView;
import com.google.gson.Gson;
import com.shell.Bean.EmailCodeBean;
import com.shell.Bean.RegisterBean;
import com.shell.R;
import com.shell.activity.LoginActivity;
import com.shell.activity.MainActivity;
import com.shell.activity.RegisterActivity;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
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

public class JiaoYiActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_Code)
    TextView tvCode;
    @BindView(R.id.editText)
    CodeInputView editText;
    @BindView(R.id.btn_change)
    Button btnChange;
    private Request<JSONObject> request;
    private String emailCode;
    private String inputPwd;


    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jiaoyi;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.tv_jy));
        tvRightTitle.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.tv_Code, R.id.btn_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_Code:
                String token = PreManager.instance().getString("token");
                if (TextUtils.isEmpty(token)) {
                    Intent intent = new Intent(JiaoYiActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                        request = NoHttp.createJsonObjectRequest(AppUrl.getJiaoYiCode, RequestMethod.POST);
                        request.addHeader("token", token);
                        mQueue.add(1, request, responseListener);
                    }

                break;
            case R.id.btn_change:
                inputPwd = editText.getText().toString().trim();
                if (TextUtils.isEmpty(inputPwd)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    goToChange();
                }
                break;
        }
    }

    private void goToChange() {
        String token = PreManager.instance().getString("token");
        String inputCode = etCode.getText().toString().trim();
        request = NoHttp.createJsonObjectRequest(AppUrl.JiaoYiURL, RequestMethod.POST);
        request.addHeader("token", token);
        request.add("token", token);
        request.add("veriCode", inputCode);
        request.add("payPassword", inputPwd);
        mQueue.add(2, request, responseListener);
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(JiaoYiActivity.this);
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
                    Log.i("song", "交易获取的短信验证码" + String.valueOf(response));
                    EmailCodeBean emailCodeBean = gson.fromJson(response.get().toString(), EmailCodeBean.class);
                    if (emailCodeBean.getResultCode().equals("999999")) {
                        emailCode = emailCodeBean.getResultData();
                    }
                    break;
                case 2:
                    Log.i("song", "交易密码修改返回的值" + String.valueOf(response));
                    RegisterBean registerBean = gson.fromJson(response.get().toString(), RegisterBean.class);
                    if (registerBean.getResultCode().equals("999999")) {
                        Toast.makeText(JiaoYiActivity.this, registerBean.getResultDesc(), Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(JiaoYiActivity.this, registerBean.getResultDesc(), Toast.LENGTH_SHORT).show();
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




}
