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

import java.util.List;

public class UnderCardAdapter extends BaseAdapter {
    private Context context;
    List<CardUnderBean.ResultDataBean> list;

    public UnderCardAdapter(List<CardUnderBean.ResultDataBean> resultList, FragmentActivity activity) {
        this.context=activity;
        this.list=resultList;
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
            convertView = inflater.inflate(R.layout.item_card_under, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            CardUnderBean.ResultDataBean resultDataBean = list.get(position);
            String typeCode = resultDataBean.getBusiCode();
            String status = resultDataBean.getStatus();
            //质押
            if(typeCode.equals("pledge")){
                viewHolder.one.setText("-"+resultDataBean.getOperateAmount()+"");
                viewHolder.one.setTextColor(Color.parseColor("#F4376D"));
                viewHolder.two.setText("质押");
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

                //充值
            }else if(typeCode.equals("charge")){
                viewHolder.one.setText("+"+resultDataBean.getOperateAmount()+"");
                viewHolder.two.setText("充值");
                viewHolder.four.setText(resultDataBean.getCreateTime());
                viewHolder.one.setTextColor(Color.parseColor("#80C35F"));
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

                //提币
            }else if(typeCode.equals("withdraw")){
                viewHolder.one.setText("+"+resultDataBean.getOperateAmount()+"");
                viewHolder.two.setText("提币");
                viewHolder.four.setText(resultDataBean.getCreateTime());
                viewHolder.one.setTextColor(Color.parseColor("#80C35F"));
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

                //清算
            }else if(typeCode.equals("settle")){
                viewHolder.one.setText("+"+resultDataBean.getOperateAmount()+"");
                viewHolder.two.setText("清算");
                viewHolder.four.setText(resultDataBean.getCreateTime());
                viewHolder.one.setTextColor(Color.parseColor("#80C35F"));
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
        private final TextView two;
        private final TextView three;
        private final TextView four;
        private final LinearLayout GenView;

        ViewHolder(View view) {
            one = (TextView) view.findViewById(R.id.tv_one);
            two = (TextView) view.findViewById(R.id.tv_two);
            three = (TextView) view.findViewById(R.id.tv_three);
            four = (TextView) view.findViewById(R.id.tv_four);
            GenView = view.findViewById(R.id.rootView);
        }
    }
}
