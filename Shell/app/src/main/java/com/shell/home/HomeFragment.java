package com.shell.home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shell.R;
import com.shell.base.BaseFragment;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.home.Bean.HomeUserBean;
import com.shell.home.Bean.JiangLiBean;
import com.shell.home.Bean.TopStaticBean;
import com.shell.home.activity.SuanChartActivity;
import com.shell.home.adapter.MessagesAdapter;
import com.shell.home.adapter.PopuCardAdapter;
import com.shell.utils.GetTwoLetter;
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
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.iv_QiTa)
    ImageView ivQiTa;
    @BindView(R.id.one2)
    TextView one2;
    @BindView(R.id.one1)
    TextView one1;
    @BindView(R.id.one3)
    TextView one3;
    @BindView(R.id.two2)
    TextView two2;
    @BindView(R.id.two1)
    TextView two1;
    @BindView(R.id.two3)
    TextView two3;
    @BindView(R.id.three2)
    TextView three2;
    @BindView(R.id.three1)
    TextView three1;
    @BindView(R.id.three3)
    TextView three3;
    @BindView(R.id.ll_gundong)
    LinearLayout llGundong;
    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.ll_map)
    RelativeLayout llMap;
    @BindView(R.id.MeiZhou)
    ImageView MeiZhou;
    @BindView(R.id.OuZhou)
    ImageView OuZhou;
    @BindView(R.id.Feizhou)
    ImageView Feizhou;
    @BindView(R.id.YaZhou)
    ImageView YaZhou;
    @BindView(R.id.DaYangZhou)
    ImageView DaYangZhou;
    @BindView(R.id.tv_word)
    TextView tvWord;
    private Request<JSONObject> request;
    private int page = 1;
    @BindView(R.id.all_suanli)
    TextView allSuanli;
    @BindView(R.id.rl_allsuanli)
    RelativeLayout rlAllsuanli;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.ll_xinyong)
    LinearLayout llXinyong;
    @BindView(R.id.tv_suanLi)
    TextView tvSuanLi;
    @BindView(R.id.ll_mysuanli)
    LinearLayout llMysuanli;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    Unbinder unbinder;
    private Timer mTimer;

    ArrayList<String> mList = new ArrayList<>();

    private RecyclerView recyclerView;
    private String creditScoreDesc;
    private String hashRateDesc;
    private List<TopStaticBean.ResultDataBean.CountryDataBean> countryList;
    private List<TopStaticBean.ResultDataBean.AllMilepostBean> bottomList;
    private List<TopStaticBean.ResultDataBean.CountryDataBean> countryDataList;
    private int handlerPosition = 0;
    @SuppressLint("HandlerLeak")
    private Handler doActionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgId = msg.what;
            switch (msgId) {
                case 1:
                    handlerPosition--;
                    if (0 < handlerPosition) {
                        recyclerView.smoothScrollToPosition(handlerPosition);
                    } else {
                        mTimer.cancel();
                    }
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(getActivity());
        ViewFlipper viewFlipper = mRootView.findViewById(R.id.viewFlipper);
        viewFlipper.startFlipping();
        recyclerView = mRootView.findViewById(R.id.recyclerView);
        for (int i = 0; i < 10; i++) {
            mList.add("测试" + i);
        }
        handlerPosition = mList.size();

        MessagesAdapter adapter = new MessagesAdapter(getActivity(), mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                doActionHandler.sendMessage(message);
            }
        }, 1000, 2000/* 表示1000毫秒之後，每隔1000毫秒執行一次 */);
    }

    /**
     * 目标项是否在最后一个可见项之后
     */
    private boolean mShouldScroll;
    /**
     * 记录目标项位置
     */
    private int mToPosition;

    /**
     * 滑动到指定位置
     *
     * @param mRecyclerView
     * @param position
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        System.out.println("smoothMoveToPosition--" + lastItem);
        if (position < firstItem) {
            System.out.println("smoothMoveToPosition-11-" + lastItem);
            // 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在第一个可见项之后，最后一个可见项之前
            // smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            System.out.println("smoothMoveToPosition-33-" + position);
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        getStaticData();
        getUserInfo();
        getJiangLi();
    }


    //获取首页静态数据
    private void getStaticData() {
        request = NoHttp.createJsonObjectRequest(AppUrl.HomeStaticUrl, RequestMethod.GET);
        String language = PreManager.instance().getString("language");
        request.addHeader("lang", language);
        mQueue.add(1, request, responseListener);
    }

    //获取用户账户数据需要登录
    private void getUserInfo() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.HomeUserData, RequestMethod.GET);
        String language = PreManager.instance().getString("language");
        request.addHeader("lang", language);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(2, request, responseListener);
    }

    //矿池奖励需要用户登录
    private void getJiangLi() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.KuangChiJiangLi, RequestMethod.GET);
        String language = PreManager.instance().getString("language");
        request.addHeader("lang", language);
        request.addHeader("token", token);
        request.add("token", token);
        mQueue.add(3, request, responseListener);
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
            Gson gson = new Gson();
            switch (what) {
                case 1:
                    Log.i("song", "首页静态数据的返回值" + String.valueOf(response));
                    TopStaticBean topStaticBean = gson.fromJson(response.get().toString(), TopStaticBean.class);
                    String resultCode = topStaticBean.getResultCode();
                    if (resultCode.equals("999999")) {
                        bottomList = topStaticBean.getResultData().getAllMilepost();
                        countryList = topStaticBean.getResultData().getCountryData();
                        creditScoreDesc = topStaticBean.getResultData().getCreditScoreDesc();
                        hashRateDesc = topStaticBean.getResultData().getHashRateDesc();
                        double hashRate = topStaticBean.getResultData().getNetworkHashRate().getHashRate();
                        allSuanli.setText(hashRate + "");
                        countryDataList = topStaticBean.getResultData().getCountryData();

                        int ouZhouCount = countryDataList.get(0).getUserCount();
                        int MeiZhouCount = countryDataList.get(1).getUserCount();
                        int YaZhouCount = countryDataList.get(2).getUserCount();
                        int DaYangZhouCount = countryDataList.get(3).getUserCount();
                        int FeiZhouCount = countryDataList.get(4).getUserCount();

                        if (ouZhouCount >= 0 && ouZhouCount < 100) {
                            OuZhou.setImageResource(R.mipmap.ouzhou1);
                        } else if (ouZhouCount > 100 && ouZhouCount <= 2999) {
                            OuZhou.setImageResource(R.mipmap.ouzhou2);
                        } else if (ouZhouCount > 3000 && ouZhouCount <= 7999) {
                            OuZhou.setImageResource(R.mipmap.ouzhou3);
                        } else if (ouZhouCount > 8000 && ouZhouCount <= 19999) {
                            OuZhou.setImageResource(R.mipmap.ouzhou4);
                        } else if (ouZhouCount > 20000 && ouZhouCount <= 49999) {
                            OuZhou.setImageResource(R.mipmap.ouzhou5);
                        } else if (ouZhouCount > 50000) {
                            OuZhou.setImageResource(R.mipmap.ouzhou6);
                        }


                        if (MeiZhouCount >= 0 && MeiZhouCount < 100) {
                            MeiZhou.setImageResource(R.mipmap.meizhou1);
                        } else if (MeiZhouCount > 100 && MeiZhouCount <= 2999) {
                            MeiZhou.setImageResource(R.mipmap.meizhou2);
                        } else if (MeiZhouCount > 3000 && MeiZhouCount <= 7999) {
                            MeiZhou.setImageResource(R.mipmap.meizhou3);
                        } else if (MeiZhouCount > 8000 && MeiZhouCount <= 19999) {
                            MeiZhou.setImageResource(R.mipmap.meizhou4);
                        } else if (MeiZhouCount > 20000 && MeiZhouCount <= 49999) {
                            MeiZhou.setImageResource(R.mipmap.meizhou5);
                        } else if (MeiZhouCount > 50000) {
                            MeiZhou.setImageResource(R.mipmap.meizhou6);
                        }


                        if (YaZhouCount >= 0 && YaZhouCount < 100) {
                            YaZhou.setImageResource(R.mipmap.yazhou1);
                        } else if (YaZhouCount > 100 && YaZhouCount <= 2999) {
                            YaZhou.setImageResource(R.mipmap.yazhou2);
                        } else if (YaZhouCount > 3000 && YaZhouCount <= 7999) {
                            YaZhou.setImageResource(R.mipmap.yazhou3);
                        } else if (YaZhouCount > 8000 && YaZhouCount <= 19999) {
                            YaZhou.setImageResource(R.mipmap.yazhou4);
                        } else if (YaZhouCount > 20000 && YaZhouCount <= 49999) {
                            YaZhou.setImageResource(R.mipmap.yazhou5);
                        } else if (YaZhouCount > 50000) {
                            YaZhou.setImageResource(R.mipmap.yazhou6);
                        }


                        if (DaYangZhouCount >= 0 && DaYangZhouCount < 100) {
                            DaYangZhou.setImageResource(R.mipmap.dayang1);
                        } else if (DaYangZhouCount > 100 && DaYangZhouCount <= 2999) {
                            DaYangZhou.setImageResource(R.mipmap.dayang2);
                        } else if (DaYangZhouCount > 3000 && DaYangZhouCount <= 7999) {
                            DaYangZhou.setImageResource(R.mipmap.dayang3);
                        } else if (DaYangZhouCount > 8000 && DaYangZhouCount <= 19999) {
                            DaYangZhou.setImageResource(R.mipmap.dayang4);
                        } else if (DaYangZhouCount > 20000 && DaYangZhouCount <= 49999) {
                            DaYangZhou.setImageResource(R.mipmap.dayang5);
                        } else if (DaYangZhouCount > 50000) {
                            DaYangZhou.setImageResource(R.mipmap.dayang6);
                        }


                        if (FeiZhouCount >= 0 && FeiZhouCount < 100) {
                            Feizhou.setImageResource(R.mipmap.feizhou1);
                        } else if (FeiZhouCount > 100 && FeiZhouCount <= 2999) {
                            Feizhou.setImageResource(R.mipmap.feizhou2);
                        } else if (FeiZhouCount > 3000 && FeiZhouCount <= 7999) {
                            Feizhou.setImageResource(R.mipmap.feizhou3);
                        } else if (FeiZhouCount > 8000 && FeiZhouCount <= 19999) {
                            Feizhou.setImageResource(R.mipmap.feizhou4);
                        } else if (FeiZhouCount > 20000 && FeiZhouCount <= 49999) {
                            Feizhou.setImageResource(R.mipmap.feizhou5);
                        } else if (FeiZhouCount > 50000) {
                            Feizhou.setImageResource(R.mipmap.feizhou6);
                        }



                        if (bottomList != null && bottomList.size() > 0) {
                            TopStaticBean.ResultDataBean.AllMilepostBean allMilepostBean = bottomList.get(0);
                            tvTime.setText(allMilepostBean.getTime());
                            tvContent.setText(allMilepostBean.getDesc());
                        }

                    } else {

                    }
                    break;
                case 2:
                    Log.i("song", "首页用户数据的返回值" + String.valueOf(response));

                    HomeUserBean homeUserBean = gson.fromJson(response.get().toString(), HomeUserBean.class);
                    String successCode = homeUserBean.getResultCode();
                    if (successCode.equals("999999")) {
                        String creditScore = homeUserBean.getResultData().getCreditScore();
                        String hashRate = homeUserBean.getResultData().getHashRate();
                        String miningAward = homeUserBean.getResultData().getMiningAward();
                        tvScore.setText(creditScore + "");
                        tvSuanLi.setText(hashRate + "");
                        tvAmount.setText(GetTwoLetter.getTwo(miningAward));
                    } else {

                    }
                    break;

                case 3:
                    Log.i("song", "首页矿池奖励的返回值" + String.valueOf(response));
                    JiangLiBean jiangLiBean = gson.fromJson(response.get().toString(), JiangLiBean.class);
                    String jiangLiBeanResultCode = jiangLiBean.getResultCode();
                    if (jiangLiBeanResultCode.equals("999999")) {
                        List<JiangLiBean.ResultDataBean> jiangLiList = jiangLiBean.getResultData();
                        //HomeAwardAdapter  homeAwardAdapter=new HomeAwardAdapter(getActivity(),jiangLiList);
                        //listView.setAdapter(homeAwardAdapter);
                        // new Timer().schedule(new TimeTaskScroll(getActivity(), listView,jiangLiList), 20, 20);

                        if (jiangLiList != null && jiangLiList.size() >= 3) {
                            llGundong.setVisibility(View.VISIBLE);
                            JiangLiBean.ResultDataBean resultDataBean = jiangLiList.get(0);
                            JiangLiBean.ResultDataBean resultDataBean1 = jiangLiList.get(1);
                            JiangLiBean.ResultDataBean resultDataBean2 = jiangLiList.get(2);
                            one1.setText(GetTwoLetter.getTwo(resultDataBean.getAmount()));
                            one3.setText(resultDataBean.getCreateTime());
                            two1.setText(GetTwoLetter.getTwo(resultDataBean1.getAmount()));
                            two3.setText(resultDataBean.getCreateTime());
                            three1.setText(GetTwoLetter.getTwo(resultDataBean2.getAmount()));
                            three3.setText(resultDataBean2.getCreateTime());
                        }
                    } else {
                        llGundong.setVisibility(View.INVISIBLE);
                    }
                    break;

            }


        }


        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            Log.i("song", "卡片下方数据返回值错误" + String.valueOf(response));
            myWaitDialog.cancel();


        }

        @Override
        public void onFinish(int what) {
            myWaitDialog.cancel();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_allsuanli, R.id.ll_xinyong, R.id.ll_mysuanli, R.id.iv_more, R.id.ll_map,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_allsuanli:
                //全网算力图
                Intent intent = new Intent(getActivity(), SuanChartActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_xinyong:
                showPopuwindow("1");
                break;
            case R.id.ll_mysuanli:
                showPopuwindow("2");
                break;
            case R.id.iv_more:
                //里程碑更多
                showCenter();
                break;

            case R.id.ll_map:
                showTopFirstWindow(countryDataList);
                break;

        }
    }


    //上方国家的window
    private void showTopWindow(int countryCode) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popu_home_top, null, false);
        final PopupWindow window = new PopupWindow(inflate, 800, 600, true);
        // final PopupWindow window = new PopupWindow(inflate, 252, 151, true);
        TextView tvTopName = inflate.findViewById(R.id.tv_topName);
        TextView tvRegisterCount = inflate.findViewById(R.id.tv_RegisterUser);
        TextView tvOrderCount = inflate.findViewById(R.id.tv_orderCount);
        TextView tvOrderMoney = inflate.findViewById(R.id.tv_orderMoney);
        TextView tvOrderJieDian = inflate.findViewById(R.id.tv_JieDian);
        ImageView ivClose = inflate.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        if (countryCode == 4) {
            //中国
            TopStaticBean.ResultDataBean.CountryDataBean countryDataBean = countryList.get(4);
            tvTopName.setText(countryDataBean.getName());
            tvRegisterCount.setText(getString(R.string.home_poprecount) + "  " + countryDataBean.getUserCount());
            tvOrderCount.setText(getString(R.string.home_ljoc) + "  " + countryDataBean.getOrderCount());
            tvOrderMoney.setText(getString(R.string.home_ljje) + "  " + countryDataBean.getOrderAmount());
            tvOrderJieDian.setText(getString(R.string.home_sjjd) + "  " + countryDataBean.getUserCount());
        } else if (countryCode == 2) {
            //韩国
            TopStaticBean.ResultDataBean.CountryDataBean countryDataBean = countryList.get(2);
            tvTopName.setText(countryDataBean.getName());
            tvRegisterCount.setText(getString(R.string.home_poprecount) + "  " + countryDataBean.getUserCount());
            tvOrderCount.setText(getString(R.string.home_ljoc) + countryDataBean.getOrderCount());
            tvOrderMoney.setText(getString(R.string.home_ljje) + "  " + countryDataBean.getOrderAmount());
            tvOrderJieDian.setText(getString(R.string.home_sjjd) + "  " + countryDataBean.getUserCount());

        } else if (countryCode == 3) {
            //日本
            TopStaticBean.ResultDataBean.CountryDataBean countryDataBean = countryList.get(3);
            tvTopName.setText(countryDataBean.getName());
            tvRegisterCount.setText(getString(R.string.home_poprecount) + "  " + countryDataBean.getUserCount());
            tvOrderCount.setText(getString(R.string.home_ljoc) + "  " + countryDataBean.getOrderCount());
            tvOrderMoney.setText(getString(R.string.home_ljje) + "  " + countryDataBean.getOrderAmount());
            tvOrderJieDian.setText(getString(R.string.home_sjjd) + "  " + countryDataBean.getUserCount());

        } else if (countryCode == 0) {
            //欧洲
            TopStaticBean.ResultDataBean.CountryDataBean countryDataBean = countryList.get(0);
            tvTopName.setText(countryDataBean.getName());
            tvRegisterCount.setText(getString(R.string.home_poprecount) + "  " + countryDataBean.getUserCount());
            tvOrderCount.setText(getString(R.string.home_ljoc) + "  " + countryDataBean.getOrderCount());
            tvOrderMoney.setText(getString(R.string.home_ljje) + "  " + countryDataBean.getOrderAmount());
            tvOrderJieDian.setText(getString(R.string.home_sjjd) + "  " + countryDataBean.getUserCount());

        } else if (countryCode == 1) {
            //美国
            TopStaticBean.ResultDataBean.CountryDataBean countryDataBean = countryList.get(1);
            tvTopName.setText(countryDataBean.getName());
            tvRegisterCount.setText(getString(R.string.home_poprecount) + "  " + countryDataBean.getUserCount());
            tvOrderCount.setText(getString(R.string.home_ljoc) + "  " + countryDataBean.getOrderCount());
            tvOrderMoney.setText(getString(R.string.home_ljje) + "  " + countryDataBean.getOrderAmount());
            tvOrderJieDian.setText(getString(R.string.home_sjjd) + "  " + countryDataBean.getUserCount());
        }


        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null), Gravity.TOP, 0, 300);
    }


    private void showCenter() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popu_center, null, false);
        final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        SmartRefreshLayout smartRefreshLayout = inflate.findViewById(R.id.smartRefreshLayout);
        ImageView ivMore = inflate.findViewById(R.id.iv_more);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置数据源
        PopuCardAdapter answerCardAdapter = new PopuCardAdapter(bottomList, getActivity());
        recyclerView.setAdapter(answerCardAdapter);

        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null), Gravity.CENTER, 0, 0);
    }


    //弹出解释说明性文件
    private void showPopuwindow(String value) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popuwindow_xinyong, null, false);
        final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        TextView tvContent = inflate.findViewById(R.id.tv_content);

        if ("1".equals(value)) {
            tvTitle.setText("信用分说明");
            tvContent.setText(creditScoreDesc);

        } else if ("2".equals(value)) {
            tvTitle.setText("算力说明");
            tvContent.setText(hashRateDesc);
        }

        ImageView ivClose = inflate.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null), Gravity.CENTER, 0, 0);
    }


    //弹出各大洲的数据
    private void showTopFirstWindow(List<TopStaticBean.ResultDataBean.CountryDataBean> value) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popu_home_top1, null, false);
        final PopupWindow firstWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        RelativeLayout llOZ = inflate.findViewById(R.id.ll_OZ);
        TextView tvOZ = inflate.findViewById(R.id.tv_ozName);
        TextView tvOZCount = inflate.findViewById(R.id.ozsl);

        RelativeLayout llMZ = inflate.findViewById(R.id.ll_MZ);
        TextView tvMZ = inflate.findViewById(R.id.tv_mz);
        TextView tvMZCount = inflate.findViewById(R.id.mzsl);


        RelativeLayout llYZ = inflate.findViewById(R.id.ll_YZ);
        TextView tvYZ = inflate.findViewById(R.id.tv_YZ);
        TextView tvYZCount = inflate.findViewById(R.id.yzsl);

        RelativeLayout llDYZ = inflate.findViewById(R.id.ll_DYZ);
        TextView tvDYZ = inflate.findViewById(R.id.tv_DYZ);
        TextView tvDYZCount = inflate.findViewById(R.id.dyzsl);


        RelativeLayout llFZ = inflate.findViewById(R.id.ll_FZ);
        TextView tvFZ = inflate.findViewById(R.id.tv_FZ);
        TextView tvFZCount = inflate.findViewById(R.id.fzsl);
        tvOZ.setText(value.get(0).getName());
        tvOZCount.setText(value.get(0).getUserCount() + "");
        tvMZ.setText(value.get(1).getName());
        tvMZCount.setText(value.get(1).getUserCount() + "");
        tvYZ.setText(value.get(2).getName());
        tvYZCount.setText(value.get(2).getUserCount() + "");
        tvDYZ.setText(value.get(3).getName());
        tvDYZCount.setText(value.get(3).getUserCount() + "");
        tvFZ.setText(value.get(4).getName());
        tvFZCount.setText(value.get(4).getUserCount() + "");


        llOZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstWindow.dismiss();
                showTopWindow(0);
            }
        });

        llMZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstWindow.dismiss();
                showTopWindow(1);
            }
        });

        llYZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstWindow.dismiss();
                showTopWindow(2);

            }
        });


        llDYZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstWindow.dismiss();
                showTopWindow(3);

            }
        });

        llFZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstWindow.dismiss();
                showTopWindow(4);
            }
        });


        backgroundAlpha(0.5f);
        firstWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        firstWindow.setBackgroundDrawable(new BitmapDrawable());
        firstWindow.setOutsideTouchable(true);
        firstWindow.setTouchable(true);
        firstWindow.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null), Gravity.TOP, 0, 300);
    }


    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //可见
            Log.i("song", "HomeFragment可见");
            getStaticData();
            getUserInfo();
            getJiangLi();
        } else {
            //不可见
            Log.i("song", "HomeFragment不可见");
        }
    }

}
