package cn.net.sunet.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.net.sunet.app.Constants;
import cn.net.sunet.base.WeChatBaseResponse;
import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.event.SearchEvent;
import cn.net.sunet.event.TabSelectedEvent;
import cn.net.sunet.mvp.contract.WeChatContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.WeChatBean;
import cn.net.sunet.utils.RxBus;
import cn.net.sunet.utils.RxUtils;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: wechat presenter
 */

public class WeChatPresenter extends RxPresenter<WeChatContract.View> implements WeChatContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    public static final int NUM_OF_PAGE = 20;
    private int curPage = 1;
    private String queryStr = null;
    private int mCurType = Constants.TYPE_WECHAT_NEWS;
    private boolean isSearch = false;

    @Inject
    WeChatPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
        registerEvent();
        registerTabSelectEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(SearchEvent.class)
                .compose(RxUtils.<SearchEvent>rxSchedulerHelper())
                .filter(new Func1<SearchEvent, Boolean>() {
                    @Override
                    public Boolean call(SearchEvent searchEvent) {
                        return searchEvent.getType() == mCurType;
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
                        isSearch = true;
                        wechatData(mCurType, false);
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
                        mView.onTabSelectedEvent(mCurType);
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
    public void wechatData(final int type, final boolean isMore) {
        Observable<WeChatBaseResponse<List<WeChatBean>>> observable = null;
        if (!isMore) {
            if (!isSearch)
                queryStr = null;
            curPage = 1;
        } else
            curPage++;
        mCurType = type;
        if (queryStr == null) {
            switch (type) {
                case Constants.TYPE_WECHAT_NEWS:
                    observable = mRetrofitHelper.wechatHot(NUM_OF_PAGE, curPage);
                    break;
                case Constants.TYPE_WECHAT_FANS:
                    observable = mRetrofitHelper.wechatFantastic(NUM_OF_PAGE, curPage);
                    break;
                case Constants.TYPE_WECHAT_BEAUTY:
                    observable = mRetrofitHelper.wechatBeauty(NUM_OF_PAGE, curPage);
                    break;
                case Constants.TYPE_NEWS_SOCIAL:
                    observable = mRetrofitHelper.wechatSocial(NUM_OF_PAGE, curPage);
                    break;
                case Constants.TYPE_NEWS_DOMESTIC:
                    observable = mRetrofitHelper.wechatDomestic(NUM_OF_PAGE, curPage);
                    break;
                case Constants.TYPE_NEWS_INTERNATIONAL:
                    observable = mRetrofitHelper.wechatInternational(NUM_OF_PAGE, curPage);
                    break;
                case Constants.TYPE_NEWS_FUNNY:
                    observable = mRetrofitHelper.wechatFunny(NUM_OF_PAGE, curPage);
                    break;
            }
        } else {
            isSearch = false;
            switch (type) {
                case Constants.TYPE_WECHAT_NEWS:
                    observable = mRetrofitHelper.wechatHotSearch(NUM_OF_PAGE, curPage, queryStr);
                    break;
                case Constants.TYPE_WECHAT_FANS:
                    observable = mRetrofitHelper.wechatFantasticSearch(NUM_OF_PAGE, curPage, queryStr);
                    break;
                case Constants.TYPE_WECHAT_BEAUTY:
                    observable = mRetrofitHelper.wechatBeautySearch(NUM_OF_PAGE, curPage, queryStr);
                    break;
                case Constants.TYPE_NEWS_SOCIAL:
                    observable = mRetrofitHelper.wechatSocialSearch(NUM_OF_PAGE, curPage, queryStr);
                    break;
                case Constants.TYPE_NEWS_DOMESTIC:
                    observable = mRetrofitHelper.wechatDomesticSearch(NUM_OF_PAGE, curPage, queryStr);
                    break;
                case Constants.TYPE_NEWS_INTERNATIONAL:
                    observable = mRetrofitHelper.wechatInternationalSearch(NUM_OF_PAGE, curPage, queryStr);
                    break;
                case Constants.TYPE_NEWS_FUNNY:
                    observable = mRetrofitHelper.wechatFunnySearch(NUM_OF_PAGE, curPage, queryStr);
                    break;
            }
        }
        Subscription rxSubscription = observable.compose(RxUtils
                .<WeChatBaseResponse<List<WeChatBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<WeChatBean>>handleWeChatResult())
                .map(new Func1<List<WeChatBean>, List<WeChatBean>>() {
                    @Override
                    public List<WeChatBean> call(List<WeChatBean> beans) {
                        for (WeChatBean bean : beans)
                            bean.setType(type);
                        return beans;
                    }
                })
                .subscribe(new Action1<List<WeChatBean>>() {
                    @Override
                    public void call(List<WeChatBean> beans) {
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
