package com.robertzhang.commonlibs.adapter;

import android.content.Context;

import java.util.List;

/**
 * 支持多种ItemType的Adapter（适用于RecyclerView）
 */
public abstract class MultiItemTypeRVBaseAdapter<T> extends RVBaseAdapter<T> {


    public MultiItemTypeRVBaseAdapter(Context context, List<T> beans) {
        super(context, beans);
    }

    @Override
    public abstract int getItemViewType(int position);


}
