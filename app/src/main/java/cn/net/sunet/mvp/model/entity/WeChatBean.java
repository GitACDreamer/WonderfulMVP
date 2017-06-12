package cn.net.sunet.mvp.model.entity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/11
   Modify: 2017/1/11
 * Description: this is a class description.
 */

public class WeChatBean {

    /**
     * ctime : 2017-01-11
     * title : 罗玉凤：求祝福，求鼓励
     * description : 我就是凤姐
     * picUrl : http://mmbiz.qpic
     * .cn/mmbiz_jpg/LXUzxjZKhjpacnDo8Hspsq77Fgbf4h4Ceww6zicaQLlNY9Oh49LhKO0I6ybvyTLWaeOPb0ozyACZxqQ6C6Rh8eA/0?wx_fmt
     * =jpeg
     * url : http://mp.weixin.qq.com/s/gNZV-TSdVM96O4Mdln5KHQ
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;
    private int type;
    private int height;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
