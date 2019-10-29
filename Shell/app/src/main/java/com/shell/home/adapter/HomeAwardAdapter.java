package com.shell.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shell.Bean.ZhiYaItemBean;
import com.shell.R;
import com.shell.home.Bean.JiangLiBean;
import com.shell.money.activity.ZhiYaActivity;

import java.util.List;

public class HomeAwardAdapter extends BaseAdapter {
    Context context;
    List<JiangLiBean.ResultDataBean> list;


    public HomeAwardAdapter(FragmentActivity activity, List<JiangLiBean.ResultDataBean> jiangLiList) {
      this.context=activity;
      this.list=jiangLiList;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i % list.size());
    }

    @Override
    public long getItemId(int i) {
        return i % list.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
      ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_home_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        JiangLiBean.ResultDataBean dataBean = list.get(position % list.size());
        viewHolder.tvOne.setText("+"+dataBean.getAmount());
        viewHolder.tvThree.setText(dataBean.getCreateTime());
        return convertView;
    }


   static class ViewHolder {
        private final TextView tvOne;
       private final TextView tvTwo;
       private final TextView tvThree;

       ViewHolder(View view) {
            tvOne = (TextView) view.findViewById(R.id.one1);
            tvTwo = view.findViewById(R.id.one2);
            tvThree = view.findViewById(R.id.one3);
        }
    }
}
