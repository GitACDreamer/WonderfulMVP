package cn.net.sunet.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.SectionDetailContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.db.RealmHelper;
import cn.net.sunet.mvp.model.entity.DailySectionDetailBean;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: section detail presenter
 */

public class SectionDetailPresenter extends RxPresenter<SectionDetailContract.View> implements SectionDetailContract
        .Presenter {
    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;

    @Inject
    SectionDetailPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.mRealmHelper = realmHelper;
    }

    @Override
    public void dailySectionDetail(int id) {
        Subscription rxSubscription = mRetrofitHelper.dailySectionDetail(id)
                .compose(RxUtils.<DailySectionDetailBean>rxSchedulerHelper())
                .map(new Func1<DailySectionDetailBean, DailySectionDetailBean>() {
                    @Override
                    public DailySectionDetailBean call(DailySectionDetailBean bean) {
                        List<DailySectionDetailBean.StoriesEntity> entities = bean.getStories();
                        for (DailySectionDetailBean.StoriesEntity entity : entities) {
                            entity.setReadState(mRealmHelper.queryReadState(entity.getId()));
                        }
                        return bean;
                    }
                })
                .subscribe(new Action1<DailySectionDetailBean>() {
                    @Override
                    public void call(DailySectionDetailBean bean) {
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
