package cn.net.sunet.mvp.presenter;

import com.blankj.utilcode.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import cn.net.sunet.base.GoldBaseResponse;
import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.GoldContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.GoldItemBean;
import cn.net.sunet.utils.RxUtils;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: gold presenter
 */

public class GoldPresenter extends RxPresenter<GoldContract.View> implements GoldContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    private static final int NUM_EACH_PAGE = 20;
    private static final int NUM_HOT_LIMIT = 3;
    private List<GoldItemBean> mData;
    private boolean mIsHot = true;
    private int mCurPage = 0;
    private String mType;

    @Inject
    GoldPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
        mData = new ArrayList<>();
    }

    @Override
    public void goldData(String type) {
        mType = type;
        mCurPage = 0;
        mData.clear();
        Observable<List<GoldItemBean>> list = mRetrofitHelper.goldData(type, NUM_EACH_PAGE, mCurPage++)
                .compose(RxUtils.<GoldBaseResponse<List<GoldItemBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<GoldItemBean>>handleGoldResult());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -3);
        Observable<List<GoldItemBean>> hotList = mRetrofitHelper.goldHotData(type, TimeUtils.millis2String(c
                .getTimeInMillis(), "yyyy-MM-dd"), NUM_HOT_LIMIT)
                .compose(RxUtils.<GoldBaseResponse<List<GoldItemBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<GoldItemBean>>handleGoldResult());
        Subscription subscription = Observable.concat(hotList, list).subscribe(new Action1<List<GoldItemBean>>() {
            @Override
            public void call(List<GoldItemBean> beans) {
                mData.addAll(beans);
                if (!mIsHot)
                    mView.showContent(beans, false, 0, 0);
                mIsHot = !mIsHot;
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.showError(throwable.getMessage(), true);
            }
        });
        addSubscribe(subscription);
    }

    @Override
    public void goldMoreData() {
        Subscription subscription = mRetrofitHelper.goldData(mType, NUM_EACH_PAGE, mCurPage++)
                .compose(RxUtils.<GoldBaseResponse<List<GoldItemBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<GoldItemBean>>handleGoldResult())
                .subscribe(new Action1<List<GoldItemBean>>() {
                    @Override
                    public void call(List<GoldItemBean> beans) {
                        mData.addAll(beans);
                        mView.showContent(beans, true, mData.size(), mData.size() + NUM_EACH_PAGE);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage(), true);
                    }
                });
        addSubscribe(subscription);
    }
}
