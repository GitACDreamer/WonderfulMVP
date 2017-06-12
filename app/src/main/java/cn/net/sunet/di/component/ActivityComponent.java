package cn.net.sunet.di.component;

import android.app.Activity;

import cn.net.sunet.ui.main.activity.MainActivity;
import cn.net.sunet.di.ActivityScope;
import cn.net.sunet.di.module.ActivityModule;
import cn.net.sunet.ui.main.activity.WelcomeActivity;
import cn.net.sunet.ui.zhihu.activity.DailyDetailActivity;
import cn.net.sunet.ui.zhihu.activity.SectionDetailActivity;
import cn.net.sunet.ui.zhihu.activity.ThemeDetailActivity;
import dagger.Component;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
 * Modify: 2016/12/22
 * Description: dagger2 Activity scope
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(WelcomeActivity welcomeActivity);

    void inject(DailyDetailActivity dailyDetailActivity);

    void inject(ThemeDetailActivity themeDetailActivity);

    void inject(SectionDetailActivity sectionDetailActivity) ;
}
