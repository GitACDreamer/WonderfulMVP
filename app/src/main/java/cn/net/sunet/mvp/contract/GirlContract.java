package cn.net.sunet.mvp.contract;

import java.util.List;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.GankItemBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: girl contract
 */

public interface GirlContract {
    interface View extends BaseView {
        void showContent(List<GankItemBean> beans, boolean isMore);

        void onTabSelectedEvent() ;
    }

    interface Presenter extends BasePresenter<View> {
        void girlData(boolean isMore);
    }
}
