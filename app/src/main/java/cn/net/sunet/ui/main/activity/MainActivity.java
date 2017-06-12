package cn.net.sunet.ui.main.activity;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.net.sunet.R;
import cn.net.sunet.app.App;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.BaseActivity;
import cn.net.sunet.mvp.contract.MainContract;
import cn.net.sunet.mvp.model.entity.VersionBean;
import cn.net.sunet.mvp.presenter.MainPresenter;
import cn.net.sunet.ui.main.fragment.WeChatNewFragment;
import cn.net.sunet.ui.news.fragment.NewsMainFragment;
import cn.net.sunet.ui.gank.fragment.GankMainFragment;
import cn.net.sunet.ui.gold.fragment.GoldMainFragment;
import cn.net.sunet.ui.main.fragment.CollectionFragment;
import cn.net.sunet.ui.main.fragment.SettingFragment;
import cn.net.sunet.ui.wechat.fragment.WeChatMainFragment;
import cn.net.sunet.ui.zhihu.fragment.ZhiHuMainFragment;
import cn.net.sunet.utils.SharedPreferenceUtils;
import cn.net.sunet.utils.UpdateService;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    private int hideFragment = Constants.TYPE_ZHIHU;
    private int showFragment = Constants.TYPE_ZHIHU;

    private TextView mTvName;     //navigationView上的名字
    private TextView mTvDes;      //navigationView上的描述信息
    private ImageView mIvAccount; //navigationView上的图片

    ZhiHuMainFragment mZhiHuMainFragment;
    WeChatMainFragment mWeChatMainFragment;
    GankMainFragment mGankMainFragment;
    NewsMainFragment mNewsMainFragment;
    GoldMainFragment mGoldMainFragment;
    CollectionFragment mCollectionFragment;
    SettingFragment mSettingFragment;
    //AboutFragment mAboutFragment;
    WeChatNewFragment mAboutFragment ;
    MenuItem mSearchMenuItem = null;
    MenuItem mLastMenuItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //知乎日报
        setToolBar(mToolbar, getString(R.string.menu_zhihu));

        //重置检测版本号的标志位
        SharedPreferenceUtils.setVersionPoint(false);

        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mToggle.syncState();
        mDrawerLayout.addDrawerListener(mToggle);

        mZhiHuMainFragment = new ZhiHuMainFragment();
        mWeChatMainFragment = new WeChatMainFragment();
        mGankMainFragment = new GankMainFragment();
        mNewsMainFragment = new NewsMainFragment();
        mGoldMainFragment = new GoldMainFragment();
        mCollectionFragment = new CollectionFragment();
        mSettingFragment = new SettingFragment();
        //mAboutFragment = new AboutFragment();
        mAboutFragment = new WeChatNewFragment();
        Bundle newsBundle = new Bundle();
        newsBundle.putString(Constants.ITEM_TYPE, "微信新闻");
        newsBundle.putInt(Constants.ITEM_TYPE_CODE, Constants.TYPE_WECHAT_NEWS);
        mAboutFragment.setArguments(newsBundle);
        mLastMenuItem = mNavigationView.getMenu().findItem(R.id.menu_zhihu);
        mLastMenuItem.setChecked(true);
        loadMultipleRootFragment(R.id.fl_main_content, 0, mZhiHuMainFragment, mWeChatMainFragment,
                mGankMainFragment, mNewsMainFragment, mGoldMainFragment, mCollectionFragment,
                mSettingFragment, mAboutFragment);
        mNavigationView.setNavigationItemSelectedListener(onItemSelectedListerner);

        //navigation header
        View headerView = mNavigationView.getHeaderView(0);
        mIvAccount = (ImageView) headerView.findViewById(R.id.iv_nav_header_img);
        mTvName = (TextView) headerView.findViewById(R.id.tv_nav_header_name);
        mTvDes = (TextView) headerView.findViewById(R.id.tv_nav_header_des);
//        mIvAccount.setImageResource(R.mipmap.ic_launcher);
//        mTvName.setText(R.string.app_name);
//        mTvDes.setText(R.string.app_intro);

        //请求存储权限
        mPresenter.externalStorage();

        //请求read phone 权限
        mPresenter.readPhone();

        //检测版本更新
        if (!SharedPreferenceUtils.getVersionPoint() && NetworkUtils.isConnected()) {
            SharedPreferenceUtils.setVersionPoint(true);
            mPresenter.checkVersion(AppUtils.getAppVersionName(mContext));
        }
    }

    @OnClick({R.id.navigation_btn_setting, R.id.navigation_btn_exit})
    public void onClick(View view) {
        if (view.getId() == R.id.navigation_btn_setting) {
            if (mLastMenuItem != null)
                mLastMenuItem.setChecked(false);            //设置被切换的页面为未选中状态
            mDrawerLayout.closeDrawer(GravityCompat.START);
            hideFragment = showFragment;
            showFragment = Constants.TYPE_SETTING;
            showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
            mToolbar.setTitle(R.string.menu_setting);
            hideFragment = showFragment;
        } else {
            mPresenter.exit();
        }
    }

    private NavigationView.OnNavigationItemSelectedListener onItemSelectedListerner = new NavigationView
            .OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_zhihu:
                    showFragment = Constants.TYPE_ZHIHU;
                    mSearchMenuItem.setVisible(false);
                    break;
                case R.id.menu_wechat:
                    showFragment = Constants.TYPE_WECHAT;
                    mSearchMenuItem.setVisible(true);
                    break;
                case R.id.menu_gank:
                    showFragment = Constants.TYPE_GANK;
                    mSearchMenuItem.setVisible(true);
                    break;
                case R.id.menu_funny:
                    showFragment = Constants.TYPE_FUNNY;
                    mSearchMenuItem.setVisible(true);
                    break;
                case R.id.menu_gold:
                    showFragment = Constants.TYPE_GOLD;
                    mSearchMenuItem.setVisible(false);
                    break;
                case R.id.menu_like:
                    showFragment = Constants.TYPE_COLLECTION;
                    mSearchMenuItem.setVisible(false);
                    break;
                case R.id.menu_setting:
                    showFragment = Constants.TYPE_SETTING;
                    mSearchMenuItem.setVisible(false);
                    break;
                case R.id.menu_about:
                    showFragment = Constants.TYPE_ABOUT;
                    mSearchMenuItem.setVisible(false);
                    break;
            }
            //清除首选项被选中（由于backup键引起)
            if (item.getItemId() != R.id.menu_zhihu)
                mNavigationView.getMenu().findItem(R.id.menu_zhihu).setChecked(false);
            if (mLastMenuItem != null)
                mLastMenuItem.setChecked(false); //设置被切换的页面为未选中状态
            mLastMenuItem = item;
            item.setChecked(true);               //设置被选中
            mToolbar.setTitle(item.getTitle());  //设置被选中的工具栏的标题
            mDrawerLayout.closeDrawers();        //选中后关闭导航栏
            //显示隐藏某个fragment
            showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
            hideFragment = showFragment;
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        mSearchMenuItem = menu.findItem(R.id.menu_search);
        mSearchMenuItem.setVisible(false);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(onQueryTextListener);
        }
        return true;
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (showFragment == Constants.TYPE_WECHAT)
                mWeChatMainFragment.doSearch(query);
            else if (showFragment == Constants.TYPE_GANK)
                mGankMainFragment.doSearch(query);
            else if (showFragment == Constants.TYPE_FUNNY)
                mNewsMainFragment.doSearch(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return mZhiHuMainFragment;
            case Constants.TYPE_WECHAT:
                return mWeChatMainFragment;
            case Constants.TYPE_GANK:
                return mGankMainFragment;
            case Constants.TYPE_FUNNY:
                return mNewsMainFragment;
            case Constants.TYPE_GOLD:
                return mGoldMainFragment;
            case Constants.TYPE_COLLECTION:
                return mCollectionFragment;
            case Constants.TYPE_SETTING:
                return mSettingFragment;
            case Constants.TYPE_ABOUT:
                return mAboutFragment;
        }
        return mZhiHuMainFragment;
    }

    @Override
    public void showContent(String msg) {
        LogUtils.e(App.TAG, msg);
        App.getInstance().exitApp();
    }

    @Override
    public void showUpdateDialog(final VersionBean version) {
        StringBuilder content = new StringBuilder("版本号： v");
        content.append(version.getCode());
        content.append("\n");
        content.append("版本大小：");
        content.append(version.getSize());
        content.append("\r\n");
        content.append("更新内容:\r\n");
        content.append("知乎日报：日报、主题、专栏、热门\n");
        content.append("微信精选：微信新闻、奇闻轶事、美女福利\n");
        content.append("稀土掘金：Android、iOS、前端、后端、设计\n");
        content.append("娱乐天地：菜谱查询、周公解梦、雷人笑话、急转弯\n");
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.view_update_item);
        Button submit = (Button) dialog.findViewById(R.id.btn_submit);
        submit.setText(R.string.about_update);
        Button cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        TextView message = (TextView) dialog.findViewById(R.id.info);
        message.setText(content);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUpdateService(version.getUri());
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    public void startUpdateService(String uri) {
        Intent intent = new Intent(mContext, UpdateService.class);
        intent.putExtra("URI", uri);
        startService(intent);
    }

    @Override
    public void showError(String msg, boolean isShow) {
        super.showError(msg, isShow);
    }

    @Override
    public void onBackPressedSupport() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (showFragment != Constants.TYPE_ZHIHU) {
                hideFragment = showFragment;
                showFragment = Constants.TYPE_ZHIHU;
                showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
                mToolbar.setTitle(R.string.menu_zhihu);
                mNavigationView.getMenu().findItem(R.id.menu_zhihu).setChecked(true);
                if (mLastMenuItem != null)
                    mLastMenuItem.setChecked(false);
                hideFragment = showFragment;
            } else {
                super.onBackPressedSupport();
            }
        }
    }
}
