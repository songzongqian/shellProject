package com.shell.money.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shell.Bean.ZhiYaItemBean;
import com.shell.R;
import com.shell.money.Bean.CardUnderBean;
import com.shell.money.activity.ZhiYaActivity;

import java.util.List;

public class ZhiYaAdapter extends BaseAdapter {
    Context context;
    List<ZhiYaItemBean> list;
    private int selectedId  = -1;
    private TextView tvName;


    public ZhiYaAdapter(List<ZhiYaItemBean> resultList, ZhiYaActivity activity) {
        this.context=activity;
        this.list= resultList;
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

    public void setSelectedId(int position) {
        selectedId  = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
      ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_zhiya, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZhiYaItemBean zhiYaItemBean = list.get(position);
        viewHolder.tvName.setText(zhiYaItemBean.getTvContent()+"USDT");

        if (selectedId  == position) {
           viewHolder.tvName.setBackgroundResource(R.drawable.zhiya_item_select);
            viewHolder.tvName.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            viewHolder.tvName.setBackgroundResource(R.drawable.zhiya_item_no_select);
            viewHolder.tvName.setTextColor(Color.parseColor("#678DA8"));
        }
        return convertView;
    }


   static class ViewHolder {
        private final TextView tvName;
        ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
