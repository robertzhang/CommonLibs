package com.robertzhang.commonlibs.fragment.base;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.robertzhang.commonlibs.R;
import com.robertzhang.commonlibs.adapter.LoadMoreWrapper;
import com.robertzhang.commonlibs.loadingview.LoadingViewHelperController;
import com.robertzhang.commonlibs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

public abstract class AbsListFragment extends BaseFragment implements IList {

    private LoadingViewHelperController mLoadingViewHelperController = null;
    private View.OnClickListener mOnClickListener = null;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private MultiTypeAdapter mAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    private int mCurrentPageIndex;
    private List mItems;

    private boolean isCanLoadMore = true;

    public void disAbleLoadMore() {
        isCanLoadMore = false;
        mLoadMoreWrapper.disableLoadMore();
    }

    @Override
    protected final int setLayoutResourceID() {
        return R.layout.fragment_base_recyclerview;
    }

    @Override
    protected final void init() {
        mCurrentPageIndex = getInitPageIndex();
        mItems = new ArrayList<>();
        mAdapter = getAdapter();
        mAdapter.applyGlobalMultiTypePool();
        mLoadMoreWrapper = new LoadMoreWrapper(getContext(), mAdapter);
        mLoadMoreWrapper.setOnLoadListener(new LoadMoreWrapper.OnLoadListener() {
            @Override
            public void onRetry() {
                loadData(mCurrentPageIndex);
            }

            @Override
            public void onLoadMore() {
                if (isCanLoadMore)
                    AbsListFragment.this.loadMore();
            }
        });
    }

    @Override
    protected final void setUpView() {
        mSwipeRefreshLayout = $(R.id.swipe_refresh_layout);
        mRecyclerView = $(R.id.recyclerview);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mLoadMoreWrapper);
        customConfig();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        if (hasLoadingView()) { // 通过子类的设置来判断是否创建 controller
            mLoadingViewHelperController = new LoadingViewHelperController(mRecyclerView);
            mOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) { //错误重试
                    showLoading();
                    loadData(getInitPageIndex());
                }
            };
        }

//        mStatusViewLayout.setOnRetryListener(new View.OnClickListener() {//错误重试
//            @Override
//            public void onClick(View v) {
//                mStatusViewLayout.showLoading();
//                loadData(getInitPageIndex());
//            }
//        });


    }


    @Override
    protected final void setUpData() {
        showLoading();
        loadData(getInitPageIndex());//初始加载首页数据
    }


    @Override
    public final void refreshData() {
        mCurrentPageIndex = getInitPageIndex();
        if (isCanLoadMore)
            mLoadMoreWrapper.showLoadMore();
        loadData(getInitPageIndex());
    }

    @Override
    public final void loadMore() {
        loadData(++mCurrentPageIndex);
    }

    @Override
    public abstract void loadData(int pageIndex);

    // 让继承的 Fragment 确定是否需要 Loading view
    // 默认情况是不需要
    public abstract boolean hasLoadingView();

    //region 可直接调用的方法

    /**
     * 列表数据接收成功时调用（相关的实现类需要手动去调用此方法）
     *
     * @param pageIndex 当前请求的页数
     * @param items     返回的数据
     */
    protected final void onDataSuccessReceived(int pageIndex, List items) {
        showContent();
        if (pageIndex == getInitPageIndex() && items.size() <= 0) {//无数据
            showEmpty(getEmptyMsg());
        } else if (pageIndex == getInitPageIndex()) {//刷新
            mItems.clear();
            mItems.addAll(items);
        } else if (items != null && items.size() != 0) {//加载更多
            mItems.addAll(items);
        } else {//没有更多数据了
            mCurrentPageIndex--;
            mLoadMoreWrapper.showLoadComplete();
        }

        mLoadMoreWrapper.notifyDataSetChanged();

    }


    /**
     * 得到当前列表数据
     *
     * @return 当前列表数据
     */
    protected final List getItems() {
        return mItems;
    }

    /**
     * 添加分隔线
     *
     * @param itemDecoration 分隔线
     */
    protected final void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        if (mRecyclerView != null)
            mRecyclerView.addItemDecoration(itemDecoration);
    }


    //endregion

    //region 根据具体的情况可选择性实现下面方法

    protected void customConfig() {

    }

    protected int getInitPageIndex() {
        return 1;
    }

    protected MultiTypeAdapter getAdapter() {
        return new MultiTypeAdapter(mItems);
    }

    @NonNull
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @NonNull
    protected String getEmptyMsg() {
        return "无数据";
    }

    //endregion

    //region 数据加载状态的处理
    @Override
    public void showError(Exception e) {
        if (mCurrentPageIndex == getInitPageIndex()) {
//            mStatusViewLayout.showError(e.getMessage());
            if (mLoadingViewHelperController != null) {
                mLoadingViewHelperController.showError(null,mOnClickListener);
            }
        } else {
            mLoadMoreWrapper.showLoadError();
            ToastUtils.getInstance().showToast(e.getMessage());
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEmpty(String msg) {
//        mStatusViewLayout.showEmpty(msg);
        if (mLoadingViewHelperController != null) {
            mLoadingViewHelperController.showEmpty(null,mOnClickListener);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
//        mStatusViewLayout.showLoading();
        if (mLoadingViewHelperController != null) {
            mLoadingViewHelperController.restore();
            mLoadingViewHelperController.showLoading(null);
        }
    }

    @Override
    public void showContent() {
//        mStatusViewLayout.showContent();
        if (mLoadingViewHelperController != null) {
            mLoadingViewHelperController.restore();
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }
    //endregion
}
