package com.shell.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shell.R;

import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * 作者：Michael on 2017/9/28 17:38
 */

/**
 * 倒計時 "mm:ss"
 */
public class OrderTimeCount extends CountDownTimer {

    private TextView mTextView;
    private TextView btnSendText;
    private LinearLayout btnSend;
    public void setWidget(TextView textView, LinearLayout btnSend, TextView btnSendText) {
        this.mTextView = textView;
        this.btnSendText = btnSendText;
        this.btnSend = btnSend;
    }

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public OrderTimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {//计时过程显示
        mTextView.setClickable(false);
        mTextView.setEnabled(false);
        mTextView.setText(hintCodeTime(millisUntilFinished));
    }

    @Override
    public void onFinish() {
        if (mTextView != null) {
            mTextView.setText("00:00");
            mTextView.setClickable(true);
            mTextView.setEnabled(true);

            mTextView.setTextColor(Color.BLACK);
            btnSendText.setTextColor(Color.BLACK);

            btnSend.setClickable(false);
            btnSend.setEnabled(false);
            btnSend.setBackgroundResource(R.drawable.button_unfinish_unenable);
        }
    }
    private String hintCodeTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss", Locale.getDefault());
            return formatter.format(time);
    }
}
