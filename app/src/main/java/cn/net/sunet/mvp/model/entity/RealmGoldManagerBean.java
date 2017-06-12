package cn.net.sunet.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/27
   Modify: 2016/12/27
 * Description: 掘金首页管理
 */

public class RealmGoldManagerBean extends RealmObject implements Parcelable {
    private RealmList<RealmGoldManagerItemBean> mManagerList;


    public RealmGoldManagerBean() {

    }

    public RealmGoldManagerBean(RealmList<RealmGoldManagerItemBean> managerList) {
        mManagerList = managerList;
    }

    public RealmList<RealmGoldManagerItemBean> getManagerList() {
        return mManagerList;
    }

    public void setManagerList(RealmList<RealmGoldManagerItemBean> managerList) {
        mManagerList = managerList;
    }

    protected RealmGoldManagerBean(Parcel in) {
        mManagerList = new RealmList<>();
        in.readList(mManagerList, RealmGoldManagerItemBean.class.getClassLoader());
    }

    public static final Creator<RealmGoldManagerBean> CREATOR = new Creator<RealmGoldManagerBean>() {
        @Override
        public RealmGoldManagerBean createFromParcel(Parcel in) {
            return new RealmGoldManagerBean(in);
        }

        @Override
        public RealmGoldManagerBean[] newArray(int size) {
            return new RealmGoldManagerBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mManagerList);
    }
}
