package com.shell.mine.adapter;

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

import java.util.List;


public class RecyclerLetterAdapter extends RecyclerView.Adapter {
    Context context;
    List<TestBean> testList;

    public RecyclerLetterAdapter(List<TestBean> testList, FragmentActivity activity) {
        this.context=activity;
        this.testList=testList;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_webletter, null,false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder= (MyViewHolder) viewHolder;
        TestBean testBean = testList.get(position);
        Log.i("song","真的进入adapter了");
        holder.tvDate.setText(testBean.getDate());
        holder.tvContent.setText(testBean.getContent());
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
