package com.shell.receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.shell.R;
import com.shell.activity.MainActivity;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by wangjungang on 16/11/2019.
 * E-Mail:811832241@qq.com
 */
public class CustomerJpushReceiver extends JPushMessageReceiver {

    private static final String TAG = "PushMessageReceiver";
    private SoundPool soundPool;

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Log.e(TAG, "[onMessage] " + customMessage);

    }

    @Override
    public void onNotifyMessageOpened(final Context context, NotificationMessage message) {
        //用户点击了通知。 一般情况下，用户不需要配置此 receiver activitydescription。
        // 在这里可以自己写代码去定义用户点击后的行为

        String a = message.notificationExtras;
        String content = message.notificationContent;
        String ss = message.notificationTitle;
        Intent in = new Intent(context, MainActivity.class);
        in.putExtra("orderFragment", "orderFragment");
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(in);
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        Log.e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if (nActionExtra == null) {
            Log.d(TAG, "ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNotifyMessageArrived(final Context context, NotificationMessage message) {
        Log.e(TAG, "[onNotifyMessageArrived] " + message);
        //JPushInterface.ACTION_MESSAGE_RECEIVED:收到了自定义消息 Push
        //收到了通知 Push。如果通知的内容为空，则在通知栏上不会展示通知。
        //add db
        String title = message.notificationTitle;
        String content = message.notificationContent;
        //  processCustomMessage(context);
        //SoundHelper.get().palyOrder();
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(1);
        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setLegacyStreamType(AudioManager.STREAM_ALARM);
        builder.setAudioAttributes(attrBuilder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder spb = new SoundPool.Builder();
            spb.setMaxStreams(10);
            // spb.setAudioAttributes(null); // 转换音频格式
            // 创建SoundPool对象
            soundPool = spb.build();

        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
        }
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0) {
                    soundPool.play(sampleId, 0.99f, 0.99f, 0, 0, 1);
                }
            }
        });
        soundPool.load(context, R.raw.tts, 0);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        Log.e(TAG, "[onNotifyMessageDismiss] " + message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        Log.e(TAG, "[onRegister] " + registrationId);
    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        Log.e(TAG, "[onConnected] " + isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        Log.e(TAG, "[onCommandResult] " + cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        Log.e(TAG, "[onConnected] " + jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }



    private void processCustomMessage(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context);
        // notification.setAutoCancel(true).setContentText("自定义推送声音").setContentTitle("极光测试").setSmallIcon(R.mipmap.ic_launcher);
        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.tts));
/*        if (!TextUtils.isEmpty(extras)) {
            try {
                JSONObject extraJson = new JSONObject(extras);
                if (null != extraJson && extraJson.length() > 0) {
                    String sound = extraJson.getString("sound");
                    if (!TextUtils.isEmpty(sound)) {
                        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.aps_sound));
                    }
                }
            } catch (JSONException e) {
            }
        }*/
        Intent mIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
        notification.setContentIntent(pendingIntent);
        notificationManager.notify(2, notification.build());
    }
}
