package cn.net.sunet.ui.zhihu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.ThemeContract;
import cn.net.sunet.mvp.model.entity.DailyThemeBean;
import cn.net.sunet.mvp.presenter.ThemePresenter;
import cn.net.sunet.ui.zhihu.activity.ThemeDetailActivity;
import cn.net.sunet.ui.zhihu.adapter.ThemeAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: 日报主题
 */

public class ThemeFragment extends BaseFragment<ThemePresenter> implements ThemeContract.View {
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    ThemeAdapter mAdapter;
    List<DailyThemeBean.OthersEntity> mData;

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
        mAdapter = new ThemeAdapter(mContext, mData);
        mAdapter.setOnItemClickListener(onItemClickListener);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.dailyTheme();
    }

    @SuppressWarnings("unchecked")
    private ThemeAdapter.OnItemClickListener onItemClickListener = new ThemeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(mContext, ThemeDetailActivity.class);
            intent.putExtra("id", position);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (view != null)
                    mContext.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, view,
                            "shareView").toBundle());
                else
                    mContext.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity)
                            .toBundle());
            } else
                mContext.startActivity(intent);
        }
    };
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.dailyTheme();
        }
    };

    @Override
    public void showContent(DailyThemeBean bean) {
        mSwipeRefresh.setRefreshing(false);
        mData.clear();
        mData.addAll(bean.getOthers());
        mAdapter.notifyDataSetChanged();
    }
}
