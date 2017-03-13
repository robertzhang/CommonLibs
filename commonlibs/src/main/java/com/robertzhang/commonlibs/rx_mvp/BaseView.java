package com.robertzhang.commonlibs.rx_mvp;

/**
 * Created by robertzhang on 16/12/16.
 * email: robertzhangsh@gmail.com
 *
 * 通用型 base view
 *
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
}
