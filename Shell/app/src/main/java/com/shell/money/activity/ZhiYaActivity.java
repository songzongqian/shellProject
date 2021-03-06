package com.shell.money.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galenleo.widgets.CodeInputView;
import com.google.gson.Gson;
import com.shell.Bean.ZhiYaItemBean;
import com.shell.R;
import com.shell.activity.ForgetActivity;
import com.shell.base.BaseActivity;
import com.shell.commom.LogonFailureUtil;
import com.shell.constant.AppUrl;
import com.shell.dialog.MilestoneDialogFragment;
import com.shell.dialog.MyWaitDialog;
import com.shell.mine.activity.JiaoYiActivity;
import com.shell.money.Bean.ZhiYaBean;
import com.shell.money.Bean.ZhiYaResultBean;
import com.shell.money.Bean.ZhiYaScoreBean;
import com.shell.money.adapter.ZhiYaAdapter;
import com.shell.money.adapter.ZhiYaScoreAdapter;
import com.shell.utils.GetTwoLetter;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhiYaActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_parent)
    RelativeLayout llParent;
    private Request<JSONObject> request;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.btn_other)
    Button btnOther;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.btn_zhiya)
    Button btnZhiya;
    @BindView(R.id.text_one)
    TextView textOne;
    @BindView(R.id.text_twe)
    TextView textTwe;
    @BindView(R.id.text_three)
    TextView textThree;
    @BindView(R.id.zhiya_parent_linnear)
    LinearLayout zhiya_parent_linnear;
    List<ZhiYaItemBean> titleList = new ArrayList<>();
    private ZhiYaAdapter zhiYaAdapter;
    private List<ZhiYaScoreBean.ResultDataBean> zhiyaScoreFirst;
    private PopupWindow pwdWindow;
    int flag = 0;
    private String clicktvContent;
    private ZhiYaBean zhiYaBean;
    String reuqestNumber = "";

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhiya;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.or_zhiya));
        tvRightTitle.setText(getString(R.string.Benefits_that));
        tvRightTitle.setVisibility(View.VISIBLE);
        etAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    flag=2;
                }
            }
        });

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        getPageData();
        getZhiYaScore();
    }


    @Override
    protected void onResume() {
        super.onResume();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag = 1;
                btnOther.setBackgroundResource(R.drawable.zhiya_item_no_select);
                zhiYaAdapter.setSelectedId(position);
                zhiYaAdapter.notifyDataSetInvalidated();
                ZhiYaItemBean zhiYaItemBean = titleList.get(position);
                clicktvContent = zhiYaItemBean.getTvContent();

            }
        });
    }

    private void getPageData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.ZhiYaUrl, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);

    }


    private void getZhiYaScore() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.ZhiYaScore, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(2, request, responseListener);
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(ZhiYaActivity.this);
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            LogonFailureUtil.gotoLoginActiviy(ZhiYaActivity.this, response.get().toString());
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "质押信息的页面返回值" + String.valueOf(response));
                    zhiYaBean = gson.fromJson(response.get().toString(), ZhiYaBean.class);
                    String resultCode = zhiYaBean.getResultCode();
                    if (resultCode.equals("999999")) {
                        int floor = (int) Math.floor(zhiYaBean.getResultData().getCreditScore());
                        textOne.setText(String.valueOf(floor));
                        textTwe.setText(GetTwoLetter.getTwo(zhiYaBean.getResultData().getPledged()) + " USDT");
                        textThree.setText(GetTwoLetter.getTwo(zhiYaBean.getResultData().getMaxAllowed()) + " USDT");
                        if (titleList != null) {
                            titleList.clear();
                        }
                        String pledgeAmount = zhiYaBean.getResultData().getPledgeAmount();
                        String[] result = pledgeAmount.split(",");
                        List<String> list = Arrays.asList(result);

                        for (int k = 0; k < list.size(); k++) {
                            ZhiYaItemBean zhiYaItemBean = new ZhiYaItemBean();
                            zhiYaItemBean.setTvContent(list.get(k));
                            titleList.add(zhiYaItemBean);
                        }

                        zhiYaAdapter = new ZhiYaAdapter(titleList, ZhiYaActivity.this);
                        gridView.setAdapter(zhiYaAdapter);
                        tvContent.setText(zhiYaBean.getResultData().getDescText());
                    } else {

                    }

                    break;

                case 2:
                    Log.i("song", "质押权益的返回值" + String.valueOf(response));
                    ZhiYaScoreBean zhiYaScoreBean = gson.fromJson(response.get().toString(), ZhiYaScoreBean.class);
                    if (zhiYaScoreBean.getResultCode().equalsIgnoreCase("999999")) {
                        zhiyaScoreFirst = zhiYaScoreBean.getResultData();
                    }
                    break;

                case 3:
                    Log.i("song", "质押的返回值" + String.valueOf(response));
                    ZhiYaResultBean zhiYaResultBean = gson.fromJson(response.get().toString(), ZhiYaResultBean.class);
                    String dataBean = zhiYaResultBean.getResultCode();
                    String resultDesc = zhiYaResultBean.getResultDesc();
                    if (dataBean.equals("999999")) {
                        pwdWindow.dismiss();
                        Toast.makeText(ZhiYaActivity.this, resultDesc, Toast.LENGTH_SHORT).show();
                        etAmount.requestFocus();
                        etAmount.setFocusable(true);
                        etAmount.setFocusableInTouchMode(true);
                        finish();
                    } else {
                        pwdWindow.dismiss();
                        Toast.makeText(ZhiYaActivity.this, resultDesc, Toast.LENGTH_SHORT).show();
                        etAmount.requestFocus();
                        etAmount.setFocusable(true);
                        etAmount.setFocusableInTouchMode(true);
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

    @OnClick({R.id.rl_back, R.id.btn_other, R.id.btn_zhiya, R.id.tv_rightTitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_other:
                //点击其他
                flag = 2;
                zhiYaAdapter.setSelectedId(-1);
                zhiYaAdapter.notifyDataSetInvalidated();
                btnOther.setBackgroundResource(R.drawable.zhiya_item_select);
                etAmount.setText(GetTwoLetter.getTwo(String.valueOf(zhiYaBean.getResultData().getAllowed())));
                break;
            case R.id.btn_zhiya:
                Boolean aBoolean = PreManager.instance().getBoolean(AppUrl.isSetPayPwd);
                if (aBoolean) {
                    etAmount.setFocusable(false);
                    showPopuwindow();
                /*   MilestoneDialogFragment fragment = new MilestoneDialogFragment();
                    fragment.show(getSupportFragmentManager(),"");*/
                } else {
                    gotosetPayPwd();
                }

                break;
            case R.id.tv_rightTitle:
                showPopuScore();
                break;
        }
    }

    private void gotosetPayPwd() {
        View inflate = LayoutInflater.from(ZhiYaActivity.this).inflate(R.layout.popuwindow_set_pay_pwd, null, false);
        final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        TextView tvContent = inflate.findViewById(R.id.tv_content);
        TextView tvOk = inflate.findViewById(R.id.tv_OK);

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhiYaActivity.this, JiaoYiActivity.class);
                startActivity(intent);
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
        window.showAtLocation(LayoutInflater.from(ZhiYaActivity.this).inflate(R.layout.fragment_home, null), Gravity.CENTER, 0, 0);
    }

    //显示质押权益列表
    private void showPopuScore() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_zhiyascore, null, false);
        final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ImageView ivClose = inflate.findViewById(R.id.iv_close);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
        TextView btnOK = inflate.findViewById(R.id.tv_OK);

        recyclerView.setLayoutManager(new LinearLayoutManager(ZhiYaActivity.this));
        ZhiYaScoreAdapter zhiYaScoreAdapter = new ZhiYaScoreAdapter(ZhiYaActivity.this, zhiyaScoreFirst);
        recyclerView.setAdapter(zhiYaScoreAdapter);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });


        ivClose.setOnClickListener(new View.OnClickListener() {
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
        window.setFocusable(true);
        window.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_zhiya, null), Gravity.CENTER, 0, 0);

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

                String inputPwd = editText.getText().toString().trim();
                //获取输入的密码
                String token = PreManager.instance().getString("token");
                if (0 == flag) {
                    return;
                }
                if (flag == 1) {
                    reuqestNumber = clicktvContent;
                } else if (flag == 2) {
                    reuqestNumber = etAmount.getText().toString().trim();

                }
                if (TextUtils.isEmpty(reuqestNumber)) {
                    return;
                }
                //  Toast.makeText(ZhiYaActivity.this,reuqestNumber,Toast.LENGTH_SHORT).show();
                request = NoHttp.createJsonObjectRequest(AppUrl.ZhiYaDataLv, RequestMethod.POST);
                request.addHeader("token", token);
                request.add("amount", reuqestNumber);
                request.add("payPassword", inputPwd);
                mQueue.add(3, request, responseListener);
            }
        });


        backgroundAlpha(0.5f);

        pwdWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
                etAmount.requestFocus();
                etAmount.setFocusable(true);
                etAmount.setFocusableInTouchMode(true);
            }
        });
        pwdWindow.setOutsideTouchable(true);
       /* pwdWindow.setTouchable(true);
        pwdWindow.setFocusable(true);*/
        pwdWindow.setBackgroundDrawable(new BitmapDrawable());
        pwdWindow.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_zhiya, null), Gravity.CENTER, 0, 0);
    }


    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


}
