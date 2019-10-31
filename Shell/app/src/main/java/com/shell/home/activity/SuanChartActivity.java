package com.shell.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.lineChat)
    LineChartView lineChat;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private List<AxisValue> axisXs1 = new ArrayList<>();//橫轴值集合
    private List<PointValue> values = new ArrayList<>(); //橫、纵坐标值

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
        tvTitle.setText("全网算力图");
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

    private void initLineChart() {


        //线
        Line line = new Line(values)
                .setColor(Color.parseColor("#187AC2"))//线颜色
                .setCubic(true)//曲线是否平滑
                .setStrokeWidth(2)//线粗细
                .setPointRadius(3)//坐标点大小
                .setHasLabels(false)//是否显示坐标文本备注（纵坐标数值）
//				.setHasLabelsOnlyForSelected(true)//点击数据坐标提示数值（设置了这个line.setHasLabels(true);就无效）
                .setHasLines(true)//是否有线（默认true）
                .setHasPoints(true)//是否有点（默认true）
                .setShape(ValueShape.CIRCLE)//点形状ValueShape.CIRCLE（圆形）、ValueShape.DIAMOND（棱形）、ValueShape.SQUARE(正方形)《默认圆形》
                .setFilled(true);//是否填充曲线的面积（默认为false）

        //线集合
        List<Line> lines = new ArrayList<>();
        //线集合添加线（添加几条线，一张表中就有几条）
        lines.add(line);
        //橫轴
        Axis axisX = new Axis()
                .setHasLines(false)//是否显示轴网格线
                .setTextColor(Color.parseColor("#1666A0"))//坐标轴文字颜色
                .setTextSize(8)//坐标轴文字大小
                .setLineColor(Color.parseColor("#187AC2"))//线颜色(横轴为网格竖线，纵轴为网格横线)
                .setHasTiltedLabels(false)//坐标轴文字是否倾斜(默认为false,不倾斜)
                .setInside(false)//坐标值文字在图标内部还是在轴下面（默认为flase,在轴下面）
                .setMaxLabelChars(3)//最大间隔
                .setValues(axisXs1);//轴数值
        //纵轴
        Axis axisY = new Axis()
                .setHasLines(true)
                .setHasSeparationLine(true)
                .setTextSize(8)
                .setMaxLabelChars(3)//最大间隔
                .setInside(false)
                .setTextColor(Color.parseColor("#1666A0"))//坐标轴文字颜色
                .setLineColor(Color.parseColor("#00000000"));//线颜色(横轴为网格竖线，纵轴为网格横线)


        //线图表数据
        LineChartData data = new LineChartData();
        //设置坐标点旁边的文字背景
        data.setValueLabelBackgroundColor(Color.parseColor("#1ca0aa"));
        //设置坐标点旁文字背景此方法必须设置为false
        data.setValueLabelBackgroundAuto(false);
        //设置坐标点旁文字背景是否可见（在line.setHasLines为true的情况下，默认为true）
        data.setValueLabelBackgroundEnabled(true);
        //设置坐标点旁边的文字颜色
        data.setValueLabelsTextColor(Color.BLACK);
        //设置坐标点旁边的文字大小
        data.setValueLabelTextSize(8);
        //设置左侧轴
        data.setAxisYLeft(axisY);
        //设置底部轴
        data.setAxisXBottom(axisX);
        //线图表数据设置线集合
        data.setLines(lines);
        //视图设置图表
        lineChat.setLineChartData(data);
        //是否可以拉伸，默认为true
        lineChat.setInteractive(true);
        //是否可以放大，默认为true（设置为true的前提是setInteractive为true）
        lineChat.setZoomEnabled(true);
        //设置放大类型
        lineChat.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        //点值是否可以点击
        lineChat.setValueTouchEnabled(true);
        //设置交叉模式

        lineChat.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                Toast.makeText(SuanChartActivity.this, "Selected: " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
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
                        List<SuanAllBean.ResultDataBean.DataBean> data = suanAllBean.getResultData().getData();
                        DecimalFormat myformat = new DecimalFormat("0.0");
                        for (int i = 0; i < data.size(); i++) {
                            axisXs1.add(new AxisValue(i).setLabel(data.get(i).getStatisticalTime().substring(5, 11)));
                            float v = Float.parseFloat(myformat.format(Float.parseFloat(data.get(i).getHashRate())));
                            values.add(new PointValue(i,v*10));
                            initLineChart();
                        }
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
