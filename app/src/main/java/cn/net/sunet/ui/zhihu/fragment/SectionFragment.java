package cn.net.sunet.ui.zhihu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.SectionContract;
import cn.net.sunet.mvp.model.entity.DailySectionsBean;
import cn.net.sunet.mvp.presenter.SectionPresenter;
import cn.net.sunet.ui.zhihu.activity.SectionDetailActivity;
import cn.net.sunet.ui.zhihu.adapter.SectionAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: 日报专栏
 */

public class SectionFragment extends BaseFragment<SectionPresenter> implements SectionContract.View {
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    List<DailySectionsBean.DataEntity> mData;
    SectionAdapter mAdapter;

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
        mAdapter = new SectionAdapter(mContext, mData);
        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.dailySections();
            }
        });
        mPresenter.dailySections();
    }

    private SectionAdapter.OnItemClickListener onItemClickListener = new SectionAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(mContext, SectionDetailActivity.class);
            intent.putExtra("id", mData.get(position).getId());
            intent.putExtra("title", mData.get(position).getName());
            mContext.startActivity(intent);
        }
    };

    @Override
    public void showContent(DailySectionsBean bean) {
        mSwipeRefresh.setRefreshing(false);
        mData.clear();
        mData.addAll(bean.getData());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
        mSwipeRefresh.setRefreshing(false);
    }
}
