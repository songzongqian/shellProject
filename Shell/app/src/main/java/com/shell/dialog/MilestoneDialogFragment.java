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
import android.widget.Button;
import android.widget.RelativeLayout;

import com.galenleo.widgets.CodeInputView;
import com.shell.R;
import com.shell.activity.MainActivity;
import com.shell.constant.AppUrl;
import com.shell.home.Bean.TopStaticBean;
import com.shell.home.adapter.PopuCardAdapter;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjungang on 20/11/2019.
 * E-Mail:811832241@qq.com
 */
public class MilestoneDialogFragment extends DialogFragment {
    String reuqestNumber = "";
    int flag = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NORMAL, R.style.W_Theme_transparent);
        setCancelable(false);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.popu_input, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setDimAmount(0.5f);//背景黑暗度


        initView(mView);
        return mView;
    }

    private void initView(View mView) {

        Button btnNo = mView.findViewById(R.id.btn_canle);
        Button btnOK = mView.findViewById(R.id.btn_ok);
         final CodeInputView editText = mView.findViewById(R.id.editText);


        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPwd = editText.getText().toString().trim();
                //获取输入的密码
                String token = PreManager.instance().getString("token");
                if (0 == flag){
                    return;
                }
                if (flag == 1) {
                 //   reuqestNumber = clicktvContent;
                } else if (flag == 2) {
                 //   reuqestNumber = etAmount.getText().toString().trim();

                }
                if (TextUtils.isEmpty(reuqestNumber)){
                    return;
                }
             /*   //  Toast.makeText(ZhiYaActivity.this,reuqestNumber,Toast.LENGTH_SHORT).show();
                request = NoHttp.createJsonObjectRequest(AppUrl.ZhiYaDataLv, RequestMethod.POST);
                request.addHeader("token", token);
                request.add("amount", reuqestNumber);
                request.add("payPassword", inputPwd);
                mQueue.add(3, request, responseListener);*/
            }
        });

    }
}
