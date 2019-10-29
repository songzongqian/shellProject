package com.shell.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shell.Bean.LetterBean;
import com.shell.Bean.OnLineBean;
import com.shell.R;
import com.shell.mine.activity.WebLetterActivity;
import com.shell.utils.GetTwoLetter;

import java.util.List;


public class MyLetterAdapter extends BaseAdapter {
    private Context context;
    List<LetterBean.ResultDataBean> list;
    private static final int TYPE_HASIMAGE = 0;  //  未读
    private static final int TYPE_NOIMG = 1;    //   已经阅读

    int initFlag=0;
    int hasReadFlag=0;

    public MyLetterAdapter(WebLetterActivity activity, List<LetterBean.ResultDataBean> resultData) {
        this.context=activity;
        this.list=resultData;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getItemViewType(int position) {
        String readFlag = list.get(position).getReadFlag();
        if(readFlag.equals("N")){
            return TYPE_HASIMAGE;
        }else {
            return TYPE_NOIMG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        VideoHolder1 videoHolder1=null;
        int type = getItemViewType(position);

        if(convertView==null){
            switch (type){
                case  TYPE_HASIMAGE: //未读状态
                    LayoutInflater inflater = LayoutInflater.from(context);
                    convertView =  inflater.inflate(R.layout.item_webletter, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                    break;

                case TYPE_NOIMG:    //已读状态
                    LayoutInflater inflater1 = LayoutInflater.from(context);
                    convertView = inflater1.inflate(R.layout.item_haswebletter, null);
                    videoHolder1 = new VideoHolder1(convertView);
                    convertView.setTag(videoHolder1);
                    break;
            }
        }else{
            switch (type){
                case  TYPE_HASIMAGE:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;

                case TYPE_NOIMG:
                    videoHolder1= (VideoHolder1) convertView.getTag();
                    break;

            }
        }


        LetterBean.ResultDataBean dataBean = list.get(position);
        switch (type){
            case TYPE_HASIMAGE:

                viewHolder.tvTitle.setText(dataBean.getTitle());
                viewHolder.tvTime.setText(dataBean.getCreateTime());
                viewHolder.tvContent.setText(dataBean.getContent());


                final ViewHolder finalViewHolder = viewHolder;
                final ViewHolder finalViewHolder1 = viewHolder;
                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalViewHolder1.rootView.setBackgroundColor(Color.parseColor("#082946"));
                        if(initFlag==0){
                           finalViewHolder.llBody.setVisibility(View.VISIBLE);
                           initFlag=1;
                        }else if(initFlag==1){
                            finalViewHolder.llBody.setVisibility(View.GONE);
                            initFlag=0;
                        }
                    }
                });
                break;
            case TYPE_NOIMG:
                videoHolder1.tvTitle.setText(dataBean.getTitle());
                videoHolder1.tvTime.setText(dataBean.getCreateTime());
                videoHolder1.tvContent.setText(dataBean.getContent());
                final VideoHolder1 finalVideoHolder = videoHolder1;
                videoHolder1.rootView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if(hasReadFlag==0){
                          finalVideoHolder.llBody.setVisibility(View.GONE);
                          hasReadFlag=1;
                      }else if(hasReadFlag==1){
                          finalVideoHolder.llBody.setVisibility(View.VISIBLE);
                          hasReadFlag=0;
                      }
                  }
              });
                break;
        }
        return convertView;
    }

    static class ViewHolder {
        View view;
        private final TextView tvTitle;
        private final TextView tvTime;
        private final TextView tvContent;
        private final LinearLayout llBody;
        private final LinearLayout rootView;


        ViewHolder(View view) {
            this.view = view;
            llBody = view.findViewById(R.id.ll_body);
            tvTitle = view.findViewById(R.id.tv_title);
            tvTime= view.findViewById(R.id.tv_time);
            tvContent = view.findViewById(R.id.tv_Content);
            rootView = view.findViewById(R.id.rootView);

        }
    }


    static class VideoHolder1 {
        View view;
        private final TextView tvTitle;
        private final TextView tvTime;
        private final TextView tvContent;
        private final LinearLayout llBody;
        private final LinearLayout rootView;


        VideoHolder1(View view) {
            this.view = view;
            llBody = view.findViewById(R.id.ll_body);
            tvTitle = view.findViewById(R.id.tv_title);
            tvTime= view.findViewById(R.id.tv_time);
            tvContent = view.findViewById(R.id.tv_Content);
            rootView = view.findViewById(R.id.rootView);
        }
    }
}
