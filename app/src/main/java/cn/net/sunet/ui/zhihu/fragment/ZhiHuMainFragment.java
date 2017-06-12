package cn.net.sunet.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.SimpleFragment;
import cn.net.sunet.ui.adapter.CommonPagerAdapter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: zhihu main fragment
 */

public class ZhiHuMainFragment extends SimpleFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    CommonPagerAdapter mCommonPagerAdapter;
    List<Fragment> mFragmentList = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        mFragmentList = new ArrayList<>();

        //tab layout
        String[] tabTitles = getResources().getStringArray(R.array.tabs_zhihu);

        mFragmentList.add(new DailyFragment());
        mFragmentList.add(new ThemeFragment());
        mFragmentList.add(new SectionFragment());
        mFragmentList.add(new HotFragment());
        mCommonPagerAdapter = new CommonPagerAdapter(getChildFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mCommonPagerAdapter);

        for (String tabTitle : tabTitles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle));
        }
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabTitles.length; ++i)
            mTabLayout.getTabAt(i).setText(tabTitles[i]);
    }
}
