package com.robertzhang.commonlibs;

import android.app.Application;
import android.os.Environment;

import com.robertzhang.commonlibs.utils.ToastUtils;

import java.io.File;

/**
 * Created by robertzhang on 16/12/13.
 * email: robertzhangsh@gmail.com
 */

public class BaseApplication extends Application {
    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastUtils.init(this);
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public File getCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return super.getCacheDir();
    }
}
