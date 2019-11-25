package com.shell.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.shell.R;
import com.shell.mine.activity.MyFriendActivity;
import com.shell.mine.activity.MyFriendBean;
import com.shell.utils.GetTwoLetter;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class FriendAdapter extends RecyclerView.Adapter {
    Context context;
    List<MyFriendBean.ResultDataBean> list;

    public FriendAdapter(List<MyFriendBean.ResultDataBean> resultData, MyFriendActivity myFriendActivity) {
        this.context = myFriendActivity;
        this.list = resultData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_friend, null, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        //todo  服务器未返回头像
        MyFriendBean.ResultDataBean resultDataBean = list.get(position);

        if (!TextUtils.isEmpty(resultDataBean.getPortrait())) {
            Glide.with(context).load(resultDataBean.getPortrait()).into(holder.ivHead);
        } else {
            Glide.with(context).load(R.mipmap.person).into(holder.ivHead);
        }

        holder.tvEmail.setText(resultDataBean.getEmail());
        holder.friendCount.setText(resultDataBean.getInvitedCount());
        holder.tvXinYongCount.setText(resultDataBean.getCreditScore());
        holder.suanliCount.setText(resultDataBean.getHashRate());
        if ("Y".equals(resultDataBean.getShowLevel())){
            holder.ll_VIP.setVisibility(View.VISIBLE);
        }else {
            holder.ll_VIP.setVisibility(View.INVISIBLE);
        }
        if (1<= resultDataBean.getLevel() && resultDataBean.getLevel() <= 4){
            holder.tv_vip.setText("B lv"+resultDataBean.getLevel());
        }else if (11<= resultDataBean.getLevel() && resultDataBean.getLevel() <= 15){
            holder.tv_vip.setText("S lv"+resultDataBean.getLevel()%10);
        }else {
            holder.tv_vip.setText("lv"+resultDataBean.getLevel());
        }
        holder.tv_zhiya_number.setText(GetTwoLetter.getTwo(resultDataBean.getPledgeAmount()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        private final RoundedImageView ivHead;
        private final TextView tvEmail;
        private final TextView tvXinYongCount;
        private final TextView friendCount;
        private final TextView suanliCount;
        TextView tv_zhiya_number;
        RelativeLayout ll_VIP;
        TextView tv_vip;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHead = itemView.findViewById(R.id.iv_head);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvXinYongCount = itemView.findViewById(R.id.tv_two);
            friendCount = itemView.findViewById(R.id.tv_four);
            suanliCount = itemView.findViewById(R.id.tv_six);
            tv_zhiya_number = itemView.findViewById(R.id.tv_zhiya_number);
            ll_VIP = itemView.findViewById(R.id.ll_VIP);
            tv_vip = itemView.findViewById(R.id.tv_vip);

        }
    }
}
