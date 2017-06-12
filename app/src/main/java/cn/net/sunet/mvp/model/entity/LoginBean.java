package cn.net.sunet.mvp.model.entity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: login bean
 */

public class LoginBean {

    /**
     * user_name : Leland
     * img : http://www.bestsource.net.cn/bestsource/account.jpg
     * signature : 业精于勤而荒于嬉，行成于思而毁于随。
     */

    private String user_name;
    private String img;
    private String signature;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
