package com.shell.base;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shell.Bean.OnLineBean;
import com.shell.R;
import com.shell.utils.GetTwoLetter;


import java.util.List;


public class HeNanAdapter extends BaseAdapter {
    private Context context;
    List<OnLineBean.ResultDataBean> list;
    private static final int TYPE_HASIMAGE = 0;  //  偶数
    private static final int TYPE_NOIMG = 1;    //   奇数

    public HeNanAdapter(FragmentActivity activity, List<OnLineBean.ResultDataBean> underList) {
        this.context=activity;
        this.list=underList;
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
                    convertView =  inflater.inflate(R.layout.item_online, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                    break;

                case TYPE_NOIMG:    //奇数
                    LayoutInflater inflater1 = LayoutInflater.from(context);
                    convertView = inflater1.inflate(R.layout.item_online_jishu, null);
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


        OnLineBean.ResultDataBean dataBean = list.get(position);
        switch (type){
            case TYPE_HASIMAGE:

                viewHolder.tvLeft.setText(dataBean.getName());
                viewHolder.tvOne.setText(GetTwoLetter.getTwo(dataBean.getFbuyPrice()));
                viewHolder.tvtwo.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));
                viewHolder.tvthree.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));
                viewHolder.tvfour.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));
                break;
            case TYPE_NOIMG:
                videoHolder1.tvLeft.setText(dataBean.getName());
                videoHolder1.tvOne.setText(GetTwoLetter.getTwo(dataBean.getFbuyPrice()));
                videoHolder1.tvtwo.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));
                videoHolder1.tvthree.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));
                videoHolder1.tvfour.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));
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
        private final TextView tvLeft;


        ViewHolder(View view) {
            this.view = view;
            tvLeft = view.findViewById(R.id.tv_left);
            tvOne = view.findViewById(R.id.tv_one);
            tvtwo = view.findViewById(R.id.tv_two);
            tvthree = view.findViewById(R.id.tv_three);
            tvfour = view.findViewById(R.id.tv_four);

        }
    }


    static class VideoHolder1 {
        View view;
        private final TextView tvLeft;
        private final TextView tvOne;
        private final TextView tvtwo;
        private final TextView tvthree;
        private final TextView tvfour;


        VideoHolder1(View view) {
            this.view = view;
            tvLeft = view.findViewById(R.id.tv_left);
            tvOne = view.findViewById(R.id.tv_one);
            tvtwo = view.findViewById(R.id.tv_two);
            tvthree = view.findViewById(R.id.tv_three);
            tvfour = view.findViewById(R.id.tv_four);
        }
    }
}
