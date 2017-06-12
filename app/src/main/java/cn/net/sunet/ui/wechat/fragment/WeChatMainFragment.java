package cn.net.sunet.ui.wechat.fragment;

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
 * Date:   2017/1/22
   Modify: 2017/1/22
 * Description: wechat main fragment
 */

public class WeChatMainFragment extends SimpleFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    CommonPagerAdapter mCommonPagerAdapter;
    List<Fragment> fragmentList = null;

    WeChatFragment newsFragment;
    WeChatFragment fansFragment;
    BeautyFragment beautyFragment;

    public static String[] tabTitles = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();

        //tab layout
        tabTitles = getResources().getStringArray(R.array.tabs_wechats);

        newsFragment = new WeChatFragment();
        fansFragment = new WeChatFragment();
        beautyFragment = new BeautyFragment();

        Bundle newsBundle = new Bundle();
        newsBundle.putString(Constants.ITEM_TYPE, tabTitles[0]);
        newsBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_WECHAT_NEWS);
        newsFragment.setArguments(newsBundle);

        Bundle fansBundle = new Bundle();
        fansBundle.putString(Constants.ITEM_TYPE, tabTitles[1]);
        fansBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_WECHAT_FANS);
        fansFragment.setArguments(fansBundle);

        Bundle beautyBundle = new Bundle();
        beautyBundle.putString(Constants.ITEM_TYPE, tabTitles[2]);
        beautyBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_WECHAT_BEAUTY);
        beautyFragment.setArguments(beautyBundle);

        fragmentList.add(newsFragment);
        fragmentList.add(fansFragment);
        fragmentList.add(beautyFragment);
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
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_WECHAT_NEWS));
                break;
            case 1:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_WECHAT_FANS));
                break;
            case 2:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_WECHAT_BEAUTY));
                break;
        }
    }
}
