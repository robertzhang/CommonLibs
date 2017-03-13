package com.robertzhang.commonlibs.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;


public class Logger {

    /**
     * 是否为开发者模式(开发模式打印LOG,非开发模式不打印LOG)
     */
    private static boolean mDebug = true;

    private Logger() {
    }

    @NonNull
    private static String getTagName(Object object) {
        String tagName = object.getClass().getSimpleName();
        if (TextUtils.isEmpty(tagName)) tagName = "AnonymityClass";
        return tagName;
    }

    /**
     * 打印info级别的log
     *
     * @param msg
     */
    public static void i(Object object, String msg) {
        String tagName = getTagName(object);
        if (mDebug) {
            Log.i(tagName, msg);
        }
    }

    /**
     * 打印info级别的log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (mDebug) {
            Log.i("LogInfo", msg);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param msg
     */
    public static void e(Object object, String msg) {
        String tagName = getTagName(object);
        if (mDebug) {
            Log.e(tagName, msg);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (mDebug) {
            Log.e("LogError", msg);
        }
    }

    /**
     * 打印debug级别的log
     * @param object
     * @param msg
     */

    public static void d(Object object, String msg) {
        String tagName = getTagName(object);
        if (mDebug) {
            Log.d(tagName, msg);
        }
    }

    public static void d(String msg) {
        if (mDebug) {
            Log.d("LogDebug", msg);
        }
    }

    public static void w(Object object, String msg) {
        String tagName = getTagName(object);
        if (mDebug) {
            Log.w(tagName, msg);
        }
    }

    public static void w(String msg) {
        if (mDebug) {
            Log.w("LogDebug", msg);
        }
    }
}
