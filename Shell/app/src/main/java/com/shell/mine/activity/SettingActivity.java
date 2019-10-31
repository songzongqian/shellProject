package com.shell.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.laojiang.imagepickers.ImagePicker;
import com.laojiang.imagepickers.data.ImagePickType;
import com.laojiang.imagepickers.data.MediaDataBean;
import com.shell.Bean.CommonBean;
import com.shell.Bean.MessageEvent;
import com.shell.Bean.PostImageBean;
import com.shell.R;
import com.shell.activity.LoginActivity;
import com.shell.base.BaseActivity;
import com.shell.constant.AppUrl;
import com.shell.dialog.MyWaitDialog;
import com.shell.utils.FileUtils;
import com.shell.utils.PreManager;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends BaseActivity {
    public RequestQueue mQueue = NoHttp.newRequestQueue(1);
    @BindView(R.id.tv_MyEmail)
    TextView tvMyEmail;
    private Request<JSONObject> request;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rightTitle)
    TextView tvRightTitle;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.ll_head)
    RelativeLayout llHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.ll_nick)
    RelativeLayout llNick;
    private String mediaPath;
    private String imagUrl;
    private String token;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.my_set));
        tvRightTitle.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        String headUrl = getIntent().getStringExtra("headUrl");
        String nickName = getIntent().getStringExtra("nickName");
        String myEmail = getIntent().getStringExtra("myEmail");
        if (TextUtils.isEmpty(headUrl)){
            Glide.with(SettingActivity.this).load(R.mipmap.person).into(ivHead);
        }else {
            RequestOptions options=new RequestOptions();
            options.placeholder(R.mipmap.person); //添加占位图
            options.error(R.mipmap.person);
            Glide.with(SettingActivity.this).load(headUrl).apply(options).into(ivHead);
        }


        tvNickName.setText(nickName);
        tvMyEmail.setText(myEmail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.ll_head, R.id.ll_nick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_head:
                //测试上传图片接口
                showSelectedWindow();
                break;
            case R.id.ll_nick:
                Intent intent = new Intent(SettingActivity.this, NickActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showSelectedWindow() {

        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_bottom_photo, null, false);
        final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        RelativeLayout rlCamera = inflate.findViewById(R.id.rl_camera);
        RelativeLayout rlPhoto = inflate.findViewById(R.id.rl_photo);
        RelativeLayout rlSure = inflate.findViewById(R.id.rl_sure);

        rlCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCamera();
                window.dismiss();
            }
        });


        rlPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPicSelect();
                window.dismiss();
            }
        });


        rlSure.setOnClickListener(new View.OnClickListener() {
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
        window.setFocusable(true);
        window.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_zhiya, null), Gravity.BOTTOM, 0, 0);
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    private void setPicSelect() {
        ImagePicker build = new ImagePicker.Builder()
                .pickType(ImagePickType.MUTIL) //设置选取类型(拍照ONLY_CAMERA、单选SINGLE、多选MUTIL)
                .maxNum(1)
                .needCamera(false)
                .cachePath(Environment.getExternalStorageDirectory().getPath() + File.separator + "pic")
                .needVideo(false)//是否显示视频
                .build();
        build.start(SettingActivity.this, 200, 205);
    }


    private void setCamera() {
        ImagePicker build = new ImagePicker.Builder()
                .pickType(ImagePickType.ONLY_CAMERA) //设置选取类型(拍照ONLY_CAMERA、单选SINGLE、多选MUTIL)
                .maxNum(1)
                .needCamera(true)
                .cachePath(Environment.getExternalStorageDirectory().getPath() + File.separator + "pic")
                .needVideo(false)//是否显示视频
                .build();
        build.start(SettingActivity.this, 200, 201);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 205) {
            List<MediaDataBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            if (resultList.size() > 0) {
                mediaPath = resultList.get(0).getMediaPath();
                Log.i("song", "选择的图片路径" + mediaPath);
                Bitmap loacalBitmap = FileUtils.getLoacalBitmap(mediaPath);
                ivHead.setImageBitmap(loacalBitmap);
                upFileList();
            }
        } else if (requestCode == 136) {
            //相机回调
            Log.i("song", "相机" + mediaPath);
            List<MediaDataBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            if (resultList.size() > 0) {
                mediaPath = resultList.get(0).getMediaPath();
                Log.i("song", "相机的图片路径" + mediaPath);
                Bitmap loacalBitmap = FileUtils.getLoacalBitmap(mediaPath);
                ivHead.setImageBitmap(loacalBitmap);
                upFileList();
            }
        }

    }

    private void upFileList() {
        String token = PreManager.instance().getString("token");
        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            request = NoHttp.createJsonObjectRequest(AppUrl.PostImage, RequestMethod.POST);
            request.add("file", new File(mediaPath));
            request.add("busiCode", "portrait");
            request.add("token", token);
            mQueue.add(1, request, responseListener);
        }
    }


    //获取图片Url上传
    private void PostUrl() {
        String token = PreManager.instance().getString("token");
        request = NoHttp.createJsonObjectRequest(AppUrl.PostURLImage, RequestMethod.POST);
        request.addHeader("token", token);
        request.add("portrait", imagUrl);
        request.add("token", token);
        mQueue.add(2, request, responseListener);
    }


    private MyWaitDialog myWaitDialog;
    OnResponseListener<JSONObject> responseListener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {
            if (myWaitDialog == null) {
                myWaitDialog = new MyWaitDialog(SettingActivity.this);
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
                    Log.i("song", "上传图片返回1的参数" + String.valueOf(response));
                    PostImageBean postImageBean = gson.fromJson(response.get().toString(), PostImageBean.class);
                    String resultCode = postImageBean.getResultCode();
                    if (resultCode.equals("999999")) {
                        imagUrl = postImageBean.getResultData();
                        EventBus.getDefault().post(new MessageEvent(imagUrl));
                        PostUrl();
                    }
                    break;

                case 2:
                    Log.i("song", "上传图片url返回的参数" + String.valueOf(response));
                    CommonBean commonBean = gson.fromJson(response.get().toString(), CommonBean.class);
                    String resultCode1 = commonBean.getResultCode();
                    if (resultCode1.equals("999999")) {
                        Toast.makeText(SettingActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SettingActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
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


}
