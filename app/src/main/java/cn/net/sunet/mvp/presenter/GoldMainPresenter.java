package cn.net.sunet.mvp.presenter;

import javax.inject.Inject;

import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.GoldMainContract;
import cn.net.sunet.mvp.model.db.RealmHelper;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerBean;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerItemBean;
import cn.net.sunet.utils.RxBus;
import cn.net.sunet.utils.RxUtils;
import cn.net.sunet.utils.SharedPreferenceUtils;
import io.realm.RealmList;
import rx.Subscription;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: gold main presenter
 */

public class GoldMainPresenter extends RxPresenter<GoldMainContract.View> implements GoldMainContract.Presenter {
    private RealmHelper mRealmHelper;
    private RealmList<RealmGoldManagerItemBean> mData;

    @Inject
    GoldMainPresenter(RealmHelper realmHelper) {
        this.mRealmHelper = realmHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription subscription = RxBus.getDefault().toObservable(RealmGoldManagerBean.class)
                .compose(RxUtils.<RealmGoldManagerBean>rxSchedulerHelper())
                .subscribe(new Action1<RealmGoldManagerBean>() {
                    @Override
                    public void call(RealmGoldManagerBean bean) {
                        mRealmHelper.updateGoldManagerList(bean);
                        mView.updateTab(bean.getManagerList());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage(), true);
                    }
                });
        addSubscribe(subscription);
    }


    @Override
    public void initManagerList() {
        if (SharedPreferenceUtils.isManagerPoint()) {
            //第一次使用，生成默认的managerList
            initList();
            mRealmHelper.updateGoldManagerList(new RealmGoldManagerBean(mData));
            mView.updateTab(mData);
        } else {
            if (mRealmHelper.getRealGoldManagerList() == null) {
                initList();
                mRealmHelper.updateGoldManagerList(new RealmGoldManagerBean(mData));
            } else
                mData = mRealmHelper.getRealGoldManagerList().getManagerList();
            mView.updateTab(mData);
        }
    }

    @Override
    public void setManagerList() {
        mView.jumpToManager(mRealmHelper.getRealGoldManagerList());
    }

    private void initList() {
        mData = new RealmList<>();
        mData.add(new RealmGoldManagerItemBean(0, true));
        mData.add(new RealmGoldManagerItemBean(1, true));
        mData.add(new RealmGoldManagerItemBean(2, true));
        mData.add(new RealmGoldManagerItemBean(3, true));
        mData.add(new RealmGoldManagerItemBean(4, false));
        mData.add(new RealmGoldManagerItemBean(5, false));
        mData.add(new RealmGoldManagerItemBean(6, false));
        mData.add(new RealmGoldManagerItemBean(7, false));
    }
}
