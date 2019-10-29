package com.shell.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.gyf.barlibrary.ImmersionBar;
import com.shell.R;
import com.shell.dialog.ImageDialog;


public abstract class BaseFragment extends CommonFragment  {

    private ImageView ivLeft;
    private TextView tvLeft;

    private TextView tvTitle;

    private ImageView ivRight;
    private TextView tvRight;
    private Toolbar toolbar;
    public String title;

    protected View mRootView;
    protected Intent mBundleIntent;
    protected ImmersionBar mImmersionBar;//状态栏工具
    protected Toast childToast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            if (getLayoutId() != 0) {
                mRootView = inflater.inflate(getLayoutId(), container, false);
            }
            // 初始化请求队列，传入的参数是请求并发值。
            initView(mRootView);
            setUserVisibleHint(true);
            setListener();
            initData();
            Log.v("main_BaseFragment", "createView");
        } else {
            // 防止重复加载，出现闪退
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            mBundleIntent = null;
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // TODO Auto-generated method stub
        super.setUserVisibleHint(isVisibleToUser);
    }


    /**
     * 此方法描述的是： 获取布局
     *
     * @author: zhangwb
     * @version: 2015-11-20 上午0:10:30
     */
    protected abstract int getLayoutId();

    /**
     * 此方法描述的是： 初始化界面
     *
     * @author: zhangwb
     * @version: 2015-11-20 上午0:10:30
     */
    protected abstract void initView(View rootView);

    /**
     * 此方法描述的是： 初始化界面
     *
     * @author: zhangwb
     * @version: 2015-11-20 下午13:10:30
     */
    protected abstract void setListener();

    /**
     * 此方法描述的是： 初始化所有数据的方法
     *
     * @author: zhm
     * @version: 2014-3-12 下午3:17:46
     */
    protected abstract void initData();

    public void setBundleIntent(Intent intent) {
        mBundleIntent = intent;
    }

    public <T extends View> T obtainView(int resId) {
        return (T) mRootView.findViewById(resId);
    }

    @Override
    public void onDestroy() {
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        super.onDestroy();
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(int title, int message) {
        showMessageDialog(getText(title), getText(message));
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(int title, CharSequence message) {
        showMessageDialog(getText(title), message);
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(CharSequence title, int message) {
        showMessageDialog(title, getText(message));
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.know, new DialogInterface.OnClickListener() {
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
        ImageDialog imageDialog = new ImageDialog(getActivity());
        imageDialog.setTitle(title);
        imageDialog.setImage(bitmap);
        imageDialog.show();
    }

    /////////////////////////////////////////
    protected void showToast(String text) {
        this.childToast = ((BaseActivity) getActivity()).mToast;
        if (childToast == null) {
            ((BaseActivity) getActivity()).mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        }
        this.childToast = ((BaseActivity) getActivity()).mToast;
        childToast.setText(text);
        childToast.setDuration(Toast.LENGTH_SHORT);
        childToast.show();
    }

    protected void cancelToast() {
        if (childToast != null) {
            childToast.cancel();
        }
    }
}