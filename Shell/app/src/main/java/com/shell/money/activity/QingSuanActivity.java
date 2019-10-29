package com.shell.money.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.galenleo.widgets.CodeInputView;
import com.google.gson.Gson;
import com.shell.Bean.QingSuanDescBean;
import com.shell.R;
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

public class QingSuanActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.tvContent)
    TextView tvContent;
    private Request<JSONObject> request;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.btn_qingsuan)
    Button btnQingsuan;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    private PopupWindow pwdWindow;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qingsuan;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("清算");
        tvRightTitle.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        getDesc();
    }

    private void getDesc() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getQingSuanTitle, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(QingSuanActivity.this);
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
                    Log.i("song", "清算文案的返回值" + String.valueOf(response));
                    QingSuanDescBean qingSuanDescBean = gson.fromJson(response.get().toString(), QingSuanDescBean.class);
                    String descBeanResultCode = qingSuanDescBean.getResultCode();
                    if (descBeanResultCode.equals("999999")) {
                     tvContent.setText(qingSuanDescBean.getResultData());
                    }else{

                    }
                    break;
                case 2:
                    Log.i("song", "开始清算的返回值" + String.valueOf(response));
                    // ZhiYaBean zhiYaBean = gson.fromJson(response.get().toString(), ZhiYaBean.class);
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

    @OnClick({R.id.rl_back, R.id.btn_qingsuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_qingsuan:
                showPopuwindow();
                break;
        }
    }


    private void showSuccess() {
        View inflate = LayoutInflater.from(QingSuanActivity.this).inflate(R.layout.popuwindow_qingsuan, null, false);
        final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        TextView tvContent = inflate.findViewById(R.id.tv_content);
        ImageView ivClose = inflate.findViewById(R.id.iv_close);
        TextView tvOk = inflate.findViewById(R.id.tv_OK);

        //TODO  内容区域需要申请接口获取


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(LayoutInflater.from(QingSuanActivity.this).inflate(R.layout.fragment_home, null), Gravity.CENTER, 0, 0);

    }


    private void showPopuwindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_input, null, false);
        pwdWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);


        Button btnNo = inflate.findViewById(R.id.btn_canle);
        Button btnOK = inflate.findViewById(R.id.btn_ok);

        final CodeInputView editText = inflate.findViewById(R.id.editText);


        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwdWindow.dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = PreManager.instance().getString("token");
                String inputPwd = editText.getText().toString().trim();
                request = NoHttp.createJsonObjectRequest(AppUrl.QingSuanURL, RequestMethod.POST);
                request.addHeader("token", token);
                request.add("token", token);
                request.add("payPassword", inputPwd);
                mQueue.add(2, request, responseListener);
            }
        });

        backgroundAlpha(0.5f);
        pwdWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        pwdWindow.setBackgroundDrawable(new BitmapDrawable());
        pwdWindow.setOutsideTouchable(true);
        pwdWindow.setTouchable(true);
        pwdWindow.setFocusable(true);
        pwdWindow.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_zhiya, null), Gravity.CENTER, 0, 0);
    }


    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = QingSuanActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        this.getWindow().setAttributes(lp);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
