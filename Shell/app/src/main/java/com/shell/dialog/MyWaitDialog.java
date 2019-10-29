/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shell.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shell.R;


/**
 * desc  网络请求的loadingDialog
 * @author 785917397@qq.com
 * create time 2019/1/3 15:50
 * Reference link
 */
public class MyWaitDialog {
//    public SpinKitView spinKitView;
//
//    public MyWaitDialog(Context context) {
//        super(context, R.style.defaultDialogStyle);//设置样式
//        setCustomDialog();
//
//    }
//
//    public MyWaitDialog(Context context, int themeResId) {
//        super(context, themeResId);
//    }
//
//    protected MyWaitDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//    }
//
//    private void setCustomDialog() {
//        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_common, null);
//        spinKitView = mView.findViewById(R.id.spin_kit);
//        spinKitView.setIndeterminateDrawable(getDialogDrawable(8));
//        spinKitView.setColor(Color.parseColor("#996600"));
//        super.setContentView(mView);
//    }
//
//    //获取动画样式
//    private Sprite getDialogDrawable(int index) {
//        Sprite sprite = null;
//        if ((index >= 0) && (index < 15)) {
//            Style style = Style.values()[index];
//            sprite = SpriteFactory.create(style);
//        } else {
//            com.github.ybq.android.spinkit.Style style = Style.values()[0];
//            sprite = SpriteFactory.create(style);
//        }
//        return sprite;
//    }

    public Dialog mDialog;
    private AnimationDrawable animationDrawable = null;

    public MyWaitDialog(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.progress_view, null);

        TextView text = (TextView) view.findViewById(R.id.progress_message);
        //text.setText("正在加载，请稍后");
        ImageView loadingImage = (ImageView) view.findViewById(R.id.progress_view);
        //loadingImage.setImageResource(R.drawable.progress_dialog_loding);
//        Glide.with(context).load(R.drawable.progress1)
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(loadingImage);
//        animationDrawable = (AnimationDrawable)loadingImage.getDrawable();
//        if(animationDrawable!=null){
//            animationDrawable.setOneShot(false);
//            animationDrawable.start();
//        }

        mDialog = new Dialog(context, R.style.dialog);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

    }

    public void show() {
        mDialog.show();
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

    public void cancel() {
        if(mDialog.isShowing()) {
            mDialog.dismiss();
            //animationDrawable.stop();
        }
    }
    public boolean isShowing(){
        if(mDialog.isShowing()) {
            return true;
        }
        return false;
    }

    public Dialog getmDialog(){
        return mDialog;
    }
}
