package cn.net.sunet.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.HotContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.db.RealmHelper;
import cn.net.sunet.mvp.model.entity.DailyHotBean;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: section presenter
 */

public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;

    @Inject
    HotPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.mRealmHelper = realmHelper;
    }

    @Override
    public void dailyHot() {
        Subscription rxSubscription = mRetrofitHelper.dailyHot()
                .compose(RxUtils.<DailyHotBean>rxSchedulerHelper())
                .map(new Func1<DailyHotBean, DailyHotBean>() {
                    @Override
                    public DailyHotBean call(DailyHotBean dailyHotBean) {
                        List<DailyHotBean.RecentEntity> entities = dailyHotBean.getRecent();
                        for (DailyHotBean.RecentEntity entity : entities) {
                            entity.setReadState(mRealmHelper.queryReadState(entity.getNews_id()));
                        }
                        return dailyHotBean;
                    }
                })
                .subscribe(new Action1<DailyHotBean>() {
                    @Override
                    public void call(DailyHotBean bean) {
                        mView.showContent(bean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage(), true);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void insertReadToDB(int id) {
        mRealmHelper.insertReadState(id);
    }
}
