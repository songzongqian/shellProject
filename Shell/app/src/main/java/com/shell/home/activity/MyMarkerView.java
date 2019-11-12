
package com.shell.home.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointD;
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

    private Context context;
    private final TextView tvContent;
    private List<SuanAllBean.ResultDataBean.DataBean> data;
    private List<String> dataText;
    private final TextView pop_text_1;
    private final TextView pop_text_2;

    public MyMarkerView(Context context, int layoutResource, ArrayList<String> dataText, List<SuanAllBean.ResultDataBean.DataBean> data) {
        super(context, layoutResource);
        this.context = context;
        this.dataText = dataText;
        this.data = data;
        tvContent = findViewById(R.id.pop_time);
        pop_text_1 = findViewById(R.id.pop_text_1);
        pop_text_2 = findViewById(R.id.pop_text_2);
        //   pop_text_3 = findViewById(R.id.pop_text_3);

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

            // 获取Entry
        /*    float x1 = e.getX();
            String s = String.valueOf(x1);

            float y1 = e.getY();
            System.out.println("------"+y1);
            int index = 0;
            List<String> indexData = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                indexData.add(data.get(i).getStatisticalTime());
            }
            for (int i = 0; i < indexData.size(); i++) {
                if (s.equals(indexData.get(i))){
                    index = i;
                }
            }*/
            int index = (int) e.getX();
            tvContent.setText(data.get(index).getStatisticalTime());
            float x = e.getX();
            float y = e.getY();
            pop_text_1.setText(context.getString(R.string.home_all_suanli_S) + ": " + data.get(index).getHashRate());
            pop_text_2.setText(context.getString(R.string.Net_trade_volume) + ": " + data.get(index).getTradingAmount());
            //   pop_text_3.setText(context.getString(R.string.So_the_pledge)+"USDT: " + data.get(index).getPledgeAmount());
        }


        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
