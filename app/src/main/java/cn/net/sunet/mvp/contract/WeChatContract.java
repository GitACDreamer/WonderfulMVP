package cn.net.sunet.mvp.contract;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: wechat contract
 */

import java.util.List;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.WeChatBean;

public interface WeChatContract {
    interface View extends BaseView {
        void showContent(List<WeChatBean> beans, boolean isMore);

        void onTabSelectedEvent(int type);
    }

    interface Presenter extends BasePresenter<View> {
         void wechatData(final int type, final boolean isMore);
    }
}
