package com.shell.money.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.shell.R;
import com.shell.home.Bean.TestBean;
import com.shell.money.Bean.ZhiYaScoreBean;
import com.shell.money.activity.ZhiYaActivity;

import java.util.List;


public class ZhiYaScoreAdapter extends RecyclerView.Adapter {
    Context context;
    List<ZhiYaScoreBean.ResultDataBean> list;


    public ZhiYaScoreAdapter(ZhiYaActivity activity, List<ZhiYaScoreBean.ResultDataBean> list) {
        this.context= activity;
        this.list=list;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_poposcore, null,false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder= (MyViewHolder) viewHolder;
        ZhiYaScoreBean.ResultDataBean dataBean = list.get(position);
        holder.tvXinYong.setText(dataBean.getCreditScore());
        holder.tvCount.setText(dataBean.getAllowedPledge());
        holder.tvThree.setText(dataBean.getOrderType());
        holder.ratingBar.setStar(dataBean.getProfit());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{


        private final TextView tvXinYong;
        private final TextView tvCount;
        private final TextView tvThree;
        private final RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvXinYong = itemView.findViewById(R.id.tv_one);
            tvCount = itemView.findViewById(R.id.tv_two);
            tvThree = itemView.findViewById(R.id.tv_three);
            ratingBar = itemView.findViewById(R.id.ratingbar);
        }
    }
}
