package cn.net.sunet.di.component;

import android.app.Activity;

import cn.net.sunet.di.FragmentScope;
import cn.net.sunet.di.module.FragmentModule;
import cn.net.sunet.ui.gank.fragment.GankMainFragment;
import cn.net.sunet.ui.gank.fragment.GirlFragment;
import cn.net.sunet.ui.gank.fragment.TechnologyFragment;
import cn.net.sunet.ui.gold.fragment.GoldMainFragment;
import cn.net.sunet.ui.gold.fragment.GoldPagerFragment;
import cn.net.sunet.ui.main.fragment.CollectionFragment;
import cn.net.sunet.ui.main.fragment.MainFragment;
import cn.net.sunet.ui.main.fragment.WeChatNewFragment;
import cn.net.sunet.ui.wechat.fragment.BeautyFragment;
import cn.net.sunet.ui.wechat.fragment.WeChatFragment;
import cn.net.sunet.ui.zhihu.fragment.CommentFragment;
import cn.net.sunet.ui.zhihu.fragment.DailyFragment;
import cn.net.sunet.ui.zhihu.fragment.HotFragment;
import cn.net.sunet.ui.zhihu.fragment.SectionFragment;
import cn.net.sunet.ui.zhihu.fragment.ThemeFragment;
import dagger.Component;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
 * Modify: 2016/12/22
 * Description: dagger2 fragment scope
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(MainFragment mainFragment);

    void inject(GankMainFragment gankMainFragment);

    void inject(DailyFragment dailyFragment);

    void inject(ThemeFragment themeFragment);

    void inject(SectionFragment sectionFragment);

    void inject(HotFragment hotFragment);

    void inject(CommentFragment commentFragment);

    void inject(CollectionFragment collectionFragment);

    void inject(WeChatFragment weChatFragment);

    void inject(BeautyFragment beautyFragment);

    void inject(TechnologyFragment technologyFragment);

    void inject(GirlFragment girlFragment);

    void inject(GoldMainFragment goldMainFragment);

    void inject(GoldPagerFragment goldPagerFragment);

    void inject(WeChatNewFragment weChatNewFragment);
}
