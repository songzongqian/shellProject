package com.shell.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

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
    @BindView(R.id.chart1)
    LineChart chart;
    private ArrayList<String> dataText = new ArrayList<>();
    private List<SuanAllBean.ResultDataBean.DataBean> data;

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

    private void initInitView() {

        chart.setViewPortOffsets(100, 100, 100, 100);
        chart.setBackgroundColor(Color.rgb(7, 24, 38));

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);
        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view,dataText,data);

        // Set the marker to the chart
        mv.setChartView(chart);
        chart.setMarker(mv);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart,data);

        XAxis x = chart.getXAxis();
        x.setTextColor(Color.rgb(36, 72, 110));
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setTextSize(12f);
        x.setLabelCount(6, false);
        x.setDrawGridLines(false);
        x.setAxisLineColor(Color.rgb(34, 62, 92));
        x.enableAxisLineDashedLine(10f, 10f, 0f);//虚线
        x.enableGridDashedLine(10f, 10f, 0f);

        x.setValueFormatter(xAxisFormatter);
        x.setGranularity(1f);//禁止放大后x轴标签重绘

        YAxis y = chart.getAxisLeft();
        y.setTextSize(12f);
        y.setLabelCount(6, false);
        y.setTextColor(Color.rgb(36, 72, 110));
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//YAxis.YAxisLabelPosition.INSIDE_CHART
        y.setZeroLineColor(Color.rgb(36, 72, 110));
        y.setAxisLineColor(Color.rgb(36, 72, 110));
        y.setGridColor(Color.rgb(36, 72, 110));
        y.setDrawAxisLine(false);//坐标轴的线是否绘制
        y.setDrawGridLines(true);//是否绘制中间的提示线
        y.enableGridDashedLine(10f, 10f, 0f);//虚线
        y.setLabelCount(11);


        chart.getAxisRight().setEnabled(false);


        chart.getLegend().setEnabled(false);

        chart.animateXY(2000, 2000);


        setData(dataText.size(),dataText.size());


        List<ILineDataSet> sets = chart.getData().getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setDrawFilled(true);//是否覆盖
            set.setDrawCircles(true);//是否有点
            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);//是否是曲线相连


            set.setCircleColor(Color.rgb(89, 173, 229));//点外面颜色
            set.setCircleHoleColor(Color.rgb(11, 19, 44));//点里面颜色
            set.setColor(Color.rgb(89, 173, 229));//线的颜色
            set.setFillColor(Color.rgb(51, 70, 89));//覆盖物的颜色
            set.setCubicIntensity(0.2f);//贝塞尔曲线的一个什么值
            set.setLineWidth(2.5f);//线宽
            set.setCircleRadius(4f);//点外圈的半径
            set.setCircleHoleRadius(2.5f);//点里面的半径
            set.setHighLightColor(Color.TRANSPARENT);
            //  set.setHighlightEnabled(false);
//            set.setFillAlpha(100);//覆盖物透明度
        }

        // don't forget to refresh the drawing
        chart.invalidate();
    }
    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            values.add(new Entry(i, Float.parseFloat(dataText.get(i))));
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setDrawCircles(true);
            set1.setDrawFilled(true);
            set1.setCubicIntensity(0.2f);
            set1.setCircleRadius(4f);
            set1.setLineWidth(1.8f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            chart.setData(data);
        }
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
                    SuanAllBean suanAllBean = gson.fromJson(response.get().toString(), SuanAllBean.class);
                    String resultCode = suanAllBean.getResultCode();
                    if (resultCode.equals("999999")) {
                        tvContent.setText(suanAllBean.getResultData().getDescText());
                        data = suanAllBean.getResultData().getData();
                        DecimalFormat myformat = new DecimalFormat("0.0");
                        for (int i = 0; i < data.size(); i++) {
                            dataText.add(data.get(i).getHashRate());
                            //datass.add(Float.parseFloat(data.get(i).getStatisticalTime()));
                        }
                        initInitView();
                    } else {
                        Toast.makeText(SuanChartActivity.this, suanAllBean.getResultDesc(), Toast.LENGTH_SHORT).show();
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
