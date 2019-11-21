package com.shell.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.shell.Bean.EmailCodeBean;
import com.shell.Bean.LanguageEvent;
import com.shell.Bean.RegisterBean;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.mine.activity.LanguageActivity;
import com.shell.utils.MypopuWindow;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.et_country)
    TextView etCountry;
    @BindView(R.id.ll_country)
    RelativeLayout llCountry;
    @BindView(R.id.rl_country)
    RelativeLayout rlCountry;
    @BindView(R.id.tv_leftTitle)
    TextView tvLeftTitle;
    @BindView(R.id.rlLeft)
    RelativeLayout rlLeft;
    private Request<JSONObject> request;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.profile_image)
    RoundedImageView profileImage;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_Code)
    TextView tvCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_friendCode)
    EditText etFriendCode;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_toLogin)
    Button btnToLogin;
    private TimeCount mTiemTimeCount;
    private String inputEmail;
    private String emailCode;
    private MypopuWindow mypopuWindow;
    String country = "";

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(R.string.re_register);
        EventBus.getDefault().register(this);
        mTiemTimeCount = new TimeCount(60000, 1000);
        rlLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        String mychoose = PreManager.instance().getString("mychoose");
        if (!TextUtils.isEmpty(mychoose) && mychoose != null) {
            tvRightTitle.setText(mychoose);
        } else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.profile_image, R.id.tv_Code, R.id.btn_register, R.id.btn_toLogin, R.id.tv_rightTitle, R.id.ll_country})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                break;
            case R.id.tv_Code:
                getEmailCode();
                break;
            case R.id.btn_register:
                RegisterToServer();
                break;
            case R.id.btn_toLogin:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_rightTitle:
                Intent intent6 = new Intent(RegisterActivity.this, LanguageActivity.class);
                startActivity(intent6);
                break;

            case R.id.ll_country:
                Intent intent1 = new Intent();
                intent1.setClass(RegisterActivity.this, CountryActivity.class);
                startActivityForResult(intent1, 12);
                break;
        }
    }

    private void RegisterToServer() {
        String inputPwd = etPwd.getText().toString().trim();
        String friendCode = etFriendCode.getText().toString().trim();
        String deviceId = Build.SERIAL;
        String inputCode = etCode.getText().toString().trim();
        Log.i("song", "设备ID" + deviceId);
        request = NoHttp.createJsonObjectRequest(AppUrl.RegisterUrl, RequestMethod.POST);
        Log.i("song", "选择的国家" + country);
        request.add("email", inputEmail);
        request.add("country", country);
        request.add("veriCode", inputCode);
        request.add("password", inputPwd);
        request.add("inviteCode", friendCode);
        request.add("source", "android");
        request.add("device", deviceId);
        mQueue.add(2, request, responseListener);

    }

    private void getEmailCode() {
        inputEmail = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(inputEmail)) {

        } else {
            mTiemTimeCount.start();
            request = NoHttp.createJsonObjectRequest(AppUrl.getEmailCode, RequestMethod.POST);
            request.add("email", inputEmail);
            mQueue.add(1, request, responseListener);
        }


    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(RegisterActivity.this);
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
                    Log.i("song", "获取的短信验证码" + String.valueOf(response));
                    EmailCodeBean emailCodeBean = gson.fromJson(response.get().toString(), EmailCodeBean.class);
                    if (emailCodeBean.getResultCode().equals("999999")) {
                        emailCode = emailCodeBean.getResultData();
                    }

                    break;

                case 2:
                    Log.i("song", "注册返回信息" + String.valueOf(response));
                    RegisterBean registerBean = gson.fromJson(response.get().toString(), RegisterBean.class);
                    if (registerBean.getResultCode().equals("999999")) {
                        Toast.makeText(RegisterActivity.this, registerBean.getResultDesc(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, registerBean.getResultDesc(), Toast.LENGTH_SHORT).show();
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
            tvCode.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            tvCode.setText(R.string.re_getcode);
            tvCode.setClickable(true);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LanguageEvent event) {
        String message = event.getMessage();
        tvRightTitle.setText(message);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 12:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String countryName = bundle.getString("countryName");
                    PreManager.instance().putString("country", countryName);
                    country = countryName;
                    etCountry.setText(countryName);
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
