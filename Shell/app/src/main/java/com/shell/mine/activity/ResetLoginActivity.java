package com.shell.mine.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.shell.activity.MainActivity;
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

public class ResetLoginActivity extends BaseActivity {
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
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_agianpwd)
    EditText etAgianpwd;
    @BindView(R.id.btn_change)
    Button btnChange;
    private String oldPwd;
    private String firstPwd;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_resetlogin;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.setlogin));
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

    @OnClick({R.id.rl_back, R.id.btn_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_change:
                oldPwd = etAccount.getText().toString().trim();
                firstPwd = etPwd.getText().toString().trim();
                String agianPwd = etAgianpwd.getText().toString().trim();

                if(TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(firstPwd) || TextUtils.isEmpty(agianPwd)){

                }else{
                    if(!firstPwd.equals(agianPwd)){

                    }else{
                        geToChange();
                    }
                }
                break;
        }
    }

    private void geToChange() {
        request = NoHttp.createJsonObjectRequest(AppUrl.ChangeLoginPwd, RequestMethod.POST);
        String token = PreManager.instance().getString("token");
        request.addHeader("token", token);
        request.add("token", token);
        request.add("oldPassword", oldPwd);
        request.add("newPassword", firstPwd);
        mQueue.add(1, request, responseListener);
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(ResetLoginActivity.this);
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
                    Log.i("song", "修改密码的返回值" + String.valueOf(response));
                    String serverResponse=String.valueOf(response);
                    if(serverResponse.contains("404")){

                    }else{
                        EmailCodeBean emailCodeBean = gson.fromJson(response.get().toString(), EmailCodeBean.class);
                        if(emailCodeBean.getResultCode().equals("999999")){
                          Toast.makeText(ResetLoginActivity.this,emailCodeBean.getResultDesc(),Toast.LENGTH_SHORT).show();
                        }else{
                          Toast.makeText(ResetLoginActivity.this,emailCodeBean.getResultDesc(),Toast.LENGTH_SHORT).show();
                        }
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
