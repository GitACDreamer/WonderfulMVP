package cn.net.sunet.mvp.model.entity;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/27
   Modify: 2016/12/27
 * Description: 收藏记录bean(包括 新闻、技术、福利)
 */

public class RealmCollectionBean extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    private String image;
    private String title;
    private String url;
    private int type ;
    private long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String uri) {
        this.url = uri;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
