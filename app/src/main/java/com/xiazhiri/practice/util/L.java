package com.xiazhiri.practice.util;

import android.text.TextUtils;
import android.util.Log;

import com.xiazhiri.practice.BuildConfig;

/**
 * Created by liuwencai on 16/4/1.
 */
public class L {

    public static void e(int depth, String msg) {
        if (!BuildConfig.DEBUG)
            return;
        depth++;
        msg = TextUtils.isEmpty(msg) ? " " : msg;
        String log = StackTrace.getTrace(depth);
        Log.e(log, msg);
    }

    public static void e() {
        e(2, null);
    }

    public static void e(String msg) {
        e(2, msg);
    }

}
