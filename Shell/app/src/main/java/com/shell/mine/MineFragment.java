package com.shell.mine;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.shell.Bean.JieDianBean;
import com.shell.Bean.MessageBean;
import com.shell.Bean.MessageEvent;
import com.shell.Bean.MyInfoBean;
import com.shell.MyApplication;
import com.shell.R;
import com.shell.activity.JiaDianActivity;
import com.shell.activity.LoginActivity;
import com.shell.activity.MainActivity;
import com.shell.base.BaseFragment;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.mine.activity.AboutUsActivity;
import com.shell.mine.activity.JiaoYiActivity;
import com.shell.mine.activity.LanguageActivity;
import com.shell.mine.activity.NewHelpFriendActivity;
import com.shell.mine.activity.OnLineHuiLvActivity;
import com.shell.mine.activity.ResetLoginActivity;
import com.shell.mine.activity.SettingActivity;
import com.shell.mine.activity.WebLetterActivity;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.tv_newnum)
    TextView tvNewnum;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.ll_VIP)
    RelativeLayout llVIP;
    private Request<JSONObject> request;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.iv_ring)
    ImageView ivRing;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.iv_one)
    ImageView ivOne;
    @BindView(R.id.ll_haoyou)
    RelativeLayout llHaoyou;
    @BindView(R.id.iv_two)
    ImageView ivTwo;
    @BindView(R.id.ll_huilv)
    RelativeLayout llHuilv;
    @BindView(R.id.iv_three)
    ImageView ivThree;
    @BindView(R.id.ll_denglu)
    RelativeLayout llDenglu;
    @BindView(R.id.iv_four)
    ImageView ivFour;
    @BindView(R.id.ll_jiaoyi)
    RelativeLayout llJiaoyi;
    @BindView(R.id.iv_five)
    ImageView ivFive;
    @BindView(R.id.ll_yuyan)
    RelativeLayout llYuyan;
    @BindView(R.id.iv_six)
    ImageView ivSix;
    @BindView(R.id.ll_version)
    RelativeLayout llVersion;
    @BindView(R.id.iv_senven)
    ImageView ivSenven;
    @BindView(R.id.ll_about)
    RelativeLayout llAbout;
    @BindView(R.id.btn_loginOut)
    Button btnLoginOut;
    Unbinder unbinder;
    private String mediaPath;
    private String token;
    private String headUrl;
    private String nickName;
    private String myHewad;
    private String myEmail;
    //fragment loading只显示一次  没时间了
    private boolean isLoading = true;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(getActivity());
        EventBus.getDefault().register(this);

    }

    @Override
    protected void setListener() {


    }

    @Override
    protected void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();
        token = PreManager.instance().getString("token");
        Log.i("song", "我的页面的token值" + token);
        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        } else {

            PackageManager packageManager = getActivity().getPackageManager();
            PackageInfo packInfo = null;
            try {
                packInfo = packageManager.getPackageInfo(getContext().getPackageName(),0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            String currentVersion = packInfo.versionName;
            String finalCurrent="V"+currentVersion;
            tvVersion.setText(finalCurrent);
            getMyData();
            getMessageCount();
            getMyLevel();
        }

    }

    private void getMessageCount() {
        request = NoHttp.createJsonObjectRequest(AppUrl.UnReadCount, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(2, request, responseListener);
    }

    private void getMyData() {
        request = NoHttp.createJsonObjectRequest(AppUrl.MyInforUrl, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);
    }


    private void getMyLevel() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getUserJieDian, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(3, request, responseListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_head, R.id.iv_ring, R.id.iv_set, R.id.ll_haoyou, R.id.ll_huilv, R.id.ll_denglu, R.id.ll_jiaoyi, R.id.ll_yuyan, R.id.ll_version, R.id.ll_about, R.id.btn_loginOut,R.id.ll_VIP})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.iv_ring:
                Intent intent0 = new Intent(getActivity(), WebLetterActivity.class);
                startActivity(intent0);
                break;
            case R.id.iv_set:
                Intent intent1 = new Intent(getActivity(), SettingActivity.class);
                intent1.putExtra("headUrl", myHewad);
                intent1.putExtra("nickName", nickName);
                intent1.putExtra("myEmail", myEmail);

                startActivity(intent1);
                break;
            case R.id.ll_haoyou:
                Intent intent2 = new Intent(getActivity(), NewHelpFriendActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_huilv:
                Intent intent3 = new Intent(getActivity(), OnLineHuiLvActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_denglu:
                Intent intent4 = new Intent(getActivity(), ResetLoginActivity.class);
                startActivity(intent4);
                break;
            case R.id.ll_jiaoyi:
                Intent intent5 = new Intent(getActivity(), JiaoYiActivity.class);
                startActivity(intent5);
                break;
            case R.id.ll_yuyan:
                Intent intent6 = new Intent(getActivity(), LanguageActivity.class);
                startActivity(intent6);
                break;
            case R.id.ll_version:

                break;
            case R.id.ll_about:
                Intent intent8 = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent8);
                break;
            case R.id.btn_loginOut:
                //清除token
                PreManager.instance().putBoolean("ISLogin", false);
                PreManager.instance().putString("token", "");
                //关闭其他Activity
                List<Activity> activityList = MyApplication.activityList;
                for (int i = 0; i < activityList.size(); i++) {
                    activityList.get(i).finish();
                }
                Intent intent9 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent9);
                break;
            case R.id.ll_VIP:
                //会员权益模块
                Intent intent10=new Intent(getActivity(), JiaDianActivity.class);
                intent10.putExtra("headUrl", myHewad);
                intent10.putExtra("nickName", nickName);
                startActivity(intent10);
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        headUrl = event.getMessage();
        Glide.with(getActivity()).load(headUrl).into(ivHead);
    }

    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (isLoading){
                if (myWaitDialog == null) {
                    myWaitDialog = new MyWaitDialog(getActivity());
                    myWaitDialog.show();
                } else {
                    myWaitDialog.show();
                }
                isLoading = false;
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "我的资料的返回值" + String.valueOf(response));
                    MyInfoBean myInfoBean = gson.fromJson(response.get().toString(), MyInfoBean.class);
                    String resultCode = myInfoBean.getResultCode();
                    if (resultCode.equals("999999")) {
                        nickName = myInfoBean.getResultData().getName();
                        myEmail = myInfoBean.getResultData().getEmail();
                        PreManager.instance().putString("myEmail", myEmail);
                        //获取用户的头像
                        myHewad = myInfoBean.getResultData().getPortrait();

                        RequestOptions options=new RequestOptions();
                        options.placeholder(R.mipmap.person); //添加占位图
                        options.error(R.mipmap.person);


                        // person
                        Glide.with(getActivity()).load(myHewad).apply(options).into(ivHead);
                        tvNickName.setText(nickName);
                        tvEmail.setText(myEmail);
                    } else {

                    }
                    break;

                case 2:
                    Log.i("song", "消息数量返回值" + String.valueOf(response));
                    MessageBean commonBean = gson.fromJson(response.get().toString(), MessageBean.class);
                    String secondCode = commonBean.getResultCode();
                    if (secondCode.equals("999999")) {
                        int resultData = commonBean.getResultData();
                        if (resultData > 0) {
                            tvNewnum.setVisibility(View.VISIBLE);
                            tvNewnum.setText(commonBean.getResultData() + "");
                        } else {
                            tvNewnum.setVisibility(View.GONE);
                        }

                    } else {

                    }
                    break;

                case 3:
                    JieDianBean jieDianBean = gson.fromJson(response.get().toString(), JieDianBean.class);
                    if(jieDianBean.getResultCode().equals("999999")) {
                        JieDianBean.ResultDataBean dataBean = jieDianBean.getResultData();
                        tvVip.setText("Blv"+dataBean.getLevel());
                    }
                    break;


            }
        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
           // myWaitDialog.cancel();
        }

        @Override
        public void onFinish(int what) {
           // myWaitDialog.cancel();
        }
    };


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //可见
            Log.i("song", "MineFragment可见");
            getMyData();
            getMessageCount();
        } else {
            //不可见
            Log.i("song", "MineFragment不可见");
        }
    }
}
