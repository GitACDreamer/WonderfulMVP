package cn.net.sunet.ui.zhihu.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.CommentContract;
import cn.net.sunet.mvp.model.entity.DailyCommentBean;
import cn.net.sunet.mvp.presenter.CommentPresenter;
import cn.net.sunet.ui.zhihu.adapter.CommentAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: 日报评论
 */

public class CommentFragment extends BaseFragment<CommentPresenter> implements CommentContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    CommentAdapter mAdapter;
    List<DailyCommentBean.CommentsEntity> mData;
    private Bundle bundle = null;

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
        mAdapter = new CommentAdapter(mContext, mData);
        bundle = getArguments();
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.dailyComment(bundle.getInt("id"), bundle.getInt("kind"));
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.dailyComment(bundle.getInt("id"), bundle.getInt("kind"));
        }
    };

    @Override
    public void showContent(DailyCommentBean bean) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mSwipeRefresh.setRefreshing(false);
        mData.clear();
        mData.addAll(bean.getComments());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
        mRecyclerView.setVisibility(View.VISIBLE);
        mSwipeRefresh.setRefreshing(false);
    }
}
