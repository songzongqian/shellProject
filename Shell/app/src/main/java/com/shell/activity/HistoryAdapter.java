package com.shell.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shell.Bean.HistoryShouYiBean;
import com.shell.Bean.JieDianBean;
import com.shell.R;
import com.shell.utils.GetTwoLetter;

import java.util.List;


public class HistoryAdapter extends BaseAdapter {
    private Context context;
    List<HistoryShouYiBean.ResultDataBean> list;
    private static final int TYPE_HASIMAGE = 0;  //  偶数
    private static final int TYPE_NOIMG = 1;    //   奇数

    public HistoryAdapter(List<HistoryShouYiBean.ResultDataBean> historyList, MyShouYiActivity myShouYiActivity) {
        this.context=myShouYiActivity;
        this.list=historyList;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0){
            return TYPE_HASIMAGE;
        }else{
            return TYPE_NOIMG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        VideoHolder1 videoHolder1=null;
        int type = getItemViewType(position);

        if(convertView==null){
            switch (type){
                case  TYPE_HASIMAGE: //偶数
                    LayoutInflater inflater = LayoutInflater.from(context);
                    convertView =  inflater.inflate(R.layout.item_shouyiou, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                    break;

                case TYPE_NOIMG:    //奇数
                    LayoutInflater inflater1 = LayoutInflater.from(context);
                    convertView = inflater1.inflate(R.layout.item_shouyiji, null);
                    videoHolder1 = new VideoHolder1(convertView);
                    convertView.setTag(videoHolder1);
                    break;
            }
        }else{
            switch (type){
                case  TYPE_HASIMAGE:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;

                case TYPE_NOIMG:
                    videoHolder1= (VideoHolder1) convertView.getTag();
                    break;

            }
        }


        HistoryShouYiBean.ResultDataBean dataBean = list.get(position);
        switch (type){
            case TYPE_HASIMAGE:
                viewHolder.tvOne.setText(dataBean.getSettlDate());
                viewHolder.tvtwo.setText(GetTwoLetter.getTwo(dataBean.getOrderCount()));
                viewHolder.tvthree.setText(GetTwoLetter.getTwo(dataBean.getOrderAmount()));
                viewHolder.tvfour.setText(GetTwoLetter.getTwo(dataBean.getHashRateAward()));
                viewHolder.tvFive.setText(GetTwoLetter.getTwo(dataBean.getOrderProfit()));
                break;
            case TYPE_NOIMG:
                videoHolder1.tvOne.setText(dataBean.getSettlDate());
                videoHolder1.tvtwo.setText(GetTwoLetter.getTwo(dataBean.getOrderCount()));
                videoHolder1.tvthree.setText(GetTwoLetter.getTwo(dataBean.getOrderAmount()));
                videoHolder1.tvfour.setText(GetTwoLetter.getTwo(dataBean.getHashRateAward()));
                videoHolder1.tvFive.setText(GetTwoLetter.getTwo(dataBean.getOrderProfit()));
                break;
        }
        return convertView;
    }

    static class ViewHolder {
        View view;
        private final TextView tvOne;
        private final TextView tvtwo;
        private final TextView tvthree;
        private final TextView tvfour;
        private final TextView tvFive;


        ViewHolder(View view) {
            this.view = view;
            tvOne = view.findViewById(R.id.tv_one);
            tvtwo = view.findViewById(R.id.tv_two);
            tvthree = view.findViewById(R.id.tv_three);
            tvfour = view.findViewById(R.id.tv_four);
            tvFive = view.findViewById(R.id.tv_five);

        }
    }


    static class VideoHolder1 {
        View view;
        private final TextView tvOne;
        private final TextView tvtwo;
        private final TextView tvthree;
        private final TextView tvfour;
        private final TextView tvFive;


        VideoHolder1(View view) {
            this.view = view;
            tvOne = view.findViewById(R.id.tv_one);
            tvtwo = view.findViewById(R.id.tv_two);
            tvthree = view.findViewById(R.id.tv_three);
            tvfour = view.findViewById(R.id.tv_four);
            tvFive = view.findViewById(R.id.tv_five);
        }
    }
}
