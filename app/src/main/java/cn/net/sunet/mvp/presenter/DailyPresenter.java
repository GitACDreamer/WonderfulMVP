package cn.net.sunet.mvp.presenter;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cn.net.sunet.app.App;
import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.event.CalendarDayEvent;
import cn.net.sunet.mvp.contract.DailyContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.db.RealmHelper;
import cn.net.sunet.mvp.model.entity.DailyNewsBeforeBean;
import cn.net.sunet.mvp.model.entity.DailyNewsLatestBean;
import cn.net.sunet.utils.RxBus;
import cn.net.sunet.utils.RxUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: daily presenter
 */

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;
    private Subscription mIntervalSubscription;
    private static final int INTERVAL_INSTANCE = 6;
    private int topCount = 0;
    private int currentTopCount = 0;

    @Inject
    DailyPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.mRealmHelper = realmHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(CalendarDayEvent.class)
                .compose(RxUtils.<CalendarDayEvent>rxSchedulerHelper())
                .map(new Func1<CalendarDayEvent, String>() {
                    @Override
                    public String call(CalendarDayEvent calendarDayEvent) {
                        mView.showLoading();
                        StringBuilder builder = new StringBuilder();
                        String year = String.valueOf(calendarDayEvent.getYear());
                        String month = String.valueOf(calendarDayEvent.getMonth() + 1);
                        String day = String.valueOf(calendarDayEvent.getDay() + 1);
                        if (month.length() < 2)
                            month = "0" + month;
                        if (day.length() < 2)
                            day = "0" + day;
                        return builder.append(year).append(month).append(day).toString();
                    }
                }).filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if (s.equals(App.getTomorrow())) {
                            dailyNewsLatest();
                            return false;
                        }
                        return true;
                    }
                }).observeOn(Schedulers.io()) //网络请求，切换到IO线程
                .flatMap(new Func1<String, Observable<DailyNewsBeforeBean>>() {
                    @Override
                    public Observable<DailyNewsBeforeBean> call(String date) {
                        return mRetrofitHelper.dailyNewsBefore(date);
                    }
                }).observeOn(AndroidSchedulers.mainThread())    //切换到主线程，显示结果
                .map(new Func1<DailyNewsBeforeBean, DailyNewsBeforeBean>() { //设置阅读状态
                    @Override
                    public DailyNewsBeforeBean call(DailyNewsBeforeBean beforeBean) {
                        List<DailyNewsLatestBean.StoriesEntity> entities = beforeBean.getStories();
                        for (DailyNewsLatestBean.StoriesEntity entity : entities) {
                            entity.setReadState(mRealmHelper.queryReadState(entity.getId()));
                        }
                        return beforeBean;
                    }
                }).subscribe(new Action1<DailyNewsBeforeBean>() {
                    @Override
                    public void call(DailyNewsBeforeBean beforeBean) {
                        mView.dismissLoading();
                        int year = Integer.valueOf(beforeBean.getDate().substring(0, 4));
                        int month = Integer.valueOf(beforeBean.getDate().substring(4, 6));
                        int day = Integer.valueOf(beforeBean.getDate().substring(6, 8));
                        mView.showMoreContent(String.format(Locale.CHINA, "%d年%d月%d日", year, month, day), beforeBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        registerEvent();
                        mView.dismissLoading();
                        mView.showError(throwable.getMessage(), true);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void dailyNewsLatest() {
        Subscription rxSubscription = mRetrofitHelper.dailyNewsLatest()
                .compose(RxUtils.<DailyNewsLatestBean>rxSchedulerHelper())
                .map(new Func1<DailyNewsLatestBean, DailyNewsLatestBean>() {   //设置阅读状态
                    @Override
                    public DailyNewsLatestBean call(DailyNewsLatestBean dailyNewsLatestBean) {
                        List<DailyNewsLatestBean.StoriesEntity> entities = dailyNewsLatestBean.getStories();
                        for (DailyNewsLatestBean.StoriesEntity entity : entities) {
                            entity.setReadState(mRealmHelper.queryReadState(entity.getId()));
                        }
                        return dailyNewsLatestBean;
                    }
                }).subscribe(new Action1<DailyNewsLatestBean>() {
                    @Override
                    public void call(DailyNewsLatestBean dailyNewsLatestBean) {
                        topCount = dailyNewsLatestBean.getTop_stories().size();
                        mView.dismissLoading();
                        mView.showContent(dailyNewsLatestBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.dismissLoading();
                        mView.showError(throwable.getMessage(), true);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void dailyNewsBefore(String date) {
        Subscription rxSubscription = mRetrofitHelper.dailyNewsBefore(date)
                .compose(RxUtils.<DailyNewsBeforeBean>rxSchedulerHelper())
                .map(new Func1<DailyNewsBeforeBean, DailyNewsBeforeBean>() {
                    @Override
                    public DailyNewsBeforeBean call(DailyNewsBeforeBean beforeBean) {
                        List<DailyNewsLatestBean.StoriesEntity> entities = beforeBean.getStories();
                        for (DailyNewsLatestBean.StoriesEntity entity : entities) {
                            entity.setReadState(mRealmHelper.queryReadState(entity.getId()));
                        }
                        return beforeBean;
                    }
                }).subscribe(new Action1<DailyNewsBeforeBean>() {
                    @Override
                    public void call(DailyNewsBeforeBean beforeBean) {
                        int year = Integer.valueOf(beforeBean.getDate().substring(0, 4));
                        int month = Integer.valueOf(beforeBean.getDate().substring(4, 6));
                        int day = Integer.valueOf(beforeBean.getDate().substring(6, 8));
                        mView.dismissLoading();
                        mView.showMoreContent(String.format(Locale.CHINA, "%d年%d月%d日", year, month, day), beforeBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.dismissLoading();
                        mView.showError(throwable.getMessage(), true);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void startInterval() {
        mIntervalSubscription = Observable.interval(INTERVAL_INSTANCE, TimeUnit.SECONDS)
                .compose(RxUtils.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (currentTopCount == topCount)
                            currentTopCount = 0;
                        mView.doInterval(currentTopCount);
                    }
                });
        addSubscribe(mIntervalSubscription);
    }

    @Override
    public void stopInterval() {
        if (mIntervalSubscription != null)
            mIntervalSubscription.unsubscribe();
    }

    @Override
    public void insertReadToDB(int id) {
        mRealmHelper.insertReadState(id);
    }
}
