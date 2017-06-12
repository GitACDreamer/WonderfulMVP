package cn.net.sunet.mvp.presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.WelcomeContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.WelcomeBean;
import cn.net.sunet.utils.RxUtils;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: welcome presenter
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {
    private static final int COUNT_DOWN_TIME = 2200;
    private static final String RES = "1080*1776";

    private RetrofitHelper mRetrofitHelper;

    @Inject
    WelcomePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void welcome() {
        Subscription rxSubscription = mRetrofitHelper.welcome(RES)
                .compose(RxUtils.<WelcomeBean>rxSchedulerHelper())
                .subscribe(new Action1<WelcomeBean>() {
                    @Override
                    public void call(WelcomeBean bean) {
                        mView.showContent(bean);
                        startCountDown();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage(), true);
                        mView.jumpToLogin();
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void startCountDown() {
        Subscription rxSubscription = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtils.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.jumpToLogin();
                    }
                });
        addSubscribe(rxSubscription);
    }
}
