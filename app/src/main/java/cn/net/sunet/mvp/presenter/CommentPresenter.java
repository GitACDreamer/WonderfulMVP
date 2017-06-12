package cn.net.sunet.mvp.presenter;

import javax.inject.Inject;

import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.CommentContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.DailyCommentBean;
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

public class CommentPresenter extends RxPresenter<CommentContract.View> implements CommentContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    public static final int SHORT_COMMENT = 0;
    @Inject
    CommentPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void dailyComment(int id, int commentKind) {
        if (commentKind == SHORT_COMMENT) {
            Subscription rxSubscription = mRetrofitHelper.dailyShortComment(id)
                    .compose(RxUtils.<DailyCommentBean>rxSchedulerHelper())
                    .subscribe(new Action1<DailyCommentBean>() {
                        @Override
                        public void call(DailyCommentBean bean) {
                            mView.showContent(bean);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mView.showError(throwable.getMessage(), true);
                        }
                    });
            addSubscribe(rxSubscription);
        } else {
            Subscription rxSubscription = mRetrofitHelper.dailyLongComment(id)
                    .compose(RxUtils.<DailyCommentBean>rxSchedulerHelper())
                    .subscribe(new Action1<DailyCommentBean>() {
                        @Override
                        public void call(DailyCommentBean bean) {
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
}
