package com.shell.newactivity.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.newactivity.adapter.WorkRobotBenchAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkRobotWorkbenchActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.list_workbench)
    RecyclerView listWorkbench;
    private List<Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initDatas() {
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Object());
        }
    }

    private void initViews() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("Workbench");
        listWorkbench.setLayoutManager(new LinearLayoutManager(this));
        WorkRobotBenchAdapter adapter = new WorkRobotBenchAdapter(this, data);
        listWorkbench.setAdapter(adapter);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_robot_workbench;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
