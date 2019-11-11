package com.shell.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        final MyViewHolder holder= (MyViewHolder) viewHolder;
        TopStaticBean.ResultDataBean.AllMilepostBean allMilepostBean = testList.get(position);
        Log.i("song","真的进入adapter了");

        holder.tvDate.setText(allMilepostBean.getTime());
        holder.tvContent.setText(allMilepostBean.getDesc());

        holder.mearHeight.post(new Runnable() {

            @Override
            public void run() {
                holder.mearHeight.getWidth(); // 获取宽度
                int height = holder.mearHeight.getHeight();// 获取高度
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.cancemearHeight.getLayoutParams();
                layoutParams.height = height+30;
                holder.cancemearHeight.setLayoutParams(layoutParams);
            }
        });


    }

    @Override
    public int getItemCount() {
        return testList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{


     TextView tvDate;
     TextView tvContent;
     RelativeLayout cancemearHeight;
     LinearLayout mearHeight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cancemearHeight = itemView.findViewById(R.id.cancemearHeight);
            mearHeight = itemView.findViewById(R.id.mearHeight);
            tvDate = itemView.findViewById(R.id.tv_time);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
