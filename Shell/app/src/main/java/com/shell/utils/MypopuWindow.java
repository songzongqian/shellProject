package com.shell.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.shell.R;



public class MypopuWindow extends PopupWindow {

    private View mainView;
    private TextView zhongguo, hanguo,riben,oumeng,meiguo;


    public MypopuWindow(Activity paramActivity, View.OnClickListener paramOnClickListener, int paramInt1, int paramInt2) {
        super(paramActivity);
        //窗口布局
        mainView = LayoutInflater.from(paramActivity).inflate(
                   R.layout.populayoutrg, null);

        zhongguo =  mainView.findViewById(R.id.tv_zhongguo);
        hanguo =  mainView.findViewById(R.id.tv_hanguo);
        riben= mainView.findViewById(R.id.tv_riben);
        oumeng = mainView.findViewById(R.id.tv_om);
        meiguo= mainView.findViewById(R.id.tv_mg);



        //设置每个子布局的事件监听器
        if (paramOnClickListener != null) {
            zhongguo.setOnClickListener(paramOnClickListener);
            hanguo.setOnClickListener(paramOnClickListener);
            riben.setOnClickListener(paramOnClickListener);
            oumeng.setOnClickListener(paramOnClickListener);
            meiguo.setOnClickListener(paramOnClickListener);

        }
        setContentView(mainView);
        //设置宽度
        setWidth(paramInt1);
        //设置高度
        setHeight(paramInt2);
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }
}
