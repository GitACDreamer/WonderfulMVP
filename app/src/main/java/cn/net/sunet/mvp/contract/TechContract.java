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
 * Description: tech contract
 */

public interface TechContract {
    interface View extends BaseView {
        void showContent(List<GankItemBean> beans , boolean isMore);

        void showGirlContent(String url, String copyright);

        void onTabSelectedEvent() ;
    }

    interface Presenter extends BasePresenter<View> {
        void gankData(String tech , int type);

        void gankMoreData(String tech);

        void girlImage();
    }
}
