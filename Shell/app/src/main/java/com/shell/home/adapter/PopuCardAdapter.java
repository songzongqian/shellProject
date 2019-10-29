package com.shell.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.shell.R;
import com.shell.home.Bean.TestBean;
import com.shell.home.Bean.TopStaticBean;


import java.util.List;


public class PopuCardAdapter extends RecyclerView.Adapter {
    Context context;
    List<TopStaticBean.ResultDataBean.AllMilepostBean> testList;

    public PopuCardAdapter(List<TopStaticBean.ResultDataBean.AllMilepostBean> testList, FragmentActivity activity) {
        this.context=activity;
        this.testList=testList;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_popu_center, null,false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder= (MyViewHolder) viewHolder;
        TopStaticBean.ResultDataBean.AllMilepostBean allMilepostBean = testList.get(position);
        Log.i("song","真的进入adapter了");
        holder.tvDate.setText(allMilepostBean.getTime());
        holder.tvContent.setText(allMilepostBean.getDesc());
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{


        private final TextView tvDate;
        private final TextView tvContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_time);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
