package cn.net.sunet.di.module;

import android.app.Activity;

import cn.net.sunet.di.ActivityScope;
import dagger.Module;
import dagger.Provides;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
 * Modify: 2016/12/22
 * Description: Activity Module layer
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return mActivity;
    }
}
