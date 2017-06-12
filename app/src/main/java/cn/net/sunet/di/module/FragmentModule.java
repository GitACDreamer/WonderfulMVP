package cn.net.sunet.di.module;

import android.app.Activity;

import cn.net.sunet.di.FragmentScope;
import dagger.Module;
import dagger.Provides;
import me.yokeyword.fragmentation.SupportFragment;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
 * Modify: 2016/12/22
 * Description: fragment Module layer
 */

@Module
public class FragmentModule {

    private SupportFragment fragment;

    public FragmentModule(SupportFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity() {
        return fragment.getActivity();
    }
}
