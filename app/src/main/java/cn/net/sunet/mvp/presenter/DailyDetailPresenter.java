package cn.net.sunet.mvp.presenter;

import com.blankj.utilcode.utils.TimeUtils;

import javax.inject.Inject;

import cn.net.sunet.app.Constants;
import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.DailyDetailContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.db.RealmHelper;
import cn.net.sunet.mvp.model.entity.DailyDetailBean;
import cn.net.sunet.mvp.model.entity.DailyStoryExtraBean;
import cn.net.sunet.mvp.model.entity.RealmCollectionBean;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: daily detail presenter
 */

public class DailyDetailPresenter extends RxPresenter<DailyDetailContract.View> implements DailyDetailContract
        .Presenter {
    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;
    private DailyDetailBean mData;

    @Inject
    DailyDetailPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.mRealmHelper = realmHelper;
    }

    @Override
    public void dailyDetail(int id) {
        Subscription rxSubscription = mRetrofitHelper.dailyDetail(id)
                .compose(RxUtils.<DailyDetailBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyDetailBean>() {
                    @Override
                    public void call(DailyDetailBean bean) {
                        mData = bean;
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
    public void dailyDetailExtra(int id) {
        Subscription rxSubscription = mRetrofitHelper.dailyDetailExtra(id)
                .compose(RxUtils.<DailyStoryExtraBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyStoryExtraBean>() {
                    @Override
                    public void call(DailyStoryExtraBean dailyStoryExtraBean) {
                        mView.showExtraInfo(dailyStoryExtraBean);
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
    public void insertCollection() {
        if (mData != null) {
            RealmCollectionBean bean = new RealmCollectionBean();
            bean.setId(mData.getId());
            bean.setImage(mData.getImage());
            bean.setTitle(mData.getTitle());
            bean.setType(Constants.TYPE_ZHIHU);
            bean.setTime(TimeUtils.getNowTimeMills());
            mRealmHelper.insertCollection(bean);
        } else
            mView.showError("收藏失败！", true);
    }

    @Override
    public void deleteCollection() {
        if (mData != null)
            mRealmHelper.deleteCollection(mData.getId());
        else
            mView.showError("删除失败", true);
    }

    @Override
    public void queryCollection(int id) {
        mView.setCollectionButtonState(mRealmHelper.queryCollection(String.valueOf(id)));
    }
}
