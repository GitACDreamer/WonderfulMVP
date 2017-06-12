package cn.net.sunet.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.ThemeDetailContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.db.RealmHelper;
import cn.net.sunet.mvp.model.entity.DailyThemeDetailBean;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: theme detail presenter
 */

public class ThemeDetailPresenter extends RxPresenter<ThemeDetailContract.View> implements ThemeDetailContract
        .Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;

    @Inject
    ThemeDetailPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.mRealmHelper = realmHelper;
    }

    @Override
    public void themeDetail(int id) {
        Subscription rxSubscription = mRetrofitHelper.dailyThemeDetail(id)
                .compose(RxUtils.<DailyThemeDetailBean>rxSchedulerHelper())
                .map(new Func1<DailyThemeDetailBean, DailyThemeDetailBean>() {   //设置阅读状态
                    @Override
                    public DailyThemeDetailBean call(DailyThemeDetailBean bean) {
                        List<DailyThemeDetailBean.StoriesEntity> entities = bean.getStories();
                        for (DailyThemeDetailBean.StoriesEntity entity : entities) {
                            entity.setReadState(mRealmHelper.queryReadState(entity.getId()));
                        }
                        return bean;
                    }
                }).subscribe(new Action1<DailyThemeDetailBean>() {
                    @Override
                    public void call(DailyThemeDetailBean bean) {
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
