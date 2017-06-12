package cn.net.sunet.mvp.contract;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.DailyNewsBeforeBean;
import cn.net.sunet.mvp.model.entity.DailyNewsLatestBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: daily fragment contract
 */

public class DailyContract {
    //view
    public interface View extends BaseView {
        //最新日报信息
        void showContent(DailyNewsLatestBean bean);

        //往期日报信息
        void showMoreContent(String date, DailyNewsBeforeBean beforeBean);

        void doInterval(int currentCount);
    }

    //presenter
    public interface Presenter extends BasePresenter<DailyContract.View> {
        //获取最新日报信息
        void dailyNewsLatest();

        //获取往期日报信息
        void dailyNewsBefore(String date);

        void startInterval();

        void stopInterval();

        void insertReadToDB(int id);
    }
}
