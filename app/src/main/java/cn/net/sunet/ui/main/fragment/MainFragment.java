package cn.net.sunet.ui.main.fragment;

import cn.net.sunet.base.BaseFragment;
import cn.net.sunet.mvp.contract.MainContract;
import cn.net.sunet.mvp.model.entity.VersionBean;
import cn.net.sunet.mvp.presenter.MainPresenter;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: main fragment
 */

public class MainFragment extends BaseFragment<MainPresenter> implements MainContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showUpdateDialog(VersionBean version) {

    }

    @Override
    public void showError(String msg, boolean isShow) {

    }
}
