package com.shell.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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
    @BindView(R.id.iv_country)
    ImageView ivCountry;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.et_country)
    EditText etCountry;
    @BindView(R.id.ll_country)
    RelativeLayout llCountry;
    @BindView(R.id.rl_country)
    RelativeLayout rlCountry;
    private Request<JSONObject> request;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
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

    @OnClick({R.id.profile_image, R.id.tv_Code, R.id.btn_register, R.id.btn_toLogin, R.id.tv_rightTitle, R.id.iv_country, R.id.rl_country})
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
            case R.id.iv_country:
                //选择国家的动作
                if (mypopuWindow == null) {
                    OnClickLintener paramOnclickListen = new OnClickLintener();
                    mypopuWindow = new MypopuWindow(RegisterActivity.this, paramOnclickListen, 250, 500);
                    mypopuWindow.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            if (!b) {
                                mypopuWindow.dismiss();
                            }
                        }
                    });
                }
                mypopuWindow.setFocusable(true);
                mypopuWindow.showAsDropDown(ivCountry, 0, 0);
                mypopuWindow.update();
                break;


            case R.id.rl_country:
                if (mypopuWindow == null) {
                    OnClickLintener paramOnclickListen = new OnClickLintener();
                    mypopuWindow = new MypopuWindow(RegisterActivity.this, paramOnclickListen, 250, 500);
                    mypopuWindow.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            if (!b) {
                                mypopuWindow.dismiss();
                            }
                        }
                    });
                }
                mypopuWindow.setFocusable(true);
                mypopuWindow.showAsDropDown(ivCountry, 0, 0);
                mypopuWindow.update();
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
        request.add("inviteCode", "");
        request.add("source", "android");
        request.add("device", deviceId);
        mQueue.add(2, request, responseListener);

    }

    private void getEmailCode() {
        inputEmail = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(inputEmail)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
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
            tvCode.setText(millisUntilFinished / 1000 + "秒后重新发送");
        }

        @Override
        public void onFinish() {
            tvCode.setText("获取验证码");
            tvCode.setClickable(true);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LanguageEvent event) {
        String message = event.getMessage();
        tvRightTitle.setText(message);
    }


    class OnClickLintener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_zhongguo:
                    PreManager.instance().putString("country", "中国");
                    country = "中国";
                    etCountry.setText("中国");
                    tvCountry.setText("中国");
                    mypopuWindow.dismiss();
                    break;
                case R.id.tv_hanguo:
                    PreManager.instance().putString("country", "韩国");
                    country = "韩国";
                    tvCountry.setText("韩国");
                    etCountry.setText("韩国");
                    mypopuWindow.dismiss();
                    break;
                case R.id.tv_riben:
                    PreManager.instance().putString("country", "日本");
                    country = "日本";
                    tvCountry.setText("日本");
                    etCountry.setText("日本");
                    mypopuWindow.dismiss();
                    break;
                case R.id.tv_om:
                    PreManager.instance().putString("country", "欧盟");
                    country = "欧盟";
                    tvCountry.setText("欧盟");
                    etCountry.setText("欧盟");
                    mypopuWindow.dismiss();
                    break;
                case R.id.tv_mg:
                    PreManager.instance().putString("country", "美国");
                    country = "美国";
                    tvCountry.setText("美国");
                    etCountry.setText("美国");
                    mypopuWindow.dismiss();
                    break;
                default:
                    break;
            }

        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
