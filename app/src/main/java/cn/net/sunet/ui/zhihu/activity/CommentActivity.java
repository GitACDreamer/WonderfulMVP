package cn.net.sunet.ui.zhihu.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.SimpleActivity;
import cn.net.sunet.ui.adapter.CommonPagerAdapter;
import cn.net.sunet.ui.zhihu.fragment.CommentFragment;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/4
   Modify: 2017/1/4
 * Description: comment activity
 */

public class CommentActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tab_comment)
    TabLayout mTabLayout;
    @BindView(R.id.vp_comment)
    ViewPager mViewPager;

    CommonPagerAdapter mCommentAdapter;
    List<Fragment> mFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        int allNum = getIntent().getExtras().getInt("allNum");
        int shortNum = getIntent().getExtras().getInt("shortNum");
        int longNum = getIntent().getExtras().getInt("longNum");
        int id = getIntent().getExtras().getInt("id");
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        setToolBar(mToolbar, String.format(Locale.CHINA, "%d条评论", allNum));

        CommentFragment shortFragment = new CommentFragment();
        Bundle shortBundle = new Bundle();
        shortBundle.putInt("id", id);
        shortBundle.putInt("kind", 0);
        shortFragment.setArguments(shortBundle);

        CommentFragment longFragment = new CommentFragment();
        Bundle longBundle = new Bundle();
        longBundle.putInt("id", id);
        longBundle.putInt("kind", 1);
        longFragment.setArguments(longBundle);
        mFragments.add(shortFragment);
        mFragments.add(longFragment);
        mCommentAdapter = new CommonPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mCommentAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(String.format(Locale.CHINA, "短评论(%d)", shortNum)));
        mTabLayout.addTab(mTabLayout.newTab().setText(String.format(Locale.CHINA, "长评论(%d)", longNum)));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(String.format(Locale.CHINA, "短评论(%d)", shortNum));
        mTabLayout.getTabAt(1).setText(String.format(Locale.CHINA, "长评论(%d)", longNum));
    }
}
