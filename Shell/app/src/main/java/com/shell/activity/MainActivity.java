package com.shell.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shell.Bean.LanguageEvent;
import com.shell.Bean.OrderEvent;
import com.shell.Bean.OrePoolRewardBean;
import com.shell.Bean.VersionBean;
import com.shell.MyApplication;
import com.shell.R;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.home.HomeFragment;
import com.shell.mine.MineFragment;
import com.shell.money.MoneyFragment;
import com.shell.order.OrderFragment;
import com.shell.updatedemo.update.OnUpdateListener;
import com.shell.updatedemo.update.UpdateManager;
import com.shell.updatedemo.utils.AppUtils;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.response.ErrorResponse;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * MainActivity
 */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private FragmentManager mFragmentmanager;
    private RadioGroup radioGroup;
    private RadioButton rbtn0, rbtn1, rbtn2, rbtn3, rbtn4;
    private HashMap<Integer, Fragment> mTabFragment = new HashMap<Integer, Fragment>();
    private int currentIndex = 0;
    private WebSocketClient client;
    private PopupWindow window;

    private UpdateManager mUpdateManager;

    private ProgressDialog mProgressDialog;


  /*  private SocketListener socketListener = new SimpleListener() {
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
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
            Log.w("song", "服务器推送的消息" + message);
            if (message.contains("userEmail")) {
                EventBus.getDefault().post(new OrderEvent(message));
            } else if (message.contains("hashAward")) {
                //一样的信息
                // 这是是不是矿池奖励数据？
                EventBus.getDefault().post(new OrePoolRewardBean(message));
            }
        }

        @Override
        public <T> void onMessage(ByteBuffer bytes, T data) {

        }
    };*/

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
        String orderFragment = getIntent().getStringExtra("orderFragment");
        mFragmentmanager = getSupportFragmentManager();
        radioGroup = obtainView(R.id.rg_choose);
        radioGroup.check(R.id.rbtn_kuangchi);

        rbtn0 = obtainView(R.id.rbtn_kuangchi);
        rbtn1 = obtainView(R.id.rbtn_getOrder);
        rbtn2 = obtainView(R.id.rbtn_money);
        rbtn3 = obtainView(R.id.rbtn_mine);
        if (!TextUtils.isEmpty(orderFragment)) {
            repleacFragment(1);
            rbtn1.setChecked(true);
        } else {
            repleacFragment(0);
        }
        initProgressDialog();
        mUpdateManager = UpdateManager.getInstance();
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
            System.out.println("------" + "fn");
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
                if (!startLoginActiviy(checkedId)) {
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
        Boolean isLogin = PreManager.instance().getBoolean("ISLogin");
        if (!isLogin && R.id.rbtn_kuangchi != checkedId) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void initData() {
        Boolean isLogin = PreManager.instance().getBoolean("ISLogin");
        if (isLogin) {
            String token = PreManager.instance().getString("token");
            linkSocket(AppUrl.WebSocket + token);
        }
        //WebSocketHandler.getDefault().addListener(socketListener);
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
        String orderFragment = getIntent().getStringExtra("orderFragment");
        if (!TextUtils.isEmpty(orderFragment)) {
            repleacFragment(1);
            rbtn1.setChecked(true);
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

    //显示更新版本提示
    public void showUpDateInfo(VersionBean.ResultDataBean versionData) {

        View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.popuwindow_version_info, null, false);
        window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        TextView tvContent = inflate.findViewById(R.id.tv_content);
        TextView tvOk = inflate.findViewById(R.id.tv_OK);
        String remark = versionData.getRemark();
        String replace = remark.replace("\\r\\n", "\n");
        tvTitle.setText(getString(R.string.version_title));
        tvContent.setText(replace);
        final String dataUrl = versionData.getUrl();
        final String replaceUrl = dataUrl.replaceAll(" ", "");
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdateManager.clearCacheApkFile();
                String trim = replaceUrl.trim();
                mUpdateManager.startToUpdate(trim, mOnUpdateListener);
            }
        });
        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.setFocusable(false);// 这个很重要
        window.setOutsideTouchable(false);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_home, null), Gravity.CENTER, 0, 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (window != null && window.isShowing()) {
            return false;
        }
        return super.dispatchTouchEvent(event);
    }

    private NotificationManager notificationManager;
    private ProgressDialog psdialog;
    NotificationCompat.Builder builder;
    Notification nf;

    //下载apk文件
    private void downLoadApk(String uploadPath, final boolean isShow) {
        notificationManager = (NotificationManager) MainActivity.this.getSystemService(Activity.NOTIFICATION_SERVICE);
        psdialog = new ProgressDialog(MainActivity.this);
        String filefoder = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filefoder = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            filefoder = MainActivity.this.getFilesDir().getAbsolutePath();
        }
        DownloadQueue downloadQueue = NoHttp.newDownloadQueue();
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(uploadPath, RequestMethod.GET, filefoder, "123.apk", true, true);
        downloadQueue.add(123, downloadRequest, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                showToast(exception.toString());
                if (isShow && psdialog != null) {
                    psdialog.dismiss();
                }
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                if (isShow) {
                    psdialog = new ProgressDialog(MainActivity.this);
                    psdialog.setTitle("");
                    psdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    psdialog.setCancelable(false);
                    psdialog.setCanceledOnTouchOutside(false);
                    psdialog.show();
                    ////
                    builder = new NotificationCompat.Builder(MyApplication.getAppInstance()).setSmallIcon(R.mipmap.ic_launcher).setContentInfo("").setContentTitle("正在下载");
                    nf = builder.build();
//                  //使用默认的声音、振动、闪光
                    nf.defaults = Notification.DEFAULT_ALL;
                    notificationManager.notify(0, nf);
                }
            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {
                psdialog.setProgress(progress);
                nf = builder.setProgress(100, progress, false).build();
                notificationManager.notify(0, nf);
                if (progress == 100) {    //下载完成后点击安装
                    notificationManager.cancel(0);
                }
            }

            @Override
            public void onFinish(int what, String filePath) {
                if (isShow) {
                    psdialog.dismiss();
                }
            }

            @Override
            public void onCancel(int what) {
                Log.e("SplshActivity", "onCancel");
                if (isShow) {
                    psdialog.dismiss();
                }
            }
        });
    }

    private void dismissProgressDialog() {
        mProgressDialog.setProgress(0);
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    private OnUpdateListener mOnUpdateListener = new OnUpdateListener() {
        @Override
        public void onStartUpdate() {
            mProgressDialog.show();
        }

        @Override
        public void onProgress(int progress) {
            mProgressDialog.setProgress(progress);
        }

        @Override
        public void onApkDownloadFinish(String apkPath) {
            showToast("newest apk download finish. apkPath: " + apkPath);
            Log.e("tag", "newest apk download finish. apkPath: " + apkPath);
            dismissProgressDialog();
            //所有的更新全部在updateManager中完成，Activity在这里只是做一些界面上的处理
        }

        @Override
        public void onUpdateFailed() {
            showToast("update failed.");
            dismissProgressDialog();
        }

        @Override
        public void onUpdateCanceled() {
            showToast("update cancled.");
            dismissProgressDialog();
        }

        @Override
        public void onUpdateException() {
            showToast("update exception.");
            dismissProgressDialog();
        }
    };

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this, R.style.DialogTheme);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void linkSocket(String url) {
        try {
            client = new WebSocketClient(new URI(url)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.e("onOpen:", "------连接成功!!!");
                }

                @Override
                public void onMessage(String message) {
                    Log.e("onMessage:", message);
                    Log.w("song", "服务器推送的消息" + message);
                    if (message.contains("userEmail")) {
                        EventBus.getDefault().post(new OrderEvent(message));
                    } else if (message.contains("hashAward")) {
                        //一样的信息
                        // 这是是不是矿池奖励数据？
                        EventBus.getDefault().post(new OrePoolRewardBean(message));
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    if (null != client && !client.isOpen()) {
                        //LogUtils.showLog("socket onStartConnect");


                        //client.reconnect();
                    }
                    Log.e("onClose:", "------连接关闭!!!" + reason);
                }

                @Override
                public void onError(Exception ex) {
                    if (null != client && !client.isOpen()) {
                        //LogUtils.showLog("socket onStartConnect");
                        // client.reconnect();
                    }
                    Log.e("onError:", ex.toString());
                }
            };
            // wss需添加
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            SSLSocketFactory factory = sslContext.getSocketFactory();
            client.setSocket(factory.createSocket());
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
