package cn.net.sunet.ui.gold.fragment;

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
import cn.net.sunet.mvp.contract.GoldContract;
import cn.net.sunet.mvp.model.entity.GoldItemBean;
import cn.net.sunet.mvp.presenter.GoldPresenter;
import cn.net.sunet.ui.gold.activity.GoldItemDecoration;
import cn.net.sunet.ui.gold.adapter.GoldPagerAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/20
   Modify: 2017/1/20
 * Description: gold pager fragment
 */

public class GoldPagerFragment extends BaseFragment<GoldPresenter> implements GoldContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private GoldPagerAdapter mAdapter;
    private List<GoldItemBean> mData;
    private GoldItemDecoration mGoldItemDecoration;
    private boolean isLoadingMore = false;
    private String mType;

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
        mData = new ArrayList<>();
        mType = getArguments().getString(Constants.IT_GOLD_TYPE);
        mGoldItemDecoration = new GoldItemDecoration();
        mAdapter = new GoldPagerAdapter(mContext, mData, getArguments().getString(Constants
                .IT_GOLD_TYPE_STR));
        mAdapter.setOnHotCloseListener(onHotCloseListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(mGoldItemDecoration);
        mRecyclerView.addOnScrollListener(onScrollListener);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mPresenter.goldData(mType);
    }

    private GoldPagerAdapter.OnHotCloseListener onHotCloseListener = new GoldPagerAdapter.OnHotCloseListener() {
        @Override
        public void onClose() {
            mRecyclerView.removeItemDecoration(mGoldItemDecoration);
        }
    };
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int lastItem = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
            int totalItem = recyclerView.getLayoutManager().getItemCount();
            if (lastItem >= totalItem - 2 && dy > 0) {
                if (!isLoadingMore) {
                    isLoadingMore = true;
                    mPresenter.goldMoreData();
                }
            }
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (!mAdapter.isHot())
                mRecyclerView.addItemDecoration(mGoldItemDecoration);
            mAdapter.setHot(true);
            mPresenter.goldData(mType);
        }
    };

    @Override
    public void showContent(List<GoldItemBean> bean, boolean isMore, int start, int end) {
        mData.addAll(bean);
        if (isMore) {
            mAdapter.notifyItemRangeChanged(start, end);
            isLoadingMore = false;
        } else {
            mSwipeRefresh.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
        mSwipeRefresh.setRefreshing(false);
    }
}
