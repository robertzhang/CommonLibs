package com.robertzhang.commonlibs.utils;

/**
 * Created by robertzhang on 17/2/16.
 * email: robertzhangsh@gmail.com
 *
 * 该工具类用于防止重复点击
 */

public class NoDoubleClickUtils {
    private static long lastClickTime = 0;
    private final static int SPACE_TIME = 800;

    public synchronized static boolean isFastDoubleClick() {
        long currTime = System.currentTimeMillis();
        boolean isClick;

        if (currTime - lastClickTime > SPACE_TIME) {
            isClick = true;
        } else {
            isClick = false;
        }

        lastClickTime = currTime;

        return isClick;
    }
}
