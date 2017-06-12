package cn.net.sunet.mvp.contract;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.DailyDetailBean;
import cn.net.sunet.mvp.model.entity.DailyStoryExtraBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: daily detail contract
 */

public class DailyDetailContract {
    public interface View extends BaseView {
        void showContent(DailyDetailBean bean);

        void showExtraInfo(DailyStoryExtraBean bean);

        void setCollectionButtonState(boolean isCollected);
    }

    public interface Presenter extends BasePresenter<View> {
        void dailyDetail(int id);

        void dailyDetailExtra(int id);

        void insertCollection();

        void deleteCollection();

        void queryCollection(int id);
    }
}
