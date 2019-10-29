package com.shell.money.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shell.R;
import com.shell.money.Bean.CardUnderBean;
import com.shell.money.Bean.TiBiBean;
import com.shell.money.activity.TiBiActivity;

import java.util.List;

public class TiBiAdapter extends BaseAdapter {
    private Context context;
    List<TiBiBean.ResultDataBean> list;

    public TiBiAdapter(List<TiBiBean.ResultDataBean> firstList, TiBiActivity tiBiActivity) {
        this.context=tiBiActivity;
        this.list=firstList;

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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_tibi, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            TiBiBean.ResultDataBean resultDataBean = list.get(position);
            String status = resultDataBean.getStatus();
            viewHolder.one.setText(resultDataBean.getOperateAmount()+"");
            viewHolder.four.setText(resultDataBean.getCreateTime());
            if(status.equals("10")){
                viewHolder.three.setText("待处理");
            }else if(status.equals("20")){
                viewHolder.three.setText("处理中");
            }else if(status.equals("21")){
                viewHolder.three.setText("区块确认中");
            }else if(status.equals("30")){
                viewHolder.three.setText("成功");
            }else if(status.equals("40")){
                viewHolder.three.setText("失败");
            }

            if(position % 2 == 0){
                //偶数行
                convertView.setBackgroundColor(Color.parseColor("#08233F"));
            }else{
                viewHolder.GenView.setBackgroundColor(Color.parseColor("#0D3354"));
            }





        }


        return convertView;
    }


    static class ViewHolder {


        private final TextView one;
        private final TextView three;
        private final TextView four;
        private final LinearLayout GenView;

        ViewHolder(View view) {
            one = (TextView) view.findViewById(R.id.tv_one);
            three = (TextView) view.findViewById(R.id.tv_three);
            four = (TextView) view.findViewById(R.id.tv_four);
            GenView = view.findViewById(R.id.rootView);
        }
    }
}
