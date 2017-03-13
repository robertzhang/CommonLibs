package com.robertzhang.commonlibs;

import android.view.View;

import java.util.Calendar;

/**
 * Created by robertzhang on 17/2/24.
 * email: robertzhangsh@gmail.com
 */
/*
    防止多次点击
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(view);
        }
    }

    public abstract void onNoDoubleClick(View view);

}
