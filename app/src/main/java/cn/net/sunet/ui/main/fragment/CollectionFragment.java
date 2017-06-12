package cn.net.sunet.ui.main.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.CollectionContract;
import cn.net.sunet.mvp.model.entity.RealmCollectionBean;
import cn.net.sunet.mvp.presenter.CollectionPresenter;
import cn.net.sunet.ui.main.adapter.CollectionAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: 收藏fragment
 */

public class CollectionFragment extends BaseFragment<CollectionPresenter> implements CollectionContract.View {
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;

    private CollectionAdapter mAdapter;
    List<RealmCollectionBean> mData;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collection;
    }

    @Override
    protected void initView() {
        mData = new ArrayList<>();
        mAdapter = new CollectionAdapter(mContext, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLongPressDragEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(onSwipeMenuItemClickListener);
        mRecyclerView.setOnItemMoveListener(onItemMoveListener);
        mPresenter.collection();
    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.list_image_size);
            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                    .setBackgroundDrawable(R.color.colorAccent)
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(18) // 文字大小。
                    .setWidth(width)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            // 添加一个按钮到左右侧侧菜单。
            swipeRightMenu.addMenuItem(deleteItem);
            swipeLeftMenu.addMenuItem(deleteItem);
        }
    };

    private OnSwipeMenuItemClickListener onSwipeMenuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();
            mPresenter.deleteCollection(mData.get(adapterPosition).getId());
            mData.remove(adapterPosition);
            mAdapter.notifyItemRemoved(adapterPosition);
        }
    };
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (mData != null) {
                boolean isPlus = fromPosition < toPosition;
                mPresenter.updateCollection(mData.get(fromPosition).getId(), mData.get(toPosition).getTime(), isPlus);
                //更新数据源中的数据的位置
                Collections.swap(mData, fromPosition, toPosition);
                //更新UI中item的位置
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }
            return false;
        }

        @Override
        public void onItemDismiss(int position) {

        }
    };

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mRecyclerView.smoothCloseMenu();
        if (!hidden) {
            mPresenter.collection();
        }
    }

    @Override
    public void showContent(List<RealmCollectionBean> beans) {
        mData.clear();
        mData.addAll(beans);
        mAdapter.notifyDataSetChanged();
    }
}
