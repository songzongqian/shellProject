package com.shell.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shell.R;
import com.shell.mine.activity.MyFriendActivity;
import com.shell.mine.activity.MyFriendBean;
import com.shell.order.activity.OrderListActivity;
import com.shell.order.activity.UnFinishDetailActivity;
import com.shell.order.bean.OrderListBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class OrderListAdapter extends RecyclerView.Adapter {
    Context context;
    List<OrderListBean.ResultDataBean> list;

    public OrderListAdapter(List<OrderListBean.ResultDataBean> resultData, OrderListActivity orderListActivity) {
       this.context=orderListActivity;
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
            holder.orderStatue.setText(context.getString(R.string.untreated));
        }else if(status.equals("20")){
            holder.orderStatue.setText(context.getString(R.string.Have_to_dealwith));
        }
        holder.tvClockTime.setText(dataBean.getCreateTime());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UnFinishDetailActivity.class);
                intent.putExtra("orderId",list.get(position).getId());
                intent.putExtra("orderStatue",list.get(position).getStatus());
                context.startActivity(intent);
            }
        });
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
