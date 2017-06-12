package cn.net.sunet.ui.wechat.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: wechat news fragment
 */

public class WeChatFragment extends BaseFragment<WeChatPresenter> implements WeChatContract.View {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    WeChatAdapter mAdapter;
    List<WeChatBean> mData;

    int mType;
    boolean isLoadingMore = false;
    int mScrollTotal = 0;
    boolean mIsTop = true;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(onScrollListener);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mPresenter.wechatData(mType, false);
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.wechatData(mType, false);
        }
    };

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mScrollTotal += dy;
            mIsTop = mScrollTotal <= 0;
            int lastItem = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
            int totalItem = mRecyclerView.getLayoutManager().getItemCount();
            if (lastItem >= totalItem - 2 && dy > 0) {  //还剩余2个时加载更多
                if (!isLoadingMore) {
                    isLoadingMore = true;
                    mPresenter.wechatData(mType, true);
                }
            }
        }
    };

    @Override
    public void showContent(List<WeChatBean> beans, boolean isMore) {
        mSwipeRefresh.setRefreshing(false);
        if (beans != null) {
            if (!isMore)
                mData.clear();
            else
                isLoadingMore = false;
            mData.addAll(beans);
            mAdapter.notifyDataSetChanged();
        }
    }

    //重复点击tab时，自动滚动到top
    @Override
    public void onTabSelectedEvent(int type) {
        if (type == Constants.TYPE_WECHAT_BEAUTY)
            return;
        if (mIsTop) {
            mSwipeRefresh.setRefreshing(true);
            mPresenter.wechatData(mType, false);
        } else
            mRecyclerView.smoothScrollToPosition(0);
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
