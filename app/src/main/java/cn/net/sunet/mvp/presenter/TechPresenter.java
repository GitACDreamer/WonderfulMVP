package cn.net.sunet.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.net.sunet.app.Constants;
import cn.net.sunet.base.GankBaseResponse;
import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.event.SearchEvent;
import cn.net.sunet.event.TabSelectedEvent;
import cn.net.sunet.mvp.contract.TechContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.GankItemBean;
import cn.net.sunet.mvp.model.entity.GankSearchItemBean;
import cn.net.sunet.ui.gank.fragment.GankMainFragment;
import cn.net.sunet.utils.RxBus;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: technology presenter
 */

public class TechPresenter extends RxPresenter<TechContract.View> implements TechContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    private static final int NUM_OF_PAGE = 20;
    private int curPage = 1;
    private String queryStr = null;
    private String curTech = GankMainFragment.tabTitles[0];
    private int curType = Constants.TYPE_ANDROID;

    @Inject
    TechPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
        registerSearchEvent();
        registerTabSelectEvent();
    }

    private void registerSearchEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(SearchEvent.class)
                .compose(RxUtils.<SearchEvent>rxSchedulerHelper())
                .filter(new Func1<SearchEvent, Boolean>() {
                    @Override
                    public Boolean call(SearchEvent searchEvent) {
                        return searchEvent.getType() == curType;
                    }
                }).map(new Func1<SearchEvent, String>() {
                    @Override
                    public String call(SearchEvent searchEvent) {
                        return searchEvent.getQuery();
                    }
                }).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        queryStr = s;
                        searchTechData(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage(), true);
                    }
                });
        addSubscribe(rxSubscription);
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
    public void gankData(String tech, int type) {
        queryStr = null;
        curPage = 1;
        curTech = tech;
        curType = type;
        Subscription rxSubscription = mRetrofitHelper.techList(tech, NUM_OF_PAGE, curPage)
                .compose(RxUtils.<GankBaseResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<GankItemBean>>handleGankResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> beans) {
                        mView.showContent(beans, false);
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
    public void gankMoreData(String tech) {
        if (queryStr != null) {
            searchTechData(true);
            return;
        }
        Subscription rxSubscription = mRetrofitHelper.techList(tech, NUM_OF_PAGE, ++curPage)
                .compose(RxUtils.<GankBaseResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<GankItemBean>>handleGankResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> beans) {
                        mView.showContent(beans, true);
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
    public void girlImage() {
        Subscription rxSubscription = mRetrofitHelper.randomGirl(1)
                .compose(RxUtils.<GankBaseResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<GankItemBean>>handleGankResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> gankItemBeen) {
                        mView.showGirlContent(gankItemBeen.get(0).getUrl(), gankItemBeen.get(0).getWho());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage(), true);
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void searchTechData(final boolean isMore) {
        if (!isMore)
            curPage = 1;
        else
            curPage++;
        Subscription rxSubscription = mRetrofitHelper.gankSearchList(queryStr, curTech, NUM_OF_PAGE, curPage)
                .compose(RxUtils.<GankBaseResponse<List<GankSearchItemBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<GankSearchItemBean>>handleGankResult())
                .map(new Func1<List<GankSearchItemBean>, List<GankItemBean>>() {
                    @Override
                    public List<GankItemBean> call(List<GankSearchItemBean> gankSearchItemBeen) {
                        List<GankItemBean> items = new ArrayList<>();
                        for (GankSearchItemBean item : gankSearchItemBeen) {
                            GankItemBean bean = new GankItemBean();
                            bean.set_id(item.getGanhuo_id());
                            bean.setDesc(item.getDesc());
                            bean.setPublishedAt(item.getPublishedAt());
                            bean.setWho(item.getWho());
                            bean.setUrl(item.getUrl());
                            items.add(bean);
                        }
                        return items;
                    }
                })
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
