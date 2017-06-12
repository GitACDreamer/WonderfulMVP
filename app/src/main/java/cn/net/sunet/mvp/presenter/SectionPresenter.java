package cn.net.sunet.mvp.presenter;

import javax.inject.Inject;

import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.SectionContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.DailySectionsBean;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: section presenter
 */

public class SectionPresenter extends RxPresenter<SectionContract.View> implements SectionContract.Presenter {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    SectionPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void dailySections() {
        Subscription rxSubscription = mRetrofitHelper.dailySections()
                .compose(RxUtils.<DailySectionsBean>rxSchedulerHelper())
                .subscribe(new Action1<DailySectionsBean>() {
                    @Override
                    public void call(DailySectionsBean bean) {
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
}
