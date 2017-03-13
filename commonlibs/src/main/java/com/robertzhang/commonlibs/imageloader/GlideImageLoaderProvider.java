package com.robertzhang.commonlibs.imageloader;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.robertzhang.commonlibs.SettingCenter;
import com.robertzhang.commonlibs.utils.NetworkUtil;

import java.io.IOException;
import java.io.InputStream;

import static com.bumptech.glide.Glide.with;

public class GlideImageLoaderProvider implements com.robertzhang.commonlibs.imageloader.IImageLoaderProvider {
    @Override
    public void loadImage(com.robertzhang.commonlibs.imageloader.ImageRequest request) {
        Context context = request.getImageView().getContext();
        if (!SettingCenter.getOnlyWifiLoadImage()) {
            loadNormal(context, request);
        } else {
            if (NetworkUtil.isWifiConnected(context)) {
                loadNormal(context, request);
            } else {
                loadCache(context, request);
            }
        }

    }

    private void loadNormal(Context ctx, com.robertzhang.commonlibs.imageloader.ImageRequest img) {
        Glide.with(ctx)
                .load(img.getUrl())
                .placeholder(img.getPlaceHolder())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(img.getImageView());
    }

    private void loadCache(Context ctx, com.robertzhang.commonlibs.imageloader.ImageRequest img) {
        with(ctx)
                .using(new StreamModelLoader<String>() {
                    @Override
                    public DataFetcher<InputStream> getResourceFetcher(final String model, int i, int i1) {
                        return new DataFetcher<InputStream>() {
                            @Override
                            public InputStream loadData(Priority priority) throws Exception {
                                throw new IOException();
                            }

                            @Override
                            public void cleanup() {

                            }

                            @Override
                            public String getId() {
                                return model;
                            }

                            @Override
                            public void cancel() {

                            }
                        };
                    }
                })
                .load(img.getUrl())
                .placeholder(img.getPlaceHolder())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .into(img.getImageView());
    }
}
