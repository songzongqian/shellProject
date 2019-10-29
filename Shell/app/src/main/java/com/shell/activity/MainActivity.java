package com.shell.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shell.Bean.LanguageEvent;
import com.shell.Bean.OrderEvent;
import com.shell.MyApplication;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.home.HomeFragment;
import com.shell.mine.MineFragment;
import com.shell.money.MoneyFragment;
import com.shell.order.OrderFragment;
import com.shell.utils.PreManager;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.response.ErrorResponse;

import org.greenrobot.eventbus.EventBus;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * MainActivity
 */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private FragmentManager mFragmentmanager;
    private RadioGroup radioGroup;
    private RadioButton rbtn0, rbtn1, rbtn2, rbtn3, rbtn4;
    public final static HashMap<Integer, Fragment> mTabFragment = new HashMap<Integer, Fragment>();
    private int currentIndex = 0;


    private SocketListener socketListener = new SimpleListener() {
        @Override
        public void onConnected() {
            Log.i("song", "onConnected()");
        }

        @Override
        public void onConnectFailed(Throwable e) {
            if (e != null) {

            } else {
                Log.i("song", "onConnectFailed");
            }
        }

        @Override
        public void onDisconnect() {
            Log.i("song", "onDisconnect");
        }

        @Override
        public void onSendDataError(ErrorResponse errorResponse) {
            Log.i("song", "onSendDataError");
            errorResponse.release();
        }

        @Override
        public <T> void onMessage(String message, T data) {
            Log.w("song", "服务器推送的消息" + message);
            if (message.contains("userEmail")) {
                //订单消息
                EventBus.getDefault().post(new OrderEvent(message));
            } else if (message.contains("hashAward")) {
                //一样的信息
            }
        }

        @Override
        public <T> void onMessage(ByteBuffer bytes, T data) {

        }
    };


    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {
        mImmersionBar.init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFragmentmanager = getSupportFragmentManager();
        radioGroup = obtainView(R.id.rg_choose);
        radioGroup.check(R.id.rbtn_kuangchi);
        repleacFragment(0);
        rbtn0 = obtainView(R.id.rbtn_kuangchi);
        rbtn1 = obtainView(R.id.rbtn_getOrder);
        rbtn2 = obtainView(R.id.rbtn_money);
        rbtn3 = obtainView(R.id.rbtn_mine);
    }


    /**
     * 切换Fragment
     */
    public void repleacFragment(int index) {
        Log.i("main_加载位置", index + "");
        Fragment tabFragment = getTabFragment(index);
        FragmentTransaction fragmentTransaction = mFragmentmanager
                .beginTransaction();
        if (mFragmentmanager.getFragments() != null
                && mFragmentmanager.getFragments().contains(tabFragment)) {
            for (Fragment fragment : mTabFragment.values()) {
                fragmentTransaction = fragment.equals(tabFragment) ? fragmentTransaction
                        .show(fragment) : fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            if (mFragmentmanager.getFragments() != null) {
                for (Fragment fragment : mFragmentmanager.getFragments()) {
                    if (!tabFragment.equals(fragment)) {
                        fragmentTransaction.hide(fragment);
                    }
                }
            }
            fragmentTransaction.add(R.id.fl_main, tabFragment)
                    .show(tabFragment).commitAllowingStateLoss();
        }
    }


    private Fragment getTabFragment(int index) {
        Fragment fragment = mTabFragment.get(index);
        Log.i("getTabFragment(int", index + "");
        if (fragment == null) {
            if (index == 0) {
                fragment = new HomeFragment();
            } else if (index == 1) {
                fragment = new OrderFragment();
            } else if (index == 2) {
                fragment = new MoneyFragment();
            } else if (index == 3) {
                fragment = new MineFragment();
            }
            mTabFragment.put(index, fragment);
        } else {

        }
        return fragment;
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        Log.i("song", "重新获取焦点的值onRestart" + System.currentTimeMillis());
    }

    @Override
    protected void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!startLoginActiviy(checkedId)){
                    rbtn0.setChecked(true);
                    return;
                }
                switch (checkedId) {
                    case R.id.rbtn_kuangchi:
                        repleacFragment(0);
                        currentIndex = 0;
                        break;
                    case R.id.rbtn_getOrder:
                        repleacFragment(1);
                        currentIndex = 1;
                        break;
                    case R.id.rbtn_money:
                        repleacFragment(2);
                        currentIndex = 2;
                        break;
                    case R.id.rbtn_mine:
                        repleacFragment(3);
                        currentIndex = 3;
                        break;
                }
            }
        });
    }


    /**
     * 如果用户未登录   跳转到登录页面
     *
     * @param checkedId
     */
    public boolean startLoginActiviy(int checkedId) {
        String token = PreManager.instance().getString("token");
        if (TextUtils.isEmpty(token) && R.id.rbtn_kuangchi != checkedId) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void initData() {
        WebSocketHandler.getDefault().addListener(socketListener);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
            };

            if (EasyPermissions.hasPermissions(this, mPermissionList)) {


            } else {
                //未同意过,或者说是拒绝了，再次申请权限
                EasyPermissions.requestPermissions(
                        this,  //上下文
                        "需要读内存卡的权限、照相机等权限权限", //提示文言
                        10, //请求码
                        mPermissionList //权限列表
                );
            }

        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        String type = PreManager.instance().getString("fromType");
        Log.i("song", "重新获取焦点的值" + type);
        if (type != null && !TextUtils.isEmpty(type)) {
            if (type.equals("login")) {
                repleacFragment(0);
                currentIndex = 0;
                PreManager.instance().putString("fromType", "");
            }
        } else {

        }
    }

    ////////权限问题//////////////////////////////////////////
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        switch (requestCode) {
            case 10:
                Log.i("获取成功的权限", "获取成功的权限" + perms);
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        switch (requestCode) {
            case 10:
                Log.i("获取失败的权限", "获取失败的权限" + perms);
                break;
        }
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        exitAppByDoubleClick();
    }

    private static Boolean isExit = false;

    //双击退出
    private void exitAppByDoubleClick() {
        Timer exit;
        if (!isExit) {
            isExit = true;
            Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exit = new Timer();
            exit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            //关闭其他Activity
            List<Activity> activityList = MyApplication.activityList;
            for (int i = 0; i < activityList.size(); i++) {
                activityList.get(i).finish();
            }
        }
    }

}
