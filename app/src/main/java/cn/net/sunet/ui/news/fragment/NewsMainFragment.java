package cn.net.sunet.ui.news.fragment;

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
import cn.net.sunet.ui.wechat.fragment.WeChatFragment;
import cn.net.sunet.utils.RxBus;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/2/20
   Modify: 2017/2/20
 * Description: news main fragment
 */

public class NewsMainFragment extends SimpleFragment {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    CommonPagerAdapter mCommonPagerAdapter;
    List<Fragment> fragmentList = null;

    WeChatFragment socialFragment;
    WeChatFragment domesticFragment;
    WeChatFragment internationalFragment;
    WeChatFragment funnyFragment;

    public static String[] tabTitles = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();

        //tab layout
        tabTitles = getResources().getStringArray(R.array.tabs_news);

        socialFragment = new WeChatFragment();
        domesticFragment = new WeChatFragment();
        internationalFragment = new WeChatFragment();
        funnyFragment = new WeChatFragment();

        Bundle socialBundle = new Bundle();
        socialBundle.putString(Constants.ITEM_TYPE, tabTitles[0]);
        socialBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_NEWS_SOCIAL);
        socialFragment.setArguments(socialBundle);

        Bundle domesticBundle = new Bundle();
        domesticBundle.putString(Constants.ITEM_TYPE, tabTitles[1]);
        domesticBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_NEWS_DOMESTIC);
        domesticFragment.setArguments(domesticBundle);

        Bundle internationalBundle = new Bundle();
        internationalBundle.putString(Constants.ITEM_TYPE, tabTitles[2]);
        internationalBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_NEWS_INTERNATIONAL);
        internationalFragment.setArguments(internationalBundle);

        Bundle funnyBundle = new Bundle();
        funnyBundle.putString(Constants.ITEM_TYPE, tabTitles[3]);
        funnyBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_NEWS_FUNNY);
        funnyFragment.setArguments(funnyBundle);

        fragmentList.add(socialFragment);
        fragmentList.add(domesticFragment);
        fragmentList.add(internationalFragment);
        fragmentList.add(funnyFragment);

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
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_NEWS_SOCIAL));
                break;
            case 1:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_NEWS_DOMESTIC));
                break;
            case 2:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_NEWS_INTERNATIONAL));
                break;
            case 3:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_NEWS_FUNNY));
                break;
        }
    }
}
