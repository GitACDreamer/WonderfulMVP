package cn.net.sunet.mvp.model.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/27
   Modify: 2016/12/27
 * Description: 阅读状态bean
 */

public class RealmReadStateBean extends RealmObject {
    @PrimaryKey
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
