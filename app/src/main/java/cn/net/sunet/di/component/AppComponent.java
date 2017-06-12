package cn.net.sunet.di.component;

import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Singleton;

import cn.net.sunet.app.App;
import cn.net.sunet.di.ContextLife;
import cn.net.sunet.di.module.AppModule;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.db.RealmHelper;
import dagger.Component;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
 * Modify: 2016/12/22
 * Description: dagger2 app scope
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    App getContext();                 // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();       //用于缓存的数据库

    RxPermissions rxPermission();    //用于权限请求
}
