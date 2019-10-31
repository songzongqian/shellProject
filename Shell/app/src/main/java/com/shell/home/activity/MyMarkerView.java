
package com.shell.home.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.shell.R;
import com.shell.home.Bean.SuanAllBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ViewConstructor")
public class MyMarkerView extends MarkerView {

    private final TextView tvContent;
    private  List<SuanAllBean.ResultDataBean.DataBean> data;
    private List<String> dataText;
    private final TextView pop_text_1;
    private final TextView pop_text_2;
    private final TextView pop_text_3;

    public MyMarkerView(Context context, int layoutResource, ArrayList<String> dataText, List<SuanAllBean.ResultDataBean.DataBean> data) {
        super(context, layoutResource);
        this.dataText = dataText;
        this.data = data;
        tvContent = findViewById(R.id.pop_time);
        pop_text_1 = findViewById(R.id.pop_text_1);
        pop_text_2 = findViewById(R.id.pop_text_2);
        pop_text_3 = findViewById(R.id.pop_text_3);

    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            tvContent.setText(Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
           // tvContent.setText(Utils.formatNumber(e.getY(), 0, true));
            float y1 = e.getY();
            int index = 0;
            List<Float> indexData = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                indexData.add(Float.parseFloat(data.get(i).getHashRate()));
            }
            for (int i = 0; i < indexData.size(); i++) {
                if (y1 == indexData.get(i)){
                    index = i;
                }
            }
            tvContent.setText(data.get(index).getStatisticalTime());
            float x = e.getX();
            float y = e.getY();
            pop_text_1.setText("全网算力: " + data.get(index).getHashRate());
            pop_text_2.setText("全网交易量: " + data.get(index).getTradingAmount());
            pop_text_3.setText("全网质押USDT: " + data.get(index).getPledgeAmount());
        }


        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
