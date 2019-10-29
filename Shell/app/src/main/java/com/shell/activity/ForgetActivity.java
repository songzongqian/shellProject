package com.shell.activity;

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

import com.google.gson.Gson;
import com.shell.Bean.EmailCodeBean;
import com.shell.Bean.RegisterBean;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
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

public class ForgetActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_Code)
    TextView tvCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_agianpwd)
    EditText etAgianpwd;
    @BindView(R.id.btn_change)
    Button btnChange;
    private TimeCount mTiemTimeCount;
    private String emailCode;
    private String inputEmail;
    private String firstPwd;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(R.string.forget_title);
        tvRightTitle.setVisibility(View.GONE);
        mTiemTimeCount = new TimeCount(60000, 1000);

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
                inputEmail = etAccount.getText().toString().trim();
                if(TextUtils.isEmpty(inputEmail)) {
                    Toast.makeText(this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else{
                    mTiemTimeCount.start();
                    request = NoHttp.createJsonObjectRequest(AppUrl.ForgetCode, RequestMethod.POST);
                    request.add("email", inputEmail);
                    mQueue.add(1, request, responseListener);
                }
                break;
            case R.id.btn_change:
                firstPwd = etPwd.getText().toString().trim();
                String agianPwd=etAgianpwd.getText().toString().trim();
                if(TextUtils.isEmpty(firstPwd) || TextUtils.isEmpty(agianPwd)){
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    if(!firstPwd.equals(agianPwd)){
                        Toast.makeText(this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                    }else{
                        goToChange();
                    }
                }

                break;
        }
    }

    private void goToChange() {
        request = NoHttp.createJsonObjectRequest(AppUrl.ResetCode, RequestMethod.POST);
        request.add("email", inputEmail);
        request.add("veriCode", emailCode);
        request.add("newPassword", firstPwd);
        mQueue.add(2, request, responseListener);
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(ForgetActivity.this);
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
                    Log.i("song", "忘记密码短信验证码" + String.valueOf(response));
                    EmailCodeBean emailCodeBean = gson.fromJson(response.get().toString(), EmailCodeBean.class);
                    if(emailCodeBean.getResultCode().equals("999999")){
                        emailCode = emailCodeBean.getResultData();
                    }

                    break;

                case 2:
                    Log.i("song", "忘记密码返回信息" + String.valueOf(response));
                    RegisterBean registerBean=gson.fromJson(response.get().toString(), RegisterBean.class);
                    if(registerBean.getResultCode().equals("999999")){
                        Toast.makeText(ForgetActivity.this,"重置密码成功",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(ForgetActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else if(registerBean.getResultCode().equals("000008")){
                        Toast.makeText(ForgetActivity.this,registerBean.getResultDesc(),Toast.LENGTH_SHORT).show();
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























    // 计时重发
    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvCode.setClickable(false);
            tvCode.setText(millisUntilFinished / 1000 + "秒后重新发送");
        }

        @Override
        public void onFinish() {
            tvCode.setText("获取验证码");
            tvCode.setClickable(true);
        }
    }
}
