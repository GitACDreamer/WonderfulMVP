package cn.net.sunet.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
 * Modify: 2016/12/22
 * Description: Activity 的生命周期
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
