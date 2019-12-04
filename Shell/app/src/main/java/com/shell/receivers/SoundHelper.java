package com.shell.receivers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.shell.MyApplication;
import com.shell.R;

/**
 * Created by wjg on 4/12/2019.
 * E-Mail:811832241@qq.com
 */
public class SoundHelper  {
    private SoundPool soundPool;
    private int idSure;

    private static SoundHelper helper;

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public SoundHelper() {
        Context context = MyApplication.topactivity;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder spb = new SoundPool.Builder();
            spb.setMaxStreams(10);
            // spb.setAudioAttributes(null); // 转换音频格式
            soundPool = spb.build(); // 创建SoundPool对象

        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
        }
        idSure = soundPool.load(context, R.raw.tts, 1);

    }

    public static SoundHelper get() {
        return new SoundHelper();
    }

    public void palyOrder() {
        soundPool.play(idSure, 1, 1, 10, 0, 1);

    }

}
