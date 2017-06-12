package cn.net.sunet.mvp.presenter;

import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import cn.net.sunet.base.WeChatBaseResponse;
import cn.net.sunet.base.RxPresenter;
import cn.net.sunet.mvp.contract.MainContract;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.entity.VersionBean;
import cn.net.sunet.utils.RxPermissionUtils;
import cn.net.sunet.utils.RxUtils;
import rx.Subscription;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: main activity presenter
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract
        .Presenter {
    private RetrofitHelper mRetrofitHelper;
    private RxPermissions mRxPermissions;

    @Inject
    MainPresenter(RetrofitHelper mRetrofitHelper, RxPermissions mRxPermissions) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.mRxPermissions = mRxPermissions;
    }

    public void externalStorage() {
        //请求外部存储权限
        RxPermissionUtils.externalStorage(new RxPermissionUtils.RequestPermission() {
            @Override
            public void onRequestSuccess() {
                //TODO do something ready
            }
        }, mRxPermissions);
    }

    public void readPhone() {
        RxPermissionUtils.phone(new RxPermissionUtils.RequestPermission() {
            @Override
            public void onRequestSuccess() {
                //TODO 请求读取手机状态权限
            }
        }, mRxPermissions);
    }

    @Override
    public void checkVersion(String version) {
//        Subscription rxSubscription = mRetrofitHelper.version()
//                .compose(RxUtils.<WeChatBaseResponse<VersionBean>>rxSchedulerHelper())
//                .compose(RxUtils.<VersionBean>handleWeChatResult())
//                .subscribe(new Action1<VersionBean>() {
//                    @Override
//                    public void call(VersionBean versionBean) {
//                        mView.showUpdateDialog(versionBean);
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        mView.showError(throwable.getMessage(), true);
//                    }
//                });
//        addSubscribe(rxSubscription);

        VersionBean bean = new VersionBean();
        bean.setUri("http://awesome-1251180347.cosgz.myqcloud.com/Wonderful.apk");
        bean.setSize("7.59M");
        bean.setCode("1.0");
        if (Integer.valueOf(version.replace(".", "")) < Integer.valueOf(bean.getCode()
                .replace(".", "")))
            mView.showUpdateDialog(bean);
    }

    @Override
    public void logout() {
        mView.showContent("注销用户");
    }

    @Override
    public void exit() {
        mView.showContent("退出应用程序");
    }
}
