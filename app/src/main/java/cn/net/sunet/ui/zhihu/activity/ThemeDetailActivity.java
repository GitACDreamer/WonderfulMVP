package cn.net.sunet.ui.zhihu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ConvertUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseActivity;
import cn.net.sunet.mvp.contract.ThemeDetailContract;
import cn.net.sunet.mvp.model.entity.DailyThemeDetailBean;
import cn.net.sunet.mvp.presenter.ThemeDetailPresenter;
import cn.net.sunet.ui.zhihu.adapter.ThemeDetailAdapter;
import cn.net.sunet.utils.ImageLoader;
import jp.wasabeef.glide.transformations.BlurTransformation;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: theme detail activity
 */

public class ThemeDetailActivity extends BaseActivity<ThemeDetailPresenter> implements ThemeDetailContract.View {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_theme_child_blur)
    ImageView ivBlur;
    @BindView(R.id.iv_theme_child_origin)
    ImageView ivOrigin;
    @BindView(R.id.tv_theme_child_des)
    TextView tvDes;
    @BindView(R.id.theme_child_appbar)
    AppBarLayout appbar;

    ThemeDetailAdapter mAdapter;
    List<DailyThemeDetailBean.StoriesEntity> mData;
    private int id = -1;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_theme_detail;
    }

    @Override
    protected void initView() {
        id = getIntent().getExtras().getInt("id");
        mPresenter.themeDetail(id);
        mData = new ArrayList<>();
        mAdapter = new ThemeDetailAdapter(mContext, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        appbar.addOnOffsetChangedListener(onOffsetChangedListener);
    }

    private ThemeDetailAdapter.OnItemClickListener onItemClickListener = new ThemeDetailAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            mPresenter.insertReadToDB(mData.get(position).getId());
            mAdapter.setReadState(position, true);
            mAdapter.notifyDataSetChanged();
            Intent intent = new Intent(mContext, DailyDetailActivity.class);
            intent.putExtra("id", mData.get(position).getId());
            mContext.startActivity(intent);
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.themeDetail(id);
        }
    };

    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
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

    @Override
    public void showContent(DailyThemeDetailBean bean) {
        mSwipeRefresh.setRefreshing(false);
        setToolBar(mToolbar, bean.getName());
        mData.clear();
        mData.addAll(bean.getStories());
        mAdapter.notifyDataSetChanged();
        ImageLoader.load(mContext, bean.getBackground(), ivOrigin);
        Glide.with(mContext).load(bean.getBackground()).bitmapTransform(new BlurTransformation(mContext)).into(ivBlur);
        tvDes.setText(bean.getDescription());
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            pop();
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else
            finish();
    }
}