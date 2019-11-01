package com.shell.mine.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    @BindView(R.id.save_image)
    LinearLayout save_image;
    private String inviteUrl;
    @BindView(R.id.send_name)
    TextView send_name;
    private String myEmail;

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
        myEmail = PreManager.instance().getString("myEmail");
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
                        tvHelpCode.setText(getString(R.string.fr_tuijian) + " " + myinviteCode);
                        tvJinE.setText(getString(R.string.mf_five) + myinviteCode);
                        tv1.setText(resultData.getDescText());
                        send_name.setText(getString(R.string.your_friend) + myEmail + getString(R.string.invited_to_join));
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

    @SuppressLint("HandlerLeak")
    private Handler doActionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = loadBitmapFromView(save_image);
            ImageToGallery.saveImageToGallery(NewHelpFriendActivity.this, bitmap, send_name);
        }
    };

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
                send_name.setVisibility(View.VISIBLE);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(10);//休眠3秒
                            doActionHandler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        /**
                         * 要执行的操作
                         */
                    }
                }.start();

                break;
            case R.id.tv_copy:
                ClipboardManager cmb1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cmb1.setText(inviteUrl);
                Toast.makeText(NewHelpFriendActivity.this, getString(R.string.tv_hascopy), Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public Bitmap loadBitmapFromView(View view) {
        Bitmap drawingCache = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(drawingCache);
        c.drawColor(Color.WHITE);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(c);
        return drawingCache;

    }
}
