package com.shell.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.zxing.client.android.utils.ZXingUtils;
import com.shell.Bean.HelpBean;
import com.shell.Bean.MyInfoBean;
import com.shell.R;
import com.shell.activity.ForgetActivity;
import com.shell.base.BaseActivity;
import com.shell.commom.LogonFailureUtil;
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

public class HelpFriendActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.iv_qrCode)
    ImageView ivQrCode;
    @BindView(R.id.tv_helpCode)
    TextView tvHelpCode;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_1)
    TextView tv1;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_helpfriend;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvRightTitle.setText("我的好友");
        tvTitle.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        String  token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.MyHelpUrl, RequestMethod.GET);
        request.addHeader("token", token);
        mQueue.add(1, request, responseListener);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.tv_rightTitle, R.id.btn_save, R.id.tv_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_rightTitle:
                Intent intent1= new Intent(HelpFriendActivity.this, MyFriendActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_save:
                //保存图片到相册
                break;
            case R.id.tv_copy:
                //复制粘贴连接
                break;
        }
    }






    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(HelpFriendActivity.this);
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            LogonFailureUtil.gotoLoginActiviy(HelpFriendActivity.this,response.get().toString());
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "邀请好友的返回值" + String.valueOf(response));
                    HelpBean myInfoBean= gson.fromJson(response.get().toString(), HelpBean.class);
                    String resultCode = myInfoBean.getResultCode();
                    HelpBean.ResultDataBean resultData = myInfoBean.getResultData();
                    if(resultCode.equals("999999")){
                        RequestOptions options = new RequestOptions()
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher);

                        Bitmap qrImage = ZXingUtils.createQRImage(resultData.getInviteUrl());
                        ivQrCode.setImageBitmap(qrImage);
                        tvHelpCode.setText("邀请码："+" "+resultData.getMyinviteCode());
                        tv1.setText(resultData.getDescText());
                    }else{

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
