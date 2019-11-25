package com.shell.newactivity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shell.R;
import com.shell.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkRobotLicenseActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.btn_log_in)
    Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        rlBack.setVisibility(View.INVISIBLE);
        tvTitle.setText("Shell Robot");
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkRobotLicenseActivity.this,WorkRobotAddAccountActivity.class));
            }
        });

    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_robot_license;
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
