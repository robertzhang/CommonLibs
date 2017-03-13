package com.robertzhang.commonlibs.imageloader;

import android.widget.ImageView;

import com.robertzhang.commonlibs.R;

public class ImageLoader {

    private static volatile IImageLoaderProvider mProvider;

    private static IImageLoaderProvider getProvider() {
        if (mProvider == null) {
            synchronized (ImageLoader.class) {
                if (mProvider == null) {
                    mProvider = new GlideImageLoaderProvider();
                }
            }
        }
        return mProvider;
    }

    public static void displayImage(ImageView iv, String url) {
        com.robertzhang.commonlibs.imageloader.ImageRequest request = new com.robertzhang.commonlibs.imageloader.ImageRequest.Builder()
                .url(url)
                .imgView(iv)
                .placeHolder(R.drawable.pic_holder)
                .create();
        getProvider().loadImage(request);
    }

    public static void displayImage(ImageView iv, String url, int placeHolder) {
        com.robertzhang.commonlibs.imageloader.ImageRequest request = new com.robertzhang.commonlibs.imageloader.ImageRequest.Builder()
                .url(url)
                .imgView(iv)
                .placeHolder(placeHolder)
                .create();
        getProvider().loadImage(request);
    }

}
