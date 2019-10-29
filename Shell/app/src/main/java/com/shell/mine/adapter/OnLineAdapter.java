package com.shell.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shell.Bean.OnLineBean;
import com.shell.R;
import com.shell.mine.activity.OnLineHuiLvActivity;
import com.shell.utils.GetTwoLetter;

import java.util.List;

public class OnLineAdapter extends BaseAdapter {
    Context context;
    List<OnLineBean.ResultDataBean> list;

    public OnLineAdapter(OnLineHuiLvActivity onLineHuiLvActivity, List<OnLineBean.ResultDataBean> firstList) {
        this.context=onLineHuiLvActivity;
        this.list=firstList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return  list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_zhiya, null);
        TextView tvleft=convertView.findViewById(R.id.tv_left);
        TextView tvone=convertView.findViewById(R.id.tv_one);
        TextView tvtwo=convertView.findViewById(R.id.tv_two);
        TextView tvthree=convertView.findViewById(R.id.tv_three);
        TextView tvfour=convertView.findViewById(R.id.tv_four);
        OnLineBean.ResultDataBean dataBean = list.get(position);
        tvleft.setText(dataBean.getName());
        tvone.setText(GetTwoLetter.getTwo(dataBean.getFbuyPrice()));
        tvtwo.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));
        tvthree.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));
        tvfour.setText(GetTwoLetter.getTwo(dataBean.getFsellPrice()));

        return convertView;
    }
}
