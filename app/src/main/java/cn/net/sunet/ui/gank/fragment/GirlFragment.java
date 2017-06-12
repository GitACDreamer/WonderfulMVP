package cn.net.sunet.ui.gank.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.GirlContract;
import cn.net.sunet.mvp.model.entity.GankItemBean;
import cn.net.sunet.mvp.presenter.GirlPresenter;
import cn.net.sunet.ui.gank.activity.GirlDetailActivity;
import cn.net.sunet.ui.gank.adapter.GirlAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/18
   Modify: 2017/1/18
 * Description: girl fragment
 */

public class GirlFragment extends BaseFragment<GirlPresenter> implements GirlContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private static final int SPAN_COUNT = 2;

    boolean isLoadingMore = false;
    GirlAdapter mAdapter;
    List<GankItemBean> mData;
    StaggeredGridLayoutManager mStaggeredGridLayoutManager;

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
        mAdapter = new GirlAdapter(mContext, mData);
        mAdapter.setOnItemClickListener(onItemClickListener);
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
        mPresenter.girlData(false);
    }

    private GirlAdapter.OnItemClickListener onItemClickListener = new GirlAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            gotoGirlDetail(mData.get(position).getUrl(), mData.get(position).get_id(), mData.get(position).getWho());
        }
    };

    private void gotoGirlDetail(String url, String id, String who) {
        Intent intent = new Intent(mContext, GirlDetailActivity.class);
        intent.putExtra(Constants.IT_DETAIL_URL, url);
        intent.putExtra(Constants.IT_DETAIL_ID, id);
        intent.putExtra(Constants.IT_DETAIL_TITLE, who);
        mContext.startActivity(intent);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mScrollTotal += dy;
            mIsTop = mScrollTotal <= 0 ;
            int[] visibleItems = mStaggeredGridLayoutManager.findLastVisibleItemPositions(null);
            int lastItem = Math.max(visibleItems[0], visibleItems[1]);
            if (lastItem > mAdapter.getItemCount() - 5 && !isLoadingMore && dy > 0) {
                isLoadingMore = true;
                mPresenter.girlData(true);
            }
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.girlData(false);
        }
    };

    //重复点击tab时，自动滚动到top
    @Override
    public void onTabSelectedEvent() {
        if (mIsTop) {
            mSwipeRefresh.setRefreshing(true);
            mPresenter.girlData(false);
        } else
            mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showContent(List<GankItemBean> beans, boolean isMore) {
        mSwipeRefresh.setRefreshing(false);
        if (!isMore) {
            mData.clear();
            mData.addAll(beans);
            mAdapter.notifyDataSetChanged();
        } else {
            isLoadingMore = false;
            mData.addAll(beans);
            //防止已加载的图片闪烁
            for (int i = mData.size() - GirlPresenter.NUM_OF_PAGE; i < mData.size(); ++i)
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
