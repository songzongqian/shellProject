package com.shell.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.shell.Bean.HistoryShouYiBean;
import com.shell.R;
import com.shell.activity.HistoryAdapter;
import com.shell.activity.MyShouYiActivity;
import com.shell.base.BaseActivity;
import com.shell.base.TodayShouBean;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.home.Bean.SuanAllBean;
import com.shell.utils.GetTwoLetter;
import com.shell.utils.PreManager;
import com.veken.chartview.view.LineChartView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SuanChartActivity extends BaseActivity {


    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.chart)
    LineChart chart;
    private SuanAllBean suanAllBean;
    private List<SuanAllBean.ResultDataBean.DataBean> databean;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.qw_suanlitu));
        tvRightTitle.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.getAllInternet, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);
    }

    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(SuanChartActivity.this);
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
                    Log.i("song", "全网算力XY的返回值" + String.valueOf(response));
                    suanAllBean = gson.fromJson(response.get().toString(), SuanAllBean.class);
                    String resultCode = suanAllBean.getResultCode();
                    if (resultCode.equals("999999")) {
                        databean = suanAllBean.getResultData().getData();
          /*              tvContent.setText(suanAllBean.getResultData().getDescText());
                        List<SuanAllBean.ResultDataBean.DataBean> data = suanAllBean.getResultData().getData();
                        for (int i = 0; i < data.size(); i++) {
                            axisXs1.add(new AxisValue(i).setLabel(data.get(i).getStatisticalTime().substring(5, 11)));
                            String hashRate = data.get(i).getHashRate();
                            String substring = hashRate.substring(0, hashRate.length() - 1);
                            float v = Float.parseFloat(substring) * 100;
                            values.add(new PointValue(i, v));
                            axisXs2.add(new AxisValue(i).setLabel(substring));
                        }*/
                      //  YourData[] dataObjects = ...;
                        List<Entry> entries = new ArrayList<Entry>();
                        for (int i = 0; i < databean.size(); i++) {
                            String substring = databean.get(i).getStatisticalTime().substring(5, 7);
                            String hashRate = databean.get(i).getHashRate();

                            entries.add(new Entry(Float.parseFloat(substring), Float.parseFloat(hashRate)));
                        }

                        LineDataSet dataSet = new LineDataSet(entries, "Lable");
                       // dataSet.setColor();
                        LineData lineData = new LineData(dataSet);
                        chart.setData(lineData);
                        chart.invalidate();
                        break;
                    }

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
