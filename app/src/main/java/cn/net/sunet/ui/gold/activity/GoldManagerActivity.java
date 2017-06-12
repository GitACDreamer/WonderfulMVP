package cn.net.sunet.ui.gold.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.Collections;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.SimpleActivity;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerBean;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerItemBean;
import cn.net.sunet.ui.gold.adapter.GoldManagerAdapter;
import cn.net.sunet.utils.RxBus;
import io.realm.RealmList;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/20
   Modify: 2017/1/20
 * Description: gold manager activity
 */

public class GoldManagerActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;

    RealmList<RealmGoldManagerItemBean> mData;
    GoldManagerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gold_manager;
    }

    @Override
    protected void initView() {
        //mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        setToolBar(mToolbar, "首页特别展示");

        mData = ((RealmGoldManagerBean) getIntent().getParcelableExtra(Constants.IT_GOLD_MANAGER)).getManagerList();
        mAdapter = new GoldManagerAdapter(mContext, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLongPressDragEnabled(true);
        mRecyclerView.setOnItemMoveListener(new OnItemMoveListener() {
            @Override
            public boolean onItemMove(int fromPosition, int toPosition) {
                if (mData != null) {
                    Collections.swap(mData, fromPosition, toPosition);
                    mAdapter.notifyItemMoved(fromPosition, toPosition);
                    return true;
                }
                return false;
            }

            @Override
            public void onItemDismiss(int position) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().post(new RealmGoldManagerBean(mData));
    }
}
