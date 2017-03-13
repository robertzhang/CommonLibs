package com.robertzhang.commonlibs.activity.base;

import com.robertzhang.commonlibs.utils.ToastUtils;

public abstract class BaseMainActivity extends BaseActivity {
    private long lastBackKeyDownTick = 0;
    private static final long MAX_DOUBLE_BACK_DURATION = 1500;

    @Override
    public void onBackPressed() {
        if (beforeOnBackPressed()) {
            long currentTick = System.currentTimeMillis();
            if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
                ToastUtils.getInstance().showToast("再按一次退出");
                lastBackKeyDownTick = currentTick;
            } else {
                finish();
                System.exit(0);
            }
        }
    }

    protected boolean beforeOnBackPressed() {
        return true;
    }
}
