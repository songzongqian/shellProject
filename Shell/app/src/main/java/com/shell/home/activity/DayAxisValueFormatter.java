package com.shell.home.activity;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.shell.home.Bean.SuanAllBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by philipp on 02/06/16.
 */
public class DayAxisValueFormatter extends ValueFormatter {

    private final BarLineChartBase<?> chart;
    private  List<SuanAllBean.ResultDataBean.DataBean> data;

    public DayAxisValueFormatter(BarLineChartBase<?> chart, List<SuanAllBean.ResultDataBean.DataBean> data) {
        this.chart = chart;
        this.data = data;
    }
    @Override
    public String getFormattedValue(float value) {
        int days = (int) value;
        System.out.println("-------" + days);
        int index = 0;
        List<String> indexData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            indexData.add(data.get(i).getHashRate());
        }
        for (int i = 0; i < indexData.size(); i++) {
            if (String.valueOf(value).equals(indexData.get(i))){
                index = i;
            }
        }
        return data.get(days).getStatisticalTime().substring(5,11);
    }


}
