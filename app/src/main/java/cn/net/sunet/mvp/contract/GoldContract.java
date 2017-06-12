package cn.net.sunet.mvp.contract;

import java.util.List;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.GoldItemBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: gold contract
 */

public interface GoldContract {
    interface View extends BaseView {
        void showContent(List<GoldItemBean> bean, boolean isMore, int start, int end);
    }

    interface Presenter extends BasePresenter<View> {
        void goldData(String type);

        void goldMoreData();
    }
}
