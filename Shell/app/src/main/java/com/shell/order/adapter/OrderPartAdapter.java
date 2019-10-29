package com.shell.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shell.R;
import com.shell.order.activity.OrderListActivity;
import com.shell.order.bean.OrderListBean;

import java.util.List;


public class OrderPartAdapter extends RecyclerView.Adapter {
    Context context;
    List<OrderListBean.ResultDataBean> list;


    public OrderPartAdapter(List<OrderListBean.ResultDataBean> resultData, FragmentActivity activity) {
        this.context=activity;
        this.list=resultData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_order_list, null,false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder= (MyViewHolder) viewHolder;
        OrderListBean.ResultDataBean dataBean = list.get(position);
        holder.tvOrderName.setText(dataBean.getOrderCurrency()+":"+dataBean.getOrderAmount());
        holder.tvOrderAddress.setText("地址:"+dataBean.getTargetAddress());
        String status = dataBean.getStatus();
        if(status.equals("10")){
            holder.orderStatue.setText("待处理");
        }else if(status.equals("20")){
            holder.orderStatue.setText("已处理");
        }
       holder.tvClockTime.setText(dataBean.getCreateTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{


        private final ImageView ivPic;
        private final TextView tvOrderName;
        private final TextView tvOrderAddress;
        private final TextView orderStatue;
        private final TextView tvClockTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.iv_money_Name);
            tvOrderName = itemView.findViewById(R.id.tv_orderCount);
            tvOrderAddress = itemView.findViewById(R.id.orderAddress);
            orderStatue = itemView.findViewById(R.id.tv_orderStatue);
            tvClockTime = itemView.findViewById(R.id.tv_clockTime);
        }
    }
}
