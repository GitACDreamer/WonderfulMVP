package cn.net.sunet.mvp.contract;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.DailyThemeBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: 日报主题contract
 */

public class ThemeContract {
    public interface View extends BaseView {
        void showContent(DailyThemeBean bean);
    }

    public interface Presenter extends BasePresenter<View> {
        void dailyTheme();
    }
}
