package com.shell.newactivity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shell.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangjungang on 3/11/2019.
 * E-Mail:811832241@qq.com
 */
public class WorkRobotBenchAdapter extends RecyclerView.Adapter<WorkRobotBenchAdapter.ViewHolder> {


    private Context context;
    private List<Object> data;

    public WorkRobotBenchAdapter(Context context, List<Object> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_work_robot_workbench_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (null != data) {
            if(i%2 != 0){
                viewHolder.startOrStop.setText("STARY");
                viewHolder.startOrStop.setBackgroundResource(R.mipmap.rectangle_blue);
            }else {
                viewHolder.startOrStop.setText("STOP");
                viewHolder.startOrStop.setBackgroundResource(R.mipmap.rectangle_red);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView startOrStop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startOrStop = itemView.findViewById(R.id.start_or_stop);
        }
    }
}
