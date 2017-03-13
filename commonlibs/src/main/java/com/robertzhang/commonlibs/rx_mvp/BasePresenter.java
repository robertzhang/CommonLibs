package com.robertzhang.commonlibs.rx_mvp;

/**
 * Created by robertzhang on 16/12/16.
 * email: robertzhangsh@gmail.com
 *
 * 该 base presenter 针对 rx
 *
 * eg:
 * public interface BaseContract {
 *     interface View extends BaseView<Presenter> {
 *          // some thing about ui refresh
 *     }
 *
 *     interface Presenter extends BasePresenter {
 *          // some thing about action
 *     }
 * }
 */

public interface BasePresenter {

    void subscribe();

    void unsubscribe();
}
