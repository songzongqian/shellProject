package com.shell.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shell.Bean.LetterBean;
import com.shell.R;
import com.shell.constant.AppUrl;
import com.shell.mine.activity.MyFriendActivity;
import com.shell.mine.activity.MyFriendBean;
import com.shell.mine.activity.WebLetterActivity;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class LetterAdapter extends RecyclerView.Adapter {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    private Request<JSONObject> request;
    private int page = 1;
    Context context;
    List<LetterBean.ResultDataBean> list;


    public LetterAdapter(WebLetterActivity webLetterActivity, List<LetterBean.ResultDataBean> resultData) {
         this.context=webLetterActivity;
         this.list=resultData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_webletter, viewGroup,false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final MyViewHolder holder= (MyViewHolder) viewHolder;
        LetterBean.ResultDataBean resultDataBean = list.get(position);
        String isRead= resultDataBean.getReadFlag();
        if(isRead.equals("Y")){
            holder.rootView.setBackgroundColor(Color.parseColor("#082946"));
            holder.tvTitle.setText(resultDataBean.getTitle());
            holder.tvTime.setText(resultDataBean.getCreateTime());
            holder.tvContent.setText(resultDataBean.getContent());
            holder.llBody.setVisibility(View.VISIBLE);

        }else if(isRead.equals("N")){
            holder.rootView.setBackgroundColor(Color.parseColor("#061D34"));
            holder.tvTitle.setText(resultDataBean.getTitle());
            holder.tvTime.setText(resultDataBean.getCreateTime());
            holder.tvContent.setText(resultDataBean.getContent());
            holder.llBody.setVisibility(View.GONE);


            //判断是否已经读
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.llBody.setVisibility(View.VISIBLE);
                    long id = list.get(position).getId();
                    PostFlag(id);

                }
            });
        }


    }

    private void PostFlag(long id) {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.PostLetterID, RequestMethod.POST);
        request.addHeader("token", token);
        request.add("msgId", id);
        request.add("token", token);
        mQueue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                switch (what) {
                    case 1:
                        Log.i("song", "站内消息上传读取消息" + String.valueOf(response));
                        break;
                }

            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{



        private final TextView tvTitle;
        private final TextView tvTime;
        private final TextView tvContent;
        private final LinearLayout llBody;
        private final LinearLayout rootView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llBody = itemView.findViewById(R.id.ll_body);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime= itemView.findViewById(R.id.tv_time);
            tvContent = itemView.findViewById(R.id.tv_Content);
            rootView = itemView.findViewById(R.id.rootView);

        }
    }
}
