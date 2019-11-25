package com.shell.home.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shell.R;
import com.shell.home.Bean.JiangLiBean;
import com.shell.utils.GetTwoLetter;
import com.shell.utils.StringUtils;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ${WangJunGang} on 7/8/2019.
 * E-Mail:811832241@qq.com
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {


    private Activity activity;
    private List<JiangLiBean.ResultDataBean> data;


    public MessagesAdapter(Activity activity, List<JiangLiBean.ResultDataBean> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.entertaining_diversions_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (null != data) {
            String amount = data.get(i).getAmount();
            viewHolder.tvNum.setText("+"+GetTwoLetter.getFour(amount)+" USDT");
            String createTime = data.get(i).getCreateTime();
            String time = StringUtils.getTime(activity,new Date(Long.parseLong(StringUtils.date2TimeStamp(createTime) + "000")));
            viewHolder.tvTime.setText(time);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
