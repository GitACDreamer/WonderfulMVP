package cn.net.sunet.mvp.presenter;

import javax.inject.Inject;

import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.CollectionContract;
import cn.net.sunet.mvp.model.db.RealmHelper;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/10
   Modify: 2017/1/10
 * Description: collection presenter
 */

public class CollectionPresenter extends RxPresenter<CollectionContract.View> implements CollectionContract.Presenter {
    private RealmHelper mRealmHelper;

    @Inject
    CollectionPresenter(RealmHelper mRealmHelper) {
        this.mRealmHelper = mRealmHelper;
    }

    @Override
    public void collection() {
        mView.showContent(mRealmHelper.getAllCollections());
    }

    @Override
    public void deleteCollection(String id) {
        mRealmHelper.deleteCollection(id);
    }

    @Override
    public void updateCollection(String id, long time, boolean isPlus) {
        mRealmHelper.updateCollection(id, time, isPlus);
    }
}
