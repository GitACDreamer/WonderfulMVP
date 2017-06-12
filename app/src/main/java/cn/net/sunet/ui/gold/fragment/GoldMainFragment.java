package cn.net.sunet.ui.gold.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.GoldMainContract;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerBean;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerItemBean;
import cn.net.sunet.mvp.presenter.GoldMainPresenter;
import cn.net.sunet.ui.adapter.CommonPagerAdapter;
import cn.net.sunet.ui.gold.activity.GoldManagerActivity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: gold main fragment
 */

public class GoldMainFragment extends BaseFragment<GoldMainPresenter> implements GoldMainContract.View {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    List<Fragment> fragmentList = null;
    private int mCurIndex = 0;

    public static String[] tabTitles;
    public static String[] tabTitleType;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_gold;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mPresenter.initManagerList();
    }

    @OnClick(R.id.iv_gold_menu)
    public void onClick() {
        mPresenter.setManagerList();
    }

    @Override
    public void updateTab(List<RealmGoldManagerItemBean> items) {
        //tablayout
        tabTitles = getResources().getStringArray(R.array.tabs_gold);
        tabTitleType = getResources().getStringArray(R.array.tabs_gold_type);

        fragmentList.clear();
        mTabLayout.removeAllTabs();
        for (RealmGoldManagerItemBean item : items) {
            if (item.isSelect()) {
                GoldPagerFragment fragment = new GoldPagerFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.IT_GOLD_TYPE, tabTitleType[item.getIndex()]);
                bundle.putString(Constants.IT_GOLD_TYPE_STR, tabTitles[item.getIndex()]);
                mTabLayout.addTab(mTabLayout.newTab().setText(tabTitles[item.getIndex()]));
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }
        }
        CommonPagerAdapter mCommonPagerAdapter = new CommonPagerAdapter(getChildFragmentManager(), fragmentList);
        mViewPager.setAdapter(mCommonPagerAdapter);
        for (RealmGoldManagerItemBean item : items) {
            if (item.isSelect())
                mTabLayout.getTabAt(mCurIndex++).setText(tabTitles[item.getIndex()]);
        }
        mCurIndex = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void jumpToManager(RealmGoldManagerBean bean) {
        Intent intent = new Intent(mContext, GoldManagerActivity.class);
        intent.putExtra(Constants.IT_GOLD_MANAGER, bean);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            mContext.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity).toBundle());
        else
            mContext.startActivity(intent);
    }
}
