package cn.net.sunet.mvp.model.entity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: gank item
 */

public class GankItemBean {

    /**
     * _id : 587c2ad5421aa9119735ac2c
     * createdAt : 2017-01-16T10:07:17.691Z
     * desc : 其实Android platform的一些API有些鲜为人知，但非常有用的方法和类，去研究一下这些API是非常有意思的
     * publishedAt : 2017-01-16T14:12:18.71Z
     * source : web
     * type : Android
     * url : http://mp.weixin.qq.com/s?__biz=MzA3OTk4ODkwNA==&mid=2449245595&idx=1&sn
     * =c1617d32014bd232591137fd75b3a58b&chksm
     * =8ba3345cbcd4bd4ad1caa6f09821957d802f74c3823fae3296a1aaba000f534cfead40a26e33#rd
     * used : true
     * who : 梧桐
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
