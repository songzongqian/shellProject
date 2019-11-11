package com.shell.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.shell.R;
import com.shell.mine.activity.MyFriendActivity;
import com.shell.mine.activity.MyFriendBean;
import com.shell.order.activity.OrderListActivity;
import com.shell.order.activity.UnFinishDetailActivity;
import com.shell.order.bean.OrderListBean;
import com.shell.utils.GetTwoLetter;
import com.shell.utils.OrderTimeCount;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class OrderListAdapter extends RecyclerView.Adapter {
    Context context;
    List<OrderListBean.ResultDataBean> list;

    public OrderListAdapter(List<OrderListBean.ResultDataBean> resultData, OrderListActivity orderListActivity) {
        this.context = orderListActivity;
        this.list = resultData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_order_list, null, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        OrderListBean.ResultDataBean dataBean = list.get(position);
        holder.tvOrderName.setText(dataBean.getOrderCurrency() + ":" + dataBean.getOrderAmount());
        holder.tvOrderAddress.setText(context.getString(R.string.address) + dataBean.getTargetAddress());
        String status = dataBean.getStatus();
        if (status.equals("10")) {
            holder.orderStatue.setText(context.getString(R.string.untreated));
        } else if (status.equals("20")) {
                holder.orderStatue.setText(context.getString(R.string.Have_to_dealwith));
        } else if ("30".equals(status)) {
            holder.orderStatue.setText(context.getString(R.string.has_been_settled));
        } else if ("40".equals(status)) {
            holder.orderStatue.setText(context.getString(R.string.has_been_cancelled));
        }


        if (0 == dataBean.getRemainingSeconds()) {
            holder.tvClockTime.setText("00:00");
        } else {
            showOrderTimeout(dataBean.getRemainingSeconds(), holder.tvClockTime);
        }
        if ("out".equals(dataBean.getType())) {
            holder.ivPic.setImageResource(R.mipmap.money_name_red);
        } else {
            holder.ivPic.setImageResource(R.mipmap.money_name);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UnFinishDetailActivity.class);
                intent.putExtra("orderId", list.get(position).getId());
                intent.putExtra("orderStatue", list.get(position).getStatus());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        private final RoundedImageView ivPic;
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

    /**
     * 显示超时时间
     * time_millions 剩余时间
     *
     * @param remainingSeconds
     */
    private void showOrderTimeout(int remainingSeconds, TextView tvClockTime) {
        // long time_millions = (orderTime * 1000L + timeOut * 1000L) - System.currentTimeMillis();
        OrderTimeCount timeCount = new OrderTimeCount(remainingSeconds, 1000L);
        timeCount.setWidget(tvClockTime);
        timeCount.start();
    }

}
