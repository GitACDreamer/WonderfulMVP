package cn.net.sunet.mvp.model.entity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: 掘金首页item管理
 */

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class RealmGoldManagerItemBean extends RealmObject implements Parcelable {
    private int mIndex = -1;
    private boolean mIsSelect;

    public RealmGoldManagerItemBean() {
        this(-1, false);
    }

    public RealmGoldManagerItemBean(int index, boolean isSelect) {
        this.mIndex = index;
        this.mIsSelect = isSelect;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public boolean isSelect() {
        return mIsSelect;
    }

    public void setSelect(boolean mIsSelect) {
        this.mIsSelect = mIsSelect;
    }

    protected RealmGoldManagerItemBean(Parcel in) {
        mIndex = in.readInt();
        mIsSelect = (in.readByte() != 0);
    }

    public static final Creator<RealmGoldManagerItemBean> CREATOR = new Creator<RealmGoldManagerItemBean>() {
        @Override
        public RealmGoldManagerItemBean createFromParcel(Parcel in) {
            return new RealmGoldManagerItemBean(in);
        }

        @Override
        public RealmGoldManagerItemBean[] newArray(int size) {
            return new RealmGoldManagerItemBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mIndex);
        dest.writeByte(mIsSelect ? (byte) 1 : (byte) 0);
    }
}
