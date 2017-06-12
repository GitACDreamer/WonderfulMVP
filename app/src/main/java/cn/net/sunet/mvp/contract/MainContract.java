package cn.net.sunet.mvp.contract;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.VersionBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: main activity contract
 */

public class MainContract {
    //view
    public interface View extends BaseView {
        void showContent(final String msg);

        void showUpdateDialog(VersionBean version);
    }

    //presenter
    public interface Presenter extends BasePresenter<MainContract.View> {

        //检测版本更新
        void checkVersion(String version);

        //切换用户
        void logout();

        //退出应用程序
        void exit();
    }
}
