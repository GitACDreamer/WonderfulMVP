package cn.net.sunet.ui.zhihu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.HotContract;
import cn.net.sunet.mvp.model.entity.DailyHotBean;
import cn.net.sunet.mvp.presenter.HotPresenter;
import cn.net.sunet.ui.zhihu.activity.DailyDetailActivity;
import cn.net.sunet.ui.zhihu.adapter.HotAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: 日报热门
 */

public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View {
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    List<DailyHotBean.RecentEntity> mData;
    HotAdapter mAdapter;

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
        mAdapter = new HotAdapter(mContext, mData);
        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mPresenter.dailyHot();
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.dailyHot();
        }
    };

    private HotAdapter.OnItemClickListener onItemClickListener = new HotAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            mPresenter.insertReadToDB(mData.get(position).getNews_id());
            mAdapter.setReadState(position, true);
            mAdapter.notifyDataSetChanged();
            Intent intent = new Intent(mContext, DailyDetailActivity.class);
            intent.putExtra("id", mData.get(position).getNews_id());
            mContext.startActivity(intent);
        }
    };

    @Override
    public void showContent(DailyHotBean bean) {
        mSwipeRefresh.setRefreshing(false);
        mRecyclerView.setVisibility(View.VISIBLE);
        mData.clear();
        mData.addAll(bean.getRecent());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
        mSwipeRefresh.setRefreshing(false);
    }
}
