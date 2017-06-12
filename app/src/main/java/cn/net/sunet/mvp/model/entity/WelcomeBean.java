package cn.net.sunet.mvp.model.entity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: welcome bean
 */

public class WelcomeBean {

    /**
     * text : Karl Fredrickson
     * img : https://pic4.zhimg.com/v2-d447d4c4a54119be40fb2c627d648157_xxdpi.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
