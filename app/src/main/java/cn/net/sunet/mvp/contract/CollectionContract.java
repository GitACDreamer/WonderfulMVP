package cn.net.sunet.mvp.contract;

import java.util.List;

import cn.net.sunet.base.BasePresenter;
import cn.net.sunet.base.BaseView;
import cn.net.sunet.mvp.model.entity.RealmCollectionBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: collection contract
 */

public class CollectionContract {
    public interface View extends BaseView {
        void showContent(List<RealmCollectionBean> beans);
    }

    public interface Presenter extends BasePresenter<View> {
        //获取收藏内容
        void collection();

        //删除收藏
        void deleteCollection(String id);

        //更改收藏时间
        void updateCollection(String id, long time, boolean isPlus);
    }
}
