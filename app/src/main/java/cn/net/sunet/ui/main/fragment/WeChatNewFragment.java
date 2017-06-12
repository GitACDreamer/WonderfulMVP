package cn.net.sunet.ui.main.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.WeChatContract;
import cn.net.sunet.mvp.model.entity.WeChatBean;
import cn.net.sunet.mvp.presenter.WeChatPresenter;
import cn.net.sunet.ui.gank.activity.TechnologyDetailActivity;
import cn.net.sunet.ui.main.adapter.PullRVAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: wechat news fragment
 */

public class WeChatNewFragment extends BaseFragment<WeChatPresenter> implements WeChatContract.View, BaseQuickAdapter
        .OnItemClickListener {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;

    PullRVAdapter adapter;
    List<WeChatBean> mData;

    int mType;
    boolean isLoadingMore = false;
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
        mType = getArguments().getInt(Constants.ITEM_TYPE_CODE);
        adapter = new PullRVAdapter(mActivity, mRecyclerView, mData);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addOnScrollListener(onScrollListener);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mRecyclerView.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                closeable.smoothCloseMenu();
            }
        });
        mRecyclerView.setAdapter(adapter);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mPresenter.wechatData(mType, false);
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.wechatData(mType, false);
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
            if (lastItem >= totalItem - 2 && dy > 0) {  //还剩余2个时加载更多
                if (!isLoadingMore) {
                    isLoadingMore = true;
                    mPresenter.wechatData(mType, true);
                }
            }
        }
    };

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = 68;
                SwipeMenuItem stockOutRequestItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.btn_grey_selector)
                        .setText("编辑") // 文字。
                        .setTextColor(Color.WHITE) // 文字颜色。
                        .setTextSize(18) // 文字大小。
                        .setWidth(width)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                // 添加一个按钮到左右侧侧菜单。
                swipeRightMenu.addMenuItem(stockOutRequestItem);
                swipeLeftMenu.addMenuItem(stockOutRequestItem);
                SwipeMenuItem detailItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.btn_grey_selector)
                        .setText("删除") // 文字。
                        .setTextColor(Color.WHITE)     // 文字颜色。
                        .setTextSize(18) // 文字大小。
                        .setWidth(width)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                swipeRightMenu.addMenuItem(detailItem);
                swipeLeftMenu.addMenuItem(detailItem);
            }
    };
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoTeachDetail(mData.get(position).getPicUrl(),
                mData.get(position).getTitle(),
                mData.get(position).getUrl(),
                mData.get(position).getPicUrl(),
                Constants.TYPE_WECHAT);
    }

    private void gotoTeachDetail(String id, String title, String url, String imgUrl, int type) {
        TechnologyDetailActivity.launch(new TechnologyDetailActivity.Builder()
                .setContext(mContext)
                .setId(String.valueOf(id))
                .setTitle(title)
                .setUrl(url)
                .setImgUrl(imgUrl)
                .setType(type));
    }

    @Override
    public void showContent(List<WeChatBean> beans, boolean isMore) {
        mSwipeRefresh.setRefreshing(false);
        if (beans != null) {
            if (!isMore)
                mData.clear();
            else {
                isLoadingMore = false;
                adapter.setHeaderAndEmptyView(false);
            }
            mData.addAll(beans);
            adapter.setNewData(mData);
            adapter.notifyDataSetChanged();
        }
    }

    //重复点击tab时，自动滚动到top
    @Override
    public void onTabSelectedEvent(int type) {
        if (type == Constants.TYPE_WECHAT_BEAUTY)
            return;
        if (mIsTop) {
            mSwipeRefresh.setRefreshing(true);
            mPresenter.wechatData(mType, false);
        } else
            mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
        adapter.setHeaderAndEmptyView(true);
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();  //取消订阅
        super.onDestroyView();
    }
}
