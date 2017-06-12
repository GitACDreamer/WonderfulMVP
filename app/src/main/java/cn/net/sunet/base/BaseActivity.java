package cn.net.sunet.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.net.sunet.R;
import cn.net.sunet.app.App;
import cn.net.sunet.di.component.ActivityComponent;
import cn.net.sunet.di.component.DaggerActivityComponent;
import cn.net.sunet.di.module.ActivityModule;
import me.yokeyword.fragmentation.SupportActivity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: MVP的activity基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView {
    @Inject
    protected T mPresenter;
    protected Activity mContext;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog = null;
    private boolean bDoubleBackToExitPressedOnce;
    private Handler mHandle = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            bDoubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        App.getInstance().addActivity(this);
        initView();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    @Override
    public void showLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) return;
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle("加载数据");
        mProgressDialog.setIcon(R.mipmap.ic_state_loading);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("请求网络中...");
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showError(String msg, boolean isShow) {
        ToastUtils.showShortToast(msg);
    }

    @Override
    public void onBackPressedSupport() {
        if (bDoubleBackToExitPressedOnce) {
            ToastUtils.cancelToast();
            if (mHandle != null)
                mHandle.removeCallbacks(mRunnable);
            App.getInstance().exitApp();
        }
        bDoubleBackToExitPressedOnce = true;
        ToastUtils.showShortToast("再按一次退出程序");
        mHandle.postDelayed(mRunnable, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtils.cancelToast();
        if (mPresenter != null)
            mPresenter.detachView();
        if (mUnBinder != null)
            mUnBinder.unbind();
        App.getInstance().removeActivity(this);
    }

    protected abstract void initInject();

    protected abstract int getLayout();

    protected abstract void initView();
}
