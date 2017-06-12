package cn.net.sunet.ui.gank.fragment;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ConvertUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.TechContract;
import cn.net.sunet.mvp.model.entity.GankItemBean;
import cn.net.sunet.mvp.presenter.TechPresenter;
import cn.net.sunet.ui.gank.activity.TechnologyDetailActivity;
import cn.net.sunet.ui.gank.adapter.TechAdapter;
import cn.net.sunet.utils.ImageLoader;
import jp.wasabeef.glide.transformations.BlurTransformation;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: technology fragment
 */

public class TechnologyFragment extends BaseFragment<TechPresenter> implements TechContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.iv_tech_blur)
    ImageView ivBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView ivOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView tvCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout appbar;

    List<GankItemBean> mData;
    TechAdapter mAdapter;
    boolean isLoadingMore = false;
    String tech = null;
    int type = 0;
    int mScrollTotal = 0;
    boolean mIsTop = true;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_technology;
    }

    @Override
    protected void initView() {
        mScrollTotal = 0;
        mIsTop = true;
        mData = new ArrayList<>();
        tech = getArguments().getString(Constants.ITEM_TYPE);
        type = getArguments().getInt(Constants.ITEM_TYPE_CODE);
        isLoadingMore = false;
        mAdapter = new TechAdapter(mContext, mData, tech);
        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(onScrollListener);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        appbar.addOnOffsetChangedListener(onOffsetChangedListener);
        mPresenter.girlImage();
        mPresenter.gankData(tech, type);
    }

    private TechAdapter.OnItemClickListener onItemClickListener = new TechAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            gotoTeachDetail(mData.get(position).get_id(),
                    mData.get(position).getDesc(),
                    mData.get(position).getUrl(),
                    type, view);
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
            if (lastItem >= totalItem - 2 && dy > 0) { //还剩余2个item时加载更多
                if (!isLoadingMore) {
                    isLoadingMore = true;
                    mPresenter.gankMoreData(tech);
                }
            }
        }
    };

    AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (verticalOffset >= 0)
                mSwipeRefresh.setEnabled(true);
            else {
                mSwipeRefresh.setEnabled(false);
                float rate = (float) (ConvertUtils.dp2px(256) + verticalOffset * 2) / ConvertUtils.dp2px(256);
                if (rate >= 0)
                    ivOrigin.setAlpha(rate);
            }
        }
    };
    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.gankData(tech, type);
        }
    };

    private void gotoTeachDetail(String id, String title, String url, int type, View view) {
        TechnologyDetailActivity.launch(new TechnologyDetailActivity.Builder()
                .setContext(mContext)
                .setId(id)
                .setTitle(title)
                .setUrl(url)
                .setType(type)
                .setAnimConfig(mActivity, view));
    }

    //重复点击tab时，自动滚动到top
    @Override
    public void onTabSelectedEvent() {
        if (mIsTop) {
            mSwipeRefresh.setRefreshing(true);
            mPresenter.gankData(tech, type);
        } else
            mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showContent(List<GankItemBean> beans, boolean isMore) {
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

    @Override
    public void showGirlContent(String url, String copyright) {
        ImageLoader.load(mContext, url, ivOrigin);
        Glide.with(mContext).load(url).bitmapTransform(new BlurTransformation(mContext)).into(ivBlur);
        tvCopyright.setText(String.format(Locale.CHINA, "by: %s", copyright));
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
