package com.shell.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.shell.Bean.LanguageEvent;
import com.shell.Bean.LoginBean;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.mine.activity.LanguageActivity;
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

public class LoginActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.ll_top)
    RelativeLayout llTop;
    @BindView(R.id.tv_leftTitle)
    TextView tvLeftTitle;
    @BindView(R.id.rlLeft)
    RelativeLayout rlLeft;
    @BindView(R.id.rl_forget)
    RelativeLayout rlForget;
    private Request<JSONObject> request;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.profile_image)
    RoundedImageView profileImage;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_toregister)
    Button btnToregister;
    @BindView(R.id.tv_forgetPwd)
    TextView tvForgetPwd;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(R.string.log_login);
        EventBus.getDefault().register(this);
        etAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showKeyboard(true);
                }
            }
        });
    }

    protected void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
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

    @OnClick({R.id.btn_login, R.id.btn_toregister, R.id.rl_forget, R.id.ll_top, R.id.rlLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //点击登录
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                String deviceId = Build.SERIAL;
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "邮箱或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    request = NoHttp.createJsonObjectRequest(AppUrl.LoginUrl, RequestMethod.POST);
                    request.add("email", account);
                    request.add("password", pwd);
                    request.add("device", deviceId);
                    mQueue.add(1, request, responseListener);
                }
                break;
            case R.id.btn_toregister:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_forget:
                Intent intent1 = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_top:
                //切换语言
                Intent intent6 = new Intent(LoginActivity.this, LanguageActivity.class);
                startActivity(intent6);
                break;
            case R.id.rlLeft:
                Intent intent7 = new Intent(LoginActivity.this, MainActivity.class);
                PreManager.instance().putString("fromType", "login");
                startActivity(intent7);
                finish();
                break;
        }
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(LoginActivity.this);
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
                    Log.i("song", "登录返回的参数" + String.valueOf(response));
                    LoginBean loginBean = gson.fromJson(response.get().toString(), LoginBean.class);
                    if (loginBean.getResultCode().equals("999999")) {
                        Toast.makeText(LoginActivity.this, loginBean.getResultDesc(), Toast.LENGTH_SHORT).show();
                        PreManager.instance().putBoolean("ISLogin", true);
                        PreManager.instance().putString("token", loginBean.getResultData());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, loginBean.getResultDesc(), Toast.LENGTH_SHORT).show();
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LanguageEvent event) {
        String message = event.getMessage();
        tvRightTitle.setText(message);
    }


}
