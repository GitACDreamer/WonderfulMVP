package cn.net.sunet.mvp.presenter;

import javax.inject.Inject;

import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.ThemeContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.DailyThemeBean;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: theme presenter
 */

public class ThemePresenter extends RxPresenter<ThemeContract.View> implements ThemeContract.Presenter {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    ThemePresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void dailyTheme() {
        Subscription rxSubscription = mRetrofitHelper.dailyThemes()
                .compose(RxUtils.<DailyThemeBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyThemeBean>() {
                    @Override
                    public void call(DailyThemeBean bean) {
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
