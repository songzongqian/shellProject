package com.shell.home.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.shell.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity  {

    private LineChart chart;
    private ArrayList<String> dataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        setTitle("CubicLineChartActivity");

        dataText = new ArrayList<>();
        dataText.add("10.01");
        dataText.add("10.02");
        dataText.add("10.03");
        dataText.add("10.04");
        dataText.add("10.05");
        dataText.add("10.06");
        dataText.add("10.07");
        dataText.add("10.08");
        dataText.add("10.09");
        dataText.add("10.10");
        chart = findViewById(R.id.chart1);
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
       // MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view,dataText, data);

        // Set the marker to the chart
       /// mv.setChartView(chart);
       // chart.setMarker(mv);


      //  ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart,dataText);

        XAxis x = chart.getXAxis();
        x.setTextColor(Color.rgb(36, 72, 110));
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setTextSize(15f);
        x.setLabelCount(6, false);
        x.setDrawGridLines(false);
        x.setAxisLineColor(Color.rgb(34, 62, 92));
        x.enableAxisLineDashedLine(10f, 10f, 0f);//虚线
        x.enableGridDashedLine(10f, 10f, 0f);

        //x.setValueFormatter(xAxisFormatter);
        x.setGranularity(1f);//禁止放大后x轴标签重绘


        YAxis y = chart.getAxisLeft();
        y.setTextSize(15f);
        y.setLabelCount(6, false);
        y.setTextColor(Color.rgb(36, 72, 110));
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//YAxis.YAxisLabelPosition.INSIDE_CHART
        y.setZeroLineColor(Color.rgb(36, 72, 110));
        y.setAxisLineColor(Color.rgb(36, 72, 110));
        y.setGridColor(Color.rgb(36, 72, 110));
        y.setDrawAxisLine(false);//坐标轴的线是否绘制
        y.setDrawGridLines(true);//是否绘制中间的提示线
        y.enableGridDashedLine(10f, 10f, 0f);//虚线


        chart.getAxisRight().setEnabled(false);


        chart.getLegend().setEnabled(false);

        chart.animateXY(2000, 2000);


        setData(10,10);


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
        ArrayList<Float> datass = new ArrayList<>();
        datass.add(0.2f);
        datass.add(0.4f);
        datass.add(0.5f);
        datass.add(0.7f);
        datass.add(0.3f);
        datass.add(0.6f);datass.add(0.8f);datass.add(1.2f);datass.add(0.7f);datass.add(0.2f);


        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, datass.get(i)));
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

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.line, menu);
        return true;
    }*/
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/CubicLineChartActivity.java"));
                startActivity(i);
                break;
            }
            case R.id.actionToggleValues: {
                for (IDataSet set : chart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                chart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if(chart.getData() != null) {
                    chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                    chart.invalidate();
                }
                break;
            }
            case R.id.actionToggleFilled: {

                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;

                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleCircles: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawCirclesEnabled())
                        set.setDrawCircles(false);
                    else
                        set.setDrawCircles(true);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleCubic: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.CUBIC_BEZIER);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleStepped: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.STEPPED
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.STEPPED);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleHorizontalCubic: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.HORIZONTAL_BEZIER);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionTogglePinch: {
                if (chart.isPinchZoomEnabled())
                    chart.setPinchZoom(false);
                else
                    chart.setPinchZoom(true);

                chart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
                chart.notifyDataSetChanged();
                break;
            }
            case R.id.animateX: {
                chart.animateX(2000);
                break;
            }
            case R.id.animateY: {
                chart.animateY(2000);
                break;
            }
            case R.id.animateXY: {
                chart.animateXY(2000, 2000);
                break;
            }
            case R.id.actionSave: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveToGallery();
                } else {
                    requestStoragePermission(chart);
                }
                break;
            }
        }
        return true;
    }*/

}
