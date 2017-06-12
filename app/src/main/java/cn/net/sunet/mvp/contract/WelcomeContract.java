package cn.net.sunet.mvp.contract;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.WelcomeBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: welcome activity contract
 */

public class WelcomeContract {
    //view
    public interface View extends BaseView {
        //显示访问成功的信息
        void showContent(WelcomeBean bean);

        //跳转到login界面
        void jumpToLogin();
    }

    //presenter
    public interface Presenter extends BasePresenter<WelcomeContract.View> {
        //获取欢迎界面图片
        void welcome();
    }
}
