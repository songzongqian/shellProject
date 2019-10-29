package com.shell.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.client.android.utils.ZXingUtils;
import com.shell.Bean.HelpBean;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.utils.ImageToGallery;
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


public class NewHelpFriendActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.llRootView)
    LinearLayout llRootView;
    private Request<JSONObject> request;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.tv_jinE)
    TextView tvJinE;
    @BindView(R.id.tv_www)
    TextView tvWww;
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
    private String inviteUrl;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newhelpfriend;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(R.string.my_jiedian);
        tvRightTitle.setText(getString(R.string.my_myfriend));
        tvRightTitle.setVisibility(View.VISIBLE);


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.MyHelpUrl, RequestMethod.GET);
        request.addHeader("token", token);
        mQueue.add(1, request, responseListener);

    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(NewHelpFriendActivity.this);
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
                    Log.i("song", "邀请好友的返回值" + String.valueOf(response));
                    HelpBean myInfoBean = gson.fromJson(response.get().toString(), HelpBean.class);
                    String resultCode = myInfoBean.getResultCode();
                    HelpBean.ResultDataBean resultData = myInfoBean.getResultData();
                    if (resultCode.equals("999999")) {
                        inviteUrl = resultData.getInviteUrl();
                        Bitmap qrImage = ZXingUtils.createQRImage(inviteUrl);
                        ivQrCode.setImageBitmap(qrImage);
                        String myinviteCode = resultData.getMyinviteCode();
                        tvHelpCode.setText(R.string.my_jiedian + " " + myinviteCode);
                        tvWww.setText(myinviteCode);
                        tv1.setText(resultData.getDescText());
                    } else {

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


    @OnClick({R.id.rl_back, R.id.tv_rightTitle, R.id.iv_qrCode, R.id.btn_save, R.id.tv_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_rightTitle:
                Intent intent1 = new Intent(NewHelpFriendActivity.this, MyFriendActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_qrCode:
                break;
            case R.id.btn_save:
                Bitmap bitmap = loadBitmapFromView(llRootView);
                ImageToGallery.saveImageToGallery(NewHelpFriendActivity.this,bitmap);
                break;
            case R.id.tv_copy:
                ClipboardManager cmb1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cmb1.setText(inviteUrl);
                Toast.makeText(NewHelpFriendActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public Bitmap loadBitmapFromView(View view) {
        if (view == null) {
            return null;
        }

        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;  //以要素为单位
        int height = metrics.heightPixels;
        view.setDrawingCacheEnabled(true);
        //调用下面这个方法非常重要，如果没有调用这个方法，得到的bitmap为null
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        //这个方法也非常重要，设置布局的尺寸和位置
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        //获得绘图缓存中的Bitmap
        view.buildDrawingCache();
        return view.getDrawingCache();

    }
}
