package com.shell.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.gyf.barlibrary.ImmersionBar;
import com.shell.R;
import com.shell.commom.ActivityCollector;
import com.shell.dialog.ImageDialog;
import com.shell.dialog.MyWaitDialog;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.RequestQueue;




/**
 * description: 基础activity
 * class: BaseActivityPos
 * params:
 * date:2017/7/26 12:57
 */

public abstract class BaseActivity extends CommonActivity  {
    protected BasePresenter presenter;
    protected ImmersionBar mImmersionBar;
    protected Toast mToast;
    protected Toolbar toolbar;
    protected MyWaitDialog mWaitDialog;
    //网络请求队列
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    public Toast toast;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        } else {
            return;
        }
        mImmersionBar = ImmersionBar.with(this);
        initView();

//        initToolBar();
//        initStatusBar();
        mImmersionBar.init();

        setListener();
        initData();
        ActivityCollector.addActivity(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 初始化ToolBar
     */

    protected abstract void initToolBar();

    /**
     * 初始化状态栏
     */
    protected abstract void initStatusBar();

    /**
     * 返回当前界面布局文件
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 设置监听器
     */
    protected abstract void setListener();

    /**
     * 初始化所有数据的方法
     */
    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 获取控件
     */
    public <T extends View> T obtainView(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 设置ActionBar
     */
    @Override
    public void initActionBar() {

    }




    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        ActivityCollector.removeActivity(this);
        Log.i("ActivityCollector,size", ActivityCollector.getActivityCollectorSize() + "");
        //取消掉所有的请求
        if (presenter != null) {
            presenter.stop();
        }
        //沉浸式状态栏    /必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        if (mImmersionBar != null)
            mImmersionBar.destroy();
        super.onDestroy();
    }


    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    /**
     * 显示图片dialog。
     *
     * @param title  标题。
     * @param bitmap 图片。
     */
    public void showImageDialog(CharSequence title, Bitmap bitmap) {
        ImageDialog imageDialog = new ImageDialog(this);
        imageDialog.setTitle(title);
        imageDialog.setImage(bitmap);
        imageDialog.show();
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction() == AppUrl.BACK_MAINACTIVITY) {
//                for (int i = 0; i < ActivityCollector.activities.size(); i++) {
//                    //销毁掉MainActivity以上的多有Activity
//                    if (i != 0) {
//                        if (!ActivityCollector.activities.get(i).isFinishing()) {
//                            ActivityCollector.activities.get(i).finish();
//                        }
//                    }
//                }
//            }
        }
    }

    /////////////////////////////////////////
    protected void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    protected void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    protected void showLoading() {
        if (mWaitDialog == null) {
            mWaitDialog = new MyWaitDialog(this);
        }
        if (!mWaitDialog.isShowing()) {
            mWaitDialog.show();
        }
    }

    protected void closeLoading() {
        if (mWaitDialog == null) {
            return;
        }
        if (mWaitDialog.isShowing()) {
            mWaitDialog.cancel();
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
