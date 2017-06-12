package cn.net.sunet.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.net.sunet.base.GankBaseResponse;
import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.event.TabSelectedEvent;
import cn.net.sunet.mvp.contract.GirlContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.GankItemBean;
import cn.net.sunet.utils.RxBus;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: girl presenter
 */

public class GirlPresenter extends RxPresenter<GirlContract.View> implements GirlContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    public static final int NUM_OF_PAGE = 20;
    private int curPage = 1;

    @Inject
    GirlPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
        registerTabSelectEvent();
    }

    private void registerTabSelectEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(TabSelectedEvent.class)
                .compose(RxUtils.<TabSelectedEvent>rxSchedulerHelper())
                .subscribe(new Action1<TabSelectedEvent>() {
                    @Override
                    public void call(TabSelectedEvent tabSelectedEvent) {
                        mView.onTabSelectedEvent();
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
    public void girlData(final boolean isMore) {
        if (!isMore)
            curPage = 1;
        else
            ++curPage;
        Subscription rxSubscription = mRetrofitHelper.girlList(NUM_OF_PAGE, curPage)
                .compose(RxUtils.<GankBaseResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<GankItemBean>>handleGankResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> beans) {
                        mView.showContent(beans, isMore);
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
