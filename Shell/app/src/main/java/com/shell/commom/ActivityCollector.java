package com.shell.commom;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc :Activity集合
 * Created by lizihao on 2017/12/21.
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
    public static int getActivityCollectorSize() {
        return activities.size();
    }
}
