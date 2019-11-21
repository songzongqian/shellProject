package com.shell.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.shell.R;
import com.shell.mine.activity.MyFriendActivity;
import com.shell.mine.activity.MyFriendBean;

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
        holder.friendCount.setText(resultDataBean.getInvitedCount() + "");
        holder.tvXinYongCount.setText(resultDataBean.getCreditScore() + "");
        holder.suanliCount.setText(resultDataBean.getHashRate() + "");
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHead = itemView.findViewById(R.id.iv_head);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvXinYongCount = itemView.findViewById(R.id.tv_two);
            friendCount = itemView.findViewById(R.id.tv_four);
            suanliCount = itemView.findViewById(R.id.tv_six);
        }
    }
}
