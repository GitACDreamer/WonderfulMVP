package cn.net.sunet.base;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: BaseView 接口
 */

public interface BaseView {

    //显示错误信息
    void showError(final String msg, final boolean isShow);

    //显示加载
    void showLoading();

    //隐藏加载
    void dismissLoading();
}
