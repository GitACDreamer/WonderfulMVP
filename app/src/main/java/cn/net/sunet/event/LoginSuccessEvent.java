package cn.net.sunet.event;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: 登录成功的传递的参数
 */

import cn.net.sunet.mvp.model.entity.LoginBean;

public class LoginSuccessEvent {
    LoginBean bean;

    LoginSuccessEvent(LoginBean bean) {
        this.bean = bean;
    }
}
