package cn.net.sunet.ui.zhihu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseActivity;
import cn.net.sunet.mvp.contract.SectionDetailContract;
import cn.net.sunet.mvp.model.entity.DailySectionDetailBean;
import cn.net.sunet.mvp.presenter.SectionDetailPresenter;
import cn.net.sunet.ui.zhihu.adapter.SectionDetailAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/4
   Modify: 2017/1/4
 * Description: section detail activity
 */

public class SectionDetailActivity extends BaseActivity<SectionDetailPresenter> implements SectionDetailContract.View {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    SectionDetailAdapter mAdapter;
    List<DailySectionDetailBean.StoriesEntity> mData;
    int id;
    String title;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_section_detail;
    }

    @Override
    protected void initView() {
        id = getIntent().getExtras().getInt("id", 0);
        title = getIntent().getExtras().getString("title");
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        setToolBar(mToolbar, title);

        mData = new ArrayList<>();
        mAdapter = new SectionDetailAdapter(mContext, mData);
        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);

        mPresenter.dailySectionDetail(id);
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.dailySectionDetail(id);
        }
    };

    private SectionDetailAdapter.OnItemClickListener onItemClickListener = new SectionDetailAdapter
            .OnItemClickListener() {
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

    @Override
    public void showContent(DailySectionDetailBean bean) {
        mSwipeRefresh.setRefreshing(false);
        mData.clear();
        mData.addAll(bean.getStories());
        mAdapter.notifyDataSetChanged();
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
