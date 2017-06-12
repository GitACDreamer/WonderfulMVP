package cn.net.sunet.ui.zhihu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.net.sunet.R;
import cn.net.sunet.app.App;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.event.CalendarDayEvent;
import cn.net.sunet.mvp.contract.DailyContract;
import cn.net.sunet.mvp.model.entity.DailyNewsBeforeBean;
import cn.net.sunet.mvp.model.entity.DailyNewsLatestBean;
import cn.net.sunet.mvp.presenter.DailyPresenter;
import cn.net.sunet.ui.zhihu.activity.CalendarActivity;
import cn.net.sunet.ui.zhihu.activity.DailyDetailActivity;
import cn.net.sunet.ui.zhihu.adapter.DailyAdapter;
import cn.net.sunet.utils.RxBus;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: daily fragment
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    String mCurrentDate;
    DailyAdapter mAdapter;
    List<DailyNewsLatestBean.StoriesEntity> mData;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initView() {
        mData = new ArrayList<>();
        mCurrentDate = App.getTomorrow();
        mAdapter = new DailyAdapter(mContext, mData);
        mAdapter.setOnItemClickListener(onItemClickListener);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.dailyNewsLatest();
    }

    private DailyAdapter.OnItemClickListener onItemClickListener = new DailyAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            mPresenter.insertReadToDB(mData.get(position).getId());
            mAdapter.setReadState(position, true);
            if (mAdapter.isBefore())
                mAdapter.notifyItemChanged(position + 1);
            else
                mAdapter.notifyItemChanged(position + 2);
            Intent intent = new Intent(mContext, DailyDetailActivity.class);
            intent.putExtra("id", mData.get(position).getId());
                mContext.startActivity(intent);
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (mCurrentDate.equals(App.getTomorrow()))
                mPresenter.dailyNewsLatest();
            else {
                int year = Integer.valueOf(mCurrentDate.substring(0, 4));
                int month = Integer.valueOf(mCurrentDate.substring(4, 6));
                int day = Integer.valueOf(mCurrentDate.substring(6, 8));
                RxBus.getDefault().post(new CalendarDayEvent(year, month - 1, day));
            }
        }
    };

    @OnClick(R.id.fab_calender)
    void onCalendar() {
        startActivity(new Intent(mContext, CalendarActivity.class));
    }

    @Override
    public void showContent(DailyNewsLatestBean bean) {
        mSwipeRefresh.setRefreshing(false);
        mData = bean.getStories();
        mCurrentDate = String.valueOf(Integer.valueOf(bean.getDate()) + 1);
        mAdapter.addDailyNewLatest(bean);
        mPresenter.stopInterval();
        mPresenter.startInterval();
    }

    @Override
    public void showMoreContent(String date, DailyNewsBeforeBean beforeBean) {
        mSwipeRefresh.setRefreshing(false);
        mPresenter.stopInterval();
        mData = beforeBean.getStories();
        mCurrentDate = String.valueOf(Integer.valueOf(beforeBean.getDate()));
        mAdapter.addDailyBeforeDate(date, beforeBean);
    }

    @Override
    public void doInterval(int currentCount) {
        mAdapter.changeToTopPager(currentCount);
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
        mSwipeRefresh.setRefreshing(false);
    }
}
