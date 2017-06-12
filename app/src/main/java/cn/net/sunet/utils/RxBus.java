package cn.net.sunet.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: RxUtils event bus
 */

public class RxBus {
    // 主题 PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private final Subject<Object, Object> mRxBusObserverable = new SerializedSubject<>(PublishSubject.create());

    private static volatile RxBus mRxBus = null;

    public static synchronized RxBus getDefault() {
        if (mRxBus == null)
            synchronized (RxBus.class) {
                mRxBus = new RxBus();
            }
        return mRxBus;
    }

    // 提供了一个新的事件
    public void post(Object o) {
        mRxBusObserverable.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mRxBusObserverable.ofType(eventType);
    }
}
