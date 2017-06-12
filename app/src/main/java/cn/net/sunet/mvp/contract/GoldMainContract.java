package cn.net.sunet.mvp.contract;

import java.util.List;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerBean;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerItemBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: gold main contract
 */

public interface GoldMainContract {
    interface View extends BaseView {
        void updateTab(List<RealmGoldManagerItemBean> items);

        void jumpToManager(RealmGoldManagerBean bean);
    }

    interface Presenter extends BasePresenter<View> {
        void initManagerList();

        void setManagerList();
    }
}
