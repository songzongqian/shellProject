package com.shell.mine.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.zxing.client.android.utils.ZXingUtils;
import com.shell.Bean.HelpBean;
import com.shell.Bean.NickBean;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
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

public class NickActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.et_email)
    EditText etEmail;
    private String inputNick;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nick;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(R.string.my_nick);
        tvRightTitle.setText(getString(R.string.nick_finish));
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

    @OnClick({R.id.rl_back, R.id.tv_rightTitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_rightTitle:
                inputNick = etEmail.getText().toString().trim();
                if(!TextUtils.isEmpty(inputNick)){
                    Log.i("song","用户输入的昵称"+inputNick);
                    ChangeNickData();
                }else{
                    Toast.makeText(NickActivity.this,"请输入昵称",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }



    private void ChangeNickData() {
        String  token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.ChangNick, RequestMethod.POST);
        request.addHeader("token", token);
        request.add("token",token);
        request.addHeader("name", inputNick);
        mQueue.add(1, request, responseListener);
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(NickActivity.this);
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
                    Log.i("song", "修改昵称的返回值" + String.valueOf(response));
                    NickBean nickBean= gson.fromJson(response.get().toString(), NickBean.class);
                    if(nickBean.getResultCode().equals("999999")){
                        Toast.makeText(NickActivity.this,nickBean.getResultDesc(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(NickActivity.this,nickBean.getResultDesc(),Toast.LENGTH_SHORT).show();
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
