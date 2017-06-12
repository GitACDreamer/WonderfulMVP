package cn.net.sunet.utils;

import com.blankj.utilcode.utils.LogUtils;
import com.google.gson.JsonObject;

import cn.net.sunet.app.App;
import cn.net.sunet.base.GankBaseResponse;
import cn.net.sunet.base.GoldBaseResponse;
import cn.net.sunet.base.WeChatBaseResponse;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: RxUtils Rx工具类
 */

public class RxUtils {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * Response to Java bean
     *
     * @param <T> WeChatBaseResponse
     * @return java bean
     */
    public static <T> Observable.Transformer<WeChatBaseResponse<T>, T> handleWeChatResult() {   //compose判断结果
        return new Observable.Transformer<WeChatBaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<WeChatBaseResponse<T>> observable) {
                return observable.flatMap(new Func1<WeChatBaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(WeChatBaseResponse<T> response) {
                        if (response.getCode() >= 0) {
                            return createData(response.getData());
                        } else { //返回服务器的错误代码
                            final JsonObject obj = new JsonObject();
                            obj.addProperty("code", response.getCode());
                            obj.addProperty("msg", response.getMessage());
                            LogUtils.e(App.TAG, obj.toString());
                            return Observable.error(new Exception(obj.toString()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     * Response to Java bean
     *
     * @param <T> GankBaseResponse
     * @return java bean
     */
    public static <T> Observable.Transformer<GankBaseResponse<T>, T> handleGankResult() {   //compose判断结果
        return new Observable.Transformer<GankBaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<GankBaseResponse<T>> observable) {
                return observable.flatMap(new Func1<GankBaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(GankBaseResponse<T> response) {
                        if (!response.getError()) {
                            return createData(response.getResults());
                        } else  //返回服务器的错误代码
                            return Observable.error(new Exception("服务器返回错误"));
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     * Response to Java bean
     *
     * @param <T> GoldBaseResponse
     * @return java bean
     */
    public static <T> Observable.Transformer<GoldBaseResponse<T>, T> handleGoldResult() {   //compose判断结果
        return new Observable.Transformer<GoldBaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<GoldBaseResponse<T>> observable) {
                return observable.flatMap(new Func1<GoldBaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(GoldBaseResponse<T> response) {
                        if (response.getResults() != null) {
                            return createData(response.getResults());
                        } else  //返回服务器的错误代码
                            return Observable.error(new Exception("服务器返回错误"));
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
