package com.shell.money.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shell.R;
import com.shell.money.Bean.ChongZhiRecordBean;

import java.util.List;


public class ChongZhiRecordAdapter extends BaseAdapter {
    private Context context;
    List<ChongZhiRecordBean.ResultDataBean> list;
    private static final int TYPE_HASIMAGE = 0;  //  偶数
    private static final int TYPE_NOIMG = 1;    //   奇数


    public ChongZhiRecordAdapter(Activity chongZhiActivity, List<ChongZhiRecordBean.ResultDataBean> chongZhiList) {
        this.context = chongZhiActivity;
        this.list = chongZhiList;
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
        if (position % 2 == 0) {
            return TYPE_HASIMAGE;
        } else {
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
        VideoHolder1 videoHolder1 = null;
        int type = getItemViewType(position);

        if (convertView == null) {
            switch (type) {
                case TYPE_HASIMAGE: //偶数
                    LayoutInflater inflater = LayoutInflater.from(context);
                    convertView = inflater.inflate(R.layout.item_tibi, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                    break;

                case TYPE_NOIMG:    //奇数
                    LayoutInflater inflater1 = LayoutInflater.from(context);
                    convertView = inflater1.inflate(R.layout.item_tibi_ji, null);
                    videoHolder1 = new VideoHolder1(convertView);
                    convertView.setTag(videoHolder1);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_HASIMAGE:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;

                case TYPE_NOIMG:
                    videoHolder1 = (VideoHolder1) convertView.getTag();
                    break;

            }
        }


        final ChongZhiRecordBean.ResultDataBean dataBean = list.get(position);
        String status = dataBean.getStatus();

        switch (type) {
            case TYPE_HASIMAGE:
                viewHolder.one.setText(dataBean.getOperateAmount() + "");
                viewHolder.four.setText(dataBean.getCreateTime());
                viewHolder.fiver.setVisibility(View.GONE);
                if (status.equals("10")) {
                    viewHolder.three.setText("待处理");
                } else if (status.equals("20")) {
                    viewHolder.three.setText("处理中");
                } else if (status.equals("21")) {
                    viewHolder.three.setText("区块确认中");
                } else if (status.equals("30")) {
                    viewHolder.three.setText("成功");
                } else if (status.equals("40")) {
                    viewHolder.three.setText("失败");
                }
                break;
            case TYPE_NOIMG:
                videoHolder1.one.setText(dataBean.getOperateAmount() + "");
                videoHolder1.four.setText(dataBean.getCreateTime());
                videoHolder1.fiver.setVisibility(View.GONE);
                if (status.equals("10")) {
                    videoHolder1.three.setText("待处理");
                } else if (status.equals("20")) {
                    videoHolder1.three.setText("处理中");
                } else if (status.equals("21")) {
                    videoHolder1.three.setText("区块确认中");
                } else if (status.equals("30")) {
                    videoHolder1.three.setText("成功");
                } else if (status.equals("40")) {
                    videoHolder1.three.setText("失败");
                }
                break;
        }
        return convertView;
    }

    static class ViewHolder {
        View view;
        private final TextView one;
        private final TextView three;
        private final TextView four;
        private final TextView fiver;


        ViewHolder(View view) {
            this.view = view;
            one = (TextView) view.findViewById(R.id.tv_one);
            three = (TextView) view.findViewById(R.id.tv_three);
            four = (TextView) view.findViewById(R.id.tv_four);
            fiver = (TextView) view.findViewById(R.id.tv_fiver);

        }
    }


    static class VideoHolder1 {
        View view;
        private final TextView one;
        private final TextView three;
        private final TextView four;
        private final TextView fiver;

        VideoHolder1(View view) {
            this.view = view;
            one = (TextView) view.findViewById(R.id.tv_one);
            three = (TextView) view.findViewById(R.id.tv_three);
            four = (TextView) view.findViewById(R.id.tv_four);
            fiver = (TextView) view.findViewById(R.id.tv_fiver);
        }
    }
}
