package com.shell.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shell.Bean.JieDianBean;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.money.Bean.CardBean;
import com.shell.money.Bean.CardUnderBean;
import com.shell.money.adapter.MoneyPageAdapter;
import com.shell.utils.GetTwoLetter;
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
import de.hdodenhof.circleimageview.CircleImageView;
import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;

public class JiaDianActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.ll_VIP)
    RelativeLayout llVIP;
    @BindView(R.id.pb)
    ZzHorizontalProgressBar pb;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.tv_suanli)
    TextView tvSuanli;
    @BindView(R.id.tv_suanliValue)
    TextView tvSuanliValue;
    @BindView(R.id.tv_suanli2)
    TextView tvSuanli2;
    @BindView(R.id.tv_suanli2Value)
    TextView tvSuanli2Value;
    @BindView(R.id.tv_shuoming)
    TextView tvShuoming;
    @BindView(R.id.tv_shuocontent)
    TextView tvShuocontent;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jiedian;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.jd_introduce));
        tvRightTitle.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        String headUrl = getIntent().getStringExtra("headUrl");
        String nickName = getIntent().getStringExtra("nickName");
        Glide.with(this).load(headUrl).into(ivHead);
        tvNickName.setText(nickName);

        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getUserJieDian, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);

    }



    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(JiaDianActivity.this);
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
                    Log.i("song", "节点说明的返回值" + String.valueOf(response));
                  JieDianBean jieDianBean = gson.fromJson(response.get().toString(), JieDianBean.class);
                    if(jieDianBean.getResultCode().equals("999999")) {
                         JieDianBean.ResultDataBean dataBean = jieDianBean.getResultData();
                         tvVip.setText("BLv"+dataBean.getLevel());
                         tvProgress.setText(dataBean.getBrokerComplete()+"/"+dataBean.getBrokerNeed());
                         tvSuanliValue.setText(GetTwoLetter.getTwo(dataBean.getHashRate()));
                         tvSuanli2Value.setText(GetTwoLetter.getTwo(dataBean.getAllHashRate()));
                         tvShuocontent.setText(dataBean.getLevelTextDesc());
                         pb.setMax(dataBean.getBrokerNeed());
                         pb.setProgress(dataBean.getBrokerComplete());
                         List<JieDianBean.ResultDataBean.LstLevelProfitBean> ProfitList = jieDianBean.getResultData().getLstLevelProfit();
                         JieDianAdapter jieDianAdapter = new JieDianAdapter(ProfitList,JiaDianActivity.this);
                         listView.setAdapter(jieDianAdapter);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_title:
                break;
        }
    }
}
