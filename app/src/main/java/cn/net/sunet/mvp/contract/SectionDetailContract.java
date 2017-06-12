package cn.net.sunet.mvp.contract;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.DailySectionDetailBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: section detail contract
 */

public class SectionDetailContract {
    public interface View extends BaseView {
        void showContent(DailySectionDetailBean bean);
    }

    public interface Presenter extends BasePresenter<View> {
        void dailySectionDetail(int id);

        void insertReadToDB(int id);
    }
}
