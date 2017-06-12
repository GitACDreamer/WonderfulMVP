package cn.net.sunet.base;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: 接口基于Rx的Presenter封装,控制订阅的生命周期
 */

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class RxPresenter<V extends BaseView> implements BasePresenter<V> {

    protected V mView;
    private CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    private void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            unSubscribe();
    }
}
