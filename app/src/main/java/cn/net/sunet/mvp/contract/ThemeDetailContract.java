package cn.net.sunet.mvp.contract;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.DailyThemeDetailBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: theme detail contract
 */

public class ThemeDetailContract {
    public interface View extends BaseView {
        void showContent(DailyThemeDetailBean bean);
    }

    public interface Presenter extends BasePresenter<View> {
        void themeDetail(int id);

        void insertReadToDB(int id);
    }
}
