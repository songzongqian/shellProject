package com.shell.money.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.client.android.utils.ZXingUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shell.Bean.ChongZhiTopBean;
import com.shell.R;
import com.shell.commom.LogonFailureUtil;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.money.Bean.ChongZhiRecordBean;
import com.shell.money.activity.ChongZhiActivity;
import com.shell.money.adapter.ChongZhiRecordAdapter;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wangjungang on 25/11/2019.
 * E-Mail:811832241@qq.com
 */
public class ChongZhiFragment extends Fragment {

    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.rl_copy)
    TextView rlCopy;
    Unbinder unbinder;
    private Request<JSONObject> request;
    private int page = 1;
    @BindView(R.id.iv_qrCode)
    RoundedImageView ivQrCode;
    @BindView(R.id.tv_qrContent)
    TextView tvQrContent;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String chongZhiUrl;
    private int AllPager = 0;
    private int CurrentPager = 1;
    private ChongZhiRecordAdapter chongZhiRecordAdapter;
    private List<ChongZhiRecordBean.ResultDataBean> firstList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chongzhi_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        chongZhiRecordAdapter = new ChongZhiRecordAdapter(getActivity(),firstList);
        listView.setAdapter(chongZhiRecordAdapter);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                CurrentPager = 1;
                getChongZhiData();
                refreshLayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CurrentPager < AllPager) {
                    CurrentPager++;
                    getChongZhiData() ;
                    refreshLayout.finishLoadMore(2000);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
        rlCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cmb1 = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                cmb1.setText(chongZhiUrl);
                Toast.makeText(getContext(), getString(R.string.tv_hascopy), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.GiveMoneyUrl, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(1, request, responseListener);
        getChongZhiData();
    }
    //获取充值记录
    private void getChongZhiData() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.CardUnderUrl+ CurrentPager, RequestMethod.GET);
        request.addHeader("token", token);
        request.add("token", token);
        request.add("busiCode", "charge");
        request.add("status", "");
        request.add("pageNum", page);
        mQueue.add(2, request, responseListener);
    }
    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(getActivity());
                myWaitDialog.show();
            } else {
                myWaitDialog.show();
            }
        }

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            LogonFailureUtil.gotoLoginActiviy(getActivity(),response.get().toString());
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "钱包充值页面上方的参数" + String.valueOf(response));
                    ChongZhiTopBean chongZhiTopBean = gson.fromJson(response.get().toString(), ChongZhiTopBean.class);
                    if (chongZhiTopBean.getResultCode().equals("999999")) {
                        chongZhiUrl = chongZhiTopBean.getResultData();
                        Bitmap qrImage = ZXingUtils.createQRImage(chongZhiUrl);
                        ivQrCode.setImageBitmap(qrImage);
                        tvQrContent.setText(chongZhiUrl);
                    }
                    break;
                case 2:
                    Log.i("song", "用户充值记录返回的值" + String.valueOf(response));
                    ChongZhiRecordBean chongZhiRecordBean = gson.fromJson(response.get().toString(), ChongZhiRecordBean.class);
                    if(chongZhiRecordBean.getResultCode().equals("999999")){
                        List<ChongZhiRecordBean.ResultDataBean> chongZhiList = chongZhiRecordBean.getResultData();
                        if(chongZhiList!=null && chongZhiList.size()>0){
                            AllPager = chongZhiRecordBean.getPages();
                            if (1 == CurrentPager) {
                                firstList.clear();
                            }
                            firstList.addAll(chongZhiList);
                            chongZhiRecordAdapter.notifyDataSetChanged();
                        }
                    }
                    break;

            }
        }


        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            myWaitDialog.cancel();
        }

        @Override
        public void onFinish(int what) {
            myWaitDialog.cancel();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
