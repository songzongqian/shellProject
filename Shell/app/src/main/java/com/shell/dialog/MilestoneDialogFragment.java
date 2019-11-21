package com.shell.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.shell.R;
import com.shell.activity.MainActivity;
import com.shell.home.Bean.TopStaticBean;
import com.shell.home.adapter.PopuCardAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjungang on 20/11/2019.
 * E-Mail:811832241@qq.com
 */
public class MilestoneDialogFragment extends DialogFragment {

    private List<TopStaticBean.ResultDataBean.AllMilepostBean> bottomList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NORMAL, R.style.W_Theme_transparent);
        setCancelable(false);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.popu_center, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setDimAmount(0.5f);//背景黑暗度
        Bundle arguments = getArguments();
        bottomList = (List<TopStaticBean.ResultDataBean.AllMilepostBean>) arguments.getSerializable("data");
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        RecyclerView recyclerView = mView.findViewById(R.id.recyclerView);
        RelativeLayout rlMore = mView.findViewById(R.id.rl_More);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置数据源
        PopuCardAdapter answerCardAdapter = new PopuCardAdapter(bottomList, getActivity());
        recyclerView.setAdapter(answerCardAdapter);
    }
}
