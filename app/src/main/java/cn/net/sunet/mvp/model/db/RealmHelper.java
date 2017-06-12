package cn.net.sunet.mvp.model.db;

import java.util.List;

import cn.net.sunet.mvp.model.entity.RealmCollectionBean;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerBean;
import cn.net.sunet.mvp.model.entity.RealmReadStateBean;
import io.realm.Realm;
import io.realm.RealmResults;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: realm database
 */

public class RealmHelper {
    private Realm mRealm;

    public RealmHelper() {
        mRealm = Realm.getDefaultInstance();
    }

    /*
     * 增加阅读记录
     * @param id
     */
    public void insertReadState(int id) {
        RealmReadStateBean readState = new RealmReadStateBean();
        readState.setId(id);
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(readState);
        mRealm.commitTransaction();
    }

    /*
     * 查询阅读记录
     * @param id
     */
    public boolean queryReadState(int id) {
        RealmResults<RealmReadStateBean> results = mRealm.where(RealmReadStateBean.class).findAll();
        for (RealmReadStateBean bean : results) {
            if (bean.getId() == id)
                return true;
        }
        return false;
    }

    /*
     * 添加收藏记录
     * @param bean
     */
    public void insertCollection(RealmCollectionBean bean) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }

    /*
    * 删除收藏记录
    * @param id
    */
    public void deleteCollection(String id) {
        RealmCollectionBean bean = mRealm.where(RealmCollectionBean.class).equalTo("id", id)
                .findFirst();
        mRealm.beginTransaction();
        if (bean != null)
            bean.deleteFromRealm();
        mRealm.commitTransaction();
    }

    /*
     * 查询收藏记录
     * @param id
     */
    public boolean queryCollection(String id) {
        RealmResults<RealmCollectionBean> results = mRealm.where(RealmCollectionBean.class).findAll();
        for (RealmCollectionBean bean : results) {
            if (bean.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /*
     * 获取所有收藏记录
     */
    public List<RealmCollectionBean> getAllCollections() {
        RealmResults<RealmCollectionBean> results = mRealm.where(RealmCollectionBean.class).findAllSorted("time");
        return mRealm.copyFromRealm(results);
    }

    /*
     * 修改收藏记录，以时间戳重新排序
     * @param id
     * @param time
     * @param isPlus
     */
    public void updateCollection(String id, long time, boolean isPlus) {
        RealmCollectionBean bean = mRealm.where(RealmCollectionBean.class).equalTo("id", id)
                .findFirst();
        mRealm.beginTransaction();
        if (isPlus)
            bean.setTime(++time);
        else
            bean.setTime(--time);
        mRealm.commitTransaction();
    }

    /*
     * 更新掘金首页管理列表
     * @param bean
     */
    public void updateGoldManagerList(RealmGoldManagerBean bean) {
        RealmGoldManagerBean data = mRealm.where(RealmGoldManagerBean.class).findFirst();
        mRealm.beginTransaction();
        if (data != null)
            data.deleteFromRealm();
        mRealm.copyToRealm(bean);
        mRealm.commitTransaction();
    }

    /*
     * 获取掘金管理首页列表
     */
    public RealmGoldManagerBean getRealGoldManagerList() {
        RealmGoldManagerBean bean = mRealm.where(RealmGoldManagerBean.class).findFirst();
        return bean == null ? null : mRealm.copyFromRealm(bean);
    }
}
