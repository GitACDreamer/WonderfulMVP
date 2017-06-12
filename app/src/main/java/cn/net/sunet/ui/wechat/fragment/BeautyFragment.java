package cn.net.sunet.ui.wechat.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.WeChatContract;
import cn.net.sunet.mvp.model.entity.WeChatBean;
import cn.net.sunet.mvp.presenter.WeChatPresenter;
import cn.net.sunet.ui.wechat.adapter.WeChatAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/22
   Modify: 2017/1/22
 * Description: this is a class description.
 */

public class BeautyFragment extends BaseFragment<WeChatPresenter> implements WeChatContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private static final int SPAN_COUNT = 2;

    boolean isLoadingMore = false;
    WeChatAdapter mAdapter;
    List<WeChatBean> mData;
    StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    int mScrollTotal = 0;
    boolean mIsTop = true;
    private int mType = Constants.TYPE_WECHAT_NEWS;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common_page;
    }

    @Override
    protected void initView() {
        mScrollTotal = 0;
        mIsTop = true;
        isLoadingMore = false;
        mData = new ArrayList<>();
        mType = getArguments().getInt(Constants.ITEM_TYPE_CODE);
        mAdapter = new WeChatAdapter(mContext, mData);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //TODO 解决下拉崩溃的临时解决办法 java.lang.IllegalArgumentException: Pixel distance must be non-negative
        //set item prefetch enable false
        mStaggeredGridLayoutManager.setItemPrefetchEnabled(false);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(onScrollListener);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mPresenter.wechatData(mType, false);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mScrollTotal += dy;
            mIsTop = mScrollTotal <= 0;
            int[] visibleItems = mStaggeredGridLayoutManager.findLastVisibleItemPositions(null);
            int lastItem = Math.max(visibleItems[0], visibleItems[1]);
            if (lastItem > mAdapter.getItemCount() - 5 && !isLoadingMore && dy > 0) {
                isLoadingMore = true;
                mPresenter.wechatData(mType, true);
            }
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.wechatData(mType, false);
        }
    };

    //重复点击tab时，自动滚动到top
    @Override
    public void onTabSelectedEvent(int type) {
        if (type != Constants.TYPE_WECHAT_BEAUTY)
            return;
        if (mIsTop) {
            mSwipeRefresh.setRefreshing(true);
            mPresenter.wechatData(mType, false);
        } else
            mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showContent(List<WeChatBean> beans, boolean isMore) {
        mSwipeRefresh.setRefreshing(false);
        if (!isMore) {
            mData.clear();
            mData.addAll(beans);
            mAdapter.notifyDataSetChanged();
        } else {
            isLoadingMore = false;
            mData.addAll(beans);
            //防止已加载的图片闪烁
            for (int i = mData.size() - WeChatPresenter.NUM_OF_PAGE; i < mData.size(); ++i)
                mAdapter.notifyItemChanged(i);
        }
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();  //取消订阅
        super.onDestroyView();
    }
}