package cn.net.sunet.di.module;

import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Singleton;

import cn.net.sunet.app.App;
import cn.net.sunet.di.ContextLife;
import cn.net.sunet.mvp.model.api.RetrofitHelper;
import cn.net.sunet.mvp.model.db.RealmHelper;
import dagger.Module;
import dagger.Provides;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
 * Modify: 2016/12/22
 * Description: Application Module layer
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ContextLife()
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }

    @Provides
    @Singleton
    RxPermissions provideRxPermissions() {
        return RxPermissions.getInstance(application) ;
    }
}
