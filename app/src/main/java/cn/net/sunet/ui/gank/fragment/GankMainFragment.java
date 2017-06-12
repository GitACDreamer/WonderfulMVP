package cn.net.sunet.ui.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.SimpleFragment;
import cn.net.sunet.event.SearchEvent;
import cn.net.sunet.event.TabSelectedEvent;
import cn.net.sunet.ui.adapter.CommonPagerAdapter;
import cn.net.sunet.utils.RxBus;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: gank main fragment
 */

public class GankMainFragment extends SimpleFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    CommonPagerAdapter mCommonPagerAdapter;
    List<Fragment> fragmentList = null;

    TechnologyFragment androidFragment;
    TechnologyFragment webFragment;
    TechnologyFragment iosFragment;
    GirlFragment girlFragment;

    public static String[] tabTitles = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();

        //tab layout
        tabTitles = getResources().getStringArray(R.array.tabs_gank);

        androidFragment = new TechnologyFragment();
        iosFragment = new TechnologyFragment();
        webFragment = new TechnologyFragment();
        girlFragment = new GirlFragment();

        Bundle androidBundle = new Bundle();
        androidBundle.putString(Constants.ITEM_TYPE, tabTitles[0]);
        androidBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_ANDROID);
        androidFragment.setArguments(androidBundle);

        Bundle iosBundle = new Bundle();
        iosBundle.putString(Constants.ITEM_TYPE, tabTitles[1]);
        iosBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_IOS);
        iosFragment.setArguments(iosBundle);

        Bundle webBundle = new Bundle();
        webBundle.putString(Constants.ITEM_TYPE, tabTitles[2]);
        webBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_WEB);
        webFragment.setArguments(webBundle);

        fragmentList.add(androidFragment);
        fragmentList.add(iosFragment);
        fragmentList.add(webFragment);
        fragmentList.add(girlFragment);
        mCommonPagerAdapter = new CommonPagerAdapter(getChildFragmentManager(), fragmentList);
        mViewPager.setAdapter(mCommonPagerAdapter);

        for (String tabTitle : tabTitles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle));
        }
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabTitles.length; ++i)
            mTabLayout.getTabAt(i).setText(tabTitles[i]);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //重复选择时，滚动到最顶层
                RxBus.getDefault().post(new TabSelectedEvent(tab.getPosition()));
            }
        });
    }

    public void doSearch(String query) {
        switch (mViewPager.getCurrentItem()) {
            case 0:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_ANDROID));
                break;
            case 1:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_IOS));
                break;
            case 2:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_WEB));
                break;
            case 3:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_GIRL));
                break;
        }
    }
}
